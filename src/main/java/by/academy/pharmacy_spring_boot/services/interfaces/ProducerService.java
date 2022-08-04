package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.ProducerDto;
import by.academy.pharmacy_spring_boot.filters.ProducerFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProducerService {
    Page<ProducerDto> findProducerWithPaginated(ProducerFilter producerFilter, int numberPage, int size, String sortField,
                                                String sortDir);

    List<ProducerDto> findAllProducers();

    ProducerDto findProducerById(Integer id);

    void saveProducer(ProducerDto producerDto);

    void deleteProducerById(Integer id);
}
