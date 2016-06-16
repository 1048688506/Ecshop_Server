package com.ecshop.commodity.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ecshop.utils.PageData;

/**
 * 
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="1048688506@qq.com">Yang</a>
 * @version  Date: 2016年6月16日 下午5:03:44
 * @serial 1.0
 * @since 2016年6月16日 下午5:03:44
 */
@Repository
public interface UserDao {

	public PageData findUserInfo(PageData pd) ;
}
