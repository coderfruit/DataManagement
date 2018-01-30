package com.stee.datamanagement.service;

import com.stee.sel.common.ResultData;
import com.stee.sel.lfm.UsageAlertInfo;
import org.springframework.data.domain.Page;

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
 * File Name    : IUsageAlertService.java
 * Package Name : com.stee.ept.service
 * Author       : chenshaoyin
 * Created      : 2016年12月1日 ---- 下午3:55:08
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public interface IUsageAlertService {
	
	public Page<UsageAlertInfo> getUsageAlertInfo(String id, int start, int end, int page, int size);

	public ResultData<UsageAlertInfo> getUsageAlertInfoByPer(int page, int size, int beginPer, int endPer);

	public ResultData<UsageAlertInfo> getUsageAlertInfoByChar(int page, int size, String check);
	
	

}
