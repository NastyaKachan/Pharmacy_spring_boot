package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> findAllCities();

    CityDto findCityById(Integer id);

    void createCity(CityDto cityDto);

    void updateCity(CityDto cityDto);

    void deleteCityById(Integer id);
}
