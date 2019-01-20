package at.htl.barbershop.rest;

import at.htl.barbershop.entity.Haircolor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.print.DocFlavor;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/haircolor")
@Stateless
public class HaircolorEndpoint {

    @PersistenceContext(name = "dbPU")
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetHaircolors(){
        TypedQuery query = em.createNamedQuery("Haircolor.findAll", Haircolor.class);
        List<Haircolor> haircolors = query.getResultList();

        System.out.println("GetHaircolors(): found " + haircolors.size() + " entries on DB");

        if(haircolors.size() == 0)
            return Response.status(404).build();
        else
            return Response.status(200).entity(haircolors).build();
    }

    @PUT
    @Path("{n}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response UpdateQuantity(Haircolor newColor, @PathParam("amount") int amount, @PathParam("n") String name){
        TypedQuery<Haircolor> query = em.createNamedQuery("Haircolor.findByName", Haircolor.class)
                .setParameter("NAME",name);

        Haircolor hc = query.getSingleResult();

        if(hc != null){
            hc.setQuantity(amount);
            return Response.status(200).entity(hc).build();
        }
        return Response.status(404).build();

    }
}
