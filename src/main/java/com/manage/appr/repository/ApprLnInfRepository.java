package com.manage.appr.repository;

import com.manage.appr.domain.*;
import com.manage.appr.dto.ApprLnInfDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprLnInfRepository extends JpaRepository<ApprLnInf, Long> {
    @Query(value = "SELECT * FROM APPR_LN_INF\n" +
            "WHERE APPR_LN_INF.USER_ID = :userId\n" +
            "AND APPR_LN_INF.APPR_ID = :apprId\n" +
            "AND APPR_LN_INF.APPR_DIV <> \"A\" or \"E\"", nativeQuery = true)
    ApprLnInf apprDetail(Long apprId, String userId);

/*
    버그 기록: 단순히 +1 쓰면 한 단계가 없어지면 애러뜸
    예: A>B>C>D 승인 단계로 가면 아무 문제 없고, A>B>D로 가면 애러
    수정 후: 현 APPR_DIV 보다 큰 DIV 중 MIN subquery 찾으면 됨
    @Query(value = "SELECT USER_ID\n" +
            "FROM APPR_LN_INF\n" +
            "WHERE APPR_LN_INF.APPR_ID = :apprId\n" +
            "AND APPR_LN_INF.APPR_PROC is null\n" +
            "AND APPR_LN_INF.APPR_DIV = CHAR(ASCII(:apprDiv)+1)", nativeQuery = true)
 */
    @Query(value = "SELECT USER_ID FROM APPR_LN_INF\n" +
            "WHERE APPR_ID = :apprId\n" +
            "AND APPR_PROC is null\n" +
            "AND APPR_DIV = (\n" +
            "SELECT MIN(APPR_DIV) FROM APPR_LN_INF\n" +
            "WHERE APPR_ID = :apprId\n" +
            "AND APPR_PROC is null\n" +
            "AND APPR_DIV > :apprDiv)", nativeQuery = true)
    List<String> nextUserNoDuplicate(Long apprId, String apprDiv);
}
