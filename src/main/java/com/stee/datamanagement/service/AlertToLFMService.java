package com.stee.datamanagement.service;

import com.stee.sel.lfm.ElectricAlertInfo;
import com.stee.sel.lfm.UsageAlertInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
 * File Name    : AlertToLFMService.java
 * Package Name : com.stee.ept.service
 * Author       : chenshaoyin
 * Created      : 2016年12月14日 ---- 下午2:51:02
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service
public class AlertToLFMService {
	
	private RestTemplate restTemplate;
	
	@Value("${urlForLFM}")
	private String urlForLFM;
	
	@Value("${usageEvent}")
	private String usageEvent;
	
	@Value("${electricEvent}")
	private String electricEvent;
	
	public void saveAndAlertUsage(List<UsageAlertInfo> info){
		System.out.println("the access path is "+urlForLFM+usageEvent);
		restTemplate = new RestTemplate();
		String response=restTemplate.postForObject(urlForLFM+usageEvent, info, String.class);
		System.out.println("saveAndAlertUsage------the response is--"+response);
	}
	
	public void saveAndAlertElectric(List<ElectricAlertInfo> info){
		System.out.println("the access path is "+urlForLFM+electricEvent);
		restTemplate = new RestTemplate();
		String response=restTemplate.postForObject(urlForLFM+electricEvent, info, String.class);
		System.out.println("saveAndAlertElectric------the response is--"+response);
	}

}
