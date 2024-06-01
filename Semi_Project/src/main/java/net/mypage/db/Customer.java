package net.mypage.db;

public class Customer {
	private String id;
	private String password;
	private String name;
	private String jumin;
	private String gender;
	private String post;
	private String address;
	private String address_detail;
	private String phone;
	private String email;
	private String register_date;	
	private String grade;	
	private int secondhand_code;
	
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getAddress_detail() {
		return address_detail;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getSecondhand_code() {
		return secondhand_code;
	}
	public void setSecondhand_code(int secondhand_code) {
		this.secondhand_code = secondhand_code;
	}		
	
	
}
