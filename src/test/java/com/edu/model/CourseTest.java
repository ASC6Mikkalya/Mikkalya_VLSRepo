package com.edu.model;

import com.edu.exception.IllegalDurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofHours;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course course1;
    private Course course2;
    @BeforeEach
    void setUp(){
        course1=new Course("C001","CoreJava","Subbu",ofHours(10),true);
        course2=new Course("C002","git","Subbu",ofHours(2),false);
    }
    @Test
    void testCourseCreation(){
        assertNotNull(course1);
        assertNotNull(course2);
    }
    @Test
    void testCourseId(){
        assertEquals("C001",course1.getCourseId());
        assertEquals("C002",course2.getCourseId());
    }
    @Test
    void testCourseIdShouldNotBeNull(){
        assertThrows(UnsupportedOperationException.class,()->new Course("","CoreJava","Subbu",ofHours(10),true));
    }
    @Test
    void testCourseName(){
        assertEquals("CoreJava",course1.getCourseName());
        assertEquals("git",course2.getCourseName());
    }
    @Test
    void testCourseNameShouldNotBeNull(){
        assertThrows(UnsupportedOperationException.class,()->new Course("C002","","Subbu",ofHours(2),false));
    }
    @Test
    void testAuthorName(){
        assertEquals("Subbu",course1.getAuthorName());
        assertEquals("Subbu",course2.getAuthorName());
    }
    @Test
    void testAuthorNameShouldNotBeNull(){
        assertThrows(UnsupportedOperationException.class,()->new Course("C002","git","",ofHours(2),false));
    }
    @Test
    void testCourseDuration(){
        assertEquals(ofHours(10),course1.getCourseDuration());
        assertEquals(ofHours(2),course2.getCourseDuration());
    }
    @Test
    void testCourseDurationShouldNotBeLessThanZero(){
        assertThrows(IllegalDurationException.class,()->new Course("C002","git","Subbu",ofHours(-2),false));
        assertThrows(IllegalDurationException.class,()->new Course("C002","git","Subbu",ofHours(0),false));
    }
    @Test
    void testCourseAvailability(){
        assertTrue(course1.getCourseAvailability());
        assertFalse(course2.getCourseAvailability());
    }
    @Test
    void testToString(){
        assertEquals("Course Details: CourseId = C001\tCourseName = CoreJava\tAuthorName = Subbu\tCourseDuration = PT10H\tCourseAvailability = true",course1.toString());
        assertEquals("Course Details: CourseId = C002\tCourseName = git\tAuthorName = Subbu\tCourseDuration = PT2H\tCourseAvailability = false",course2.toString());
    }
}
