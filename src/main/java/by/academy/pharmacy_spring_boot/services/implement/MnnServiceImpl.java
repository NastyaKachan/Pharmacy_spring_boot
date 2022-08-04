package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.MnnDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.entity.MNN;
import by.academy.pharmacy_spring_boot.filters.MnnFilter;
import by.academy.pharmacy_spring_boot.mapper.MnnMapper;
import by.academy.pharmacy_spring_boot.mapper.ProductMapper;
import by.academy.pharmacy_spring_boot.repository.MnnRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.MnnService;
import by.academy.pharmacy_spring_boot.specifications.MnnSpecification;
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
public class MnnServiceImpl implements MnnService {

    private final MnnMapper mnnMapper;
    private final ProductMapper productMapper;
    private final MnnRepository mnnRepository;

    @Override
    public Page<MnnDto> findMnnWithPaginated(MnnFilter mnnFilter, int numberPage, int size, String sortField,
                                             String sortDir) {
        Specification<MNN> mnnSpecification =
                Specification
                        .where(Optional.ofNullable(mnnFilter.getMnnFilter())
                                .map(MnnSpecification::getMnnByTitle)
                                .orElse(null));
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable page = PageRequest.of(numberPage - 1, size, sort);
        return mnnRepository.findAll(mnnSpecification, page).map(mnnMapper::toDto);
    }

    @Override
    @Transactional
    public List<ProductDto> findProductWithMnn(Integer mnnId) {
        return mnnRepository.findProductsWithMnn(mnnId)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MnnDto> findAllMnn() {
        return mnnRepository.findAll()
                .stream()
                .map(mnnMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MnnDto findMnnById(Integer id) {
        return mnnMapper.toDto(mnnRepository.findById(id).orElse(null));
    }

    @Override
    public void saveMnn(MnnDto mnnDto) {
        mnnRepository.save(mnnMapper.toEntity(mnnDto));
    }

    @Override
    public void deleteMnnById(Integer id) {
        mnnRepository.deleteById(id);
    }
}
