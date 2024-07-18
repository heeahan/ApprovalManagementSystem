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
    private String apprProc;
    private String cmnt;
    private Long apprLnTmptId;
//
//    public void setUserIds(List<String> userId){
//        this.userId = userId;
//    }
//
//    public List<String> getUserIds(){
//        return userId;
//    }
}
