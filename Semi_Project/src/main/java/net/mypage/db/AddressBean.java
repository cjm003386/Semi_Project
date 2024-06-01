package net.mypage.db;

public class AddressBean {
	private int addresslist_num;
	private String address_id;
	private String address_name;
	private String address_receiver;
	private int address_post;
	private String address_phone;
	private String address1;
	private String address2;
	public int getAddresslist_num() {
		return addresslist_num;
	}
	public void setAddresslist_num(int addresslist_num) {
		this.addresslist_num = addresslist_num;
	}
	
	public int getAddress_post() {
		return address_post;
	}
	public void setAddress_post(int address_post) {
		this.address_post = address_post;
	}
	
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public String getAddress_receiver() {
		return address_receiver;
	}
	public void setAddress_receiver(String address_receiver) {
		this.address_receiver = address_receiver;
	}
	public String getAddress_phone() {
		return address_phone;
	}
	public void setAddress_phone(String address_phone) {
		this.address_phone = address_phone;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address) {
		this.address1 = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
}
