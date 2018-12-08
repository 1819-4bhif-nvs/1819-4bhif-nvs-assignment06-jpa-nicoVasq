package at.htl.barbershop.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Blowdryer.findAll", query = "select b from Blowdryer b"),
        @NamedQuery(name = "Blowdryer.findBySettings", query = "select b from Blowdryer b where b.heatSettings = :SETTINGS")
})
public class Blowdryer extends Equipment{

    private int heatSettings;
    private boolean hasCoolShot;


    //region Constructor
    public Blowdryer() {
    }

    public Blowdryer(String name, Integer quantity, int heatSettings, boolean hasCoolShot) {
        super(name, quantity);
        this.heatSettings = heatSettings;
        this.hasCoolShot = hasCoolShot;
    }
    //endregion


    //region Getter Setter
    public int getHeatSettings() {
        return heatSettings;
    }

    public void setHeatSettings(int heatSettings) {
        this.heatSettings = heatSettings;
    }

    public boolean isHasCoolShot() {
        return hasCoolShot;
    }

    public void setHasCoolShot(boolean hasCoolShot) {
        this.hasCoolShot = hasCoolShot;
    }
    //endregion
}
