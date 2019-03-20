package com.yunang.fangda.business.authority.jpa;

import com.yunang.fangda.business.authority.model.AuthorityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ld
 * @name 职位权限对应表
 * @table
 * @remarks
 */
public interface AuthorityJpa extends JpaSpecificationExecutor<AuthorityModel>,
        JpaRepository<AuthorityModel, String> {

    //    ORDER BY ?#{#pageable}
    @Query(value = "select j.uuid,j.jur_name jurName,j.jur_flag jurFlag,j.jur_parent jurParent,j.jur_type jurType from authority_table a left join jurisdiction_table j on j.uuid = a.jurisdiction_id where a.position_id = ?1 order by j.uuid",
            nativeQuery = true)
    List<Object[]> findJurisdictionModelByAutPostId(String autPosId);

    AuthorityModel findByPositionModelUuidAndJurisdictionModelUuid(String posId,String jurId);


}
