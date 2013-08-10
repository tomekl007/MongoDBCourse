package week2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 8/10/13
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsertTest {

    public static void main(String[]  args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));//default connect to localhost

        DB database = client.getDB("test");
        DBCollection collection = database.getCollection("things");

        BasicDBObject doc = new BasicDBObject();
        doc.put("userName", "Tomek");
        doc.put("birthDate", new Date());
        doc.put("age", 38);
        doc.put("programer", true);
        doc.put("langague", Arrays.asList("Java", "C++"));
        doc.put("address", new BasicDBObject("street", "20 Main")
                .append("town", "krk"));
        DBObject doc2 = new BasicDBObject().append("x", 2);
        System.out.println(doc);
        collection.insert(Arrays.asList(doc, doc2));
        System.out.println(doc);





    }
}
