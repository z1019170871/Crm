package com.briup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Role;
import com.briup.service.IRoleService;

/** 
* @author 作者 zjf
* @version 创建时间：2020年3月27日 上午11:50:43 
* 说明 : 角色管理模块
*/
@Controller
public class RoleController {
	
	@Autowired
	private IRoleService service;
	
	@RequestMapping("toRole")
	public String toRole(HttpSession session) {
		//将数据库中所有角色信息查询出来,保存到session中
		Page<Role> roles = service.findAllRoles();
		session.setAttribute("roles", roles);
		return "pages/role";
	}
	
	@RequestMapping("saveRole")
	@ResponseBody
	public String saveRole(Role role) {
		if(role.getId()!=null) {
			service.saveOrUpdateRole(role);
			return "修改成功";
		}else {
			service.saveOrUpdateRole(role);
			return "保存成功"; 
		}
	}
	
	@RequestMapping("deleteRole")
	@ResponseBody
	public String deleteRole(Integer id) {
		service.deleteRole(id);
		return "删除成功";
	}
	
	@RequestMapping("pageRole")
	public String updatePageRole(Integer pageIndex,HttpSession session) {
		Page<Role> roles = service.findAllRoles(pageIndex);
		session.setAttribute("roles", roles);
		return "pages/role";
	}
	
	@RequestMapping("findRoleById")
	@ResponseBody
	public Role findRoleById(Integer id) {
		return service.findRoleById(id);
	}
	
	@RequestMapping("allRoles")
	@ResponseBody
	public List<Role> allRoles() {
		List<Role> list = service.allRoles();
		return list;
	}
	
	@RequestMapping("findRoleByName")
	@ResponseBody
	public Role findRoleByName(String name) {
		Role role = service.findRoleByName(name);
		return role;
	}
}
