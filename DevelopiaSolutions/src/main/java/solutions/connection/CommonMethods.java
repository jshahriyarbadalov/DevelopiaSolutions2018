package solutions.connection;

import java.sql.ResultSet;
import java.sql.Statement;

public class CommonMethods  {

private DB db=DB.getInstance();


    // ready
    public long rowCountInTableForThisCondition(String tableName, String condition) {
        long count = 0;
        try {

            Statement s = db.connection.createStatement();
            ResultSet r = s.executeQuery("select count(*) from " + tableName + " " + condition);
            r.next();
            count = r.getLong(1);

            r.close();
            s.close();
        } catch (Exception ex) {
            count = 0;
            ex.printStackTrace();
        }
        return count;
    }
}
