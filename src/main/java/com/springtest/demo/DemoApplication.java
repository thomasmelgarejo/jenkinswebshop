package com.springtest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class DemoApplication {
    private static final String DB_URL = "jdbc:mysql://3.125.3.231:3306";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private static Connection conn = null;


    public static void main(String[] args) {
        System.out.println("Before Application");
        connectToDB();
        SpringApplication.run(DemoApplication.class, args);
    }

    private static void connectToDB(){
        Statement statement = null;
        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = conn.createStatement();
            String sql = "SHOW databases;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                String brugernavn = resultSet.getString("Database");
                System.out.println("scheme: " + brugernavn );
            }

            resultSet.close();
            statement.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            System.out.println("Error connecting to MySQL Server");
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        System.out.println("Goodbye!");
    }

}
