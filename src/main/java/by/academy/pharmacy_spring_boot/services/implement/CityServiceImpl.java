package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.CityDto;
import by.academy.pharmacy_spring_boot.mapper.CityMapper;
import by.academy.pharmacy_spring_boot.repository.CityRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;
    private final CityRepository cityRepository;

    @Override
    public List<CityDto> findAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CityDto findCityById(Integer id) {
        return cityRepository.findById(id)
                .map(cityMapper::toDto)
                .orElse(null);
    }

    @Override
    public void createCity(CityDto cityDto) {
        cityRepository.save(cityMapper.toEntity(cityDto));
    }

    @Override
    public void updateCity(CityDto cityDto) {
        cityRepository.save(cityMapper.toEntity(cityDto));
    }

    @Override
    public void deleteCityById(Integer id) {
        cityRepository.deleteById(id);
    }
}
