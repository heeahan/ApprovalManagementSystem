package com.manage.appr.domain;

import com.manage.appr.dto.ApprDto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appr_inf")

public class ApprInf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPR_ID", nullable = false)
    private Long apprId;

    @Column(name = "TASK_DIV", length = 5)
    private String taskDiv;

    @Column(name = "APPR_TYP", length = 5)
    private String apprTyp;

    @Column(name = "TITL", length = 200)
    private String titl;

    @Column(name = "CNTNT_TYP", length = 1)
    private String cntntTyp;

    @Column(name = "CNTNT", length = 3000)
    private String cntnt;

    @Column(name = "APPR_LN_CHG_PSBL_YN", length = 1)
    private String apprLnChgPsblYN;

    @Column(name = "CALLBACK_URL", length = 3000)
    private String callBack_url;

    @Column(name = "FRST_REG_USER_ID", length = 5)
    private String frstRegUserId;

    @Column(name = "FRST_REG_DTMT")
    private LocalDateTime frstRegDtmt;

    @Column(name = "LAST_CHG_USER_ID", length = 5)
    private String lastChgUserId;

    @Column(name = "LAST_CHG_DTMT")
    private LocalDateTime lastChgDtmt;

    public ApprDto toApi(){
        ApprDto dto = new ApprDto();
        BeanUtils.copyProperties(this,dto);
        return dto;
    }

    /* maybe stupid
    public ApprInfDto toApi() {
    return new ApprInfDto(
            this.apprId,
            this.taskDiv,
            this.apprTyp,
            this.titl,
            this.cntntTyp,
            this.cntnt,
            this.apprLnChgPsblYN,
            this.callBack_url,
            this.frstRegUserId,
            this.frstRegDtmt,
            this.lastChgUserId,
            this.lastChgDtmt);}
     */
}