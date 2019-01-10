package at.htl.barbershop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e")
})
public class Employee extends Person{

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Service> serviceList;
    private double salary;

    //region Constructor
    public Employee() {
    }

    public Employee(double salary) {
        this.salary = salary;
    }
    //endregion

    //region Setter Getter

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
    //endregion
}
