package com.ecshop.commodity.model;


/**
 * 用户模块实体类
 * @author RaoJin
 *
 */
public class Collect {

	private int user_id;
	private String type;
	private int ltem_id;
	private String keyword;
	private int add_time;

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLtem_id() {
		return ltem_id;
	}
	public void setLtem_id(int ltem_id) {
		this.ltem_id = ltem_id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getAdd_time() {
		return add_time;
	}
	public void setAdd_time(int add_time) {
		this.add_time = add_time;
	}

}
