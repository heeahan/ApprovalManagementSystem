package com.manage.appr.service;

import com.manage.appr.domain.*;
import com.manage.appr.dto.ApprDto;

import java.util.List;

public interface ApprService {
    ApprInf createAppr(ApprDto apprDto);
    List<Object[]> getToDoList(String userId, String apprDiv);
    ApprLnInf getApprDetail(Long apprId, String userId, String cmnt, String apprProc);
    List<String> getNextUserNotDuplicate(Long apprId, String apprDiv);
    Object[] apprInfo(Long apprId);
}