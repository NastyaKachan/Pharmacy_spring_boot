package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.entity.Pharmacy;
import by.academy.pharmacy_spring_boot.filters.PharmacyFilter;
import by.academy.pharmacy_spring_boot.mapper.PharmacyMapper;
import by.academy.pharmacy_spring_boot.mapper.ProductMapper;
import by.academy.pharmacy_spring_boot.repository.PharmacyRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.PharmacyService;
import by.academy.pharmacy_spring_boot.specifications.PharmacySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyMapper pharmacyMapper;
    private final ProductMapper productMapper;
    private final PharmacyRepository pharmacyRepository;

    @Override
    public Page<PharmacyDto> findPharmacyWithPaginated(PharmacyFilter pharmacyFilter, int numberPage, int size, String sortField,
                                                       String sortDir) {
        Specification<Pharmacy> pharmacySpecification =
                Specification
                        .where(Optional.ofNullable(pharmacyFilter.getNamePharmacyFilter())
                                .map(PharmacySpecification::getPharmacyByTitle)
                                .orElse(null));
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable page = PageRequest.of(numberPage - 1, size, sort);
        return pharmacyRepository.findAll(pharmacySpecification, page).map(pharmacyMapper::toDto);
    }

    @Override
    @Transactional
    public List<ProductDto> findProductsOfPharmacy(Integer pharmacyId) {
        return pharmacyRepository.findProductsOfPharmacy(pharmacyId)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacyDto> findAllPharmacies() {
        return pharmacyRepository.findAll()
                .stream()
                .map(pharmacyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PharmacyDto findPharmacyById(Integer id) {
        return pharmacyMapper.toDto(pharmacyRepository.findById(id).orElse(null));
    }

    @Override
    public void savePharmacy(PharmacyDto pharmacyDto) {
        pharmacyRepository.save(pharmacyMapper.toEntity(pharmacyDto));
    }

    @Override
    public void deletePharmacyById(Integer id) {
        pharmacyRepository.deleteById(id);
    }
}
