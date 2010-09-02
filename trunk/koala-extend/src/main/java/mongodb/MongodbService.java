package mongodb;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: phoenixup
 * Date: 2010-8-31
 * Time: 14:15:18
 * To change this template use File | Settings | File Templates.
 */
public interface MongodbService {


    public abstract DBCollection getCollection();

    /**
     * ��Mapת����DBObject
     *
     * @param map
     * @return
     */
    public abstract DBObject map2Obj(Map<String, Object> map);

    /**
     * �������ݼ��ϵ�Map,��������
     * map��key��Ӧ���ݿ��е�DBCollection��keyֵ
     *
     * @param map
     */
    public abstract void insert(Map<String, Object> map);

    /**
     * ����List<Map<String,Object>>�ṹ�����ݼ���,��������
     *
     * @param list
     */
    public abstract void insertBatch(List<Map<String, Object>> list);

    /**
     * ����������������map,ɾ������
     *
     * @param map
     */
    public abstract void delete(Map<String, Object> map);

    /**
     * ���ն��������Ĳ���,����ɾ������
     *
     * @param list
     */
    public abstract void deleteBatch(List<Map<String, Object>> list);

    /**
     * �õ�Collection()�ܵļ�¼��
     *
     * @return
     */
    public abstract long getCollectionCount();

    public abstract long getCount(Map<String, Object> map);


    @SuppressWarnings("unchecked")
    public abstract List<Map<String, Object>> find(Map<String, Object> map);

    /**
     * ����whereFields����,����setFieldsֵ
     *
     * @param setFields
     * @param whereFields
     */
    public abstract void update(Map<String, Object> setFields,
                                Map<String, Object> whereFields);

    @SuppressWarnings("unchecked")
    public abstract List<Map<String, Object>> findAll();

    /**
     * ����ID�ҵ�Ψһ����
     * ��1��id�ֶα��
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public abstract Map<String, Object> getById(String id);

    /**
     * ���ļ������浽���ݿ�
     *
     * @param in
     * @param filename
     */
    public abstract void saveFile(InputStream in, String filename);

    /**
     * ���ļ����浽���ݿ�
     *
     * @param file
     * @param filename
     */
    public abstract void saveFile(File file, String filename);

    public abstract void saveFile(byte[] bytes, String filename);

    /**
     * �����ļ����ƣ������ݿ��в����ļ�
     *
     * @param fileName
     * @return
     */
    public abstract List<GridFSDBFile> findFilesByName(String fileName);

    public abstract GridFSDBFile findFileByName(String filename);

    /**
     * ������ݿ����ļ���inputstream��
     *
     * @param filename
     * @return
     */
    public abstract InputStream getFileInputStream(String filename);

    /**
     * �����ļ�������ȡ���ݿ��е�һ������
     *
     * @param map
     * @return
     */
    public abstract GridFSDBFile findFirstFile(Map<String, Object> map);

    /**
     * ��ȡ�������ݿ�����
     *
     * @return
     */
    public List<String> getAllDBNames();

    /**
     * ��ȡ�ļ��ܼ�¼��
     *
     * @return
     */
    public long getFileCount();

    /**
     * ��ȡ�����ļ��б�
     *
     * @return
     */
    public List<GridFSDBFile> getAllFiles();

    /**
     * �����ļ���ɾ���ļ�
     *
     * @param filename
     */
    public void removeFile(String filename);

    /**
     * ����dbobjectɾ���ļ�
     *
     * @param query
     */
    public void removeFile(BasicDBObject query);

    /**
     * ����idɾ���ļ�
     *
     * @param id
     */
    public void removeFile(ObjectId id);

    /**
     * ɾ�������ļ�
     */
    public void removeAllFile();

    public abstract GridFS getGridFS();

    public abstract String getDbName();

    public abstract void setDbName(String dbName);

    public abstract String getCollName();

    public abstract void setCollName(String collName);

    public abstract DB getDb();

    public abstract void setDb(DB db);

    public Mongo getMongo();

    public void setMongo(Mongo mongo);

}


