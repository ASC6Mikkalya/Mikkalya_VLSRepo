package com.edu.repository;

import com.edu.connectdb.DatabaseConnection;
import com.edu.exception.CourseNotFoundException;
import com.edu.exception.DuplicateCourseException;
import com.edu.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.time.Duration.ofHours;

public class CourseRepositoryDatabaseImplementation implements CourseRepository{
    Scanner sc=new Scanner(System.in);
    public boolean addCourse(Course course){
        int noOfRowsAffected=0;
        try{
            Connection connection= DatabaseConnection.getInstance();
            PreparedStatement statement=connection.prepareStatement("insert into course values(?,?,?,?,?);");
            statement.setString(1,course.getCourseId());
            statement.setString(2,course.getCourseName());
            statement.setString(3,course.getAuthorName());
            statement.setLong(4, course.getCourseDuration().toHours());
            statement.setBoolean(5,course.getCourseAvailability());
            noOfRowsAffected=statement.executeUpdate();
        }
        catch(SQLException sqlException){
            try{
                if(sqlException.getErrorCode()==1062){
                    throw new DuplicateCourseException("Course already exists");
                }
            }
            catch(DuplicateCourseException duplicateCourseException){
                System.out.println(duplicateCourseException.getMessage());
            }
            System.out.println(sqlException.getMessage());
        }
        return noOfRowsAffected>0;
    }
    public List<Course> displayCourse(){
        List<Course> courseList=new ArrayList<>();
        try{
            Connection connection= DatabaseConnection.getInstance();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Course;");
            while(resultSet.next()){
                Course course=new Course(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),ofHours(resultSet.getLong(4)),resultSet.getBoolean(5));
                courseList.add(course);
            }
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return courseList;
    }
    public boolean searchCourseByCourseName(String courseName){
        boolean courseSearched=false;
        try{
            Connection connection= DatabaseConnection.getInstance();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Course where courseName=\'"+courseName+"\'");
            if(resultSet.next()){
                courseSearched=true;
            }
            if(!courseSearched){
                throw new CourseNotFoundException("Course Not Found");
            }
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        catch(CourseNotFoundException courseNotFoundException){
            System.out.println(courseNotFoundException.getMessage());
        }
        return courseSearched;
    }
    public boolean searchCourseByAuthorName(String authorName){
        boolean courseSearched=false;
        try{
            Connection connection= DatabaseConnection.getInstance();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Course where authorName=\'"+authorName+"\'");
            if(resultSet.next()){
                courseSearched=true;
            }
            if(!courseSearched){
                throw new CourseNotFoundException("Course Not Found");
            }
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        catch(CourseNotFoundException courseNotFoundException){
            System.out.println(courseNotFoundException.getMessage());
        }
        return courseSearched;
    }
    public boolean upadteCourse(String courseId){
        boolean courseSearched=false;
        int noOfRowsAffected=0;
        try{
            Connection connection= DatabaseConnection.getInstance();
            Statement statement=connection.createStatement();
            StringBuilder query=new StringBuilder("update Course set ");
            System.out.println("1.Update CourseName");
            System.out.println("2.Update AuthorName");
            System.out.println("3.Update Duration");
            System.out.println("4.Update CourseAvailable");
            System.out.println("Enter choice to update");
            int choice=sc.nextInt();
            if(!(choice>0&&choice<5)) return noOfRowsAffected>0;
            while(choice>0&&choice<5){
                switch(choice){
                    case 1:
                        sc.nextLine();
                        query.append("courseName=");
                        System.out.println("Enter course name to update");
                        query.append("\'"+sc.nextLine()+"\'");
                        break;
                    case 2:
                        sc.nextLine();
                        query.append("authorName=");
                        System.out.println("Enter author name to update");
                        query.append("\'"+sc.nextLine()+"\'");
                        break;
                    case 3:
                        query.append("courseDuration=");
                        System.out.println("Enter course duration to update");
                        query.append(sc.nextLong());
                        break;
                    case 4:
                        query.append("courseAvailability");
                        System.out.println("Enter course availability to update");
                        query.append(sc.nextBoolean());
                        break;
                    default:
                        break;
                }
                System.out.println("Enter choice to update");
                choice=sc.nextInt();
                if(choice>0&&choice<5) query.append(" , ");
            }
            query.append(" where courseId=\'"+courseId+"\'");
            noOfRowsAffected=statement.executeUpdate(String.valueOf(query));
            if(noOfRowsAffected<=0){
                throw new CourseNotFoundException("Course Not Found");
            }
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        catch(CourseNotFoundException courseNotFoundException){
            System.out.println(courseNotFoundException.getMessage());
        }
        return noOfRowsAffected>0;
    }
    public boolean deleteCourse(String courseId){
        boolean courseSearched=false;
        int noOfRowsAffected=0;
        try{
            Connection connection= DatabaseConnection.getInstance();
            Statement statement=connection.createStatement();
            noOfRowsAffected=statement.executeUpdate("delete from Course where courseId=\'"+courseId+"\'");
            if(noOfRowsAffected<=0){
                throw new CourseNotFoundException("Course Not Found");
            }
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        catch(CourseNotFoundException courseNotFoundException){
            System.out.println(courseNotFoundException.getMessage());
        }
        return noOfRowsAffected>0;
    }
}
