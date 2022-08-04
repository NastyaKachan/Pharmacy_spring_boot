package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.CityDto;
import by.academy.pharmacy_spring_boot.dto.RegionDto;
import by.academy.pharmacy_spring_boot.mapper.CityMapper;
import by.academy.pharmacy_spring_boot.mapper.RegionMapper;
import by.academy.pharmacy_spring_boot.repository.RegionRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;
    private final CityMapper cityMapper;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Page<RegionDto> findCityWithPaginated(int numberPage, int size, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable page = PageRequest.of(numberPage - 1, size, sort);
        return regionRepository.findAll(page).map(regionMapper::toDto);
    }

    @Override
    @Transactional
    public List<CityDto> findCitiesOfRegion(Integer regionId) {
        return regionRepository.findCitiesOfRegion(regionId)
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegionDto> findAllRegions() {
        return regionRepository.findAll()
                .stream()
                .map(regionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RegionDto findRegionById(Integer id) {
        return regionMapper.toDto(regionRepository.findById(id).orElse(null));
    }

    @Override
    public void saveRegion(RegionDto regionDto) {
        regionRepository.save(regionMapper.toEntity(regionDto));
    }

    @Override
    public void deleteRegionById(Integer id) {
        regionRepository.deleteById(id);
    }
}
