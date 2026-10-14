package vn.fit.de05.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.fit.de05.model.Employee;
import vn.fit.de05.service.EmployeeService;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {
    private final EmployeeService service = new EmployeeService();
    @GET
    public Response getAll(){
        return Response.ok(service.findAll()).build();
    }
    @GET
    @Path("/{id}")
    public Response getById (@PathParam( ("id")) int id){
        Employee o = service.findById(id);
        return o != null ? Response.ok(o).build():Response.status(404).build();
    }
    @POST
    public Response create (Employee o ){
        if (o == null || o.getName() == null)
            return Response.status(400).build();
        return Response.status(201).entity(service.save(o)).build();
    }
}
