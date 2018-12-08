package at.htl.barbershop.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class Equipment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;

    //region Constuctor
    public Equipment() {
    }

    public Equipment(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    //endregion


    //region Getter Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    //endregion
}
