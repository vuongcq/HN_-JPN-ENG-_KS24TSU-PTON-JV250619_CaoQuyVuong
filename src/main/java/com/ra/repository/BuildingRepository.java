package com.ra.repository;

import com.ra.model.dto.BuildingResponseDTO;
import com.ra.model.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Integer> {

Page<Building> findBuildingByBuildingNameContainingIgnoreCase(String buildingName, Pageable pageable);
Page<Building> findBuildingByStatus (Byte status, Pageable pageable);
}
