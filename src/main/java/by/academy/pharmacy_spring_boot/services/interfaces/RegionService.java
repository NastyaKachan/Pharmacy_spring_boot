package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.RegionDto;

import java.util.List;

public interface RegionService {

    List<RegionDto> findAllRegions();

    RegionDto findRegionById(Integer id);

    void createRegion(RegionDto regionDto);

    void updateRegion(RegionDto regionDto);

    void deleteRegionById(Integer id);
}
