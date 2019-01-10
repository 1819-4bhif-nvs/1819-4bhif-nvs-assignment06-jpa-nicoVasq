package at.htl.barbershop.rest;

import at.htl.barbershop.entity.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Service")
public class ServiceEndpoint {

    @PersistenceContext(name = "dbPU")
    EntityManager em;

    @GET
    public Response GetServices(){

        TypedQuery<Service> query = em.createNamedQuery("Service.findAll",Service.class);
        List<Service> services = query.getResultList();

        if(services.size() == 0)
            return Response.status(404).build();
        else
            return Response.status(200).build();

    }
}
