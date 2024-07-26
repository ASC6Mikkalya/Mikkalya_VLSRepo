package com.edu.connectdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connection;
    public static Connection getInstance(){
        if(connection==null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1/VLSDB","root","mysql");
            }
            catch (ClassNotFoundException classNotFoundException){
                System.out.println(classNotFoundException.getMessage());
            }
            catch (SQLException sqlException){
                System.out.println(sqlException.getMessage());
            }
        }
        return connection;
    }
}
