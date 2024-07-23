package com.manage.appr.repository;

import com.manage.appr.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprInfRepository extends JpaRepository<ApprInf, Long>{
    @Query(value = "SELECT USER_ID as NEXT_USER FROM (\n" +
            "SELECT appr_ln_srno, USER_ID FROM APPR_LN_INF, appr_inf\n" +
            "WHERE appr_ln_inf.APPR_ID = appr_inf.APPR_ID \n" +
            "and appr_inf.appr_id = :apprId \n" +
            "AND appr_ln_inf.APPR_PROC is null  and appr_ln_inf.APPR_PROC_DTMT is null\n" +
            "and appr_ln_inf.APPR_DIV <> \"A\"\n" +
            "order by appr_ln_inf.APPR_LN_SRNO asc\n" +
            ") as t LIMIT 1", nativeQuery = true)
    String getNextUserId(Long apprId);

}
