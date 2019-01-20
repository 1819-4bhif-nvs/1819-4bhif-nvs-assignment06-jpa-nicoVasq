package at.htl.barbershop.rest;

import at.htl.barbershop.entity.Customer;

import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customer")
public class CustomerEndpoint {

    @PersistenceContext(name = "dbPU")
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetCustomers(){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
        List<Customer> customers = query.getResultList();

        System.out.println("GetCustomers(): found " + customers.size() + " entries on DB");

        if(customers.size() == 0)
            return Response.status(404).build();
        else
            return Response.status(200).entity(customers).build();
    }

    @DELETE
    @Path("/{n}")
    @Transactional
    public Response DeleteCustomer(@PathParam("n") String name){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByName",Customer.class)
                .setParameter("NAME", name);

        Customer customer = query.getSingleResult();
        if(customer == null)
            return Response.status(404).build();

        em.remove(customer);
        return Response.status(200).build();
    }
}
