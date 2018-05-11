package com.in28minutes.soap.webservices.soapcoursemanagement.soap;

//com.in28minutes.soap.webservices.soapcoursemanagement.soap.service

import com.in28minutes.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.in28minutes.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.in28minutes.courses.*;
import com.in28minutes.soap.webservices.soapcoursemanagement.soap.bean.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    //method
    //input     - GetCourseDetailsRequest
    //output    - GetCourseDetailsResponse

    //http://in28minutes.com/courses
    //GetCourseDetailsRequest

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload
                                                                        GetCourseDetailsRequest request) {

        Course course = service.findById(request.getId());

        if (course.getId() == 0)
            throw new CourseNotFoundException("invalid Course ID = " + request.getId());

        return mapCourseDetails(course);
    }

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseRequest")
    @ResponsePayload
    public GetCourseDetailsResponse deleteCourseRequest(@RequestPayload
                                                                DeleteCourseRequest request) {

        Course course = service.deleteById(request.getId());

        return mapCourseDetails(course);
    }

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseStatusRCRequest")
    @ResponsePayload
    public DeleteCourseStatusRCResponse deleteCourseStatusRCRequest(@RequestPayload
                                                                            DeleteCourseStatusRCRequest request) {
        DeleteCourseStatusRCResponse response = new DeleteCourseStatusRCResponse();
        int status = service.deleteByIdRC(request.getId());
        response.setStatus(status);
        return response;
    }

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseStatusRequest")
    @ResponsePayload
    public DeleteCourseStatusResponse deleteCourseStatusRequest(@RequestPayload
                                                                        DeleteCourseStatusRequest request) {
        CourseDetailsService.Status status = service.deleteByIdStatus(request.getId());
        DeleteCourseStatusResponse response = new DeleteCourseStatusResponse();
        response.setStatus(mapStatus(status));
        return response;
    }

    private Status mapStatus(CourseDetailsService.Status status) {
        if (status == CourseDetailsService.Status.FAILURE)
            return Status.FAILURE;
        return Status.SUCCESS;
    }

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload
                                                                              GetAllCourseDetailsRequest request) {
        return mapAllCourseDetailsResponse(service.findAll());
    }

    private GetAllCourseDetailsResponse mapAllCourseDetailsResponse(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

        for (Course course : courses) {
            CourseDetails courseDetails = mapCourse(course);
            response.getCourseDetails().add(courseDetails);
        }
        return response;
    }

    private GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

}
