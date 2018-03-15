package com.stee.datamanagement.repository;

import com.stee.sel.inventory.AlarmThreshold;
import com.stee.sel.report.DeviceDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface DeviceDataEntityRepository extends JpaRepository<DeviceDataEntity, Integer> {


}
