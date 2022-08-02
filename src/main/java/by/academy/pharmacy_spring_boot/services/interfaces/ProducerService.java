package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.ProducerDto;
import by.academy.pharmacy_spring_boot.filters.ProducerFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProducerService {

    List<ProducerDto> findAllProducers();

    Page<ProducerDto> findAllProducersWithPaginated(ProducerFilter producerFilter, int numberPage, int size, String sortField,
                                                    String sortDir);

    ProducerDto findProducerById(Integer id);

    void createProducer(ProducerDto producerDto);

    void updateProducer(ProducerDto producerDto);

    void deleteProducerById(Integer id);
}
