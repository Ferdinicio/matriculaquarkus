package org.ferdtech.controller;

import java.util.UUID;

import org.ferdtech.entity.SemesterEntity;
import org.ferdtech.service.SemesterService;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/semesters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SemesterController {

    private final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
    @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var semesters = semesterService.findAll(page, pageSize);
        return Response.ok(semesters).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID semesterId) {
        return Response.ok(semesterService.findById(semesterId)).build();
    }

    @POST
    @Transactional
    public Response createSemester(SemesterEntity semesterEntity) {
        return Response.ok(semesterService.createSemester(semesterEntity)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateSemester(@PathParam("id") UUID semesterId, SemesterEntity semesterEntity) {
        return Response.ok(semesterService.updateSemester(semesterId, semesterEntity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") UUID semesterId) {
        semesterService.deleteSemester(semesterId);
        return Response.noContent().build();
    }
}

