package com.manage.appr.service.impl;

import com.manage.appr.dto.*;
import com.manage.appr.domain.*;
import com.manage.appr.repository.*;
import com.manage.appr.service.ApprService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApprServiceimpl implements ApprService {
    private static final Logger log = LoggerFactory.getLogger(ApprServiceimpl.class);
    @Autowired
    private ApprInfRepository apprInfRepository;
    @Autowired
    private ApprLnInfRepository apprLnInfRepository;
    @Autowired
    private ApprAtchdFileInfRepository apprAtchdFileInfRepository;

    @Transactional
    @Override
    public ApprInf createAppr(ApprDto apprDto) {
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

        // 이 단계에서 최초 등록/마지막 수정 같은 시간으로 지정 (강제로)
        LocalDateTime init_time = LocalDateTime.now();
        apprInf.setFrstRegDtmt(init_time);
        apprInf.setLastChgDtmt(init_time);

        apprInf.setLastChgUserId(apprDto.getLastChgUserId());
        // 해당 apprId를 Primary Key로 저장, 나중에 사용
        Long pk_apprId = apprInfRepository.save(apprInf).getApprId();

        /*
        Approval Line (brute force..)
        Use for loop to receive appr LINE info
        sub user solution:
        if list not isempty, loop every one in the list with all set/get -> sub_user
         */
        // For loop to get Each Appr Line Info DTO from the 'List<ApprLnInfDto> apprLnInfDto'
        int ln_srno_cnt = 1;
        for (int i = 0; i < (apprDto.getApprLnInfDto()).size(); i++) {

            // If Sub User List exists (In This Project, ALWAYS EXIST)
            boolean haveSubUser = !apprDto.getApprLnInfDto().get(i).getUserId().isEmpty();
            if (haveSubUser) {
                for (int sub_user = 0; sub_user < (apprDto.getApprLnInfDto().get(i).getUserId().size()); sub_user++) {
                    ApprLnInf apprLnInf = new ApprLnInf();
                    apprLnInf.setApprId(pk_apprId);

                    // first_reg, last_chg 모두 최초 등록한 유저로 지정 (강제로)
                    apprLnInf.setFrstRegUserId(apprDto.getFrstRegUserId());
                    apprLnInf.setLastChgUserId(apprDto.getLastChgUserId());

                    // 최초 등록/마지막 수정 같은 시간으로 지정 (강제로)
                    apprLnInf.setFrstRegDtmt(init_time);

                    // 결재선 중 index #0인 유저(제안자)만 -> 등록시간/처리상태/처리시간 등록
                    if (i == 0) {
                        apprLnInf.setApprProcDTMT(init_time);
                        apprLnInf.setApprProc("1");
                        apprLnInf.setLastChgDtmt(init_time);
                    }

                    apprLnInf.setApprLnId(apprDto.getApprLnInfDto().get(i).getApprLnId());
                    apprLnInf.setApprLnSrno((long) ln_srno_cnt);
                    apprLnInf.setApprDiv(apprDto.getApprLnInfDto().get(i).getApprDiv());
                    apprLnInf.setUserId(apprDto.getApprLnInfDto().get(i).getUserId().get(sub_user));
                    apprLnInf.setCmnt(apprDto.getApprLnInfDto().get(i).getCmnt());
                    apprLnInf.setApprLnTmptId(apprDto.getApprLnInfDto().get(i).getApprLnTmptId());
                    ApprLnInf _apprLnInf = apprLnInfRepository.save(apprLnInf);
                    ln_srno_cnt++; // 각 결재선 시리얼번호
                }
            }
        }

        // AtchdFile
        // Also use for loop to receive each one
        for (int file = 0; file < (apprDto.getApprAtchdFileInfDto().size()); file++) {
            ApprAtchdFileInf apprAtchdFileInf = new ApprAtchdFileInf();
            apprAtchdFileInf.setApprId(pk_apprId);

            // first_reg, last_chg 모두 등록한 유저로 지정 (강제로)
            apprAtchdFileInf.setFrstRegUserId(apprDto.getFrstRegUserId());
            apprAtchdFileInf.setLastChgUserId(apprDto.getLastChgUserId());

            // 최초 등록/마지막 수정 같은 시간으로 지정 (강제로)
            apprAtchdFileInf.setFrstRegDtmt(init_time);
            apprAtchdFileInf.setLastChgDtmt(init_time);

            apprAtchdFileInf.setApprAtchdFileId(apprDto.getApprAtchdFileInfDto().get(file).getApprAtchdFileId());
            apprAtchdFileInf.setApprAtchdFileSrno((long) file + 1);
            apprAtchdFileInf.setFileId(apprDto.getApprAtchdFileInfDto().get(file).getFileId());
            apprAtchdFileInf.setFileNm(apprDto.getApprAtchdFileInfDto().get(file).getFileNm());
            apprAtchdFileInf.setFilePath(apprDto.getApprAtchdFileInfDto().get(file).getFilePath());
            ApprAtchdFileInf _apprAtchdFileInf = apprAtchdFileInfRepository.save(apprAtchdFileInf);
        }

        // 다음 유저한테 노티를 log로 출력
        String tmp = apprInfRepository.getNextUserId(pk_apprId);
        log.info("### For the next user. User ID: {}, Approval ID: {}", tmp, pk_apprId);

        return null;
    }

    @Transactional
    @Override
    public List<Object[]> getToDoList(String userId, String apprDiv) {
        // 각 todolist를 object로 받음
        List<Object[]> toDoObjectList = apprInfRepository.getToDo(userId, apprDiv);
        return toDoObjectList;
    }

    @Transactional
    @Override
    public ApprLnInf getApprDetail(Long apprId, String userId, String cmnt, String apprProc) {
        ApprLnInf apprDetail = apprLnInfRepository.apprDetail(apprId, userId);
        apprDetail.setCmnt(cmnt);
        apprDetail.setApprProc(apprProc);
        apprDetail.setApprProcDTMT(LocalDateTime.now());
        apprDetail.setLastChgDtmt(LocalDateTime.now());
        apprDetail.setLastChgUserId(userId);
        apprDetail.setLastChgDtmt(LocalDateTime.now());
        return apprLnInfRepository.save(apprDetail);
    }

    public List<String> getNextUserNotDuplicate(Long apprId, String apprDiv) {
        return apprLnInfRepository.nextUserNoDuplicate(apprId, apprDiv);
    }
}
