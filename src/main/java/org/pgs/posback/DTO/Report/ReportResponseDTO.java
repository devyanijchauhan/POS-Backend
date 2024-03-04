package org.pgs.posback.DTO.Report;

import lombok.Data;

import java.util.Date;

@Data
public class ReportResponseDTO {

    private Long id;

    private String type;

    private Date date;

    private String content;

    private Date createdAt;

    private Date updatedAt;

}
