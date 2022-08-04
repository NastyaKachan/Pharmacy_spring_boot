package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.filters.PharmacyChainFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PharmacyChainService {

    Page<PharmacyChainDto> findChainWithPaginated(PharmacyChainFilter pharmacyChainFilter, int numberPage, int size, String sortField,
                                                  String sortDir);

    List<PharmacyDto> findPharmaciesOfChain(Integer chainId);

    List<PharmacyChainDto> findAllPharmacyChain();

    PharmacyChainDto findPharmacyChainById(Integer id);

    void savePharmacyChain(PharmacyChainDto pharmacyChainDto);

    void deletePharmacyChainById(Integer id);

}
