package com.manage.appr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprAtchdFileInfDto {
    private Long apprAtchdFileId;
    private Long apprAtchdFileSrno;
    private Long fileId;
    private String fileNm;
    private String filePath;
}
