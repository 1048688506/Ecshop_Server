package com.ecshop.commodity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecshop.utils.PageData;

@Repository
public interface CartDao {

	void saveCart(PageData pd);

	List<PageData> getCartByCartId(PageData pd);

}
