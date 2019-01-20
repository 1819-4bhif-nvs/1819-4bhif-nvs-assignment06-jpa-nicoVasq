package at.htl.barbershop.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Service.findAll", query = "select s from Service s")
})
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Employee employee;
    private String serviceType;
    private double price;
    private LocalDate date;

    //region Constructor
    public Service() {
    }

    public Service(Customer customer, String serviceType, double price, LocalDate date) {
        this.serviceType = serviceType;
        this.price = price;
        this.date = date;
    }

    public Service(Customer customer, Employee employee, String serviceType, double price, LocalDate date) {
        this.customer = customer;
        this.employee = employee;
        employee.AddServiceToList(this);
        this.serviceType = serviceType;
        this.price = price;
        this.date = date;
    }
    //endregion

    //region Getter Setter
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    //endregion

}
