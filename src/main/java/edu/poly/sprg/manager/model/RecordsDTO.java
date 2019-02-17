package edu.poly.sprg.manager.model;

import java.util.Date;

public class RecordsDTO {
    private int id;
    private boolean type;
    private String reason;
    private Date date;
    private StaffsDTO staffs;

    public RecordsDTO(){

    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setStaffs(StaffsDTO staffs) {
        this.staffs = staffs;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public StaffsDTO getStaffs() {
        return staffs;
    }

    public RecordsDTO(int id, boolean type, String reason, Date date, StaffsDTO staffs) {
        this.id = id;
        this.type = type;
        this.reason = reason;
        this.date = date;
        this.staffs = staffs;
    }
}
