package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.RegionDto;
import by.academy.pharmacy_spring_boot.mapper.RegionMapper;
import by.academy.pharmacy_spring_boot.repository.RegionRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;
    private final RegionRepository regionRepository;

    @Override
    public List<RegionDto> findAllRegions() {
        return regionRepository.findAll()
                .stream()
                .map(regionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RegionDto findRegionById(Integer id) {
        return regionRepository.findById(id)
                .map(regionMapper::toDto)
                .orElse(null);
    }

    @Override
    public void createRegion(RegionDto regionDto) {
        regionRepository.save(regionMapper.toEntity(regionDto));
    }

    @Override
    public void updateRegion(RegionDto regionDto) {
        regionRepository.save(regionMapper.toEntity(regionDto));
    }

    @Override
    public void deleteRegionById(Integer id) {
        regionRepository.deleteById(id);
    }
}
