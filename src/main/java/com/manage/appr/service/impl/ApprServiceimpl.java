package com.manage.appr.service.impl;

import com.manage.appr.dto.*;
import com.manage.appr.domain.*;
import com.manage.appr.repository.*;
import com.manage.appr.service.ApprService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.plaf.OptionPaneUI;
import java.time.LocalDateTime;
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
    @Override
    public ApprDto createAppr(ApprDto apprDto) {
//    public ApprInf createAppr(ApprDto apprDto) {
        // SET AND GET using dto
        ApprInf apprInf = new ApprInf();
        apprInf.setApprId((apprDto.getApprId()));
        apprInf.setTaskDiv(apprDto.getTaskDiv());
        apprInf.setApprTyp(apprDto.getApprTyp());
        apprInf.setTitl(apprDto.getTitl());
        apprInf.setCntntTyp(apprDto.getCntntTyp());
        apprInf.setCntnt(apprDto.getCntnt());
        apprInf.setApprLnChgPsblYN(apprDto.getApprLnChgPsblYN());
        apprInf.setCallBack_url(apprDto.getCallBack_url());
        apprInf.setFrstRegUserId(apprDto.getFrstRegUserId());
        apprInf.setFrstRegDtmt(apprDto.getFrstRegDtmt());
        apprInf.setLastChgUserId(apprDto.getLastChgUserId());
        apprInf.setLastChgDtmt(apprDto.getLastChgDtmt());

        // Save and use as keys
        Long pk_apprId = apprInfRepository.save(apprInf).getApprId();
        LocalDateTime frst_reg_time = apprInfRepository.save(apprInf).getFrstRegDtmt();
        LocalDateTime last_chg_time = apprInfRepository.save(apprInf).getLastChgDtmt();
        String frst_reg_id = apprInfRepository.save(apprInf).getFrstRegUserId();
        String last_chg_id = apprInfRepository.save(apprInf).getLastChgUserId();

        // Appr Line
        // brute force..
        // Use for loop to receive appr LINE info [Assume there are 6] -> no need to assume, just loop the size
        // sub user solution: if list not isempty, loop every one in the list with all set/get -> sub_user

        // For loop to get Each Appr Line Info DTO from the 'List<ApprLnInfDto> apprLnInfDto'
        for (int i = 0; i < (apprDto.getApprLnInfDto()).size(); i++) {
            // If Sub User List exists (In This Project, ALWAYS EXIST)
            boolean haveSubUser = !apprDto.getApprLnInfDto().get(i).getUserId().isEmpty();
            if (haveSubUser) {
                for (int sub_user = 0; sub_user < (apprDto.getApprLnInfDto().get(i).getUserId().size()); sub_user++) {
                    ApprLnInf apprLnInf = new ApprLnInf();
                    apprLnInf.setApprId(pk_apprId);
                    apprLnInf.setFrstRegDtmt(frst_reg_time);
                    apprLnInf.setLastChgDtmt(last_chg_time);
                    apprLnInf.setFrstRegUserId(frst_reg_id);
                    apprLnInf.setLastChgUserId(last_chg_id);
                    apprLnInf.setApprProcDTMT(last_chg_time);
                    apprLnInf.setApprLnId(apprDto.getApprLnInfDto().get(i).getApprLnId());
                    apprLnInf.setApprLnSrno(apprDto.getApprLnInfDto().get(i).getApprLnSrno());
                    apprLnInf.setApprDiv(apprDto.getApprLnInfDto().get(i).getApprDiv());
                    apprLnInf.setUserId(apprDto.getApprLnInfDto().get(i).getUserId().get(sub_user));
                    apprLnInf.setApprProc(apprDto.getApprLnInfDto().get(i).getApprProc());
                    apprLnInf.setCmnt(apprDto.getApprLnInfDto().get(i).getCmnt());
                    apprLnInf.setApprLnTmptId(apprDto.getApprLnInfDto().get(i).getApprLnTmptId());
                    ApprLnInf _apprLnInf = apprLnInfRepository.save(apprLnInf);
                }
            }
        }

        // AtchdFile
        // Also use for loop to receive each one
        for (int file = 0; file < (apprDto.getApprAtchdFileInfDto().size()); file++) {
            ApprAtchdFileInf apprAtchdFileInf = new ApprAtchdFileInf();
            apprAtchdFileInf.setApprId(pk_apprId);
            apprAtchdFileInf.setFrstRegDtmt(frst_reg_time);
            apprAtchdFileInf.setLastChgDtmt(last_chg_time);
            apprAtchdFileInf.setFrstRegUserId(frst_reg_id);
            apprAtchdFileInf.setLastChgUserId(last_chg_id);
            apprAtchdFileInf.setApprAtchdFileId(apprDto.getApprAtchdFileInfDto().get(file).getApprAtchdFileId());
            apprAtchdFileInf.setApprAtchdFileSrno(apprDto.getApprAtchdFileInfDto().get(file).getApprAtchdFileSrno());
            apprAtchdFileInf.setFileId(apprDto.getApprAtchdFileInfDto().get(file).getFileId());
            apprAtchdFileInf.setFileNm(apprDto.getApprAtchdFileInfDto().get(file).getFileNm());
            apprAtchdFileInf.setFilePath(apprDto.getApprAtchdFileInfDto().get(file).getFilePath());
            ApprAtchdFileInf _apprAtchdFileInf = apprAtchdFileInfRepository.save(apprAtchdFileInf);
        }

        ApprInf _apprInf = apprInfRepository.save(apprInf);
//        return _apprInf;
        return apprDto;
    }

    @Transactional
    @Override
    public ApprInf getAppr(Long apprId) {
        Optional<ApprInf> apprInfo = apprInfRepository.findById(apprId);
        return apprInfo.orElse(null);
    }
}
