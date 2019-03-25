package com.yunang.fangda.business.carBrand.jpa;

import com.yunang.fangda.business.account.model.AccountModel;
import com.yunang.fangda.business.carBrand.model.CarBrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface CarBrandJpa extends JpaSpecificationExecutor<CarBrandModel>,
        JpaRepository<CarBrandModel, String> {

    List<CarBrandModel> findByBrandName(String brandName);
}
