package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.PharmacyDto;

import by.academy.pharmacy_spring_boot.filters.PharmacyFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PharmacyService {
    Page<PharmacyDto> findPharmacyWithPaginated(PharmacyFilter pharmacyFilter, int numberPage, int size, String sortField,
                                                String sortDir);

    List<PharmacyDto> findAllPharmacies();

    List<PharmacyDto> findPharmaciesByIds(Integer[] pharmaciesIds);

    PharmacyDto findPharmacyById(Integer id);

    void createPharmacy(PharmacyDto pharmacyDto);

    void updatePharmacy(PharmacyDto pharmacyDto);

    void deletePharmacyById(Integer id);
}
