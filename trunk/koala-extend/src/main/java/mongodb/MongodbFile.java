package mongodb;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * User: YaFengLi
 * Date: 2010-8-30
 * Time: 18:24:55
 */
public class MongodbFile {
    static DB imgDB = null;
    static GridFS gridFS = null;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Mongo mongo = new Mongo("127.0.0.1", 20000);   //���ݿ��ַ���˿ں�
        imgDB = mongo.getDB("imgs");  // ���ݿ�����        
        gridFS = new GridFS(imgDB);
           

        MongodbFile mon = new MongodbFile();
        File fileIN = new File("f:/tmp/1.pcap");// ��ȡ428 M ���ļ�
        File fileOUT = new File("f:/tmp/1.pcap.out"); // д���ļ�����

        /**
         * ���ļ����� MongoDB ���ݿ���
         */
        mon.saveFile(fileIN, fileIN.getName());
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        /**
         * ��MongoDB�ж�ȡ���ݣ�����д�����
         */
        List<?> list = mon.findFilesByName(fileIN.getName());
        GridFSDBFile gridFSDBFile = (GridFSDBFile) list.get(0);
        gridFSDBFile.writeTo(fileOUT);
        System.out.println(list.size());
    }

    /**
     * д���ļ�
     *
     * @param fileName
     */
    public void saveFile(File file, String fileName) {
        try {
            GridFSFile mongofile = gridFS.createFile(file);
            mongofile.put("filename", fileName);
            mongofile.put("uploadDate", new Date());
            mongofile.put("contentType", fileName.substring(fileName.lastIndexOf(".")));
            mongofile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡ�ļ�
     *
     * @param fileName
     */
    public List<GridFSDBFile> findFilesByName(String fileName) {
        List<GridFSDBFile> list = gridFS.find(fileName);
        return list;
    }
}

