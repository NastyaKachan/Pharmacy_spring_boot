package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
import by.academy.pharmacy_spring_boot.entity.PharmacyChain;
import by.academy.pharmacy_spring_boot.filters.PharmacyChainFilter;
import by.academy.pharmacy_spring_boot.mapper.PharmacyChainMapper;
import by.academy.pharmacy_spring_boot.repository.PharmacyChainRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.PharmacyChainService;
import by.academy.pharmacy_spring_boot.specifications.PharmacyChainSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyChainServiceImpl implements PharmacyChainService {

    private final PharmacyChainMapper pharmacyChainMapper;
    private final PharmacyChainRepository pharmacyChainRepository;

    @Override
    public List<PharmacyChainDto> findAllPharmacyChain() {
        return pharmacyChainRepository.findAll()
                .stream()
                .map(pharmacyChainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PharmacyChainDto findPharmacyChainById(Integer id) {
        return pharmacyChainRepository.findById(id)
                .map(pharmacyChainMapper::toDto)
                .orElse(null);
    }

    @Override
    public void createPharmacyChain(PharmacyChainDto pharmacyChainDto) {
        pharmacyChainRepository.save(pharmacyChainMapper.toEntity(pharmacyChainDto));
    }

    @Override
    public void updatePharmacyChain(PharmacyChainDto pharmacyChainDto) {
        pharmacyChainRepository.save(pharmacyChainMapper.toEntity(pharmacyChainDto));
    }

    @Override
    public void deletePharmacyChainById(Integer id) {
        pharmacyChainRepository.deleteById(id);
    }

    @Override
    public Page<PharmacyChainDto> findAllPaginatedAndFiltered(PharmacyChainFilter filter,
                                                              int numberPage, int size, String sortField,
                                                              String sortDir) {
        Specification<PharmacyChain> pharmacyChainSpecification =
                Specification
                        .where(Optional.ofNullable(filter.getNameChainFilter())
                                .map(PharmacyChainSpecification::getChainByName)
                                .orElse(null));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable paged = PageRequest.of(numberPage - 1, size, sort);
        return pharmacyChainRepository.findAll(pharmacyChainSpecification, paged)
                .map(pharmacyChainMapper::toDto);
    }
}
