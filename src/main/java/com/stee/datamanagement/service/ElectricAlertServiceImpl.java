package com.stee.datamanagement.service;

import com.stee.datamanagement.entity.AlertParam;
import com.stee.datamanagement.entity.ElectricAlertInfo;
import com.stee.datamanagement.util.QueryBean;
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
import java.util.Date;
import java.util.List;

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
 * File Name    : ElectricAlertServiceImpl.java
 * Package Name : com.stee.ept.service
 * Author       : chenshaoyin
 * Created      : 2016年12月2日 ---- 下午3:11:48
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Component
public class ElectricAlertServiceImpl implements IElectricAlertService{
	
	
	@Override
	public List<ElectricAlertInfo> getTestAll() {
		// TODO Auto-generated method stub
		ElectricAlertInfo eai = new ElectricAlertInfo();
		eai.setId(1);
		eai.setAlertPara(AlertParam.ACTIVE_POWER);
		eai.setRatio(100);
		eai.setCurrentValue(220.0);
		eai.setSeverityLevel(2);
		eai.setUpdateTime(new Date());
//		electricAlertDao.save(eai);
//		return electricAlertDao.findAll();
		return null;
	}



	@Override
	public Page<ElectricAlertInfo> getElectricAlertInfo(final QueryBean term,
			int page, int size) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "trackingType");
		Pageable  pageable = new PageRequest(page,size,sort);
		
		Specification<ElectricAlertInfo> spe=new Specification<ElectricAlertInfo>(){

			@Override
			public Predicate toPredicate(Root<ElectricAlertInfo> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate idLike = null;
				if(term!=null&&term.getId()!=null &&!"".equals(term.getId().trim())){
					idLike = cb.like(root.<String>get("id"), "%"+term.getId().trim()+"%");
					System.out.println("where id like"+term.getId().trim());
				}
				Predicate paraLike = null;
				if(term!=null&&term.getAlertPara()!=null &&!"".equals(term.getAlertPara().trim())){
					paraLike = cb.like(root.<String>get("alertPara"), "%"+term.getAlertPara().trim()+"%");
					System.out.println("where alertPara like"+term.getAlertPara().trim());
				}
				Predicate perBetween =null;
				if(term.getStart()>=0&&term.getEnd()>=0&&term.getStart()<term.getEnd()){
					perBetween = cb.between(root.<Integer>get("ratio"), term.getStart(), term.getEnd());
				}
//				if(idLike!=null) query.where(idLike);
//				if(paraLike!=null) query.where(paraLike);
//				if(perBetween!=null) query.where(perBetween);
				query.where(idLike,paraLike,perBetween);
				return null;
			}
			
		};
		
//		Page<ElectricAlertInfo> pageResult = electricAlertDao.findAll(spe, pageable);
		
		return null;
	}

}
