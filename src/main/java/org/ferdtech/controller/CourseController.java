package org.ferdtech.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.ferdtech.entity.CourseEntity;
import org.ferdtech.service.CourseService;

import java.util.UUID;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var courses = courseService.findAll(page, pageSize);

        return Response.ok(courses).build();
    }

    @POST
    @Transactional
    public Response createCourse(CourseEntity courseEntity) {
        return Response.ok(courseService.createCourse(courseEntity)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCourse(@PathParam("id") UUID courseId, CourseEntity courseEntity) {
        return Response.ok(courseService.updateCourse(courseId, courseEntity)).build();
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID courseId) {
        return Response.ok(courseService.findById(courseId)).build();
    }

    @GET
    @Path("/area/{area}")
    public Response getByArea(@PathParam("area") String courseArea) {
        return Response.ok(courseService.findByCoursearea(courseArea)).build();
    }

    @GET
    @Path("/name/{name}")
    public Response getByName(@PathParam("name") String courseName) {
        return Response.ok(courseService.findByCoursename(courseName)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") UUID courseId) {
        courseService.deleteById(courseId);
        return Response.noContent().build();
    }
}
