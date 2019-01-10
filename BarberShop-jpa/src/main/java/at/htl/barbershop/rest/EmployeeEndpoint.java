package at.htl.barbershop.rest;

import at.htl.barbershop.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
public class EmployeeEndpoint {

    @PersistenceContext(name = "dbPU")
    EntityManager em;

    @GET
    public Response GetEmployees(){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll",Employee.class);
        List<Employee> employees = query.getResultList();

        if(employees.size() == 0)
            return Response.status(404).build();
        else
            return Response.status(200).build();
    }
}
