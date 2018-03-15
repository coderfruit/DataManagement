package com.stee.datamanagement.repository;

import com.stee.sel.inventory.DeviceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Jerry on 2016/12/11.
 */
public interface LampInfoRepository extends JpaRepository<DeviceInfoEntity, String>,JpaSpecificationExecutor<DeviceInfoEntity> {
    List<DeviceInfoEntity> findByGeozoneId(Integer geoZoneId);
}
