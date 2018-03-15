package com.stee.datamanagement.service;

import com.stee.sel.report.DeviceDataEntity;

import java.util.List;

public interface IEptTrackingService {
    void getAndTracking(List<DeviceDataEntity> deviceDataList);
}
