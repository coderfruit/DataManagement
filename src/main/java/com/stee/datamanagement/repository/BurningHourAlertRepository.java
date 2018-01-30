package com.stee.datamanagement.repository;

import com.stee.sel.lfm.BurningHourAlert;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_BHCP
 * File Name    : BurningHourAlertRepository.java
 * Author       : Jerry
 * Created      : 2016年12月1日 下午3:59:51
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public interface BurningHourAlertRepository
		extends PagingAndSortingRepository<BurningHourAlert, Integer>, JpaSpecificationExecutor<BurningHourAlert> {
}
