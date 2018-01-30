package com.stee.datamanagement.util;
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
 * File Name    : QueryBean.java
 * Package Name : com.stee.ept.util
 * Author       : chenshaoyin
 * Created      : 2016年12月5日 ---- 上午11:38:27
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public class QueryBean {
	
	private Integer start;
	private Integer end;
	private String id;
	private String alertPara;
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlertPara() {
		return alertPara;
	}
	public void setAlertPara(String alertPara) {
		this.alertPara = alertPara;
	}
	public QueryBean(Integer start, Integer end, String id, String alertPara) {
		super();
		this.start = start;
		this.end = end;
		this.id = id;
		this.alertPara = alertPara;
	}
	public QueryBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "QueryBean [start=" + start + ", end=" + end + ", id=" + id
				+ ", alertPara=" + alertPara + "]";
	}
	
	
	
	

}
