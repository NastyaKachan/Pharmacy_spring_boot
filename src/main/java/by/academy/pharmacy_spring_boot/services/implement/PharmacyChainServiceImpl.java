package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.entity.PharmacyChain;
import by.academy.pharmacy_spring_boot.filters.PharmacyChainFilter;
import by.academy.pharmacy_spring_boot.mapper.PharmacyChainMapper;
import by.academy.pharmacy_spring_boot.mapper.PharmacyMapper;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyChainServiceImpl implements PharmacyChainService {

    private final PharmacyChainMapper pharmacyChainMapper;
    private final PharmacyMapper pharmacyMapper;
    private final PharmacyChainRepository pharmacyChainRepository;

    @Override
    public Page<PharmacyChainDto> findChainWithPaginated(PharmacyChainFilter pharmacyChainFilter, int numberPage,
                                                         int size, String sortField, String sortDir) {
        Specification<PharmacyChain> pharmacyChainSpecification =
                Specification
                        .where(Optional.ofNullable(pharmacyChainFilter.getNameChainFilter())
                                .map(PharmacyChainSpecification::getChainByName)
                                .orElse(null));
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable page = PageRequest.of(numberPage - 1, size, sort);
        return pharmacyChainRepository.findAll(pharmacyChainSpecification, page).map(pharmacyChainMapper::toDto);
    }

    @Override
    @Transactional
    public List<PharmacyDto> findPharmaciesOfChain(Integer chainId) {
        return pharmacyChainRepository.findPharmacyOfChain(chainId)
                .stream()
                .map(pharmacyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacyChainDto> findAllPharmacyChain() {
        return pharmacyChainRepository.findAll()
                .stream()
                .map(pharmacyChainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PharmacyChainDto findPharmacyChainById(Integer id) {
        return pharmacyChainMapper.toDto(pharmacyChainRepository.findById(id).orElse(null));
    }

    @Override
    public void savePharmacyChain(PharmacyChainDto pharmacyChainDto) {
        pharmacyChainRepository.save(pharmacyChainMapper.toEntity(pharmacyChainDto));
    }

    @Override
    public void deletePharmacyChainById(Integer id) {
        pharmacyChainRepository.deleteById(id);
    }
}
