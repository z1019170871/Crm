package com.briup.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Chance;
import com.briup.bean.User;
import com.briup.service.IChanceService;
import com.briup.service.IUserService;

/** 
* @author 作者 zjf
* @version 创建时间：2020年4月1日 下午12:45:35 
* 说明 
*/
@Controller
public class ChanceController {
	@Autowired
	private IChanceService service;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("toChance")
	public String toChance(HttpSession session,String customer,String address) {
		session.setAttribute("customer", customer);
		session.setAttribute("address", address);
		User user = (User)session.getAttribute("user");
		//分页查询出第一页的所有商机
		Page<Chance> chances = service.findChances(user.getId(),0, customer, address);
		session.setAttribute("chances", chances);
		
		//查出所有经理(经理的角色id为4)
		List<User> allManagers = userService.findByRoleId(4);
		session.setAttribute("allManagers", allManagers);
		
		//查出所有地区
		List<String> allAddress = service.findAllAddress();
		session.setAttribute("allAddress", allAddress);
		return "pages/sales";
	}
	
	@RequestMapping("updateChancePage")
	public String updateChancePage(HttpSession session,Integer pageIndex) {
		String customer = (String)session.getAttribute("customer");
		String address = (String)session.getAttribute("address");
		User user = (User)session.getAttribute("user");
		Page<Chance> chances = service.findChances(user.getId(),pageIndex, customer, address);
		session.setAttribute("chances", chances);
		return "pages/sales";
	}
	
	//重置
	@RequestMapping("resetChance")
	@ResponseBody
	public String resetChance(HttpSession session) {
		session.removeAttribute("customer");
		session.removeAttribute("address");
		return "重置成功";
	}
	
	//新增和修改
	@RequestMapping("saveChance")
	@ResponseBody
	public String saveChance(Chance chance) {
		if (chance.getId()!=null) {
			service.saveChance(chance);
			return "修改成功";
		}else {
			service.saveChance(chance);
			return "保存成功";
		}
	}
	
	//根据id返回一个chance对象
	@RequestMapping(value = "chance/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Chance selectUserById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@RequestMapping("deleteChance")
	@ResponseBody
	public String deleteChance(Integer id) {
		service.deleteById(id);
		return "删除成功";
	}
}
