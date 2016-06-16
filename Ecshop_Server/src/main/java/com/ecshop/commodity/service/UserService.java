package com.ecshop.commodity.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecshop.commodity.dao.UserDao;
import com.ecshop.utils.PageData;

/**
 * 
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="1048688506@qq.com">Yang</a>
 * @version  Date: 2016年6月16日 下午4:46:30
 * @serial 1.0
 * @since 2016年6月16日 下午4:46:30
 */
@Service("userService")
public class UserService {
	@Autowired
	private UserDao dao ;
	public PageData findUserInfo(PageData pd) {
		return dao.findUserInfo(pd);
	}

}
