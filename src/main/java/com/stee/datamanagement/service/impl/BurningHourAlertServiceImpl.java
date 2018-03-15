package com.stee.datamanagement.service.impl;

import com.google.gson.Gson;
import com.stee.datamanagement.entity.DeviceAlarmsEntity;
import com.stee.datamanagement.entity.QueryBean;
import com.stee.datamanagement.repository.*;
import com.stee.datamanagement.service.IBurningHourAlertService;
import com.stee.sel.inventory.AlarmThreshold;
import com.stee.sel.inventory.DeviceInfoEntity;
import com.stee.sel.inventory.DeviceModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.NumberFormat;
import java.util.ArrayList;
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
 * File Name    : BurningHourAlertServiceImpl.java
 * Author       : Jerry
 * Created      : 2016年12月1日 下午4:13:37
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service
public class BurningHourAlertServiceImpl implements IBurningHourAlertService {


	@Autowired
    LampInfoRepository lampRepo;

	@Autowired
    private DeviceModelDao deviceModelDao;
    @Autowired
    private AlarmThresholdRepository alarmThresholdRepository;
    @Autowired
    private DeviceAlarmsRepository deviceAlarmsRepository;
    @Autowired
	private SimpMessagingTemplate template;

	@Override
	public Page<DeviceAlarmsEntity> getAlertPage(QueryBean query, Integer pageNo, Integer pageSize, String direction) {

//		PageRequest request = new PageRequest(pageNo, pageSize,
//				new Sort(direction.equals("DESC") ? Direction.DESC : Direction.ASC, "ratio"));
//		Page<DeviceAlarms> page = null;
//		if (null == query) {
//			page = this.repository.findAll(request);
//		} else {
//			page = this.repository.findAll(where(query.getModuleId(), query.getBeginning(), query.getEnding()),
//					request);
//		}
//		return page;
		return null;
	}

	@Override
	public void computeBurningHourAlert() {
		List<DeviceInfoEntity> lamps = lampRepo.findAll();
        if (null != lamps && !lamps.isEmpty()) {
			DeviceAlarmsEntity burningHourAlertSet = new DeviceAlarmsEntity();
            lamps.forEach(lampInfo -> {
                Integer burningHour = lampInfo.getBurningHour();
                String moduleId = lampInfo.getDeviceModelId();
                DeviceModelEntity lifetimeConfig = deviceModelDao.findByDeviceModelId(moduleId);
                Integer lifetime = lifetimeConfig.getLifeTime();
                //这个地方dataType暂定为burningHour
                List<AlarmThreshold> thresholds = alarmThresholdRepository.findByDataType("BurningHour");
                double percent = (double) burningHour / (double) lifetime;
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(0);
				Double ratio = Double.valueOf(numberFormat.format(percent * 100));
                if (ratio>thresholds.get(0).getThresholdValue()){
                    //给DeviceAlarms的属性赋值,未完成
					burningHourAlertSet.setSource(lampInfo.getDeviceId());
                    burningHourAlertSet.setCreateTime(new java.util.Date());
                    burningHourAlertSet.setSeverityLevel(thresholds.get(0).getThresholdLevel());
                    burningHourAlertSet.setDescription(thresholds.get(0).getDescription());
                    deviceAlarmsRepository.save(burningHourAlertSet);
					// 通过websocket将报警信息推送给前端
					Gson gson=new Gson();
					template.convertAndSend("/datamanagement", gson.toJson(burningHourAlertSet));
                }
//                for (int i = 0; i < thresholds.size(); i++) {
//                    ThresholdsOfLifetime threshold = thresholds.get(i);
//                    String threshold1 = threshold.getThreshold();
//                    if (ratio >= Integer.valueOf(threshold1) && thresholds.size() > 1) {
//                        continue;
//                    } else {
//                        ThresholdsOfLifetime thresholdsOfLifetime = thresholds.get(thresholds.size() > 1 ? i - 1 : i);
//                        BurningHourAlert burningHourAlert = new BurningHourAlert();
//                        burningHourAlert.setAlertMsg(thresholdsOfLifetime.getAlertMsg());
//                        burningHourAlert.setBurningHour(burningHour);
//                        burningHourAlert.setLuminaireId(lampInfo.getId());
//                        burningHourAlert.setRatio(ratio);
//                        burningHourAlert.setTrackingType(lifetimeConfig.getTrackingEntity());
//                        burningHourAlert.setSeverityLevel(thresholdsOfLifetime.getSeverityLevel());
//                        burningHourAlertSet.add(burningHourAlert);
//                    }
//                }
            });

//            if (null != burningHourAlertSet && !burningHourAlertSet.isEmpty()) {
//                repository.save(burningHourAlertSet);
//            }
        }

    }

	private Specification<DeviceAlarmsEntity> where(final String moduleId, final Integer start, final Integer end) {
		return new Specification<DeviceAlarmsEntity>() {

			@Override
			public Predicate toPredicate(Root<DeviceAlarmsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (null != moduleId && !moduleId.equals("")) {
					predicates.add(cb.like(root.<String>get("luminaireId"), moduleId));
				}
				if (null != start && null != end && (start <= end)) {
					predicates.add(cb.between(root.<Integer>get("ratio"), start, end));
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
			}
		};
	}


}
