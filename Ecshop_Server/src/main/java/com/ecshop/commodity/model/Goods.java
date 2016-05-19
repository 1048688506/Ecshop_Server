package com.ecshop.commodity.model;


/**
 * 用户模块实体类
 * @author RaoJin
 *
 */
public class Goods {
    private int goods_id;//商品id
    private int store_id;//店铺id
    private String store_name;//店铺名
    private String owner_name;//店主名
    
    private String type;//商品类型
    private String goods_name;//商品名称
    private String description;//商品描述
    private int cate_id;//商品分类所属id
    private int cate1_id;//一级分类
    private int cate2_id;//二级分类
    private String cate_name;	//商品分类名称 
    private int spec_qty;//商品数量 
    private String default_image;//商品图片
    
    
    public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	public int getCate1_id() {
		return cate1_id;
	}
	public void setCate1_id(int cate1_id) {
		this.cate1_id = cate1_id;
	}
	public int getCate2_id() {
		return cate2_id;
	}
	public void setCate2_id(int cate2_id) {
		this.cate2_id = cate2_id;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public int getSpec_qty() {
		return spec_qty;
	}
	public void setSpec_qty(int spec_qty) {
		this.spec_qty = spec_qty;
	}
	public String getDefault_image() {
		return default_image;
	}
	public void setDefault_image(String default_image) {
		this.default_image = default_image;
	}
	
}
