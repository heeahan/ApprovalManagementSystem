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
            @ApiResponse(responseCode = "200", description = "품의서 기안이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "필요한 정보를 입력하세요."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류 :(")
    })
    public ResponseEntity<ApprInf> createAppr(@RequestBody ApprDto apprDto) {
        try {
//            ApprDto createdAppr = apprService.createAppr(apprDto);
            ApprInf _apprInf = apprService.createAppr(apprDto);
            return new ResponseEntity<>(_apprInf, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//        System.out.println(apprDto.toString());
//        return null;

//        try {
//            if (apprDto.getApprId() == null) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }

}
