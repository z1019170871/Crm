package com.briup.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Chance;
import com.briup.dao.ChanceDao;
import com.briup.service.IChanceService;

/** 
* @author 作者 zjf
* @version 创建时间：2020年4月1日 下午1:20:26 
* 说明 
*/
@Service
public class ChanceServiceImpl implements IChanceService{
	@Autowired
	private ChanceDao dao;

	@Override
	public Page<Chance> findChances(Integer creator_id,Integer pageIndex, String customer, String address) {
		customer = "".equals(customer)?null:customer;
		if (customer!=null && address!=null) {
			return dao.findByCustomerAndAddress(creator_id,customer, address, PageRequest.of(pageIndex, 1));
		}else if (customer!=null && address==null) {
			return dao.findByCustomer(creator_id,customer, PageRequest.of(pageIndex, 1));
		}else if (customer==null && address!=null) {
			return dao.findByAddress(creator_id,address, PageRequest.of(pageIndex, 1));
		}else {
			return dao.findByCreator_id(creator_id, PageRequest.of(pageIndex, 1));
		}
	}

	@Override
	public List<String> findAllAddress() {
		return dao.findAllAddress();
	}

	@Override
	public void saveChance(Chance chance) {
		chance.setStatus("待处理");
		dao.save(chance);
	}

	@Override
	public Chance findById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

}
