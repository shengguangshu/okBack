package com.yunang.fangda.business.jurisdiction.jpa;

import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface JurisdictionJpa extends JpaSpecificationExecutor<JurisdictionModel>,
        JpaRepository<JurisdictionModel, String> {
}
