package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.CityDto;
import by.academy.pharmacy_spring_boot.dto.RegionDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RegionService {
    Page<RegionDto> findCityWithPaginated(int numberPage, int size, String sortField,
                                          String sortDir);

    List<CityDto> findCitiesOfRegion(Integer regionId);

    List<RegionDto> findAllRegions();

    RegionDto findRegionById(Integer id);

    void saveRegion(RegionDto regionDto);

    void deleteRegionById(Integer id);
}
