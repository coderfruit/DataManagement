package com.stee.datamanagement.repository;

import com.stee.sel.inventory.DeviceModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceModelDao extends JpaRepository<DeviceModelEntity,String>,JpaSpecificationExecutor<DeviceModelEntity> {
        Page<DeviceModelEntity> findAll(Specification specification, Pageable pageable);
        DeviceModelEntity findByDeviceModelId(String id);

}
