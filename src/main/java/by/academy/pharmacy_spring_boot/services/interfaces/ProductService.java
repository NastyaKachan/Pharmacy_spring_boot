package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.filters.ProductFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<ProductDto> findProductWithPaginated(ProductFilter productFilter, int numberPage, int size, String sortField,
                                              String sortDir);

    List<PharmacyDto> findPharmaciesOfProduct(Integer productId);

    List<ProductDto> findAllProducts();

    ProductDto findProductById(Integer id);

    void saveProduct(ProductDto productDto);

    void deleteProductById(Integer id);
}
