package com.edu.repository;

import com.edu.exception.CourseNotFoundException;
import com.edu.exception.DuplicateCourseException;
import com.edu.model.Course;

import java.time.Duration;
import java.util.*;

public class CourseRepositoryCollectionImplementation implements CourseRepository{
    Scanner sc=new Scanner(System.in);
    Map<String,Course> courseMap=new HashMap<>();
    public boolean addCourse(Course course){
        boolean courseAdded=false;
        try{
            if(courseMap.containsKey(course.getCourseId())){
                throw new DuplicateCourseException("Course Already Exists");
            }
            courseMap.put(course.getCourseId(),course);
            courseAdded=true;
        }
        catch(DuplicateCourseException duplicateCourseException){
            System.out.println(duplicateCourseException.getMessage());
        }
        return courseAdded;
    }
    public List<Course> displayCourse(){
        List<Course> courseList= new ArrayList<>();
        for(Map.Entry<String,Course> courseMapEntry:courseMap.entrySet()){
            courseList.add(courseMapEntry.getValue());
        }
        return courseList;
    }
    public boolean searchCourseByCourseName(String courseName){
        boolean courseSearched=false;
        try{
            for(Map.Entry<String,Course> courseMapEntry:courseMap.entrySet()){
                Course course=courseMapEntry.getValue();
                if((course.getCourseName()).equals(courseName)){
                    courseSearched=true;
                }
            }
            if(!courseSearched)throw new CourseNotFoundException("Course Not Exists");
        }
        catch(CourseNotFoundException courseNotFoundException){
            System.out.println(courseNotFoundException.getMessage());
        }
        return courseSearched;
    }
    public boolean searchCourseByAuthorName(String authorName){
        boolean courseSearched=false;
        try{
            for(Map.Entry<String,Course> courseMapEntry:courseMap.entrySet()){
                Course course=courseMapEntry.getValue();
                if((course.getAuthorName()).equals(authorName)){
                    courseSearched=true;
                }
            }
            if(!courseSearched)throw new CourseNotFoundException("Course Not Exists");
        }
        catch(CourseNotFoundException courseNotFoundException){
            System.out.println(courseNotFoundException.getMessage());
        }
        return courseSearched;
    }
    public boolean upadteCourse(String courseId){
        boolean courseUpdated=false;
        try{
            if(courseMap.containsKey(courseId)){
                Course course=courseMap.get(courseId);
                System.out.println("1.Update CourseName");
                System.out.println("2.Update AuthorName");
                System.out.println("3.Update Duration");
                System.out.println("4.Update CourseAvailable");
                System.out.println("Enter choice to update");
                int choice=sc.nextInt();
                while(choice>0&&choice<5) {
                    switch (choice) {
                        case 1:
                            sc.nextLine();
                            System.out.println("Enter course name to update");
                            course.setCourseName(sc.nextLine());
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.println("Enter author name to update");
                            course.setAuthorName(sc.nextLine());
                            break;
                        case 3:
                            System.out.println("Enter course duration to update");
                            course.setCourseDuration(Duration.ofHours(sc.nextInt()));
                            break;
                        case 4:
                            System.out.println("Enter course availability to update");
                            course.setCourseAvailability(sc.nextBoolean());
                            break;
                        default:
                            break;
                    }
                    System.out.println("Enter choice to update");
                    choice=sc.nextInt();
                }
                courseMap.put(courseId,course);
                courseUpdated=true;
            }
            else throw new CourseNotFoundException("Course Not Exists");
        }
        catch(CourseNotFoundException courseNotFoundException){
            System.out.println(courseNotFoundException.getMessage());
        }
        return courseUpdated;
    }
    public boolean deleteCourse(String courseId){
        boolean courseDeleted=false;
        try{
            if(courseMap.containsKey(courseId)){
                courseMap.remove(courseId);
                courseDeleted=true;
            }
            else throw new CourseNotFoundException("Course Not Exists");
        }
        catch(CourseNotFoundException courseNotFoundException){
            System.out.println(courseNotFoundException.getMessage());
        }
        return courseDeleted;
    }
}
