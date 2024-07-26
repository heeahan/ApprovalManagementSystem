package com.manage.appr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprLnInfDto {
    private Long apprLnId;
    private Long apprLnSrno;
    private String apprDiv;
    private List<String> userId;
//    private String apprProc; -> service에서 작업 후 수정하면 됨. 여기서 초기화할 필요 없음
    private String cmnt;
    private Long apprLnTmptId;
}
