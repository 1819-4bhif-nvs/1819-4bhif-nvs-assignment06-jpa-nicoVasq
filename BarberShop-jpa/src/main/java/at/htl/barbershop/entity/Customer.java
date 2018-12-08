package at.htl.barbershop.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.time.LocalDateTime;

@Entity
@NamedQuery(name = "Customer.findByName", query = "select c from Customer c where c.name = :NAME")
public class Customer extends Client{

    private LocalDateTime lastVisited;

    //region Constructor
    public Customer() {
    }

    public Customer(String name, LocalDateTime lastVisited) {
        super(name);
        this.lastVisited = lastVisited;
    }
    //endregion


    //region Getter Setter
    public LocalDateTime getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(LocalDateTime lastVisited) {
        this.lastVisited = lastVisited;
    }
    //endregion
}
