package com.stee.datamanagement.service;

import com.stee.datamanagement.entity.DeviceAlarmsEntity;

import java.util.List;

public interface IAlertToLFMService {
     void saveAndAlertUsage(List<DeviceAlarmsEntity> info);

}
