package com.yunang.fangda.business.jurisdiction.jpa;

import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface JurisdictionJpa extends JpaSpecificationExecutor<JurisdictionModel>,
        JpaRepository<JurisdictionModel, String> {

    @Query(value = "SELECT" +
            " j.uuid,j.jur_name jurName,j.jur_flag as jurFlag,j.jur_parent jurParent,j.jur_type jurType," +
            " a.uuid as isy" +
            " FROM" +
            " authority_table a" +
            " RIGHT JOIN jurisdiction_table j ON j.uuid = a.aut_jur_id" +
            " AND a.aut_pos_id = '100000'", nativeQuery = true)
    List<Object[]> findByPosId(String posId);
}
