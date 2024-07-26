package com.edu.ui;

import com.edu.exception.IncorrectChoiceException;
import com.edu.model.Course;
import com.edu.service.CourseService;
import com.edu.service.CourseServiceImplementation;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import static java.time.Duration.ofHours;

public class Menu {
    Scanner sc=new Scanner(System.in);
    CourseService courseService=new CourseServiceImplementation();
    void displayMenu(){
        System.out.println("1. Add Course");
        System.out.println("2. Display Course");
        System.out.println("3. Search Course");
        System.out.println("4. Update Course");
        System.out.println("5. Delete Course");
    }
    int getChoice(){
        return sc.nextInt();
    }
    Course courseInfo(){
        sc.nextLine();
        System.out.println("Enter CourseId");
        String courseId=sc.nextLine();
        System.out.println("Enter CourseName");
        String courseName=sc.nextLine();
        System.out.println("Enter AuthorName");
        String authorName=sc.nextLine();
        System.out.println("Enter CourseDuration");
        Duration courseDuration=ofHours(sc.nextInt());
        System.out.println("Enter CourseAvailability");
        boolean courseAvailability=sc.nextBoolean();
        return new Course(courseId,courseName,authorName,courseDuration,courseAvailability);
    }
    void addCourse(Course course){
        boolean courseAdded=courseService.addCourse(course);
        if(courseAdded){
            System.out.println("Course Added");
        }
        else{
            System.out.println("Course Not Added");
        }
    }
    void displayCourse(){
        List<Course> courseList=courseService.displayCourse();
        for(Course course:courseList){
            System.out.println(course);
        }
    }
    void searchCourse(){
        System.out.println("1. SearchBy CourseName");
        System.out.println("2. SearchBy AuthorName");
        int choice= sc.nextInt();
        boolean courseSearched=false;
        try{
            if(choice ==1){
                sc.nextLine();
                System.out.println("Enter CourseName");
                courseSearched=courseService.searchCourseByCourseName(sc.nextLine());
            }
            else if(choice==2){
                sc.nextLine();
                System.out.println("Enter AuthorName");
                courseSearched=courseService.searchCourseByAuthorName(sc.nextLine());
            }
            else{
                throw new IncorrectChoiceException("Search choice is Incorrect");
            }
        }
        catch(IncorrectChoiceException incorrectChoiceException){
            System.out.println(incorrectChoiceException.getMessage());
        }
        if(courseSearched){
            System.out.println("Course Found");
        }
        else{
            System.out.println("Course Not Found");
        }
    }
    void updateCourse(){
        sc.nextLine();
        System.out.println("Enter CourseId to Update");
        boolean courseUpdated= courseService.upadteCourse(sc.nextLine());
        if(courseUpdated){
            System.out.println("Course Updated");
        }
        else{
            System.out.println("Course Not Updated");
        }
    }
    void deleteCourse(){
        sc.nextLine();
        System.out.println("Enter CourseId to Delete");
        boolean courseDeleted= courseService.deleteCourse(sc.nextLine());
        if(courseDeleted){
            System.out.println("Course Deleted");
        }
        else{
            System.out.println("Course Not Deleted");
        }
    }
}
