package com.ra.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuildingRequestDTO {

    @NotBlank(message = "Ten toa nha khong duoc de trong")
    @Size (max = 100)
    private String buildingName;

    @NotNull(message = "Dien tich xay dung khong duoc de trong")
    private Double buildingArea;

    @NotBlank(message = "Don vi dien tich xay dung khong duoc de trong, phai theo dinh dang yyyy-MM-đ")
    @Size(max = 10)
    private String areaUnit;

    @NotNull(message = "Ngay khoi cong khong duoc de trong")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "thoi gian xay dung khong duoc de trong")
    private int time;

    @NotBlank(message = "Don vi tinh thoi gian xay dung khong duoc de trong")
    @Size(max = 10)
    private String timeUnit;

    @NotNull(message = "Duong dan den hinh anh khong duoc de trong")
    private MultipartFile design;

    @NotBlank(message = "Noi dung xay dung khong duoc de trong")
    @Size(max = 255)
    private String content;

    @NotNull(message = "Trang thai quy hoach  khong duoc de trong")
    private Byte status =1;
}
