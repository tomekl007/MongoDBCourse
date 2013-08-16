package week3;

import com.google.common.collect.Collections2;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.*;

public class h3_1 {
    
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));//default connect to localhost

        DB database = client.getDB("school");
        DBCollection collection = database.getCollection("students");
        DBCollection collection2 = database.getCollection("studentsAfter");
        DBCursor cursor = collection.find();
        try{
            while(cursor.hasNext()){
                BasicDBList result = new BasicDBList();
                BasicDBList scoreResult = new BasicDBList();
                DBObject cur = cursor.next();
                result.add(cur);
                ArrayList<BasicDBObject> scores  = (ArrayList) cur.get("scores");
                System.out.println(scores);
                ArrayList<BasicDBObject> homeworkScoresForStudent = new ArrayList<BasicDBObject>();
                for(BasicDBObject score : scores){
                    System.out.println(score);

                    if(score.get("type").equals("homework")){
                        homeworkScoresForStudent.add(score);
                        //System.out.println("score for homework : " + score.get("score"));
                    }else{
                          scoreResult.add(score);
                    }

                }
                System.out.println("scores for this student : " + homeworkScoresForStudent);
                Collections.sort(homeworkScoresForStudent, new Comparator<BasicDBObject>() {
                    @Override
                    public int compare(BasicDBObject o1, BasicDBObject o2) {
                        return (int) ((Double)o1.get("score") - (Double)o2.get("score"));
                    }
                });
                System.out.println("smaller score : " + homeworkScoresForStudent.get(0));
                homeworkScoresForStudent.remove(0);
                scoreResult.addAll(homeworkScoresForStudent);
                System.out.println("ready to insert : " + scoreResult);

                BasicDBObject c = (BasicDBObject) result.get(0);
                c.append("scores", scoreResult);
                System.out.println(c);
                System.out.println("result : " + result);
                collection2.insert(c);

            }
        }finally {
            cursor.close();
        }


    }
}
