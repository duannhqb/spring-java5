package edu.poly.sprg.manager.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class StaffsDTO {
	private int id;
	private String name;
	private boolean gender;
	private Date birthDay;
	private String photo;
	private String email;
	private String phone;
	private int salary;
	private MultipartFile file;
	private String notes;
	private DepartsDTO departs;
	private List<RecordsDTO> recordsList;

	public StaffsDTO() {

	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRecordsList(List<RecordsDTO> recordsList) {
		this.recordsList = recordsList;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDeparts(DepartsDTO departs) {
		this.departs = departs;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getPhoto() {
		return photo;
	}

	public String getNotes() {
		return notes;
	}

	public String getEmail() {
		return email;
	}

	public int getSalary() {
		return salary;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public DepartsDTO getDeparts() {
		return departs;
	}

	public List<RecordsDTO> getRecordsList() {
		return recordsList;
	}

	public boolean isGender() {
		return gender;
	}

}
