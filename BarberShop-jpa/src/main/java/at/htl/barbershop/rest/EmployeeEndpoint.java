package at.htl.barbershop.rest;

import at.htl.barbershop.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
public class EmployeeEndpoint {

    @PersistenceContext(name = "dbPU")
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetEmployees(){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll",Employee.class);
        List<Employee> employees = query.getResultList();

        System.out.println("GetEmployees(): found " + employees.size() + " entries on DB");

        if(employees.size() == 0)
            return Response.status(404).build();
        else
            return Response.status(200).entity(employees).build();
    }
}
