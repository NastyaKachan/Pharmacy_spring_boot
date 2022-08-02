package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.entity.Product;
import by.academy.pharmacy_spring_boot.enumeration.PrescriptionEnum;
import by.academy.pharmacy_spring_boot.filters.ProductFilter;
import by.academy.pharmacy_spring_boot.mapper.*;
import by.academy.pharmacy_spring_boot.repository.*;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PharmacyRepository pharmacyRepository;
    private final ProducerRepository producerRepository;
    private final MnnRepository mnnRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProducerMapper producerMapper;
    private final MnnMapper mnnMapper;
    private final CategoryMapper categoryMapper;
    private final SubMapper subMapper;
    private final PharmacyMapper pharmacyMapper;


    @Override
    public Page<ProductDto> findAllProductsWithPaginated(int page, int size,
                                                         String fieldSort, String typeSort, ProductFilter productFilter) {
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
    public ProductDto findProductById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public void saveProduct(Integer idProducer, Integer idMnn, Integer idCategory, Integer[] idPharmacies,
                            String drugName, PrescriptionEnum prescription, String instruction, Double price,
                            Double count, Date dateUpdate) {
        List<Integer> pharmacyList = new ArrayList<>();
        Collections.addAll(pharmacyList, idPharmacies);
        List<ProductDto.PharmacyDto> pharmacyDtoList = pharmacyRepository.findAllById(pharmacyList).stream()
                .map(pharmacyMapper::toDto)
                .map(subMapper::toPharmacyDto)
                .collect(Collectors.toList());
        ProductDto.ProducerDto producerDto = producerRepository.findById(idProducer)
                .map(producerMapper::toDto)
                .map(subMapper::toProducerDto)
                .orElse(null);
        ProductDto.MnnDto mnnDto = mnnRepository.findById(idMnn)
                .map(mnnMapper::toDto)
                .map(subMapper::toMnnDto)
                .orElse(null);
        ProductDto.CategoryDto categoryDto = categoryRepository.findById(idCategory)
                .map(categoryMapper::toDto)
                .map(subMapper::toCategoryDto)
                .orElse(null);

        ProductDto productDto = ProductDto.builder()
                .drugName(drugName)
                .prescription(prescription)
                .instruction(instruction)
                .price(price)
                .countAvailable(count)
                .datetimeUpdate(dateUpdate)
                .producer(producerDto)
                .mnn(mnnDto)
                .category(categoryDto)
                .pharmacies(pharmacyDtoList)
                .build();
        productRepository.save(productMapper.toEntity(productDto));
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }


}
