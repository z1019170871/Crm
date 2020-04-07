package com.briup.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.bean.Customer;

/** 
* @author 作者 zjf
* @version 创建时间：2020年4月3日 上午10:45:56 
* 说明 
*/
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	//客户管理相关内容
	
	//数据分析相关内容
	List<Customer> findByRegion(String region);
	List<Customer> findByLevel(String level);
	
	
}
