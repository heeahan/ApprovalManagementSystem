package com.manage.appr.controller;

import com.manage.appr.domain.ApprInf;
import com.manage.appr.domain.ApprLnInf;
import com.manage.appr.dto.*;
import com.manage.appr.repository.*;
import com.manage.appr.service.ApprService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Approval Management System", description = "Internship Misson 1")
@RestController
@RequestMapping("/api")
public class ApprController {

    private static final Logger log = LoggerFactory.getLogger(ApprController.class);
    @Autowired
    private ApprService apprService;

    @Autowired
    private ApprInfRepository apprInfRepository;
    private ApprLnInfRepository apprLnInfRepository;
    private ApprAtchdFileInfRepository apprAtchdFileInfRepository;

    @PostMapping("/appr/new")
    @Operation(summary = "Create a new Letter Of Approval", description = "새로운 품의서를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "품의서 기안이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "필요한 정보를 입력하세요."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류 :(")
    })
    public ResponseEntity<ApprInf> createAppr(@RequestBody ApprDto apprDto) {
        try {
            if (apprDto.getApprId() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            for (int i = 0; i < (apprDto.getApprLnInfDto()).size(); i++) {
                if (apprDto.getApprLnInfDto().get(i).getApprLnId() == null || apprDto.getApprLnInfDto().get(i).getApprLnSrno() == null) {
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
            }
            for (int file = 0; file < (apprDto.getApprAtchdFileInfDto().size()); file++) {
                if (apprDto.getApprAtchdFileInfDto().get(file).getApprAtchdFileId() == null ||
                        apprDto.getApprAtchdFileInfDto().get(file).getApprAtchdFileSrno() == null ||
                        apprDto.getApprAtchdFileInfDto().get(file).getFileId() == null) {
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
            }
            ApprInf _apprInf = apprService.createAppr(apprDto);
            return new ResponseEntity<>(_apprInf, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/appr/get")
    @Operation(summary = "Find to-do list by user ID", description = "확인 대기중인 품의서 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "품의서 리스트 조회 성공했습니다."),
            @ApiResponse(responseCode = "204", description = "확인 대기중인 품의서가 없습니다."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류 :(")
    })
    public ResponseEntity<List<Object[]>> getToDo(@RequestParam String userId, @RequestParam String apprDiv) {
        try {
            List<Object[]> toDoList = apprService.getToDoList(userId, apprDiv);
            if (toDoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(toDoList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/appr/check")
    @Operation(summary = "Check the details of the approval", description = "품의서 상세 내역 조회 및 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "품의서 처리 완료되었습니다."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류 :(")
    })
    public ResponseEntity<ApprLnInf> getApprDetail(@RequestParam Long apprId, @RequestParam String userId, @RequestParam String cmnt, @RequestParam String apprProc) {
        try {
            ApprLnInf _apprLnInf = apprService.getApprDetail(apprId, userId, cmnt, apprProc);
            if (_apprLnInf == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else if (apprProc.equals("3")) {
                String apprAuthor = _apprLnInf.getFrstRegUserId();
                log.info("{} Manager , Approval '{}' got rejected.", apprAuthor, _apprLnInf.getApprId());
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
//            String nextUserId = apprInfRepository.getNextUserId(apprId);
            String div = _apprLnInf.getApprDiv();
            List<String> nextUserId = apprService.getNextUserNotDuplicate(apprId, div);
            if (!nextUserId.isEmpty()) {
                for (int user = 0; user < nextUserId.size(); user++)
                    log.info("{} Manager, Please check the approval!", nextUserId.get(user));
            } else {
                log.info("품의서 최종 승인 완료되었습니다.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            log.info("/api/appr/check/apprId={}&apprProc={}", apprId, apprProc);
            return new ResponseEntity<>(_apprLnInf, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}