package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.MnnDto;
import by.academy.pharmacy_spring_boot.dto.ProducerDto;
import by.academy.pharmacy_spring_boot.entity.MNN;
import by.academy.pharmacy_spring_boot.entity.Producer;
import by.academy.pharmacy_spring_boot.filters.MnnFilter;
import by.academy.pharmacy_spring_boot.filters.ProducerFilter;
import by.academy.pharmacy_spring_boot.mapper.MnnMapper;
import by.academy.pharmacy_spring_boot.repository.MnnRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.MnnService;
import by.academy.pharmacy_spring_boot.specifications.MnnSpecification;
import by.academy.pharmacy_spring_boot.specifications.ProducerSpecification;
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
public class MnnServiceImpl implements MnnService {

    private final MnnMapper mnnMapper;
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
    public List<MnnDto> findAllMnn() {
        return mnnRepository.findAll()
                .stream()
                .map(mnnMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MnnDto findMnnById(Integer id) {
        return mnnRepository.findById(id)
                .map(mnnMapper::toDto)
                .orElse(null);
    }

    @Override
    public void createMnn(MnnDto mnnDto) {
        mnnRepository.save(mnnMapper.toEntity(mnnDto));
    }

    @Override
    public void updateMnn(MnnDto mnnDto) {
        mnnRepository.save(mnnMapper.toEntity(mnnDto));
    }

    @Override
    public void deleteMnnById(Integer id) {
        mnnRepository.deleteById(id);
    }
}
