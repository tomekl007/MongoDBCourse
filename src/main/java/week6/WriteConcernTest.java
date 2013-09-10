package week6;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 9/5/13
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class WriteConcernTest {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        MongoClient client = new MongoClient(Arrays.asList(
                new ServerAddress("localhost", 27018),
                new ServerAddress("localhost", 27017),
                new ServerAddress("localhost", 27019))
        );//default connect to localhost
        client.setReadPreference(ReadPreference.primary());

        DBCollection test = client.getDB("course").getCollection("write.test");
        test.setReadPreference(ReadPreference.nearest());
        test.setWriteConcern(WriteConcern.UNACKNOWLEDGED);       //j=1 wait to acknowledge of write
        test.drop();


        DBObject doc = new BasicDBObject("_id", 1);
        test.insert(doc);
        System.out.println("inserted doc : " + 1);

        try{
            test.insert(doc);
        }catch (MongoException.DuplicateKey e){
            System.out.println(e.getMessage());
        }



    }
}
