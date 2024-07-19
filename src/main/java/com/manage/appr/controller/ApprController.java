package com.manage.appr.controller;

import com.manage.appr.domain.ApprInf;
import com.manage.appr.dto.*;
import com.manage.appr.repository.*;
import com.manage.appr.service.ApprService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

@Tag(name = "Approval Management System", description = "Internship Misson 1")
@RestController
@RequestMapping("/api")
public class ApprController {

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
    public ResponseEntity<ApprDto> createAppr(@RequestBody ApprDto apprDto) {
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
//            ApprInf _apprInf = apprService.createAppr(apprDto);
//            return new ResponseEntity<>(_apprInf, HttpStatus.CREATED);
            ApprDto _apprDto = apprService.createAppr(apprDto);
            return new ResponseEntity<>(_apprDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("appr/{apprID}")
    @Operation(summary = "Get the approval by ID", description = "ID로 품의서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "품의서 조회 성공했습니다."),
            @ApiResponse(responseCode = "204", description = "품의서 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류 :(")
    })
    public ResponseEntity<ApprInf> getAppr(@PathVariable("apprID") Long apprID) {
        try {
            ApprInf apprInf = new ApprInf();
            apprInf = apprService.getAppr(apprID);
            if (apprInf == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(apprInf, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
