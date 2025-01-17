package com.manage.appr.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
}