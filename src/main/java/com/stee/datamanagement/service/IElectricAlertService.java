package com.stee.datamanagement.service;

import com.stee.datamanagement.entity.ElectricAlertInfo;
import com.stee.datamanagement.util.QueryBean;
import org.springframework.data.domain.Page;

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
 * File Name    : IElectricAlertService.java
 * Package Name : com.stee.ept.service
 * Author       : chenshaoyin
 * Created      : 2016年12月2日 ---- 下午3:11:29
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public interface IElectricAlertService {
	
	public Page<ElectricAlertInfo> getElectricAlertInfo(QueryBean term, int page, int size);
	
	public List<ElectricAlertInfo> getTestAll();

}
