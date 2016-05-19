package com.ecshop.commodity.model;


/**
 * 用户模块实体类
 * @author RaoJin
 *
 */
public class Gcategory {
	
	private int cateId; //类别id
	private int storeId; //商家id   
	private String cateName; //分类名称
	private int parentId; //分类父级id
	private int sortOrder;  //排序号
	private int idShow; //是否显示
	
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getIdShow() {
		return idShow;
	}
	public void setIdShow(int idShow) {
		this.idShow = idShow;
	}
}
