package com.stee.datamanagement.repository;

import com.stee.sel.inventory.AlarmThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public interface AlarmThresholdRepository extends JpaRepository<AlarmThreshold, Integer> {

    AlarmThreshold findByAlarmThresholdId(Integer id);

    List<AlarmThreshold> findByDataType(String dataType);
}
