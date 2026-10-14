package vn.fit.de05.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.fit.de05.model.Department;
import vn.fit.de05.model.DepartmentEmployeeDTO;
import vn.fit.de05.service.DepartmentService;

import java.math.BigDecimal;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentController {
    private final DepartmentService service = new DepartmentService();
    @GET
    public Response getAll(){
        return Response.ok(service.findAll()).build();
    }
    @GET
    @Path("/{id}")
    public Response getById (@PathParam( ("id")) int id){
        Department o = service.findById(id);
        return o != null ? Response.ok(o).build():Response.status(404).build();
    }
    @GET
    @Path("/{departmentId:\\d+}/employees")
    public Response getDepartmentWithEmployee(
            @PathParam("departmentId") int departmentId,
            @QueryParam("min") BigDecimal minSalary,
            @QueryParam("max") BigDecimal maxSalary ){
                if (minSalary != null && maxSalary != null && minSalary.compareTo(maxSalary) > 0){
                    return Response.status(Response.Status.BAD_REQUEST).entity("minSalary phải <= maxSalary").build();
                }
        DepartmentEmployeeDTO dto = service.getDepartmentWithChildren(departmentId, minSalary, maxSalary);
                if(dto == null){
                    return Response.status(Response.Status.NOT_FOUND).entity("Không tìm thấy ID phòng ban").build();
                }
                return Response.ok(dto).build();
    }
    @GET
    @Path("/statistics")
    public Response getThongKe(){
        return Response.ok(service.getThongKe()).build();
    }

}
