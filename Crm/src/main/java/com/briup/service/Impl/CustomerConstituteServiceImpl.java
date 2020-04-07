package com.briup.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Customer;
import com.briup.bean.CustomerConstitute;
import com.briup.dao.CustomerDao;
import com.briup.service.ICustomerConstituteService;

/** 
* @author 作者 zjf
* @version 创建时间：2020年4月3日 上午10:44:33 
* 说明 
*/
@Service
public class CustomerConstituteServiceImpl implements ICustomerConstituteService{
	
	@Autowired
	private CustomerDao dao;

	@Override
	public List<CustomerConstitute> regionAnalyze() {
		//从数据库将所有客户信息查找出来
		float nums = dao.findAll().size();
		ArrayList<CustomerConstitute> list = new ArrayList<>();
		String[] regions = {"华中","华南","华东","华北"};
		//然后根据地区进行筛选,然后封装成List自定义对象
		for (String region : regions) {
			float size = dao.findByRegion(region).size();
			float percent = size/nums*100;
			CustomerConstitute constitute = new CustomerConstitute(region, percent, null);
			list.add(constitute);
		}
		return list;
	}

	@Override
	public List<CustomerConstitute> levelAnalyze() {
		//从数据库将所有客户信息查找出来
		float nums = dao.findAll().size();
		ArrayList<CustomerConstitute> list = new ArrayList<>();
		String[] levels = {"普通客户","大客户","重点开发客户","合作伙伴"};
		//然后根据等级进行筛选,然后封装成List自定义对象
		for (String level : levels) {
			float size = dao.findByLevel(level).size();
			float percent = size/nums*100;
			CustomerConstitute constitute = new CustomerConstitute(level, percent, null);
			list.add(constitute);
		}
		return list;
	}

}
