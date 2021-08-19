package model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "employee")
@NamedQueries({
        @NamedQuery(name = "com.miramontes.EmployeeModel.findAll", query = "SELECT e FROM EmployeeModel e"),
        @NamedQuery(name = "com.miramontes.EmployeeModel.findById", query = "SELECT e FROM EmployeeModel e WHERE e.emp_id = :emp_id")
})
public class EmployeeModel {

    @Id
    @Column(name = "emp_id")
    @NotNull
    private Integer emp_id;

    @Column(name = "name")
    @NotBlank @Length(min=2, max=255)
    private String name;

    @Column(name = "lastname")
    @NotBlank @Length(min=2, max=255)
    private String lastname;

    @Column(name = "email")
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;

    @Column(name = "country")
    @NotBlank @Length(min=2, max=255)
    private String country;

    @Column(name = "position")
    @NotBlank @Length(min=2, max=255)
    private String position;

    public EmployeeModel() {
    }

    public EmployeeModel(Integer emp_id, String name, String lastname, String email, String country, String position) {
        this.emp_id = emp_id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.country = country;
        this.position = position;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
