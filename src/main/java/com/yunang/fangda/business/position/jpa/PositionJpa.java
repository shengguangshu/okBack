package com.yunang.fangda.business.position.jpa;

import com.yunang.fangda.business.position.model.PositionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name 职位
 * @table
 * @remarks
 */
public interface PositionJpa extends JpaSpecificationExecutor<PositionModel>,
        JpaRepository<PositionModel, String> {

//    List<PositionModel> findByPosParent(String posParent);

}
