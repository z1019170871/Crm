package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.bean.User;

/** 
* @author 作者 zjf
* @version 创建时间：2020年3月26日 下午3:30:08 
* 类说明 : 用来映射thymeleaf页面的controller类
*/

@Controller
public class ViewController {
	
	@RequestMapping("login")
	public String toLoginPage() {
		//返回逻辑视图名
		return "login";
	}
	
	@RequestMapping("index")
	public String toIndexPage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user==null) {
			return "login";
		}
		return "index";
	}
}
