package com.yunang.fangda.business.carNumber.jpa;

import com.yunang.fangda.business.carNumber.model.CarNumberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface CarNumberJpa extends JpaSpecificationExecutor<CarNumberModel>,
        JpaRepository<CarNumberModel, String> {

}
