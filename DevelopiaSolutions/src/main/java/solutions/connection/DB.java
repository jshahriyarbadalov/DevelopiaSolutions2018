package solutions.connection;




import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DB {
    protected Connection connection;



    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String dbUrl = "";
    private String username = "";
    private String password = "";
    private String configFolderPath = "";
    private boolean successfulConnection = false;
    private static DB instance = new DB();

    //ready
    private DB() {
        System.out.println("DB Object created...");

        try {
            connect();
            successfulConnection=true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    protected void connect() throws SQLException, IOException {

        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        String filePath = "solutions/connection/config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream in = loader.getResourceAsStream(filePath);

        Properties properties = new Properties();
        properties.load(in);

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        connection = DriverManager.getConnection(url, username, password);
        if (!connection.equals(null)) {
            System.out.println("**Success**");
        }
        in.close();
    }


    // ready
    public ArrayList<String> getColumnRowsFromTable(String tableName, String columnName, String condition) {
        ArrayList<String> list = new ArrayList<>();

        try {
            Statement s = connection.createStatement();
            ResultSet r = s
                    .executeQuery("select " + columnName + " from " + tableName + " " + condition);

            while (r.next()) {
                list.add(r.getString(1));

            }
            r.close();
            s.close();
        } catch (Exception ex) {
            list.clear();
            ex.printStackTrace();
        }

        return list;
    }

    //ready
    public String getColumnSingleRowFromTable(String tableName, String columnName, String condition) {
        String row = "";
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select " + columnName + " from " + tableName + " " + condition);

            if (resultSet.next()) {
                row = resultSet.getString(1);
            } else {
                row = "";
            }

            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            row = "";
            ex.printStackTrace();
        }

        return row;
    }


    public boolean isSuccessfulConnection() {
        return successfulConnection;
    }

    public void setSuccessfulConnection(boolean successfulConnection) {
        this.successfulConnection = successfulConnection;
    }

    //ready
    public boolean iud(String query) {
        boolean result = false;
        try {
            Statement s = connection.createStatement();
            s.execute(query);
            s.close();
            result = true;
        } catch (Exception ex) {
            result = false;
            JOptionPane.showMessageDialog(null, "Error-Information not committed");
            ex.printStackTrace();
        }
        return result;

    }

    //ready
    public void disconnect() {
        try {
            if (connection.isClosed()) {

            } else {
                if (connection == null) {

                } else {
                    connection.close();
                    System.out.println("Database Closed...");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get the only object available
    public static DB getInstance() {
        return instance;
    }


    //ready
    private ArrayList<String> getTxtLines(String txtName) {

        ArrayList<String> lines = new ArrayList<String>();
        try {
            String path =   txtName;
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                lines.add(line);

                line = br.readLine();
            }

            br.close();
        } catch (Exception e) {
            lines.clear();
            e.printStackTrace();
        }


        return lines;

    }




    //ready
    public void userEnteredTheSystem(String username, String now) {

        iud("insert into userslogintimes (userName,date,type) values ('" + username + "','" + now + "',0) ");
        System.out.println("User Entered The System");
    }
    //ready
    public void userExitedFromTheSystem(String username, String now) {

        iud("insert into userslogintimes (userName,date,type) values ('" + username + "','" + now + "',1) ");
        System.out.println("User Exited From The System");
    }

    //ready
    // bu metod ona verilən sorğuya görə yeni bir reslutset yaradır və onu geriyə qaytarır.
    public ResultSet getResultSet(String query) {
        ResultSet rs=null;

        try {

            Statement s= connection.createStatement();
            rs=s.executeQuery(query);

        }catch (Exception ex){
            rs=null;
            ex.printStackTrace();
        }

        return rs;
    }
    //ready
    // bu metod ona verilən resultset i statement i ilə birgə bağlayır.
    public void closeResultSet(ResultSet rs){

        try{
            rs.getStatement().close();
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }


    // ready
    public long getMaxValueFromColumn(String tableName, String column) {
        long max = 0;
        try {

            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("select max("+column+") from " + tableName);
            r.next();
            max = r.getLong(1);

            r.close();
            s.close();
        } catch (Exception ex) {
            max = 0;
            ex.printStackTrace();
        }
        return max;
    }

}
