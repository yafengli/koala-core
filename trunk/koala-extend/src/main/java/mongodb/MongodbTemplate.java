package mongodb;

import com.mongodb.DB;

/**
 * Created by IntelliJ IDEA.
 * User: phoenixup
 * Date: 2010-9-1
 * Time: 17:40:26
 * To change this template use File | Settings | File Templates.
 */
public interface MongodbTemplate {
    public DB getDB(String name);
}
