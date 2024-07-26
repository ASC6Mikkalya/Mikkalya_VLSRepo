package com.edu.service;

import com.edu.model.Course;
import com.edu.repository.CourseRepository;
import com.edu.repository.CourseRepositoryCollectionImplementation;
import com.edu.repository.CourseRepositoryDatabaseImplementation;

import java.util.List;

public class CourseServiceImplementation implements CourseService{
//    CourseRepository courseRepository=new CourseRepositoryCollectionImplementation();
    CourseRepository courseRepository=new CourseRepositoryDatabaseImplementation();
    public boolean addCourse(Course course){
        return courseRepository.addCourse(course);
    }
    public List<Course> displayCourse(){
        return courseRepository.displayCourse();
    }
    public boolean searchCourseByCourseName(String courseName){
        return courseRepository.searchCourseByCourseName(courseName);
    }
    public boolean searchCourseByAuthorName(String authorName){
        return courseRepository.searchCourseByAuthorName(authorName);
    }
    public boolean upadteCourse(String courseId){
        return courseRepository.upadteCourse(courseId);
    }
    public boolean deleteCourse(String courseId){
        return courseRepository.deleteCourse(courseId);
    }
}
