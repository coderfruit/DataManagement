package com.stee.datamanagement.service;

import com.stee.sel.lfm.ElectricAlertInfo;
import com.stee.sel.lim.LampInfo;
import com.stee.sel.lim.status.LampStatus;
import com.stee.sel.ocm.EnergyManagementConfiguration;
import com.stee.sel.ocm.Threshold;

import java.util.Set;

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
 * File Name    : ElectricCheckService.java
 * Package Name : com.stee.ept.service
 * Author       : chenshaoyin
 * Created      : 2016年12月14日 ---- 下午2:03:00
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public class ElectricCheckService {
	
	public static ElectricAlertInfo CheckLampParameter(EnergyManagementConfiguration emc, LampInfo lamp){
		
		boolean isCanCheck = false;
		if(emc.getThresholds()!=null
				&&emc.getThresholds().size()>0
				&&lamp.getLampStatus()!=null){
			isCanCheck = true;
		}
		if(isCanCheck){
			Set<Threshold> thresholdSet=emc.getThresholds();
			LampStatus lampStatus = lamp.getLampStatus();
			return null;
		}else{
			return null;
		}
	}
}
