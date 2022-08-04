package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.entity.Product;
import by.academy.pharmacy_spring_boot.filters.ProductFilter;
import by.academy.pharmacy_spring_boot.mapper.PharmacyMapper;
import by.academy.pharmacy_spring_boot.mapper.ProductMapper;
import by.academy.pharmacy_spring_boot.repository.ProductRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.ProductService;
import by.academy.pharmacy_spring_boot.specifications.ProductSpecification;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PharmacyMapper pharmacyMapper;


    @Override
    public Page<ProductDto> findProductWithPaginated(ProductFilter productFilter, int page, int size,
                                                     String fieldSort, String typeSort) {
        Specification<Product> productSpecification =
                Specification.where(Optional.ofNullable(productFilter.getDrugNameFilter())
                                .map(ProductSpecification::getProductByName)
                                .orElse(null))
                        .and(Optional.ofNullable(productFilter.getProducerFilter())
                                .map(ProductSpecification::getProductByProducer)
                                .orElse(null))
                        .and(Optional.ofNullable(productFilter.getPharmacyFilter())
                                .map(ProductSpecification::getProductByPharmacy)
                                .orElse(null));
        Sort sort = typeSort.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(fieldSort).ascending() :
                Sort.by(fieldSort).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return productRepository.findAll(productSpecification, pageable).map(productMapper::toDto);
    }

    @Override
    @Transactional
    public List<PharmacyDto> findPharmaciesOfProduct(Integer productId) {
        return productRepository.findPharmaciesOfProduct(productId)
                .stream()
                .map(pharmacyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductById(Integer id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        productRepository.save(productMapper.toEntity(productDto));
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }


}
