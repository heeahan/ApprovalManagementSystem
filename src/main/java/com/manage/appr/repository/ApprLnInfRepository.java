package com.manage.appr.repository;

import com.manage.appr.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprLnInfRepository extends JpaRepository<ApprLnInf, Long> {
}
