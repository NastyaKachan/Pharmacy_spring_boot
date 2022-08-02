package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.enumeration.PrescriptionEnum;
import by.academy.pharmacy_spring_boot.filters.ProductFilter;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface ProductService {

    Page<ProductDto> findAllProductsWithPaginated(int page, int size, String fieldSort, String typeSort, ProductFilter productFilter);

    ProductDto findProductById(Integer id);

    void saveProduct(Integer idProducer, Integer idMnn, Integer idCategory, Integer[] idPharmacies,
                     String drugName, PrescriptionEnum prescription, String instruction, Double price,
                     Double count, Date dateUpdate);

    void deleteProductById(Integer id);
}
