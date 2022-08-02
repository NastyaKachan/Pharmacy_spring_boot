package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
import by.academy.pharmacy_spring_boot.filters.PharmacyChainFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PharmacyChainService {

    List<PharmacyChainDto> findAllPharmacyChain();

    PharmacyChainDto findPharmacyChainById(Integer id);

    void createPharmacyChain(PharmacyChainDto pharmacyChainDto);

    void updatePharmacyChain(PharmacyChainDto pharmacyChainDto);

    void deletePharmacyChainById(Integer id);

    Page<PharmacyChainDto> findAllPaginatedAndFiltered(PharmacyChainFilter filter, int numberPage,
                                                       int allPages, String fieldSort, String typeSort);
}
