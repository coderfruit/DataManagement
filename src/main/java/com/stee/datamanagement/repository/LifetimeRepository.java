package com.stee.datamanagement.repository;

import com.stee.sel.asm.LifetimeTrackingConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jerry on 2016/12/11.
 */
public interface LifetimeRepository extends JpaRepository<LifetimeTrackingConfig, Integer> {
    LifetimeTrackingConfig findByLuminaireId(String id);
}
