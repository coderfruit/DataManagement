package com.stee.datamanagement.repository;

import com.stee.datamanagement.entity.DeviceAlarmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface DeviceAlarmsRepository extends JpaRepository<DeviceAlarmsEntity, String> {

}
