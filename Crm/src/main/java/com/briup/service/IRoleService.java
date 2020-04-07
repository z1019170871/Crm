package com.briup.service;
/** 
* @author 作者 zjf
* @version 创建时间：2020年3月27日 下午2:34:21 
* 说明 
*/

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.bean.Role;

public interface IRoleService {
	//查询刚进入角色管理模块的所有角色
	Page<Role> findAllRoles();
	
	//查询指定页上的数据信息
	Page<Role> findAllRoles(Integer pageIndex);
	
	//通过id找到对应的role
	Role findRoleById(Integer id);
	
	//新增或修改角色
	void saveOrUpdateRole(Role role);
	
	//删除角色
	void deleteRole(Integer id);
	
	//查询所有角色信息
	List<Role> allRoles();
	
	//根据角色名查询角色
	Role findRoleByName(String name);
}
