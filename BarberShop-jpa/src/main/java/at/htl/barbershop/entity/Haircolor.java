package at.htl.barbershop.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Haircolor.findByName", query = "select hc from Haircolor hc where hc.name = :NAME")
public class Haircolor extends Equipment {

    private String color;
    private boolean isSemiPermanent;

    //region Constructor
    public Haircolor() {
    }

    public Haircolor(String name, Integer quantity, String color, boolean isSemiPermanent) {
        super(name, quantity);
        this.color = color;
        this.isSemiPermanent = isSemiPermanent;
    }
    //endregion


    //region Getter Setter
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSemiPermanent() {
        return isSemiPermanent;
    }

    public void setSemiPermanent(boolean semiPermanent) {
        isSemiPermanent = semiPermanent;
    }
    //endregion
}
