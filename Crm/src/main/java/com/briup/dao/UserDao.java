package com.briup.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.briup.bean.Role;
import com.briup.bean.User;

/** 
* @author 作者 zjf
* @version 创建时间：2020年3月26日 下午4:53:47 
* 说明 
*/
public interface UserDao extends JpaRepository<User, Integer>{
	User findByName(String name);
	
	Page<User> findByRole(Role role,Pageable page);
	
	//根据roleId查询User
	List<User> findByRoleId(Integer roleId);
	
	@Modifying
	@Transactional
	@Query("delete from User as u where u.id=?1")
	void deleteUserById(Integer id);
}
