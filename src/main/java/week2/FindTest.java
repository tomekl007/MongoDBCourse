package week2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 8/10/13
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindTest {

    public static void main(String[]  args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));//default connect to localhost

        DB database = client.getDB("test");
        DBCollection collection = database.getCollection("things");
        collection.drop();

        for( int i = 0; i < 10; i++){
            collection.insert(new BasicDBObject("x", new Random().nextInt(2))
            .append("y", new Random().nextInt(100)));
        }

        DBObject query = new BasicDBObject("x", 0)
                 .append("y", new BasicDBObject("$gt", 10).append("$lt", 90));

        QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(70);


        System.out.println("find one: ");
        DBObject one = collection.findOne();
        System.out.println(one);

        System.out.println("find all");
        DBCursor cursor = collection.find(builder.get(), new BasicDBObject("y", true).append("_id", false));
        try{
            while(cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }

        System.out.println("\ncount : ");
        System.out.println(collection.count(query));


    }
}
