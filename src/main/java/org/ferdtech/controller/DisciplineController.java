package org.ferdtech.controller;
import java.util.UUID;

import org.ferdtech.entity.DisciplineEntity;
import org.ferdtech.service.DisciplineService;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/disciplines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
    @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var disciplines = disciplineService.findAll(page,pageSize);
        return Response.ok(disciplines).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID disciplineId) {
        return Response.ok(disciplineService.findById(disciplineId)).build();
    }

    @POST
    @Transactional
    public Response createDiscipline(DisciplineEntity disciplineEntity) {
        return Response.ok(disciplineService.createDiscipline(disciplineEntity)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDiscipline(@PathParam("id") UUID disciplineId, DisciplineEntity disciplineEntity) {
        return Response.ok(disciplineService.updateDiscipline(disciplineId, disciplineEntity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") UUID disciplineId) {
        disciplineService.deleteById(disciplineId);
        return Response.noContent().build();
    }
}
