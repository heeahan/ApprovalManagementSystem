package com.manage.appr.repository;

import com.manage.appr.domain.*;

import com.manage.appr.dto.ApprDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ApprInfRepository extends JpaRepository<ApprInf, Long>{
}
