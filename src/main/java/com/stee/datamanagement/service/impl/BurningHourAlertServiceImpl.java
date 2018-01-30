package com.stee.datamanagement.service.impl;

import com.google.common.collect.Sets;
import com.stee.sel.asm.LifetimeTrackingConfig;
import com.stee.sel.asm.ThresholdsOfLifetime;
import com.stee.sel.lfm.BurningHourAlert;
import com.stee.sel.lim.LampInfo;
import com.stee.sel.lim.status.LampStatus;
import com.stee.datamanagement.entity.QueryBean;
import com.stee.datamanagement.repository.BurningHourAlertRepository;
import com.stee.datamanagement.repository.LampInfoRepository;
import com.stee.datamanagement.repository.LifetimeRepository;
import com.stee.datamanagement.service.IBurningHourAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	BurningHourAlertRepository repository;

	@Autowired
    LampInfoRepository lampRepo;

	@Autowired
    LifetimeRepository lifetimeRepo;

	@Override
	public Page<BurningHourAlert> getAlertPage(QueryBean query, Integer pageNo, Integer pageSize, String direction) {

		PageRequest request = new PageRequest(pageNo, pageSize,
				new Sort(direction.equals("DESC") ? Direction.DESC : Direction.ASC, "ratio"));
		Page<BurningHourAlert> page = null;
		if (null == query) {
			page = this.repository.findAll(request);
		} else {
			page = this.repository.findAll(where(query.getModuleId(), query.getBeginning(), query.getEnding()),
					request);
		}
		return page;
	}

	@Override
	public void computeBurningHourAlert() {
		List<LampInfo> lamps = lampRepo.findAll();
        if (null != lamps && !lamps.isEmpty()) {
            Set<BurningHourAlert> burningHourAlertSet = Sets.newHashSet();
            lamps.forEach(lampInfo -> {
                LampStatus lampStatus = lampInfo.getLampStatus();
                Integer burningHour = lampStatus.getBurningHour();
                String moduleId = lampInfo.getModuleId();
                LifetimeTrackingConfig lifetimeConfig = lifetimeRepo.findByLuminaireId(moduleId);
                Integer lifetime = lifetimeConfig.getLifetime();
                List<ThresholdsOfLifetime> thresholds = lifetimeConfig.getThresholds();
                double percent = (double) burningHour / (double) lifetime;
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(0);
                Integer ratio = Integer.valueOf(numberFormat.format(percent * 100));
                for (int i = 0; i < thresholds.size(); i++) {
                    ThresholdsOfLifetime threshold = thresholds.get(i);
                    String threshold1 = threshold.getThreshold();
                    if (ratio >= Integer.valueOf(threshold1) && thresholds.size() > 1) {
                        continue;
                    } else {
                        ThresholdsOfLifetime thresholdsOfLifetime = thresholds.get(thresholds.size() > 1 ? i - 1 : i);
                        BurningHourAlert burningHourAlert = new BurningHourAlert();
                        burningHourAlert.setAlertMsg(thresholdsOfLifetime.getAlertMsg());
                        burningHourAlert.setBurningHour(burningHour);
                        burningHourAlert.setLuminaireId(lampInfo.getId());
                        burningHourAlert.setRatio(ratio);
                        burningHourAlert.setTrackingType(lifetimeConfig.getTrackingEntity());
                        burningHourAlert.setSeverityLevel(thresholdsOfLifetime.getSeverityLevel());
                        burningHourAlertSet.add(burningHourAlert);
                    }
                }
            });
            if (null != burningHourAlertSet && !burningHourAlertSet.isEmpty()) {
                repository.save(burningHourAlertSet);
            }
        }
        // TODO: 2016/12/14 后需主动推送Alert至前端进行显示
    }

	private Specification<BurningHourAlert> where(final String moduleId, final Integer start, final Integer end) {
		return new Specification<BurningHourAlert>() {

			@Override
			public Predicate toPredicate(Root<BurningHourAlert> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
