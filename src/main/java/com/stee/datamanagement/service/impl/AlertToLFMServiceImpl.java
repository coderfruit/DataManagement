package com.stee.datamanagement.service.impl;

import com.stee.datamanagement.entity.DeviceAlarmsEntity;
import com.stee.datamanagement.service.IAlertToLFMService;
import com.stee.datamanagement.service.IAlertToLFMService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AlertToLFMServiceImpl implements IAlertToLFMService {
    private RestTemplate restTemplate;

    @Value("${urlForLFM}")
    private String urlForLFM;

    @Value("${alarmEvent}")
    private String usageEvent;


    @Override
    public void saveAndAlertUsage(List<DeviceAlarmsEntity> info) {
        String response=restTemplate.postForObject(urlForLFM+usageEvent, info, String.class);
    }

}
