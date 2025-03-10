package org.ferdtech.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CourseNotFoundExceptionMapper implements ExceptionMapper<CourseNotFoundException> {

    @Override
    public Response toResponse(CourseNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Course not found").build();
    }
}
