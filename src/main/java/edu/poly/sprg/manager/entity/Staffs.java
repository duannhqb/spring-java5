package edu.poly.sprg.manager.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Staffs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean gender;
    private Date birthDay;
    private String photo;
    private String email;
    private String phone;
    private int salary;
    private String notes;

    //    nhiều nhân viên thuộc về một phòng ban
    @ManyToOne
    @JoinColumn(name = "departId")
    private Departs departs;

    @OneToMany(mappedBy = "staffs", fetch = FetchType.LAZY)
    private List<Records> recordsList;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setDeparts(Departs departs) {
        this.departs = departs;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public Departs getDeparts() {
        return departs;
    }

    public int getSalary() {
        return salary;
    }

    public String getEmail() {
        return email;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setRecordsList(List<Records> recordsList) {
        this.recordsList = recordsList;
    }

    public List<Records> getRecordsList() {
        return recordsList;
    }

    public boolean isGender() {
        return gender;
    }

    public Staffs() {

    }
}
