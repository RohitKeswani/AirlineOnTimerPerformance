package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utility {

    public static Connection createConnection(String URL, String user, String password)
    {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(URL, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("ERROR: COULD NOT CONNECT TO " + URL +" WITH USER "+user+" and Password "+password);
        }
        return connection;
    }
}
