package com.stee.datamanagement.service;

import com.stee.datamanagement.repository.LampInfoDao;
import com.stee.datamanagement.repository.ThresholdInfoDao;
import com.stee.sel.lfm.ElectricAlertInfo;
import com.stee.sel.lfm.UsageAlertInfo;
import com.stee.sel.lim.LampInfo;
import com.stee.sel.ocm.EnergyManagementConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * File Name    : EPTTrackingService.java
 * Package Name : com.stee.ept.service
 * Author       : chenshaoyin
 * Created      : 2016年12月13日 ---- 下午2:51:23
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service
public class EPTTrackingService {
	
	@Autowired
	private LampInfoDao lampInfoDao;
	
	@Autowired
	private ThresholdInfoDao thresholdInfoDao;
	
	@Autowired
	private AlertToLFMService alertToLFMService;
	
	public void getAndTracking(){
		
		//get all lampinfo now
		List<LampInfo> lampList = lampInfoDao.findAll();
		
		//get all threshold now
		List<EnergyManagementConfiguration> thresholdInfo = thresholdInfoDao.findAll();
		Map<String,EnergyManagementConfiguration> usageThresholdMap =
				new HashMap<String,EnergyManagementConfiguration>();
		Map<String,EnergyManagementConfiguration> electricThresholdMap =
				new HashMap<String,EnergyManagementConfiguration>();
		
		for(EnergyManagementConfiguration emc:thresholdInfo ){
			if(emc.getName()!=null&&!"".equals(emc.getName())&&"Energy Usage".equals(emc.getType())){
				usageThresholdMap.put(emc.getName(), emc);
			}
			if(emc.getName()!=null&&!"".equals(emc.getName())&&"Electrical Parameters".equals(emc.getType())){
				electricThresholdMap.put(emc.getName(), emc);
			}
		}
		
		List<UsageAlertInfo> usageAlertList = new ArrayList<UsageAlertInfo>();
		List<ElectricAlertInfo> electricAlertList = new ArrayList<ElectricAlertInfo>();
		
		//polling parameter
		for(LampInfo lamp:lampList){
			//Usage
			if(usageThresholdMap.containsKey(lamp.getModuleId())){
				//checkUsage
				System.out.println("ID:"+lamp.getId()+"---ModelID:"+lamp.getModuleId()+"---checkUsage");
				EnergyManagementConfiguration emc  =usageThresholdMap.get(lamp.getModuleId());
				
				UsageAlertInfo info  =UsageCheckService.CheckLampUsage(emc, lamp);
				
				if(info!=null){
					usageAlertList.add(info);
				}
				
				
			}
			//electric
			if(electricThresholdMap.containsKey(lamp.getModuleId())){
				//checkElectricalParameters
				System.out.println("ID:"+lamp.getId()+"---ModelID:"+lamp.getModuleId()+"---checkElectricalParameters");
				EnergyManagementConfiguration emc  = electricThresholdMap.get(lamp.getModuleId());
				
				ElectricAlertInfo info = ElectricCheckService.CheckLampParameter(emc, lamp);
				
				if(info!=null){
					electricAlertList.add(info);
				}
			}
		}
		
		if(usageAlertList.size()>0){
			System.out.println("usageAlertList'size is "+usageAlertList.size());
			alertToLFMService.saveAndAlertUsage(usageAlertList);
		}
		if(electricAlertList.size()>0){
			System.out.println("electricAlertList'size is "+electricAlertList.size());
			alertToLFMService.saveAndAlertElectric(electricAlertList);
		}
		
		
		
		
	}
	

}
