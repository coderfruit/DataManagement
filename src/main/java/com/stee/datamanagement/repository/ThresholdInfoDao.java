package com.stee.datamanagement.repository;

import com.stee.sel.ocm.EnergyManagementConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
 * File Name    : ThresholdInfoDao.java
 * Package Name : com.stee.ept.dao
 * Author       : chenshaoyin
 * Created      : 2016年12月13日 ---- 下午4:55:31
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Repository
public interface ThresholdInfoDao extends JpaRepository<EnergyManagementConfiguration,Integer>{

}
