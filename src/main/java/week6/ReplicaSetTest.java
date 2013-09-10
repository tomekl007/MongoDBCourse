package week6;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 9/5/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReplicaSetTest {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        MongoClient client = new MongoClient(Arrays.asList(
                new ServerAddress("localhost", 27018),
                new ServerAddress("localhost", 27017),
                new ServerAddress("localhost", 27019))
                );//default connect to localhost

        DBCollection test = client.getDB("course").getCollection("replica.test");

        test.drop();

        for (int i = 0; i < 100; i++) {
            for (int retry = 0; retry < 3; retry++) {
                try{
                    test.insert(new BasicDBObject("_id", i));
                    System.out.println("inserted doc : " + i);
                    break;
                }catch(MongoException.DuplicateKey ex){
                    System.out.println("Document already inserted");
                }
                catch(MongoException e){
                    System.out.println(e.getMessage());
                    System.out.println("retrying..");
                    Thread.sleep(500);
                }
            }
            Thread.sleep(500);
        }


    }
}
