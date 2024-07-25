package com.manage.appr.repository;

import com.manage.appr.domain.*;
import com.manage.appr.dto.ApprLnInfDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprLnInfRepository extends JpaRepository<ApprLnInf, Long> {
    @Query(value = "SELECT * FROM APPR_LN_INF\n" +
            "WHERE APPR_LN_INF.USER_ID = :userId\n" +
            "AND APPR_LN_INF.APPR_ID = :apprId\n" +
            "AND APPR_LN_INF.APPR_DIV <> \"A\" or \"E\"", nativeQuery = true)
    ApprLnInf apprDetail(Long apprId, String userId);
}
