
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
     public static Connection Connector(){
        try {
            String s ="jdbc:mysql://localhost:3307/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= (Connection) DriverManager.getConnection(s, "root","123abhijith456");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(" "+e);
          return null;   
          
        }
        
    }
}

