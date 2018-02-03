package com.stee.datamanagement.entity;

import javax.persistence.*;
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
 * File Name    : ElectricAlertInfo.java
 * Package Name : com.stee.ept.entity
 * Author       : chenshaoyin
 * Created      : 2016年12月2日 ---- 下午1:51:11
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Entity
@Table(name = "STL_LFM_ELECTRIC_ALERT")
public class ElectricAlertInfo {

	private Integer id;

	private String luminaireId;

	private AlertParam alertPara;

	private Double currentValue;

	private Integer ratio;

	private Integer severityLevel;

	private String alertMsg;

	private Date updateTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "luminaire_id")
	public String getLuminaireId() {
		return luminaireId;
	}

	public void setLuminaireId(String luminaireId) {
		this.luminaireId = luminaireId;
	}

	@Column(name = "alert_para")
	@Enumerated(EnumType.STRING)
	public AlertParam getAlertPara() {
		return alertPara;
	}

	public void setAlertPara(AlertParam alertPara) {
		this.alertPara = alertPara;
	}

	@Column(name = "current_value")
	public Double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}

	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}

	@Column(name = "severity_level")
	public Integer getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(Integer severityLevel) {
		this.severityLevel = severityLevel;
	}

	@Column(name = "alert_msg")
	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	@Column(name = "update_time")
	@Temporal(TemporalType.DATE)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ElectricAlertInfo [id=" + id + ", luminaireId=" + luminaireId + ", alertPara=" + alertPara
				+ ", currentValue=" + currentValue + ", ratio=" + ratio + ", severityLevel=" + severityLevel
				+ ", alertMsg=" + alertMsg + ", updateTime=" + updateTime + "]";
	}

}
