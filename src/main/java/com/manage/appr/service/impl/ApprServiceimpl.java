package com.manage.appr.service.impl;

import com.manage.appr.dto.*;
import com.manage.appr.domain.*;
import com.manage.appr.repository.*;
import com.manage.appr.service.ApprService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprServiceimpl implements ApprService {
    @Autowired
    private ApprInfRepository apprInfRepository;
    @Autowired
    private ApprLnInfRepository apprLnInfRepository;
    @Autowired
    private ApprAtchdFileInfRepository apprAtchdFileInfRepository;

    @Transactional
//    @Override
    public ApprInf createAppr(ApprInf apprInf){
        // Save the Approval information as "apprInfData", will use for set/get
        ApprInf apprInfData = apprInfRepository.save(apprInf);
        return apprInfData;


    }

//    public ApprInf createNewAppr(ApprInf apprInf){
//        //apprInfRepository.save
//
//    }
}
