package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.CityDto;
import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CityService {

    Page<CityDto> findCityWithPaginated(int numberPage, int size, String sortField,
                                        String sortDir);

    List<PharmacyDto> findPharmaciesOfCity(Integer cityId);

    List<CityDto> findAllCities();

    CityDto findCityById(Integer id);

    void saveCity(CityDto cityDto);

    void deleteCityById(Integer id);
}
