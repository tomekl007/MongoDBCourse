package week2;

import com.mongodb.BasicDBObject;
import freemarker.template.TemplateException;

import java.util.Arrays;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 8/10/13
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentRepresenationTest {
    public static void main(String[]  args){
        BasicDBObject doc = new BasicDBObject();
        doc.put("userName", "Tomek");
        doc.put("birthDate", new Date());
        doc.put("age", 38);
        doc.put("programer", true);
        doc.put("langague", Arrays.asList("Java", "C++"));
        doc.put("address", new BasicDBObject("street", "20 Main")
                .append("town", "krk"));

    }

}
