package mongodb;

import com.mongodb.Mongo;

/**
 * User: YaFengLi
 * Date: 2010-8-31
 * Time: 14:28:52
 */
public class MongodbServiceFactory {
    public MongodbServiceFactory(Mongo mongo) {
        this.setMongo(mongo);
    }

    public MongodbServiceFactory() {
    }

    private Mongo mongo;

    public Mongo getMongo() {
        return mongo;
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }

    public MongodbService factory(String dbName, String collName) {
        MongodbService service = new MongodbServiceImpl(dbName, collName);
        service.setMongo(this.getMongo());
        return service;
    }

    public MongodbService factory(String dbName) {
        MongodbService service = new MongodbServiceImpl(dbName);
        service.setMongo(this.getMongo());
        return service;
    }
}
