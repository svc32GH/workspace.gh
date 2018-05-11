package com.in28minutes.soap.webservices.soapcoursemanagement.soap.service;

import com.in28minutes.soap.webservices.soapcoursemanagement.soap.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {
    public enum Status {
        SUCCESS,
        FAILURE;
    }

    private static List<Course> courses = new ArrayList<>();
    private static Course unknownCourse;

    static {
        unknownCourse = new Course(0, "Unknown", "Unknown course");

        Course course = new Course(1, "Spring", "10 Steps");
        courses.add(course);

        course = new Course(2, "Spring MVC", "10 Examples");
        courses.add(course);

        course = new Course(3, "Spring Boot", "6K Students");
        courses.add(course);

        course = new Course(4, "Maven", "Most popular maven course on internet!");
        courses.add(course);
    }

    //course - 1
    //Course FindById(int id)
    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id)
                return course;
        }
        return unknownCourse;
    }

    //courses
    //List<Course> findAll()
    public List<Course> findAll() {
        return courses;
    }

    //deletecourse
    //Course deleteById(int id)
    public Course deleteById(int id) {
        Course resCourse = unknownCourse;
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                resCourse = course;
                iterator.remove();
                return resCourse;
            }
        }
        return resCourse;
    }

    public int deleteByIdRC(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                iterator.remove();
                return 1;
            }
        }
        return 0;
    }

    public Status deleteByIdStatus(int id) {
        Status res = deleteByIdRC(id) == 1 ? Status.SUCCESS : Status.FAILURE;
        return res;
    }
}
