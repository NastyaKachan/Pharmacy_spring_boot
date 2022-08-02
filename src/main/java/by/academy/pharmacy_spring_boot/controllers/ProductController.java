package by.academy.pharmacy_spring_boot.controllers;

import by.academy.pharmacy_spring_boot.dto.*;
import by.academy.pharmacy_spring_boot.enumeration.PrescriptionEnum;
import by.academy.pharmacy_spring_boot.filters.*;
import by.academy.pharmacy_spring_boot.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes(value = {"producerDtoList", "mnnDtoList", "categoryDtoList", "pharmacyDtoList", "page", "size", "sortField", "sortDir"})
public class ProductController {
    public static final int NUMBER_PAGE = 1;
    public static final int SIZE = 5;
    public static final String ID = "id";
    public static final String ASC = "asc";
    public static final String PRODUCER_FILTER = "producerFilter";
    public static final String DRUG_NAME_FILTER = "drugNameFilter";
    public static final String PHARMACY_FILTER = "pharmacyFilter";
    public static final String PRODUCT_DTO_LIST = "productDtoList";
    public static final String NUMBER_PAGE1 = "numberPage";
    public static final String SIZE1 = "size";
    public static final String TOTAL_PAGES = "totalPages";
    public static final String TOTAL_ELEMENTS = "totalElements";
    public static final String SORT_FIELD = "sortField";
    public static final String SORT_DIR = "sortDir";
    public static final String DESC_SORT = "descSort";
    public static final String ASС = "asc";
    public static final String DESC = "desc";
    public static final String PRODUCERS = "producers";
    public static final String PRODUCER_DTO = "producerDto";
    public static final String MNN_DTO_LIST = "mnnDtoList";
    public static final String MNN_DTO = "mnnDto";
    public static final String CATEGORY_DTO_LIST = "categoryDtoList";
    public static final String CATEGORY_DTO = "categoryDto";
    public static final String PHARMACY_DTO_LIST = "pharmacyDtoList";
    public static final String PHARMACY_DTO_LIST1 = "pharmacyDtoList";
    public static final String NUMBER_PAGE2 = "numberPage";
    public static final String PRODUCT_DTO = "productDto";
    public static final String PRODUCER_DTO1 = "producerDto";
    public static final String MNN_DTO1 = "mnnDto";
    public static final String CATEGORY_DTO1 = "categoryDto";
    public static final String PHARMACY_DTO_LIST2 = "pharmacyDtoList";
    private final ProductService productService;
    private final ProducerService producerService;
    private final MnnService mnnService;
    private final CategoryService categoryService;
    private final PharmacyService pharmacyService;


    @GetMapping("/products")
    public String first(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return findProducts(NUMBER_PAGE, SIZE, ID, ASС, null, null, null, model, sessionStatus);
    }

    @GetMapping("/products/page/{numberPage}")
    public String findProducts(@PathVariable("numberPage") int numberPage, int size,
                               String sortField, String sortDir, String producerFilter, String drugNameFilter,
                               String pharmacyFilter, Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        ProductFilter productFilter = ProductFilter.builder()
                .producerFilter(producerFilter)
                .drugNameFilter(drugNameFilter)
                .pharmacyFilter(pharmacyFilter)
                .build();
        Page<ProductDto> page = productService.findAllProductsWithPaginated(numberPage, size, sortField, sortDir, productFilter);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<ProductDto> productDtoList = page.getContent();
        paginatedAndSorted(numberPage, size, totalPages, totalElements, sortField, sortDir, model);
        model.addAttribute(PRODUCER_FILTER, producerFilter);
        model.addAttribute(DRUG_NAME_FILTER, drugNameFilter);
        model.addAttribute(PHARMACY_FILTER, pharmacyFilter);
        model.addAttribute(PRODUCT_DTO_LIST, productDtoList);
        return "products";
    }

    private void paginatedAndSorted(@PathVariable("numberPage") int numberPage, int size, int totalPages, long totalElements,
                                    String sortField, String sortDir, Model model) {
        model.addAttribute(NUMBER_PAGE1, numberPage);
        model.addAttribute(SIZE1, size);
        model.addAttribute(TOTAL_PAGES, totalPages);
        model.addAttribute(TOTAL_ELEMENTS, totalElements);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        model.addAttribute(DESC_SORT, sortDir.equals(ASС) ? DESC : ASС);
    }

    @GetMapping("/products/add_product")
    public String addProduct(Model model) {
        model.addAttribute("prescription", PrescriptionEnum.values());
        return "add_product";
    }

    @GetMapping("/products/add_product/select_producer")
    public String selectProducer(Model model) {
        return findProducers(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping("/products/add_product/select_producer/page/{numberPage}")
    public String findProducers(@PathVariable("numberPage") int numberPage, int size,
                                String sortField, String sortDir, String nameFilter, Model model) {
        selectProducerForProduct(numberPage, size, sortField, sortDir, nameFilter, model);
        return "add_product-producer";
    }

    private void selectProducerForProduct(int numberPage, int size, String sortField, String sortDir, String nameFilter, Model model) {
        ProducerFilter producerFilter = ProducerFilter.builder()
                .nameProducer(nameFilter)
                .build();
        Page<ProducerDto> page = producerService.findAllProducersWithPaginated(producerFilter, numberPage, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<ProducerDto> producers = page.getContent();
        paginatedAndSorted(numberPage, size, totalPages, totalElements, sortField, sortDir, model);
        model.addAttribute(PRODUCERS, producers);
    }

    @GetMapping("/products/add_product/select_producer/save")
    public String saveProducer(Model model, Integer idProducer) {
        ProducerDto producerDto = producerService.findProducerById(idProducer);
        model.addAttribute(PRODUCER_DTO, producerDto);
        return "add_product";
    }

    @GetMapping("/products/add_product/select_mnn")
    public String selectMnn(Model model) {
        return findMnn(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping("/products/add_product/select_mnn/page/{numberPage}")
    public String findMnn(@PathVariable("numberPage") int numberPage, int size,
                          String sortField, String sortDir, String titleFilter, Model model) {
        selectMnnForProduct(numberPage, size, sortField, sortDir, titleFilter, model);
        return "add_product-mnn";
    }

    private void selectMnnForProduct(int numberPage, int size, String sortField, String sortDir, String titleFilter, Model model) {
        MnnFilter mnnFilter = MnnFilter.builder()
                .mnnFilter(titleFilter)
                .build();
        Page<MnnDto> page = mnnService.findMnnWithPaginated(mnnFilter, numberPage, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<MnnDto> mnnDtoList = page.getContent();
        paginatedAndSorted(numberPage, size, totalPages, totalElements, sortField, sortDir, model);
        model.addAttribute(MNN_DTO_LIST, mnnDtoList);
    }

    @GetMapping("/products/add_product/select_mnn/save")
    public String saveMnn(Model model, Integer idMnn) {
        MnnDto mnnDto = mnnService.findMnnById(idMnn);
        model.addAttribute(MNN_DTO, mnnDto);
        return "add_product";
    }

    @GetMapping("/products/add_product/select_category")
    public String selectCategory(Model model) {
        return findCategory(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping("/products/add_product/select_category/page/{numberPage}")
    public String findCategory(@PathVariable("numberPage") int numberPage, int size,
                               String sortField, String sortDir, String titleFilter, Model model) {
        selectCategoryForProduct(numberPage, size, sortField, sortDir, titleFilter, model);
        return "add_product-category";
    }

    private void selectCategoryForProduct(int numberPage, int size, String sortField, String sortDir, String titleFilter, Model model) {
        CategoryFilter categoryFilter = CategoryFilter.builder()
                .categoryFilter(titleFilter)
                .build();
        Page<CategoryDto> page = categoryService.findCategoryWithPaginated(categoryFilter, numberPage, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<CategoryDto> categoryDtoList = page.getContent();
        paginatedAndSorted(numberPage, size, totalPages, totalElements, sortField, sortDir, model);
        model.addAttribute(CATEGORY_DTO_LIST, categoryDtoList);
    }

    @GetMapping("/products/add_product/select_category/save")
    public String saveCategory(Model model, Integer idCategory) {
        CategoryDto categoryDto = categoryService.findCategoryById(idCategory);
        model.addAttribute(CATEGORY_DTO, categoryDto);
        return "add_product";
    }

    @GetMapping("/products/add_product/select_pharmacies")
    public String selectPharmacies(Model model) {
        return findPharmacy(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping("/products/add_product/select_pharmacies/page/{numberPage}")
    public String findPharmacy(@PathVariable("numberPage") int numberPage, int size, String sortField,
                               String sortDir, String titleFilter, Model model) {
        selectPharmaciesForProduct(numberPage, size, sortField, sortDir, titleFilter, model);
        return "add_product-pharmacies";
    }

    private void selectPharmaciesForProduct(int numberPage, int size, String sortField, String sortDir,
                                            String titleFilter, Model model) {
        PharmacyFilter pharmacyFilter = PharmacyFilter.builder()
                .namePharmacyFilter(titleFilter)
                .build();
        Page<PharmacyDto> page = pharmacyService.findPharmacyWithPaginated(pharmacyFilter, numberPage, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<PharmacyDto> pharmacyDtoList = page.getContent();
        paginatedAndSorted(numberPage, size, totalPages, totalElements, sortField, sortDir, model);
        model.addAttribute(PHARMACY_DTO_LIST, pharmacyDtoList);
    }

    @GetMapping("/products/add_product/select_pharmacies/save")
    public String savePharmacies(Model model, Integer[] idPharmacies) {
        List<PharmacyDto> pharmacyDtoList = pharmacyService.findPharmaciesByIds(idPharmacies);
        model.addAttribute(PHARMACY_DTO_LIST1, pharmacyDtoList);
        return "add_product";
    }

    @PostMapping("/products/add_product")
    public String createProduct(Integer producerId, Integer mnnId, Integer categoryId, Integer[] pharmaciesId,
                                String drugName, PrescriptionEnum prescriptionEnum, String instruction, Double price,
                                Double count, Date dateTime) {
        productService.saveProduct(producerId, mnnId, categoryId, pharmaciesId, drugName, prescriptionEnum, instruction,
                price, count, dateTime);
        return "redirect:/products";
    }

    @GetMapping("/products/page/{idProduct}/{numberPage}")
    public String editProduct(@PathVariable("idProduct") Integer idProduct, @PathVariable("numberPage") int numberPage,
                              int size, String sortField, String sortDir, Model model) {
        ProductDto productDto = productService.findProductById(idProduct);
        model.addAttribute(NUMBER_PAGE2, numberPage);
        model.addAttribute(SIZE1, size);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        model.addAttribute(PRODUCT_DTO, productDto);
        return "edit_product";
    }

    @GetMapping("/products/edit_product/select_producer")
    public String selectProducerEdit(Model model) {
        return findProducerEdit(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping("/products/edit_product/select_producer/page/{numberPage}")
    public String findProducerEdit(@PathVariable("numberPage") int numberPage, int size,
                                   String sortField, String sortDir, String producerFilter, Model model) {
        selectProducerForProduct(numberPage, size, sortField, sortDir, producerFilter, model);
        return "edit_product-producer";
    }

    @GetMapping("/products/edit_product/select_producer/save")
    public String saveProducerEdit(Model model, Integer producerId) {
        ProducerDto producerDto = producerService.findProducerById(producerId);
        model.addAttribute(PRODUCER_DTO1, producerDto);
        return "edit_product";
    }

    @GetMapping("/products/edit_product/select_mnn")
    public String selectMnnEdit(Model model) {
        return findMnnEdit(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping("/products/edit_product/select_mnn/page/{numberPage}")
    public String findMnnEdit(@PathVariable("numberPage") int numberPage, int size,
                              String sortField, String sortDir, String mnnFilter, Model model) {
        selectMnnForProduct(numberPage, size, sortField, sortDir, mnnFilter, model);
        return "edit_product-mnn";
    }

    @GetMapping("/products/edit_product/select_mnn/save")
    public String saveMnnEdit(Model model, Integer mnnId) {
        MnnDto mnnDto = mnnService.findMnnById(mnnId);
        model.addAttribute(MNN_DTO1, mnnDto);
        return "edit_product";
    }

    @GetMapping("/products/edit_product/select_category")
    public String selectCategoryEdit(Model model) {
        return findCategoryEdit(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping("/products/edit_product/select_category/page/{numberPage}")
    public String findCategoryEdit(@PathVariable("numberPage") int numberPage, int size,
                                   String sortField, String sortDir, String categoryFilter, Model model) {
        selectCategoryForProduct(numberPage, size, sortField, sortDir, categoryFilter, model);
        return "edit_product-category";
    }

    @GetMapping("/products/edit_product/select_category/save")
    public String saveCategoryEdit(Model model, Integer categoryId) {
        CategoryDto categoryDto = categoryService.findCategoryById(categoryId);
        model.addAttribute(CATEGORY_DTO1, categoryDto);
        return "edit_product";
    }

    @GetMapping("/products/edit_product/select_pharmacies")
    public String selectPharmacyEdit(Model model) {
        return findPharmacyEdit(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping("/products/edit_product/select_pharmacies/page/{numberPage}")
    public String findPharmacyEdit(@PathVariable("numberPage") int numberPage, int size, String sortField,
                                   String sortDir, String pharmacyFilter, Model model) {
        selectPharmaciesForProduct(numberPage, size, sortField, sortDir, pharmacyFilter, model);
        return "edit_product-pharmacies";
    }

    @GetMapping("/products/edit_product/select_pharmacies/save")
    public String savePharmacyEdit(Model model, Integer[] pharmaciesIds) {
        List<PharmacyDto> pharmacyDtoList = pharmacyService.findPharmaciesByIds(pharmaciesIds);
        model.addAttribute(PHARMACY_DTO_LIST2, pharmacyDtoList);
        return "edit_product";
    }

    @PostMapping("/products/edit_product/{idProduct}")
    public String updateBook(@PathVariable("idProduct") Integer idProduct, Integer producerId, Integer mnnId, Integer categoryId,
                             Integer[] pharmaciesIds, String drugName, PrescriptionEnum prescription,
                             String instruction, Double price, Double count, Date dateTime, int numberPage,
                             int size, String sortField, String sortDir, Model model, SessionStatus sessionStatus) {
        productService.saveProduct(producerId, mnnId, categoryId, pharmaciesIds, drugName, prescription, instruction, price,
                count, dateTime);
        return findProducts(numberPage, size, sortField, sortDir, null, null,
                null, model, sessionStatus);
    }

    @PostMapping("/products/page/{idProduct}/{numberPage}")
    public String deleteProduct(@PathVariable("idProduct") Integer idProduct, @PathVariable("numberPage") int numberPage,
                                int size, String sortField, String sortDir, SessionStatus sessionStatus, Model model) {
        productService.deleteProductById(idProduct);
        model.addAttribute(NUMBER_PAGE1, numberPage);
        model.addAttribute(SIZE1, size);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        return findProducts(numberPage, size, sortField, sortDir, null, null, null, model,
                sessionStatus);
    }

}
