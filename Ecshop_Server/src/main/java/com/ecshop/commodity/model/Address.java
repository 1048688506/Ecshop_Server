package com.ecshop.commodity.model;


/**
 * 用户模块实体类
 * @author RaoJin
 *
 */
public class Address {

	private int addr_id;
	private int user_id;
	private String consignee;
	private int region_id;
	private String region_name;
	private String address;
	private int zipcode;
	private String phone_tel;
	private String phone_mob;
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhone_tel() {
		return phone_tel;
	}
	public void setPhone_tel(String phone_tel) {
		this.phone_tel = phone_tel;
	}
	public String getPhone_mob() {
		return phone_mob;
	}
	public void setPhone_mob(String phone_mob) {
		this.phone_mob = phone_mob;
	}
	

	
	
}
