package at.htl.BarbershopST;

import jdk.jfr.MemoryAddress;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.print.DocFlavor;
import javax.print.attribute.standard.Media;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BarberShopTests {
    private Client client;
    private WebTarget target;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/BarberShop-jpa/api/");
    }


    @Test
    public void T01_GetBarbershops(){
        Response response = this.target.path("barbershop").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));

        JsonArray arr = response.readEntity(JsonArray.class);
        assertThat(arr.size(),is(3));
    }

    @Test
    public void T02_GetEmployees(){
        Response response = this.target.path("employee").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));

        JsonArray arr = response.readEntity(JsonArray.class);
        assertThat(arr.size(),is(2));
    }

    @Test
    public void T03_GetBlowdryers(){
        Response response = this.target.path("blowdryer").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));

        JsonArray arr = response.readEntity(JsonArray.class);
        assertThat(arr.size(),is(1));
    }

    @Test
    public void T04_GetCustomers(){
        Response response = this.target.path("customer").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));

        JsonArray arr = response.readEntity(JsonArray.class);
        assertThat(arr.size(),is(2));
    }

    @Test
    public void T05_GetHaircolors(){
        Response response = this.target.path("haircolor").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));

        JsonArray arr = response.readEntity(JsonArray.class);
        assertThat(arr.size(),is(3));
    }

    @Test
    public void T06_GetServices(){
        Response response = this.target.path("service").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));

        JsonArray arr = response.readEntity(JsonArray.class);
        assertThat(arr.size(),is(2));
    }

    @Test
    public void T07_GetBlowdryersBySettings(){
        Response response = target.path("blowdryer/99").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(404));

        response = target.path("blowdryer/3").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is (200));
    }

    @Test
    public void T08_UpdateHairColorQuantityByName(){
        JsonObject color = Json.createObjectBuilder()
                .add("name","Schwarzkopf")
                .add("color","Ultraviolet")
                .add("isSemiPermanent",true)
                .build();

        Response response = target.path("haircolor/Schwarzkopf/10").request(MediaType.APPLICATION_JSON).put(Entity.json(color));
        assertThat(response.getStatus(),is(200));

        JsonObject hc = response.readEntity(JsonObject.class);
        assertThat(hc.getInt("quantity"), is(10));
    }

    @Test
    public void T09_DeleteBarberShopByName(){

        Response response = target.path("barbershop/Schnittzone").request().delete();
        assertThat(response.getStatus(),is(200));

        response = this.target.path("barbershop").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));

        JsonArray arr = response.readEntity(JsonArray.class);
        assertThat(arr.size(),is(2));
    }

    @Test
    public void T10_PostBlowdryer(){
        JsonObject blowdryer = Json.createObjectBuilder()
                .add("name","Braun")
                .add("quantity",5)
                .add("hasCoolShot",true)
                .add("heatSettings", 2)
                .build();

        Response response = target.path("blowdryer").request().post(Entity.json(blowdryer));
        assertThat(response.getStatus(),is(200));

        response = this.target.path("blowdryer").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));

        JsonArray arr = response.readEntity(JsonArray.class);
        assertThat(arr.size(),is(2));
    }

        /*
    @Test
    public void T01_GetBlowdryers(){
        Response response = target.path("blowdryer").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
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

     //   Response response= target.path("/customer/Tom").request().delete();
    //    assertThat(response.getStatus(), is(200));
    }
    @Test
    public void T05_GetServices(){
        Response response = target.path("service").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void T06_Employees(){
        Response response = target.path("employee").request().get();
        assertThat(response.getStatus(), is(200));
    }
    */

}
