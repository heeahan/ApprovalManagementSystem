package com.manage.appr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String lastChgUserId;

 /*
 처음에 여기서 LocalDateTime.now()로 바로 시간 찍었는데 틀린 방법이었다.
 수정 후: service에서 등록/수정 시간 그때 그때 now() 찍으면 됨
    private LocalDateTime frstRegDtmt;
    private LocalDateTime lastChgDtmt;
 */

    //결재선, 첨부파일 list<dto>로 받음
    private List<ApprLnInfDto> apprLnInfDto;
    private List<ApprAtchdFileInfDto> apprAtchdFileInfDto;
}
