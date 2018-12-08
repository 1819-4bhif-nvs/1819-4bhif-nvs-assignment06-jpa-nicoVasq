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
    public InitBean() {}
    @PostConstruct
    private void init() {

        Blowdryer blowdryer = new Blowdryer("Maxwell",2 , 3, true);
        em.persist(blowdryer);

            Haircolor haircolor = new Haircolor("Schwarzkopf", 5 ,"Ultraviolet", true);
        em.persist(haircolor);

        Customer customer = new Customer("Tom", LocalDateTime.now().minusDays(6L));
        Customer customer2 = new Customer("Max", LocalDateTime.now().minusDays(20L));
        em.persist(customer);
        em.persist(customer2);

        Regular regular = new Regular("Alex",LocalDateTime.now().minusYears(1L),34L);
        em.persist(regular);
    }
}
