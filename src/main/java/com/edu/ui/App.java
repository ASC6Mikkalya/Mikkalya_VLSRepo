package com.edu.ui;

import com.edu.connectdb.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Login Page");
        boolean ableToLogin=false;
        try{
            Connection connection= DatabaseConnection.getInstance();
            Statement statement=connection.createStatement();
            System.out.println("Enter Username");
            String userName=sc.nextLine();
            System.out.println("Enter password");
            String password=sc.nextLine();
            ResultSet resultSet=statement.executeQuery("select * from Login where userName=\'"+userName+"\' and password=\'"+password+"\'");
            if(resultSet.next()) ableToLogin=true;
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        if(ableToLogin){
            Menu menu=new Menu();
            System.out.println("Login Successful");
            System.out.println("Welcome to Virtual Learning System");
            menu.displayMenu();
            System.out.println("Enter Choice from Menu");
            int choice= menu.getChoice();
            while(choice>0&&choice<=5){
                switch(choice){
                    case 1:
                        menu.addCourse(menu.courseInfo());
                        break;
                    case 2:
                        menu.displayCourse();
                        break;
                    case 3:
                        menu.searchCourse();
                        break;
                    case 4:
                        menu.updateCourse();
                        break;
                    case 5:
                        menu.deleteCourse();
                        break;
                    default:
                        break;
                }
                menu.displayMenu();
                System.out.println("Enter Choice from Menu");
                choice= menu.getChoice();
            }
        }
        else{
            System.out.println("Login Unsuccessful");
        }
    }
}
