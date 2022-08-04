package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.CityDto;
import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.mapper.CityMapper;
import by.academy.pharmacy_spring_boot.mapper.PharmacyMapper;
import by.academy.pharmacy_spring_boot.repository.CityRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.CityService;
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
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;
    private final PharmacyMapper pharmacyMapper;
    private final CityRepository cityRepository;

    @Override
    public Page<CityDto> findCityWithPaginated(int numberPage, int size, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable page = PageRequest.of(numberPage - 1, size, sort);
        return cityRepository.findAll(page).map(cityMapper::toDto);
    }

    @Override
    @Transactional
    public List<PharmacyDto> findPharmaciesOfCity(Integer cityId) {
        return cityRepository.findPharmacyOfCity(cityId)
                .stream()
                .map(pharmacyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDto> findAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CityDto findCityById(Integer id) {
        return cityMapper.toDto(cityRepository.findById(id).orElse(null));
    }

    @Override
    public void saveCity(CityDto cityDto) {
        cityRepository.save(cityMapper.toEntity(cityDto));
    }

    @Override
    public void deleteCityById(Integer id) {
        cityRepository.deleteById(id);
    }
}
