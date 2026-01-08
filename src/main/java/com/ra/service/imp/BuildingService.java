package com.ra.service.imp;

import com.ra.model.dto.BuildingRequestDTO;
import com.ra.model.dto.BuildingResponseDTO;
import com.ra.model.entity.Building;
import com.ra.repository.BuildingRepository;
import com.ra.service.UploadFileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuildingService implements com.ra.service.BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public Page<BuildingResponseDTO> findAll(Pageable pageable) {
        Page<Building> buildings = buildingRepository.findAll(pageable);
        return buildings.map( building ->
                BuildingResponseDTO.builder()
                        .id(building.getId())
                        .buildingName(building.getBuildingName())
                        .buildingArea(building.getBuildingArea())
                        .areaUnit(building.getAreaUnit())
                        .startDate(building.getStartDate())
                        .time(building.getTime())
                        .timeUnit(building.getTimeUnit())
                        .design(building.getDesign())
                        .content(building.getContent())
                        .status(building.getStatus())
                        .build()
        );
    }

    @Override
    public BuildingResponseDTO addBuilding(BuildingRequestDTO buildingRequestDTO) {
        String fileName = uploadFileService.uploadFile(buildingRequestDTO.getDesign());
        Building building = Building.builder()
                .buildingName(buildingRequestDTO.getBuildingName())
                .buildingArea(buildingRequestDTO.getBuildingArea())
                .areaUnit(buildingRequestDTO.getAreaUnit())
                .startDate(buildingRequestDTO.getStartDate())
                .time(buildingRequestDTO.getTime())
                .timeUnit(buildingRequestDTO.getTimeUnit())
                .design(fileName)
                .content(buildingRequestDTO.getContent())
                .status(buildingRequestDTO.getStatus())
        .build();

        Building savedBuilding = buildingRepository.save(building);
        return BuildingResponseDTO.builder()
                .id(savedBuilding.getId())
                .buildingName(savedBuilding.getBuildingName())
                .buildingArea(savedBuilding.getBuildingArea())
                .areaUnit(savedBuilding.getAreaUnit())
                .startDate(savedBuilding.getStartDate())
                .time(savedBuilding.getTime())
                .timeUnit(savedBuilding.getTimeUnit())
                .design(savedBuilding.getDesign())
                .content(savedBuilding.getContent())
                .status(savedBuilding.getStatus())
                .build();
    }

    @Override
    public Building findBuildingById(int id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building khong ton tai"));
    }

    @Override
    public BuildingResponseDTO updateBuilding(int id, BuildingRequestDTO buildingRequestDTO) {
        Building existBuilding = findBuildingById(id);
        existBuilding.setBuildingName(buildingRequestDTO.getBuildingName());
        existBuilding.setBuildingArea(buildingRequestDTO.getBuildingArea());
        existBuilding.setAreaUnit(buildingRequestDTO.getAreaUnit());
        existBuilding.setStartDate(buildingRequestDTO.getStartDate());
        existBuilding.setTime(buildingRequestDTO.getTime());
        existBuilding.setTimeUnit(buildingRequestDTO.getTimeUnit());
        existBuilding.setContent(buildingRequestDTO.getContent());
        existBuilding.setStatus(buildingRequestDTO.getStatus());
        if (buildingRequestDTO.getDesign()!= null && !buildingRequestDTO.getDesign().isEmpty()){
            String updateFileName = uploadFileService.uploadFile(buildingRequestDTO.getDesign());
            existBuilding.setDesign(updateFileName);
        }

        Building updateBuilding = buildingRepository.save(existBuilding);

        return BuildingResponseDTO.builder()
                .id(updateBuilding.getId())
                .buildingName(updateBuilding.getBuildingName())
                .buildingArea(updateBuilding.getBuildingArea())
                .areaUnit(updateBuilding.getAreaUnit())
                .startDate(updateBuilding.getStartDate())
                .time(updateBuilding.getTime())
                .timeUnit(updateBuilding.getTimeUnit())
                .design(updateBuilding.getDesign())
                .content(updateBuilding.getContent())
                .status(updateBuilding.getStatus())
                .build();
    }

    @Override
    public Page<BuildingResponseDTO> searchByBuildingName(String buildingName, Pageable pageable) {
        Page<Building> buildings = buildingRepository.findBuildingByBuildingNameContainingIgnoreCase(buildingName, pageable);
        if (buildings !=null){
            return buildings.map(building-> BuildingResponseDTO.builder()
                    .id(building.getId())
                    .buildingName(building.getBuildingName())
                    .buildingArea(building.getBuildingArea())
                    .areaUnit(building.getAreaUnit())
                    .startDate(building.getStartDate())
                    .time(building.getTime())
                    .timeUnit(building.getTimeUnit())
                    .design(building.getDesign())
                    .content(building.getContent())
                    .status(building.getStatus())
                    .build());
        }
        return null;
    }

    @Override
    public Page<BuildingResponseDTO> searchByStatus(Byte status, Pageable pageable) {
        Page<Building> buildings = buildingRepository.findBuildingByStatus(status,pageable);
        if (buildings!=null){
            return buildings.map(building-> BuildingResponseDTO.builder()
                    .id(building.getId())
                    .buildingName(building.getBuildingName())
                    .buildingArea(building.getBuildingArea())
                    .areaUnit(building.getAreaUnit())
                    .startDate(building.getStartDate())
                    .time(building.getTime())
                    .timeUnit(building.getTimeUnit())
                    .design(building.getDesign())
                    .content(building.getContent())
                    .status(building.getStatus())
                    .build());
        }
        return null;
    }
}
