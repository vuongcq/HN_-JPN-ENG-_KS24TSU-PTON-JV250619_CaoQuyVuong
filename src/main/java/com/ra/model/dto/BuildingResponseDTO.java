package com.ra.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuildingResponseDTO {
    private int id;
    private String buildingName;
    private Double buildingArea;
    private String areaUnit;
    private Date startDate;
    private int time;
    private String timeUnit;
    private String design;
    private String content;
    private Byte status =1;
}
