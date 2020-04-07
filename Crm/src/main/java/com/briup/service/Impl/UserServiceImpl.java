package com.briup.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.dao.UserDao;
import com.briup.service.IUserService;

/** 
* @author 作者 zjf
* @version 创建时间：2020年3月26日 下午4:51:07 
* 说明 
*/
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserDao userDao;

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}


	@Override
	public User findUserById(Integer id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void deleteUserById(Integer id) {
		userDao.deleteUserById(id);
	}


	@Override
	public Page<User> findUsersByRole(Integer roleId,Integer pageIndex) {
		if (roleId==null) {  //没有角色条件的筛选
			return userDao.findAll(PageRequest.of(pageIndex, 3));
		}else {  //根据角色条件筛选
			Role role = new Role();
			role.setId(roleId);
			return userDao.findByRole(role,PageRequest.of(pageIndex, 3));
		}
	}

	@Override
	public Page<User> findUsersByRole(Integer roleId) {
		return findUsersByRole(roleId, 0);
	}


	@Override
	public List<User> findByRoleId(Integer roleId) {
		return userDao.findByRoleId(roleId);
	}

}
