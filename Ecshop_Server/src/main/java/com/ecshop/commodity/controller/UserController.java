package com.ecshop.commodity.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ecshop.commodity.service.UserService;
import com.ecshop.utils.MD5;
import com.ecshop.utils.PageData;
import com.ecshop.utils.ResponseUtil;

/**
 * 
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="1048688506@qq.com">Yang</a>
 * @version  Date: 2016年6月16日 下午4:44:07
 * @serial 1.0
 * @since 2016年6月16日 下午4:44:07
 */
@Controller
@RequestMapping(value="/appuer")
public class UserController {
	@Autowired
	private UserService userService ;
	
		
	/**
	 * 用户登录，验证用户名密码
	 * @param request
	 * @param response
	 * @param username 用户名必填
	 * @param password 密码必填写
	 * @return
	 * @throws Exception
	  * 请求返回参数描述：
	 * result : 02 请求服务器成功
	 * result : 03  请求传递参数不正确
	 * result : 01 用户名密码不正确
	 * result : 04 服务器异常
	 * @throws Exception 
	 */
	@RequestMapping("/login")
	public String  userLogin(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="password" ,required=false) String password ) throws Exception{
		PageData pd = new PageData() ;
		PageData pageData = new PageData() ;
		JSONObject result = new JSONObject() ;
		if(username!=null&&username!=""&&password!=null&&password!=""){
			pd.put("username",username);
			pd.put("password",MD5.md5(password) ) ;
			try {
				pageData = userService.findUserInfo(pd);
				
				if(!pageData.isEmpty()){
					result.put("result", "02");//验证成功
					result.put("userinfo", pageData);
				}else{
					
					result.put("result", "01");//用户名密码不正确
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.put("result", "04");//服务器异常
			}
			
		
		}else{
			result.put("result", "03");//参数不合法
		}
		
		ResponseUtil.write(response, result) ;
		return null ;
		
		
	}
	

}
