package com.manage.appr.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ApprAtchdFileInfDto {
    private Long apprAtchdFileId;
    private Long apprAtchdFileSrno;
    private Long fileId;
    private String fileNm;
    private String filePath;
    private Long apprId;
    private String frstRegUserId;
    private LocalDateTime frstRegDtmt;
    private String lastChgUserId;
    private LocalDateTime lastChgDtmt;
}
