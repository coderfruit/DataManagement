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
 * Project Name : STL_BHCP
 * File Name    : QueryBean.java
 * Author       : Jerry
 * Created      : 2016年12月1日 下午5:06:28
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public class QueryBean {
	private Integer beginning;
	private Integer ending;
	private String moduleId;

	public Integer getBeginning() {
		return beginning;
	}

	public void setBeginning(Integer beginning) {
		this.beginning = beginning;
	}

	public Integer getEnding() {
		return ending;
	}

	public void setEnding(Integer ending) {
		this.ending = ending;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Override
	public String toString() {
		return "QueryBean [beginning=" + beginning + ", ending=" + ending + ", moduleId=" + moduleId + "]";
	}

}
