package com.briup.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.briup.bean.Chance;

/** 
* @author 作者 zjf
* @version 创建时间：2020年4月1日 下午1:06:43 
* 说明 
*/
public interface ChanceDao extends JpaRepository<Chance, Integer>{
	//根据客户名和地址查询当前主管创建的商机
	@Query(value = "select * from chance where creator_id=?1 and customer like %?2% and address=?3",nativeQuery = true)
	Page<Chance> findByCustomerAndAddress(Integer creator_id,String customer,String address,Pageable page);
	
	//根据客户名查询当前主管创建的商机
	@Query(value = "select * from chance where creator_id=?1 and customer like %?2%",nativeQuery = true)
	Page<Chance> findByCustomer(Integer creator_id,String customer,Pageable page);
	
	//根据地址查询当前主管创建的商机
	@Query(value = "select * from chance where creator_id=?1 and address=?2",nativeQuery = true)
	Page<Chance> findByAddress(Integer creator_id,String address,Pageable page);
	
	//查询当前主管创建的商机
	Page<Chance> findByCreator_id(Integer creator_id,Pageable page);
	
	//查询商机表里的所有地区
	@Query(value = "select distinct address from chance",nativeQuery = true)
	List<String> findAllAddress();
}
