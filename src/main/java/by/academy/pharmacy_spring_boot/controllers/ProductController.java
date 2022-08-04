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

import java.util.List;

import static by.academy.pharmacy_spring_boot.constants.Constants.*;

@Controller
@RequiredArgsConstructor
@SessionAttributes(value = {ProductController.PRODUCER_DTO_LIST, ProductController.MNN_DTO_LIST, ProductController.CATEGORY_DTO_LIST, ProductController.PHARMACY_DTO_LIST,
        ProductController.PAGE, SIZE, SORT_FIELD, SORT_DIR})
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
    public static final String PRODUCER_DTO_LIST = "producerDtoList";
    public static final String PAGE = "page";
    public static final String PRODUCTS = "/products";
    public static final String PRODUCTS_PAGE_NUMBER_PAGE = "/products/page/{numberPage}";
    public static final String NUMBER_PAGE3 = "numberPage";
    public static final String PRODUCTS_ADD_PRODUCT = "/products/add_product";
    public static final String PRESCRIPTION = "prescription";
    public static final String ADD_PRODUCT = "add_product";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_PRODUCER = "/products/add_product/select_producer";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_PRODUCER_PAGE_NUMBER_PAGE = "/products/add_product/select_producer/page/{numberPage}";
    public static final String ADD_PRODUCT_PRODUCER = "add_product-producer";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_PRODUCER_SAVE = "/products/add_product/select_producer/save";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_MNN = "/products/add_product/select_mnn";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_MNN_PAGE_NUMBER_PAGE = "/products/add_product/select_mnn/page/{numberPage}";
    public static final String ADD_PRODUCT_MNN = "add_product-mnn";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_MNN_SAVE = "/products/add_product/select_mnn/save";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_CATEGORY = "/products/add_product/select_category";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_CATEGORY_PAGE_NUMBER_PAGE = "/products/add_product/select_category/page/{numberPage}";
    public static final String ADD_PRODUCT_CATEGORY = "add_product-category";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_CATEGORY_SAVE = "/products/add_product/select_category/save";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_PHARMACIES = "/products/add_product/select_pharmacies";
    public static final String PRODUCTS_ADD_PRODUCT_SELECT_PHARMACIES_PAGE_NUMBER_PAGE = "/products/add_product/select_pharmacies/page/{numberPage}";
    public static final String ADD_PRODUCT_PHARMACIES = "add_product-pharmacies";
    public static final String PRODUCTS_ADD_PRODUCT1 = "/products/add_product";
    public static final String REDIRECT_PRODUCTS = "redirect:/products";
    public static final String PRODUCTS_PAGE_ID_PRODUCT_NUMBER_PAGE = "/products/page/{idProduct}/{numberPage}";
    public static final String ID_PRODUCT = "idProduct";
    public static final String EDIT_PRODUCT = "edit_product";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_PRODUCER = "/products/edit_product/select_producer";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_PRODUCER_PAGE_NUMBER_PAGE = "/products/edit_product/select_producer/page/{numberPage}";
    public static final String EDIT_PRODUCT_PRODUCER = "edit_product-producer";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_PRODUCER_SAVE = "/products/edit_product/select_producer/save";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_MNN = "/products/edit_product/select_mnn";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_MNN_PAGE_NUMBER_PAGE = "/products/edit_product/select_mnn/page/{numberPage}";
    public static final String EDIT_PRODUCT_MNN = "edit_product-mnn";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_MNN_SAVE = "/products/edit_product/select_mnn/save";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_CATEGORY = "/products/edit_product/select_category";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_CATEGORY_PAGE_NUMBER_PAGE = "/products/edit_product/select_category/page/{numberPage}";
    public static final String EDIT_PRODUCT_CATEGORY = "edit_product-category";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_CATEGORY_SAVE = "/products/edit_product/select_category/save";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_PHARMACIES = "/products/edit_product/select_pharmacies";
    public static final String PRODUCTS_EDIT_PRODUCT_SELECT_PHARMACIES_PAGE_NUMBER_PAGE = "/products/edit_product/select_pharmacies/page/{numberPage}";
    public static final String EDIT_PRODUCT_PHARMACIES = "edit_product-pharmacies";
    public static final String PRODUCTS_EDIT_PRODUCT_ID_PRODUCT = "/products/edit_product/{idProduct}";
    public static final String PRODUCTS_PAGE_ID_PRODUCT_NUMBER_PAGE1 = "/products/page/{idProduct}/{numberPage}";
    private final ProductService productService;
    private final ProducerService producerService;
    private final MnnService mnnService;
    private final CategoryService categoryService;
    private final PharmacyService pharmacyService;


    @GetMapping(PRODUCTS)
    public String first(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return findProducts(NUMBER_PAGE, SIZE, ID, ASС, null, null, null, model, sessionStatus);
    }

    @GetMapping(PRODUCTS_PAGE_NUMBER_PAGE)
    public String findProducts(@PathVariable(NUMBER_PAGE3) int numberPage, int size,
                               String sortField, String sortDir, String producerFilter, String drugNameFilter,
                               String pharmacyFilter, Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        ProductFilter productFilter = ProductFilter.builder()
                .producerFilter(producerFilter)
                .drugNameFilter(drugNameFilter)
                .pharmacyFilter(pharmacyFilter)
                .build();
        Page<ProductDto> page = productService.findProductWithPaginated(productFilter, numberPage, size, sortField, sortDir);
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

    private void paginatedAndSorted(@PathVariable(NUMBER_PAGE1) int numberPage, int size, int totalPages, long totalElements,
                                    String sortField, String sortDir, Model model) {
        model.addAttribute(NUMBER_PAGE1, numberPage);
        model.addAttribute(SIZE1, size);
        model.addAttribute(TOTAL_PAGES, totalPages);
        model.addAttribute(TOTAL_ELEMENTS, totalElements);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        model.addAttribute(DESC_SORT, sortDir.equals(ASС) ? DESC : ASС);
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT)
    public String addProduct(Model model) {
        model.addAttribute(PRESCRIPTION, PrescriptionEnum.values());
        return ADD_PRODUCT;
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_PRODUCER)
    public String selectProducer(Model model) {
        return findProducers(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_PRODUCER_PAGE_NUMBER_PAGE)
    public String findProducers(@PathVariable(NUMBER_PAGE1) int numberPage, int size,
                                String sortField, String sortDir, String nameFilter, Model model) {
        selectProducerForProduct(numberPage, size, sortField, sortDir, nameFilter, model);
        return ADD_PRODUCT_PRODUCER;
    }

    private void selectProducerForProduct(int numberPage, int size, String sortField, String sortDir, String nameFilter, Model model) {
        ProducerFilter producerFilter = ProducerFilter.builder()
                .nameProducer(nameFilter)
                .build();
        Page<ProducerDto> page = producerService.findProducerWithPaginated(producerFilter, numberPage, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<ProducerDto> producers = page.getContent();
        paginatedAndSorted(numberPage, size, totalPages, totalElements, sortField, sortDir, model);
        model.addAttribute(PRODUCERS, producers);
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_PRODUCER_SAVE)
    public String saveProducer(Model model, Integer idProducer) {
        ProducerDto producerDto = producerService.findProducerById(idProducer);
        model.addAttribute(PRODUCER_DTO, producerDto);
        return ADD_PRODUCT;
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_MNN)
    public String selectMnn(Model model) {
        return findMnn(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_MNN_PAGE_NUMBER_PAGE)
    public String findMnn(@PathVariable(NUMBER_PAGE1) int numberPage, int size,
                          String sortField, String sortDir, String titleFilter, Model model) {
        selectMnnForProduct(numberPage, size, sortField, sortDir, titleFilter, model);
        return ADD_PRODUCT_MNN;
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

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_MNN_SAVE)
    public String saveMnn(Model model, Integer idMnn) {
        MnnDto mnnDto = mnnService.findMnnById(idMnn);
        model.addAttribute(MNN_DTO, mnnDto);
        return ADD_PRODUCT;
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_CATEGORY)
    public String selectCategory(Model model) {
        return findCategory(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_CATEGORY_PAGE_NUMBER_PAGE)
    public String findCategory(@PathVariable(NUMBER_PAGE1) int numberPage, int size,
                               String sortField, String sortDir, String titleFilter, Model model) {
        selectCategoryForProduct(numberPage, size, sortField, sortDir, titleFilter, model);
        return ADD_PRODUCT_CATEGORY;
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

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_CATEGORY_SAVE)
    public String saveCategory(Model model, Integer idCategory) {
        CategoryDto categoryDto = categoryService.findCategoryById(idCategory);
        model.addAttribute(CATEGORY_DTO, categoryDto);
        return ADD_PRODUCT;
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_PHARMACIES)
    public String selectPharmacies(Model model) {
        return findPharmacy(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping(PRODUCTS_ADD_PRODUCT_SELECT_PHARMACIES_PAGE_NUMBER_PAGE)
    public String findPharmacy(@PathVariable(NUMBER_PAGE1) int numberPage, int size, String sortField,
                               String sortDir, String titleFilter, Model model) {
        selectPharmaciesForProduct(numberPage, size, sortField, sortDir, titleFilter, model);
        return ADD_PRODUCT_PHARMACIES;
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


    @PostMapping(PRODUCTS_ADD_PRODUCT1)
    public String createProduct(ProductDto productDto) {
        productService.saveProduct(productDto);
        return REDIRECT_PRODUCTS;
    }

    @GetMapping(PRODUCTS_PAGE_ID_PRODUCT_NUMBER_PAGE)
    public String editProduct(@PathVariable(ID_PRODUCT) Integer idProduct, @PathVariable(NUMBER_PAGE1) int numberPage,
                              int size, String sortField, String sortDir, Model model) {
        ProductDto productDto = productService.findProductById(idProduct);
        model.addAttribute(NUMBER_PAGE2, numberPage);
        model.addAttribute(SIZE1, size);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        model.addAttribute(PRODUCT_DTO, productDto);
        return EDIT_PRODUCT;
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_PRODUCER)
    public String selectProducerEdit(Model model) {
        return findProducerEdit(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_PRODUCER_PAGE_NUMBER_PAGE)
    public String findProducerEdit(@PathVariable(NUMBER_PAGE1) int numberPage, int size,
                                   String sortField, String sortDir, String producerFilter, Model model) {
        selectProducerForProduct(numberPage, size, sortField, sortDir, producerFilter, model);
        return EDIT_PRODUCT_PRODUCER;
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_PRODUCER_SAVE)
    public String saveProducerEdit(Model model, Integer producerId) {
        ProducerDto producerDto = producerService.findProducerById(producerId);
        model.addAttribute(PRODUCER_DTO1, producerDto);
        return EDIT_PRODUCT;
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_MNN)
    public String selectMnnEdit(Model model) {
        return findMnnEdit(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_MNN_PAGE_NUMBER_PAGE)
    public String findMnnEdit(@PathVariable(NUMBER_PAGE1) int numberPage, int size,
                              String sortField, String sortDir, String mnnFilter, Model model) {
        selectMnnForProduct(numberPage, size, sortField, sortDir, mnnFilter, model);
        return EDIT_PRODUCT_MNN;
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_MNN_SAVE)
    public String saveMnnEdit(Model model, Integer mnnId) {
        MnnDto mnnDto = mnnService.findMnnById(mnnId);
        model.addAttribute(MNN_DTO1, mnnDto);
        return EDIT_PRODUCT;
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_CATEGORY)
    public String selectCategoryEdit(Model model) {
        return findCategoryEdit(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_CATEGORY_PAGE_NUMBER_PAGE)
    public String findCategoryEdit(@PathVariable(NUMBER_PAGE1) int numberPage, int size,
                                   String sortField, String sortDir, String categoryFilter, Model model) {
        selectCategoryForProduct(numberPage, size, sortField, sortDir, categoryFilter, model);
        return EDIT_PRODUCT_CATEGORY;
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_CATEGORY_SAVE)
    public String saveCategoryEdit(Model model, Integer categoryId) {
        CategoryDto categoryDto = categoryService.findCategoryById(categoryId);
        model.addAttribute(CATEGORY_DTO1, categoryDto);
        return EDIT_PRODUCT;
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_PHARMACIES)
    public String selectPharmacyEdit(Model model) {
        return findPharmacyEdit(NUMBER_PAGE, SIZE, ID, ASС, null, model);
    }

    @GetMapping(PRODUCTS_EDIT_PRODUCT_SELECT_PHARMACIES_PAGE_NUMBER_PAGE)
    public String findPharmacyEdit(@PathVariable(NUMBER_PAGE1) int numberPage, int size, String sortField,
                                   String sortDir, String pharmacyFilter, Model model) {
        selectPharmaciesForProduct(numberPage, size, sortField, sortDir, pharmacyFilter, model);
        return EDIT_PRODUCT_PHARMACIES;
    }

    @PostMapping(PRODUCTS_EDIT_PRODUCT_ID_PRODUCT)
    public String updateProduct(@PathVariable(ID_PRODUCT) Integer idProduct, ProductDto productDto, int numberPage,
                                int size, String sortField, String sortDir, Model model, SessionStatus sessionStatus) {
        productService.saveProduct(productDto);
        return findProducts(numberPage, size, sortField, sortDir, null, null,
                null, model, sessionStatus);
    }

    @PostMapping(PRODUCTS_PAGE_ID_PRODUCT_NUMBER_PAGE1)
    public String deleteProduct(@PathVariable(ID_PRODUCT) Integer idProduct, @PathVariable(NUMBER_PAGE1) int numberPage,
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
