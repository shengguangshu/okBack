package com.yunang.fangda.business.jurisdiction.jpa;

import com.yunang.fangda.business.account.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface JurisdictionJpa extends JpaSpecificationExecutor<AccountModel>,
        JpaRepository<AccountModel, String> {
}
