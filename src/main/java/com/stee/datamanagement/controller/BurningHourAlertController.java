package com.stee.datamanagement.controller;

import com.stee.datamanagement.service.IBurningHourAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value = "/burninghour/alert")
public class BurningHourAlertController {
	@Autowired
	IBurningHourAlertService service;

	@JmsListener(destination = "rolling.status")
	public void receiveMsg (boolean flag) {
		if (flag) {
			service.computeBurningHourAlert();
		}
	}

}
