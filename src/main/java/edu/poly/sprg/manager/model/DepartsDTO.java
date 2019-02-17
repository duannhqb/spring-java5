package edu.poly.sprg.manager.model;

import java.util.List;

public class DepartsDTO {
	private int id;
	private String name;
	private List<StaffsDTO> staffsList;

	public void setId(int id) {
		this.id = id;
	}

	public DepartsDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public DepartsDTO() {

	}

	public void setStaffsList(List<StaffsDTO> staffsList) {
		this.staffsList = staffsList;
	}

	public List<StaffsDTO> getStaffsList() {
		return staffsList;
	}
}
