package com.manage.appr.domain;

import com.manage.appr.dto.

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionId;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appr_atchd_file_inf")

public class ApprAtchdFileInf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPR_ATCHD_FILE_ID", nullable = false)
    private Long apprAtchdFileId;

    @Column(name = "APPR_ATCHD_FILE_SRNO", nullable = false)
    private Long apprAtchdFileSrno;

    @Column(name = "FILE_ID", nullable = false)
    private Long fileId;

    @Column(name = "FILE_NM", length = 200)
    private String fileNm;

    @Column(name = "FILE_PATH", length = 1000)
    private String filePath;

    @Column(name = "APPR_ID")
    private Long apprId;

    @Column(name = "FRST_REG_USER_ID", length = 5)
    private String frstRegUserId;

    @Column(name = "FRST_REG_DTMT")
    private LocalDateTime frstRegDtmt;

    @Column(name = "LAST_CHG_USER_ID", length = 5)
    private String lastChgUserId;

    @Column(name = "LAST_CHG_DTMT")
    private LocalDateTime lastChgDtmt;
}
