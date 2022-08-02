package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.MnnDto;
import by.academy.pharmacy_spring_boot.filters.MnnFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MnnService {
    Page<MnnDto> findMnnWithPaginated(MnnFilter mnnFilter, int numberPage, int size, String sortField,
                                      String sortDir);

    List<MnnDto> findAllMnn();

    MnnDto findMnnById(Integer id);

    void createMnn(MnnDto mnnDto);

    void updateMnn(MnnDto mnnDto);

    void deleteMnnById(Integer id);
}
