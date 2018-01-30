package com.stee.datamanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

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
 * File Name    : LampUseageInfo.java
 * Package Name : com.stee.ept.entity
 * Author       : chenshaoyin
 * Created      : 2016年12月1日 ---- 下午3:15:08
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Entity
@Table(name="STL_EPT_USAGE_ALERT")
public class UsageAlertInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "MODELID")
	private String modelId;
	
	@Column(name = "GEOZONEID")
	private String geozoneId;
	
	@Column(name = "GROUPID")
	private String dimmingGroupId;
	
	@Column(name = "USEENERGY")
	private Double usage;
	
	@Column(name = "USEPERCENTAGE")
	private Integer usePercentage;
	
	@Column(name = "TYPE")
	private Integer severityLevel;
	
	@Column(name = "ALERTMSG")
	private String alertMsg;
	
	@Column(name = "UPDATETIME")
	private Date updateTime;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getGeozoneId() {
		return geozoneId;
	}

	public void setGeozoneId(String geozoneId) {
		this.geozoneId = geozoneId;
	}

	public String getDimmingGroupId() {
		return dimmingGroupId;
	}

	public void setDimmingGroupId(String dimmingGroupId) {
		this.dimmingGroupId = dimmingGroupId;
	}

	public Double getUsage() {
		return usage;
	}

	public void setUsage(Double usage) {
		this.usage = usage;
	}

	public Integer getUsePercentage() {
		return usePercentage;
	}

	public void setUsePercentage(Integer usePercentage) {
		this.usePercentage = usePercentage;
	}

	

	public Integer getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(Integer severityLevel) {
		this.severityLevel = severityLevel;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	@Override
	public String toString() {
		return "UsageAlertInfo [id=" + id + ", modelId=" + modelId
				+ ", geozoneId=" + geozoneId + ", dimmingGroupId="
				+ dimmingGroupId + ", usage=" + usage + ", usePercentage="
				+ usePercentage + ", severityLevel=" + severityLevel
				+ ", alertMsg=" + alertMsg + ", updateTime=" + updateTime + "]";
	}

}
