package com.stee.datamanagement.controller;

import com.stee.datamanagement.service.IBurningHourAlertService;
import com.stee.datamanagement.service.impl.EptTrackingServiceImpl;
import com.stee.sel.report.DeviceDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * Project Name : STL_BHCP
 * File Name    : BurningHourAlertController.java
 * Author       : Jerry
 * Created      : 2016年12月1日 下午3:56:55
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@RestController
@RequestMapping(value = "/ept")
public class EptTrackingController {
	@Autowired
	private EptTrackingServiceImpl service;


	/**
	 * 将STL_Interface传过来的数据进行入库，并对energy usage和electric parameter进行判断，
	 * 是否需要进行报警，如需报警，将报警信息发送给STL_Alarm模块
	 * @param deviceDataList
	 */
    @RequestMapping(path = "/tracking",method = RequestMethod.POST)
	public void receiveMsg (List<DeviceDataEntity> deviceDataList) {
			service.getAndTracking(deviceDataList);
	}

}
