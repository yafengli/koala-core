package org.helloworld;

import com.mongodb.*;
import com.mongodb.gridfs.GridFSDBFile;
import mongodb.MongodbService;
import mongodb.MongodbServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: phoenixup
 * Date: 2010-8-31
 * Time: 16:03:39
 * To change this template use File | Settings | File Templates.
 */
public class MongodbTest {
    private Mongo mongo = null;
    private MongodbServiceFactory factory = null;
    private MongodbService service = null;

    @Before
    public void init() {
        try {
            /*��������mongos����ʹ�÷�Ƭ���ܣ��������mongod�����ķ����ϴ洢*/
            mongo = new Mongo("58.223.0.180", 27017);
            factory = new MongodbServiceFactory(mongo);
            service = factory.factory("test", "people");
            initCreateShards();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initCreateShards() {
        DB config = mongo.getDB("admin");
        BasicDBObject cmd = new BasicDBObject();
        cmd.put("enableSharding", "test_s7");
        CommandResult rt = config.command(cmd);
        System.out.println("#msg" + rt.getErrorMessage());
        cmd.clear();
        cmd.put("shardCollection", "test_s7.people_s1");
        BasicDBObject name = new BasicDBObject();
        name.put("name", 1);
        cmd.put("key", name);
        rt = config.command(cmd);
        System.out.println("#msg" + rt.getErrorMessage());
    }

//    @Test

    public void testDB() {
        try {
            List<Map<String, Object>> list = service.findAll();
            System.out.println("#" + list.size() + "#");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test

    public void testMongod() throws Exception {

        DB db = mongo.getDB("test_s7");
        DBCollection dbcoll = db.getCollection("people_s1");
        BasicDBObject val = new BasicDBObject();
        val.put("_id", System.currentTimeMillis());
        val.put("name", "���");
        dbcoll.save(val);

        DBCursor cur = dbcoll.find();
        System.out.println("@@count=" + dbcoll.count() + "," + cur.count());
        for (DBObject obj = null; cur.hasNext();) {
            obj = cur.next();
            System.out.println("@@" + obj);
        }
    }

    @Test

    public void testFile() {
        FileChannel fc = null;
        InputStream in = null;
        try {
            String filename = "460030947772780_zj_917430821.pcap";
            FileOutputStream fos = new FileOutputStream("f:/tmp/hhh.pcap.out");
            fc = fos.getChannel();
            service.saveFile(new File("f:/tmp/460030947772780_zj_917430821.pcap"), "460030947772780_zj_917430821.pcap");
//            GridFSDBFile dbfile = service.findFileByName(filename);
//            service.removeFile(filename);
            in = service.getFileInputStream(filename);
            ReadableByteChannel channel = Channels.newChannel(in);

            System.out.printf("#count %d,[%s]", service.getFileCount(), fc.transferFrom(channel, 0, 24));
            fos.close();
            fc.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
