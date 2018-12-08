package at.htl.barbershop.rest;

import at.htl.barbershop.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/customer")
public class CustomerEndpoint {

    @PersistenceContext(name = "dbPU")
    EntityManager em;

    @DELETE
    @Path("/{n}")
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
