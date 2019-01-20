package at.htl.barbershop.business;

import at.htl.barbershop.entity.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Startup
@Singleton

public class InitBean {
    @PersistenceContext
    private EntityManager em;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public InitBean() {}
    @PostConstruct
    private void init() {
        loadData();
    }

    private void loadData(){

        BarberShop shop = new BarberShop("Klipp");
        BarberShop shop2 = new BarberShop("DM");
        BarberShop shop3 = new BarberShop("Schnittzone");
        em.persist(shop);
        em.persist(shop2);
        em.persist(shop3);

        Blowdryer blowdryer = new Blowdryer("Maxwell",7 , 3, true);
        em.persist(blowdryer);

        Haircolor haircolor = new Haircolor("Schwarzkopf", 5 ,"Ultraviolet", true);
        Haircolor haircolor2 = new Haircolor("Loreal", 20 ,"Red", true);
        Haircolor haircolor3 = new Haircolor("Syoss", 12 ,"Blue", true);
        em.persist(haircolor);
        em.persist(haircolor2);
        em.persist(haircolor3);

        shop.AddEquipment(blowdryer);
        shop.AddEquipment(haircolor);
        shop.AddEquipment(haircolor2);
        shop.AddEquipment(haircolor3);

        Customer customer = new Customer("Tom", LocalDate.parse("01.06.2018",dtf));
        Customer customer2 = new Customer("Max", LocalDate.parse("23.06.2018",dtf));
        em.persist(customer);
        em.persist(customer2);

        Employee employee = new Employee("Sophie",1200, shop);
        Employee employee2 = new Employee("Dianna",1205, shop);
        Service service = new Service(customer, employee, "Cut Hair",20, customer.getLastVisited());
        Service service2 = new Service(customer2, employee2, "Dye Hair", 50, customer2.getLastVisited());
        em.persist(employee);
        em.persist(employee2);
        em.persist(service);
        em.persist(service2);

    }
}
