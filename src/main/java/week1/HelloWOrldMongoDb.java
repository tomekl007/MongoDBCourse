package week1;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 8/10/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWOrldMongoDb {
    public static void main(String[]  args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));//default connect to localhost

        DB database = client.getDB("test");
        DBCollection collection = database.getCollection("things");

        DBObject document =  collection.findOne();
        System.out.println(document);
    }
}
