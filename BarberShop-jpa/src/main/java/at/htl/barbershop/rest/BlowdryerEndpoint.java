package at.htl.barbershop.rest;

import at.htl.barbershop.entity.Blowdryer;

import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/blowdryer")
@Stateless
public class BlowdryerEndpoint {

    @PersistenceContext (name = "dbPU")
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetBlowdryers(){

        TypedQuery<Blowdryer> query = em.createNamedQuery("Blowdryer.findAll", Blowdryer.class);
        List<Blowdryer> blowdryers = query.getResultList();

        System.out.println("GetBlowdryers(): found " + blowdryers.size() + " entries on DB");

        if(blowdryers.size()==0)
            return Response.status(404).build();
        else
            return Response.status(200).entity(blowdryers).build();
    }

    @GET
    @Path("{settings}")
    public Response GetBlowdryers(@PathParam("settings") int settings){

        TypedQuery<Blowdryer> query = em.createNamedQuery("Blowdryer.findBySettings", Blowdryer.class)
                .setParameter("SETTINGS", settings);
        List<Blowdryer> blowdryers = query.getResultList();

        if(blowdryers.size()==0)
            return Response.status(404).build();
        else
            return Response.status(200).entity(blowdryers).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddBlowdryer(JsonObject input){

        Blowdryer blowdryer = new Blowdryer(
                input.getString("name"),
                input.getInt("quantity"),
                input.getInt("heatSettings"),
                input.getBoolean("hasCoolShot")
        );

        em.persist(blowdryer);

        return Response.status(200).build();


    }




}
