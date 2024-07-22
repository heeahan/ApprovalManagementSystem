package com.manage.appr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprDto {
    private Long apprId;
    private String taskDiv;
    private String apprTyp;
    private String titl;
    private String cntntTyp;
    private String cntnt;
    private String apprLnChgPsblYN;
    private String callBack_url;
    private String frstRegUserId;
    private LocalDateTime frstRegDtmt = LocalDateTime.now(); // 현재 로컬 타임
    private String lastChgUserId;
//    private LocalDateTime lastChgDtmt;

    private List<ApprLnInfDto> apprLnInfDto;
    private List<ApprAtchdFileInfDto> apprAtchdFileInfDto;
}
