package com.stee.datamanagement.service;

import com.stee.sel.common.ResultData;
import com.stee.sel.lfm.UsageAlertInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_EPT
 * File Name    : UsageAlertServiceImpl.java
 * Package Name : com.stee.ept.service
 * Author       : chenshaoyin
 * Created      : 2016年12月1日 ---- 下午4:15:27
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Component
public class UsageAlertServiceImpl implements IUsageAlertService{
	

	@Override
	public Page<UsageAlertInfo> getUsageAlertInfo(final String id, int start, int end, int page,
                                                  int size) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "trackingType");
		Pageable  pageable = new PageRequest(page,size,sort);
		
//		Page<UsageAlertInfo> pageResult = usageAlertDao.findAll(pageable);
		Specification<UsageAlertInfo> spe=new Specification<UsageAlertInfo>(){

			@Override
			public Predicate toPredicate(Root<UsageAlertInfo> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate idLike = null;
				if(id!=null &&!"".equals(id)){
					idLike = cb.like(root.<String>get("id"), "%"+id+"%");
				}
//				Predicate perBetween =null;
//				if(start>0&&end>0&&start<end){
//					perBetween = cb.between(root.get("usePercentage"), start, end);
//				}
				if(idLike!=null) query.where(idLike);
//				if(perBetween!=null) query.where(perBetween);
				return null;
			}
			
		};
		
//		Page<UsageAlertInfo> pageResult = usageAlertDao.findAll(spe, pageable);
		
//		Page<UsageAlertInfo> pageResult = usageAlertDao.findByIdLike("%"+"t2"+"%",pageable);
//		ResultData<UsageAlertInfo> result = new ResultData<UsageAlertInfo>();
		
//		result.setStatus("000000");
		
		return null;
		
//		return null;
	}

	@Override
	public ResultData<UsageAlertInfo> getUsageAlertInfoByPer(int page,
                                                             int size, int beginPer, int endPer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData<UsageAlertInfo> getUsageAlertInfoByChar(int page,
                                                              int size, String check) {
		// TODO Auto-generated method stub
		return null;
	}

}
