package edu.poly.sprg.manager.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean type;
    private String reason;
    private Date date;

    //    Một nhân viên có nhiều đánh giá, nhiều đánh giá thuộc một nhân viên
    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staffs staffs;

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStaffs(Staffs staffs) {
        this.staffs = staffs;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Staffs getStaffs() {
        return staffs;
    }

    public String getReason() {
        return reason;
    }

    public boolean isType() {
        return type;
    }

    public Records() {
    }
}
