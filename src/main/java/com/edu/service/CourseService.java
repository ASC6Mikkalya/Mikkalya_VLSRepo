package com.edu.service;

import com.edu.model.Course;

import java.util.List;

public interface CourseService {
    boolean addCourse(Course course);
    List<Course> displayCourse();
    boolean searchCourseByCourseName(String courseName);
    boolean searchCourseByAuthorName(String authorName);
    boolean upadteCourse(String courseId);
    boolean deleteCourse(String courseId);
}
