package com.briup.service;
/** 
* @author 作者 zjf
* @version 创建时间：2020年3月26日 下午4:47:39 
* 说明 
*/

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.bean.User;

public interface IUserService {
	User findByName(String name);
	
	User findUserById(Integer id);
	
	//保存
	void saveUser(User user);
	
	//删除
	void deleteUserById(Integer id);
	
	//查询首页User信息
	Page<User> findUsersByRole(Integer roleId);
	//根据分页查询User
	Page<User> findUsersByRole(Integer roleId,Integer pageIndex);
	
	//根据role_id查询user
	List<User> findByRoleId(Integer roleId);
}
