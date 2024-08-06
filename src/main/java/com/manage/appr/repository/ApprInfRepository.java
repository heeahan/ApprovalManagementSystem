package com.manage.appr.repository;

import com.manage.appr.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprInfRepository extends JpaRepository<ApprInf, Long> {
    @Query(value = "SELECT USER_ID as NEXT_USER FROM (\n" +
            "SELECT appr_ln_srno, USER_ID FROM APPR_LN_INF, appr_inf\n" +
            "WHERE appr_ln_inf.APPR_ID = appr_inf.APPR_ID \n" +
            "and appr_inf.appr_id = :apprId \n" +
            "AND appr_ln_inf.APPR_PROC is null  and appr_ln_inf.APPR_PROC_DTMT is null\n" +
            "and appr_ln_inf.APPR_DIV <> \"A\"\n" +
            "order by appr_ln_inf.APPR_LN_SRNO asc\n" +
            ") as t LIMIT 1", nativeQuery = true)
    String getNextUserId(Long apprId);

    @Query(value = "SELECT APPR_INF.APPR_ID, APPR_INF.TASK_DIV, APPR_INF.APPR_TYP, APPR_INF.TITL FROM APPR_INF\n" +
            "JOIN APPR_LN_INF\n" +
            "ON APPR_INF.APPR_ID = APPR_LN_INF.APPR_ID\n" +
            "WHERE APPR_LN_INF.APPR_DIV = :apprDiv AND APPR_LN_INF.USER_ID = :userId\n" +
            "AND APPR_LN_INF.APPR_PROC is null AND APPR_LN_INF.APPR_PROC_DTMT is null\n" +
            "AND 0 < (SELECT COUNT(*) FROM APPR_LN_INF B, (SELECT MAX(APPR_DIV) MAX_APPR_DIV FROM APPR_LN_INF WHERE APPR_ID = APPR_INF.APPR_ID AND APPR_LN_INF.APPR_DIV < :apprDiv) A\n" +
            "WHERE B.APPR_ID = APPR_INF.APPR_ID AND B.APPR_DIV = A.MAX_APPR_DIV AND B.APPR_PROC IS NOT NULL)\n" +
            "ORDER BY APPR_INF.APPR_ID, APPR_LN_INF.APPR_LN_SRNO ASC", nativeQuery = true)
    List<Object[]> getToDo(String userId, String apprDiv);

    @Query(value = "SELECT TITL, CNTNT, FRST_REG_USER_ID, FRST_REG_DTMT\n" +
            "FROM APPR_INF\n" +
            "WHERE APPR_ID = :apprId", nativeQuery = true)
    Object[] getApprInfo(Long apprId);
}

/*
"SELECT APPR_INF.APPR_ID, APPR_INF.TASK_DIV, APPR_INF.APPR_TYP, APPR_INF.TITL FROM APPR_INF\n" +
        "JOIN APPR_LN_INF\n" +
        "ON APPR_INF.APPR_ID = APPR_LN_INF.APPR_ID\n" +
        "WHERE APPR_LN_INF.APPR_DIV = :apprDiv AND APPR_LN_INF.USER_ID = :userId\n" +
        "AND APPR_LN_INF.APPR_PROC is null AND APPR_LN_INF.APPR_PROC_DTMT is null\n" +
        "AND 0 = (SELECT COUNT(*) FROM APPR_LN_INF WHERE APPR_INF.APPR_ID = APPR_LN_INF.APPR_ID AND APPR_LN_INF.APPR_DIV = 'C' AND APPR_LN_INF.APPR_PROC is not null)\n" +
        "AND 0 = (SELECT COUNT(*) FROM APPR_LN_INF WHERE APPR_INF.APPR_ID = APPR_LN_INF.APPR_ID  and APPR_LN_INF.APPR_DIV < :apprDiv and APPR_LN_INF.APPR_PROC is null)  \n" +
        "ORDER BY APPR_INF.APPR_ID, APPR_LN_INF.APPR_LN_SRNO ASC", nativeQuery = true
*/