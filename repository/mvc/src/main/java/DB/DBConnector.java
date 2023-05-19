/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    
    //Your database MUST be on port 1527 with the name 'IOTBAY' 
    private static final String URL = "jdbc:derby://localhost:1527/IOTBAY";
        //When you make your DB make sure your USERNAME is 'isduser'
    private static final String USERNAME = "isduser";
        //And make sure your password is 'admin'
    private static final String PASSWORD = "admin";
    
    private static Connection conn;
    
    public Connection openConnection() throws SQLException{
        if(conn == null || conn.isClosed()){
             conn =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
             System.out.println("Connection to database initalized");
        }
        return conn;
    }
    
    public void closeConnection() throws SQLException{
        if (conn != null && !conn.isClosed()){
            conn.close();
            System.out.println("Connection to database closed");
        }
    }
}


