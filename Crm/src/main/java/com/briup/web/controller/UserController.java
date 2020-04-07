package com.briup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.service.IRoleService;
import com.briup.service.IUserService;

/** 
* @author 作者 zjf
* @version 创建时间：2020年3月26日 下午4:32:00 
* 类说明 : 
*/
@Controller
public class UserController {
	
	@Autowired
	private IUserService service;
	@Autowired
	private IRoleService roleService;
	
	//获取前台输入的用户名密码进行校验
	@PostMapping("/user/login")
	@ResponseBody
	public String login(String name,String password,HttpSession session) {
		//用户名密码判空处理前台已经做了
		
		//调用service层,判断用户名和密码是否正确
		User user = service.findByName(name);
		if (user==null) {
			return "当前用户不存在";
		}
		if (!user.getPassword().equals(password)) {
			return "密码错误";
		}
		if (user.getFlag()!=1) { //判断用户状态 1为正常 2为已被注销
			return "该用户已被注销";
		}
		//将数据保存在session范围中
		session.setAttribute("user", user);
		return "success";
	}
	
	//退出功能
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	//显示用户管理的界面
	@RequestMapping("toUser")
	public String toUser(HttpSession session,Integer roleId) {
		session.setAttribute("roleId", roleId);
		Page<User> users = service.findUsersByRole(roleId);
		session.setAttribute("users", users);
		
		List<Role> allRoles = roleService.allRoles();
		session.setAttribute("allRoles", allRoles);
		return "pages/user";
	}
	
	@RequestMapping(value = "saveUser",method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(User user) {
		if (user.getId()!=null) {
			service.saveUser(user);
			return "修改成功";
		}else {
			service.saveUser(user);
			return "保存成功";
		}
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody
	public String deleteUser(Integer id) {
		service.deleteUserById(id);
		return "删除成功";
	}
	
	//分页查询
	@RequestMapping("updateUserPage")
	public String userPage(Integer pageIndex,HttpSession session) {
		Integer roleId = (Integer)session.getAttribute("roleId");
		Page<User> users = service.findUsersByRole(roleId, pageIndex);
		session.setAttribute("users", users);
		return "pages/user";
	}
	
	//重置
	@RequestMapping("resetUser")
	@ResponseBody
	public String resetUser(HttpSession session) {
		session.removeAttribute("roleId");
		return "重置成功";
	}
	
	@RequestMapping(value = "user/{id}",method = RequestMethod.GET)
	@ResponseBody
	public User selectUserById(@PathVariable Integer id) {
		return service.findUserById(id);
	}
}
