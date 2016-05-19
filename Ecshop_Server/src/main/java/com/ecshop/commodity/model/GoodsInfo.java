package com.ecshop.commodity.model;


/**
 * 用户模块实体类
 * @author RaoJin
 *
 */
public class GoodsInfo {
    private int goods_id;//商品id ecm_goods
    private int store_id;//店铺id ecm_goods

    private String goods_name;//商品名称 ecm_goods
    private String description;//商品描述 ecm_goods
    private float price;//商品价钱 ecm_goods
    private int spec_qty;//属性的数量数目 ecm_goods
    private String spe_name_1;//第一属性名 ecm_goods
    private String spe_name_2;//第二属性名  ecm_goods
    
    private int Stock;//规格表ecm_goods_spec 库存数量
    private String thumbnail;//缩略图地址 ecm_goods_image
    private int Sales;//被售出的次数 ecm_goods_statistics
    
    
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getSpec_qty() {
		return spec_qty;
	}
	public void setSpec_qty(int spec_qty) {
		this.spec_qty = spec_qty;
	}
	public String getSpe_name_1() {
		return spe_name_1;
	}
	public void setSpe_name_1(String spe_name_1) {
		this.spe_name_1 = spe_name_1;
	}
	public String getSpe_name_2() {
		return spe_name_2;
	}
	public void setSpe_name_2(String spe_name_2) {
		this.spe_name_2 = spe_name_2;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getSales() {
		return Sales;
	}
	public void setSales(int sales) {
		Sales = sales;
	}
	

}
