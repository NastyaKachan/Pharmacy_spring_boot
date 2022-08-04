package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.ProducerDto;
import by.academy.pharmacy_spring_boot.entity.Producer;
import by.academy.pharmacy_spring_boot.filters.ProducerFilter;
import by.academy.pharmacy_spring_boot.mapper.ProducerMapper;
import by.academy.pharmacy_spring_boot.repository.ProducerRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.ProducerService;
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
public class ProducerServiceImpl implements ProducerService {

    private final ProducerMapper producerMapper;

    private final ProducerRepository producerRepository;

    @Override
    public Page<ProducerDto> findProducerWithPaginated(ProducerFilter producerFilter, int numberPage, int size, String sortField,
                                                           String sortDir) {
        Specification<Producer> producerSpecification =
                Specification
                        .where(Optional.ofNullable(producerFilter.getNameProducer())
                                .map(ProducerSpecification::getProducerByName)
                                .orElse(null));
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable page = PageRequest.of(numberPage - 1, size, sort);
        return producerRepository.findAll(producerSpecification, page).map(producerMapper::toDto);
    }

    @Override
    public List<ProducerDto> findAllProducers() {
        return producerRepository.findAll()
                .stream()
                .map(producerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProducerDto findProducerById(Integer id) {
        return producerMapper.toDto(producerRepository.findById(id).orElse(null));
    }

    @Override
    public void saveProducer(ProducerDto producerDto) {
        producerRepository.save(producerMapper.toEntity(producerDto));
    }

    @Override
    public void deleteProducerById(Integer id) {
        producerRepository.deleteById(id);
    }
}
