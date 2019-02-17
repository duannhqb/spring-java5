package edu.poly.sprg.manager.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Departs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    //    Một phòng ban có nhiều nhân viên
    @OneToMany(mappedBy = "departs", fetch = FetchType.LAZY)
    private List<Staffs> staffsList;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Departs() {

    }

	public Departs(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setStaffsList(List<Staffs> staffsList) {
        this.staffsList = staffsList;
    }

    public List<Staffs> getStaffsList() {
        return staffsList;
    }
}
