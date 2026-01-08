package com.ra.service;

import com.ra.model.dto.BuildingRequestDTO;
import com.ra.model.dto.BuildingResponseDTO;
import com.ra.model.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BuildingService {
    Page<BuildingResponseDTO> findAll(Pageable pageable);
    BuildingResponseDTO addBuilding(BuildingRequestDTO buildingRequestDTO);
    Building findBuildingById(int id);
    BuildingResponseDTO updateBuilding(int id, BuildingRequestDTO buildingRequestDTO);
    Page<BuildingResponseDTO> searchByBuildingName(String buildingName, Pageable pageable);
    Page<BuildingResponseDTO> searchByStatus(Byte status, Pageable pageable);

}
