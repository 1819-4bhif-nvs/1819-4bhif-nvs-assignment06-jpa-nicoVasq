package at.htl.barbershop.entity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Regular extends Client{

    private LocalDateTime regularSince;
    private Long visitCount;


    //region Constructor
    public Regular() {
    }

    public Regular(String name, LocalDateTime regularSince, Long visitCount) {
        super(name);
        this.regularSince = regularSince;
        this.visitCount = visitCount;
    }
    //endregion


    //region Getter Setter
    public LocalDateTime getRegularSince() {
        return regularSince;
    }

    public void setRegularSince(LocalDateTime regularSince) {
        this.regularSince = regularSince;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }
    //endregion
}
