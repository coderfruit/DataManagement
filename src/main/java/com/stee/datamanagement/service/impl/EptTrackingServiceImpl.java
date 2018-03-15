package com.stee.datamanagement.service.impl;

import com.stee.datamanagement.entity.DeviceAlarmsEntity;
import com.stee.datamanagement.entity.ElectricAlertInfo;
import com.stee.datamanagement.entity.UsageAlertInfo;
import com.stee.datamanagement.repository.AlarmThresholdRepository;
import com.stee.datamanagement.repository.DeviceDataEntityRepository;
import com.stee.datamanagement.service.IAlertToLFMService;
import com.stee.datamanagement.service.IEptTrackingService;
import com.stee.sel.inventory.AlarmThreshold;
import com.stee.sel.inventory.DeviceInfoEntity;
import com.stee.sel.report.DeviceDataEntity;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EptTrackingServiceImpl implements IEptTrackingService {

    @Autowired
    private AlarmThresholdRepository thresholdInfoDao;

    @Autowired
    private IAlertToLFMService alertToLFMService;

    @Autowired
    private DeviceDataEntityRepository deviceDataEntityRepository;

    @Override
    public void getAndTracking(List<DeviceDataEntity> deviceDataList) {
        //入库
        try{
            deviceDataEntityRepository.save(deviceDataList);
        } catch (Exception e){
            throw new ServiceException("save deviceDataEntity failed !!! error message:"+e.getMessage());
        }
        //energy usage和electric parameter tracking
        //查询所有阈值
        List<AlarmThreshold> usageThresholds = thresholdInfoDao.findByDataType("EnergyUsage");
        List<AlarmThreshold> currentThresholds = thresholdInfoDao.findByDataType("Current");
        List<AlarmThreshold> voltageThresholds = thresholdInfoDao.findByDataType("Voltage");
        List<AlarmThreshold> activePowerThresholds = thresholdInfoDao.findByDataType("ActivePower");
        List<AlarmThreshold> reactivePowerThresholds = thresholdInfoDao.findByDataType("ReactivePower");
        List<AlarmThreshold> apparentPowerThresholds = thresholdInfoDao.findByDataType("ApparentPower");
        //创建发送给STL_Alarm的参数
        List<DeviceAlarmsEntity> usageAlertList=new ArrayList<>(deviceDataList.size());
        List<DeviceAlarmsEntity> electricAlertList=new ArrayList<>(deviceDataList.size());

        deviceDataList.forEach(deviceDataEntity -> {
            if (1==deviceDataEntity.getDataType()){
                compare(usageThresholds,deviceDataEntity,usageAlertList);
            }else if (3==deviceDataEntity.getDataType()){
                compare(currentThresholds,deviceDataEntity,electricAlertList);
            }else if (4==deviceDataEntity.getDataType()){
                compare(voltageThresholds,deviceDataEntity,electricAlertList);
            }else if (5==deviceDataEntity.getDataType()){
                compare(activePowerThresholds,deviceDataEntity,electricAlertList);
            }else if (6==deviceDataEntity.getDataType()){
                compare(reactivePowerThresholds,deviceDataEntity,electricAlertList);
            }else if (9==deviceDataEntity.getDataType()){
                compare(apparentPowerThresholds,deviceDataEntity,electricAlertList);
            }
        });

        if(usageAlertList.size()>0){
            alertToLFMService.saveAndAlertUsage(usageAlertList);
        }
        if(electricAlertList.size()>0){
            alertToLFMService.saveAndAlertUsage(electricAlertList);
        }
    }

    public DeviceAlarmsEntity createDeviceAlarm(Date createTime,String description,Integer level,String source){
        DeviceAlarmsEntity deviceAlarmsEntity = new DeviceAlarmsEntity();
        deviceAlarmsEntity.setCreateTime(createTime);
        deviceAlarmsEntity.setDescription(description);
        deviceAlarmsEntity.setSeverityLevel(level);
        deviceAlarmsEntity.setSource(source);
        return deviceAlarmsEntity;
    }

    //比较相关数据是否超过阈值
    public void compare(List<AlarmThreshold> Thresholds, DeviceDataEntity deviceDataEntity, List<DeviceAlarmsEntity> AlertList){
        Thresholds.forEach(alarmThreshold -> {
            if ("greater than".equals(alarmThreshold.getThresholdRule())){
                if (deviceDataEntity.getData()>alarmThreshold.getThresholdValue()){
                    DeviceAlarmsEntity deviceAlarm = createDeviceAlarm(new Date(), alarmThreshold.getDescription(),
                            alarmThreshold.getThresholdLevel(), deviceDataEntity.getDeviceId());
                    AlertList.add(deviceAlarm);
                }
            }else if ("less than".equals(alarmThreshold.getThresholdRule())){
                if (deviceDataEntity.getData()<alarmThreshold.getThresholdValue()){
                    DeviceAlarmsEntity deviceAlarm = createDeviceAlarm(new Date(), alarmThreshold.getDescription(),
                            alarmThreshold.getThresholdLevel(), deviceDataEntity.getDeviceId());
                    AlertList.add(deviceAlarm);
                }
            }else{
                int compare = deviceDataEntity.getData().compareTo(alarmThreshold.getThresholdValue());
                if (0==compare){
                    DeviceAlarmsEntity deviceAlarm = createDeviceAlarm(new Date(),alarmThreshold.getDescription(),alarmThreshold.getThresholdLevel(),
                            deviceDataEntity.getDeviceId());
                    AlertList.add(deviceAlarm);
                }
            }
        });
    }
}
