package com.ra.controller;

import com.ra.model.dto.BuildingRequestDTO;
import com.ra.model.dto.BuildingResponseDTO;
import com.ra.model.dto.ResponseWrapper;
import com.ra.service.BuildingService;
import com.ra.service.UploadFileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

@RestController
@RequestMapping("/api/v1/buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private View error;

    @GetMapping
    public ResponseEntity<?> getAllBuilding(@PageableDefault
                                                    (page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<BuildingResponseDTO> buildingResponseDTOPage = buildingService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Get Building successfull")
                        .data(buildingResponseDTOPage)
                        .httpStatus(HttpStatus.OK.value()).build()
        );
    }

    @PostMapping
    public ResponseEntity<?> createBuilding(@Valid @ModelAttribute BuildingRequestDTO buildingRequestDTO) {
        BuildingResponseDTO buildingResponseDTO = buildingService.addBuilding(buildingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Create Building successfull")
                        .data(buildingResponseDTO)
                        .httpStatus(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBuilding(@Valid @ModelAttribute BuildingRequestDTO buildingRequestDTO, @PathVariable int id) {
        BuildingResponseDTO buildingResponseDTO = buildingService.updateBuilding(id, buildingRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Update successfull")
                        .data(buildingResponseDTO)
                        .httpStatus(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBuilding(
            @RequestParam(name = "buildingName", required = false) String buildingName,
            @RequestParam(name = "status", required = false) Byte status,
            @PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<BuildingResponseDTO> buildingResponseDTOS;

        if (buildingName != null && status == null) {
            buildingResponseDTOS = buildingService.searchByBuildingName(buildingName, pageable);
        } else {
            if (status != null && buildingName == null) {
                buildingResponseDTOS = buildingService.searchByStatus(status, pageable);
            }
            else {
                throw new IllegalArgumentException("Chi duoc truyen mot trong hai: buildingName hoac Status");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Search successfull")
                        .data(buildingResponseDTOS)
                        .httpStatus(HttpStatus.OK.value())
                        .build()
        );
    }

}
