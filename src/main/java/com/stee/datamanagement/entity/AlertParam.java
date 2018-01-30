package com.stee.datamanagement.entity;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_SEL
 * File Name    : AlertParam.java
 * Author       : Jerry
 * Created      : 2016年12月8日 下午2:34:39
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public enum AlertParam {
	CURRENT("Current"), VOLTAGE("Voltage"), POWER_FACTOR("PowerFactor"), REACTIVE_POWER("ReactivePower"), ACTIVE_POWER(
			"ActivePower");

	private AlertParam(String type) {
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}
}
