package com.briup.service;
/** 
* @author 作者 zjf
* @version 创建时间：2020年4月1日 下午1:17:43 
* 说明 
*/

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.bean.Chance;

public interface IChanceService {
	//根据条件查询当前主管创建的商机
	Page<Chance> findChances(Integer creator_id,Integer pageIndex,String customer,String address);
	
	//查询所有地区
	List<String> findAllAddress();
	
	//保存商机
	void saveChance(Chance chance);
	
	//根据id查询
	Chance findById(Integer id);
	
	//删除商机
	void deleteById(Integer id);
}
