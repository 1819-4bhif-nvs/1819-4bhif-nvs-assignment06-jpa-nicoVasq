package at.htl.barbershop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer c"),
        @NamedQuery(name = "Customer.findByName", query = "select c from Customer c where c.name = :NAME")
})
public class Customer extends Person{

    private LocalDate lastVisited;

    //region Constructor
    public Customer() {
    }

    public Customer(String name, LocalDate lastVisited) {
        super(name);
        this.lastVisited = lastVisited;
    }
    //endregion

    //region Getter Setter
    public LocalDate getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(LocalDate lastVisited) {
        this.lastVisited = lastVisited;
    }
    //endregion
}
