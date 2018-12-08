package at.htl.BarbershopST;

import jdk.jfr.MemoryAddress;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.json.Json;
import javax.json.JsonObject;
import javax.print.DocFlavor;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BarberShopTests {
    private Client client;
    private WebTarget target;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/BarberShop-jpa/api");
    }

    @Test
    public void T01_GetBlowdryers(){
        Response response = target.path("blowdryer").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void T02_GetBlowdryersById(){
        Response response = target.path("blowdryer/99").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(404));

        response = target.path("blowdryer/3").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is (200));
    }

    @Test
    public void T03_PutHairColorByName(){
        JsonObject color = Json.createObjectBuilder()
                .add("name","Schwarzkopf")
                .add("color","Ultraviolet")
                .add("isSemiPermanent",true)
                .build();

        Response response = target.path("haircolor/Schwarzkopf/10").request(MediaType.APPLICATION_JSON).put(Entity.json(color));
        assertThat(response.getStatus(),is(200));

    }

    @Test
    public void T04_DeleteCustomerByName(){

        Response response= target.path("/customer/Tom").request().delete();
        assertThat(response.getStatus(), is(200));
    }

}
