package com.manage.appr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.manage.appr.domain.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
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
    private LocalDateTime frstRegDtmt;
    private String lastChgUserId;
    private LocalDateTime lastChgDtmt;

    private List<ApprLnInfDto> apprLnInfDto;
    private List<ApprAtchdFileInfDto> apprAtchdFileInfDto;


    public ApprDto(ApprInf apprInf){
        BeanUtils.copyProperties(apprInf, this);
    }



//    @Override
//    public ApprInf toEntity() {
//        ApprInf entity = new ApprInf();
//        BeanUtils.copyProperties(this, entity);
//        return entity;
//    }
}

/*
{
    "1":{"승인구분": "A", "사용자ID": "user1"},
        "2":{"승인구분": "B", "사용자ID": "user2"},
        "3":{"승인구분": "C", "사용자ID": {"1": "user3", "2": "user4", "3": "user5"}},
        "4":{"승인구분": "D", "사용자ID": "user6"},
                }

 */