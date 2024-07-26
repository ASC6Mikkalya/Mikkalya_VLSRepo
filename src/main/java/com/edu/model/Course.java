package com.edu.model;

import com.edu.exception.IllegalDurationException;

import java.time.Duration;

public class Course {
    private String courseId;
    private String courseName;
    private String authorName;
    private Duration courseDuration;
    private boolean courseAvailability;
    public Course(String courseId,String courseName,String authorName,Duration courseDuration,boolean courseAvailability){
        if(courseId==null||courseId.isEmpty()||courseName==null||courseName.isEmpty()||authorName==null||authorName.isEmpty()){
            throw new UnsupportedOperationException();
        }
        if(courseDuration.isNegative()||courseDuration.isZero()){
            throw new IllegalDurationException("Duration should not be negative or zero");
        }
        this.courseId=courseId;
        this.courseName=courseName;
        this.authorName=authorName;
        this.courseDuration=courseDuration;
        this.courseAvailability=courseAvailability;
    }
    public void setCourseId(String courseId){
        if(courseId==null||courseId.isEmpty()){
            throw new UnsupportedOperationException();
        }
        this.courseId=courseId;
    }
    public void setCourseName(String courseName){
        if(courseName==null||courseName.isEmpty()){
            throw new UnsupportedOperationException();
        }
        this.courseName=courseName;
    }
    public void setAuthorName(String authorName){
        if(authorName==null||authorName.isEmpty()){
            throw new UnsupportedOperationException();
        }
        this.authorName=authorName;
    }
    public void setCourseDuration(Duration courseDuration){
        if(courseDuration.isNegative()||courseDuration.isZero()){
            throw new IllegalDurationException("Duration should not be negative or zero");
        }
        this.courseDuration=courseDuration;
    }
    public void setCourseAvailability(boolean courseAvailability){
        this.courseAvailability=courseAvailability;
    }
    public String getCourseId(){
        return courseId;
    }
    public String getCourseName(){
        return courseName;
    }
    public String getAuthorName(){
        return authorName;
    }
    public Duration getCourseDuration(){
        return  courseDuration;
    }
    public boolean getCourseAvailability(){
        return  courseAvailability;
    }
    public String toString(){
        return "Course Details: CourseId = "+courseId+"\tCourseName = "+courseName+"\tAuthorName = "+authorName+"\tCourseDuration = "+courseDuration+"\tCourseAvailability = "+courseAvailability;
    }
}
