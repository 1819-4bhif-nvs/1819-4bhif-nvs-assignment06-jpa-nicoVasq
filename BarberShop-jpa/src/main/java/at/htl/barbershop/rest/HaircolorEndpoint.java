package at.htl.barbershop.rest;

import at.htl.barbershop.entity.Haircolor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.print.DocFlavor;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/haircolor")
@Stateless
public class HaircolorEndpoint {

    @PersistenceContext(name = "dbPU")
    EntityManager em;

    @PUT
    @Path("{n}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateQuantity(Haircolor newColor, @PathParam("amount") int amount, @PathParam("n") String name){
        TypedQuery<Haircolor> query = em.createNamedQuery("Haircolor.findByName", Haircolor.class)
                .setParameter("NAME",name);

        Haircolor hc = query.getSingleResult();

        if(hc == null)
            return Response.status(404).build();

        hc.setQuantity(hc.getQuantity());
        return Response.status(200).entity(hc).build();
    }
}
