package com.stee.datamanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.security.auth.message.config.AuthConfigFactory;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
 * Project Name : STL_EPT
 * File Name    : EPTManagementController.java
 * Package Name : com.stee.ept
 * Author       : chenshaoyin
 * Created      : 2016年12月1日 ---- 下午2:34:10
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *
 */
import org.springframework.web.client.RestTemplate;

import com.stee.datamanagement.entity.ElectricAlertInfo;
import com.stee.datamanagement.service.AlertToLFMService;
import com.stee.datamanagement.service.IElectricAlertService;
import com.stee.datamanagement.service.IUsageAlertService;
import com.stee.datamanagement.util.QueryBean;
import com.stee.sel.lfm.UsageAlertInfo;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.stee.sel","com.stee.datamanagement"})
//@EnableJpaRepositories({"com.stee.sel.ocm","com.stee.sel.lim","com.stee.ept"})
//@EntityScan({"com.stee.sel.ocm","com.stee.sel.lim","com.stee.datamanagement","com.stee.sel.lfm"})
public class DatamanagementApplication {


	public static void main(String[] args) {

		SpringApplication.run(DatamanagementApplication.class, args);
	}
}
