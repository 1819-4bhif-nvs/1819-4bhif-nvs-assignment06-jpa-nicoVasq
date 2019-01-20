package at.htl.barbershop.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "BarberShop.findAll", query = "select b from BarberShop b"),
        @NamedQuery(name = "BarberShop.findByName", query = "select b from BarberShop b where b.name = :NAME")
})
public class BarberShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonbTransient
    @OneToMany(mappedBy = "barberShop",cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Equipment> equipment;

    //region Constructor
    public BarberShop() {
    }

    public BarberShop(String name) {
        this.name = name;
        employees = new LinkedList<>();
        equipment = new LinkedList<>();
    }
    //endregion


    //region Getter Setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
    //endregion

    public void AddEmployee(Employee emp){
        employees.add(emp);
        emp.setBarberShop(this);
    }
    public void AddEquipment(Equipment eq){
        equipment.add(eq);
    }
}
