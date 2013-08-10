package week1;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 8/10/13
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWordSpark {
    public static void main(String[]  args) throws UnknownHostException {

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarker.class, "/");

        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));//default connect to localhost

        DB database = client.getDB("test");
        final DBCollection collection = database.getCollection("hello");


        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try{
                Template helloTemplate = configuration.getTemplate("hello.ftl");
                DBObject document =  collection.findOne();

                //Map<String,Object> helloMap = new HashMap<String, Object>();
               // helloMap.put("name", "Freemarker");
                System.out.println(document);
                helloTemplate.process(document, writer);
                System.out.println(writer);
                }catch (Exception ex){
                    halt(500);
                    ex.printStackTrace();
                }
                return writer;
            }
        });
    }
}
