package com.manage.appr.service;

import com.manage.appr.domain.*;
import com.manage.appr.dto.ApprDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApprService {
//    ApprInf createAppr(ApprDto apprDto);
    ApprDto createAppr(ApprDto apprDto);
    ApprInf getAppr(Long apprId);
}
