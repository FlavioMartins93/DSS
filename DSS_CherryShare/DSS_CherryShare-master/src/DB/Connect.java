package DB;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Connect {

private static final String URL = "localhost:3306";
private static final String SCHEMA = "cherrysharedb?serverTimezone=GMT&verifyServerCertificate=false&useSSL=true"; /*aqui deve ter erro teste myDB?useSSL=false, mydb?useSSL=false
																 e CherryShareDB?useSSL=false*/
private static final String USERNAME = "root";
private static final String PASSWORD = "12345";

public static Connection connect() {
    try {
        //Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection("jdbc:mysql://"+URL+"/"+SCHEMA, USERNAME, PASSWORD);
    } catch (Exception e) {
    //unable to connect
    e.printStackTrace();
    }
    return null;
 }

public static void close(Connection connection) {
    try {
        connection.close();
    } catch (Exception e) {
    //nothing to close
    }
    } 
}

