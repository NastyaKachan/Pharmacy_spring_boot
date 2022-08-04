package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.filters.PharmacyFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PharmacyService {
    Page<PharmacyDto> findPharmacyWithPaginated(PharmacyFilter pharmacyFilter, int numberPage, int size, String sortField,
                                                String sortDir);

    List<ProductDto> findProductsOfPharmacy(Integer pharmacyId);

    List<PharmacyDto> findAllPharmacies();

    PharmacyDto findPharmacyById(Integer id);

    void savePharmacy(PharmacyDto pharmacyDto);

    void deletePharmacyById(Integer id);
}
