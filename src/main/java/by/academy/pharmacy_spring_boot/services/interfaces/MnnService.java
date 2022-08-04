package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.MnnDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.filters.MnnFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MnnService {

    Page<MnnDto> findMnnWithPaginated(MnnFilter mnnFilter, int numberPage, int size, String sortField,
                                      String sortDir);

    List<ProductDto> findProductWithMnn(Integer mnnId);

    List<MnnDto> findAllMnn();

    MnnDto findMnnById(Integer id);

    void saveMnn(MnnDto mnnDto);

    void deleteMnnById(Integer id);
}
