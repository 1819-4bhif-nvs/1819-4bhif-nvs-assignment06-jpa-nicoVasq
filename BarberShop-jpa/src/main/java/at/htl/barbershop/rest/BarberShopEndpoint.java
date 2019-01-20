package at.htl.barbershop.rest;

import at.htl.barbershop.entity.BarberShop;
import at.htl.barbershop.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/barbershop")
public class BarberShopEndpoint {

    @PersistenceContext(name = "dbPU")
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetBarbershops(){
        TypedQuery<BarberShop> query = em.createNamedQuery("BarberShop.findAll", BarberShop.class);
        List<BarberShop> shops = query.getResultList();

        System.out.println("GetBarbershops(): found " + shops.size() + " shops on DB");


        if(shops.size() == 0)
            return Response.status(404).build();
        else
            return Response.status(200).entity(shops).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}/emp")
    public Response GetBarbershopEmployees(@PathParam("name") String name){
        TypedQuery<BarberShop> query = em.createNamedQuery("BarberShop.findByName", BarberShop.class)
                .setParameter("NAME",name);
        BarberShop shop = query.getSingleResult();

        if(shop != null){
            List<Employee> employees = shop.getEmployees();
            if(employees.size() != 0)
                return  Response.status(200).entity(employees).build();
        }
        return Response.status(404).build();
    }

    @DELETE
    @Path("{name}")
    @Transactional
    public Response DeleteBarberShopByName(@PathParam("name") String name){
        TypedQuery<BarberShop> query = em.createNamedQuery("BarberShop.findByName", BarberShop.class)
                .setParameter("NAME", name);
        BarberShop shop = query.getSingleResult();

        if(shop != null){
            em.remove(shop);
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

}
