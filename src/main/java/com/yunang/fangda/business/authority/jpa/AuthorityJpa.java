package com.yunang.fangda.business.authority.jpa;

import com.yunang.fangda.business.authority.model.AuthorityModel;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name 职位权限对应表
 * @table
 * @remarks
 */
public interface AuthorityJpa extends JpaSpecificationExecutor<AuthorityModel>,
        JpaRepository<AuthorityModel, String> {

    List<JurisdictionModel> findByAutPostId(String autPosId);

    JurisdictionModel findByAutPostIdAndAutJurId(String autPostId, String autJurId);

}
