package com.manage.appr.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appr_ln_inf")

public class ApprLnInf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPR_LN_ID", nullable = false)
    private Long apprLnId;

    @Column(name = "APPR_LN_SRNO", nullable = false)
    private Long apprLnSrno;

    @Column(name = "APPR_DIV", length = 1)
    private String apprDiv;

    @Column(name = "USER_ID", length = 5)
    private String userId;

    @Column(name = "APPR_PROC", length = 1)
    private String apprProc;

    @Column(name = "APPR_PROC_DTMT")
    private LocalDateTime apprProcDTMT;

    @Column(name = "CMNT", length = 1000)
    private String cmnt;

    @Column(name = "APPR_ID")
    private Long apprId;

    @Column(name = "APPR_LN_TMPT_ID")
    private Long apprLnTmptId;

    @Column(name = "FRST_REG_USER_ID", length = 5)
    private String frstRegUserId;

    @Column(name = "FRST_REG_DTMT")
    private LocalDateTime frstRegDtmt;

    @Column(name = "LAST_CHG_USER_ID", length = 5)
    private String lastChgUserId;

    @Column(name = "LAST_CHG_DTMT")
    private LocalDateTime lastChgDtmt;

}
