package com.manage.appr.repository;

import com.manage.appr.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprAtchdFileInfRepository extends JpaRepository<ApprAtchdFileInf, Long>{

    @Query(value = "SELECT FILE_NM FROM APPR_ATCHD_FILE_INF\n" +
            "WHERE APPR_ID = :apprId;", nativeQuery = true)
    List<String> getAtchdFiles(Long apprId);
}
