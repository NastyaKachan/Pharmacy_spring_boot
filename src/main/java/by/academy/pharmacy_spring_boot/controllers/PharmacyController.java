package by.academy.pharmacy_spring_boot.controllers;

import by.academy.pharmacy_spring_boot.dto.CityDto;
import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.filters.PharmacyChainFilter;
import by.academy.pharmacy_spring_boot.filters.PharmacyFilter;
import by.academy.pharmacy_spring_boot.services.interfaces.CityService;
import by.academy.pharmacy_spring_boot.services.interfaces.PharmacyChainService;
import by.academy.pharmacy_spring_boot.services.interfaces.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.academy.pharmacy_spring_boot.constants.Constants.*;
import static by.academy.pharmacy_spring_boot.controllers.ProductController.ASC;
import static by.academy.pharmacy_spring_boot.controllers.ProductController.ID;

@Controller
@RequiredArgsConstructor
@SessionAttributes(value = {CITY_DTO, CHAIN_DTO})
@RequestMapping(PHARMACY)
public class PharmacyController {

    @Autowired
    private final PharmacyService pharmacyService;
    @Autowired
    private final PharmacyChainService pharmacyChainService;
    @Autowired
    private final CityService cityService;

    @GetMapping
    public String findPharmacies(Model model) {
        return findPharmaciesPaginated(PAGE_NUMBER_ONE, SIZE_FIVE, ID, ASC, null, model);
    }

    @GetMapping(PAGE_PAGE_NUMBER)
    public String findPharmaciesPaginated(@PathVariable(PAGE_NUMBER) int pageNumber, int size,
                                          String sortField, String sortDir, String nameFilter,
                                          Model model) {
        PharmacyFilter pharmacyFilter = PharmacyFilter.builder()
                .namePharmacyFilter(nameFilter)
                .build();
        Page<PharmacyDto> page = pharmacyService.findPharmacyWithPaginated(pharmacyFilter,
                pageNumber, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalChains = page.getTotalElements();
        List<PharmacyDto> pharmacyDtoList = page.getContent();
        sortedAndFilter(pageNumber, size, sortField, sortDir, totalPages, totalChains, model);
        model.addAttribute(NAME_FILTER, nameFilter);
        model.addAttribute(PHARMACY_DTO_LIST, pharmacyDtoList);
        return "pharmacy";
    }

    @GetMapping(PRODUCTS_IN_PHARMACY_ID)
    public String findProductsInPharmacy(@PathVariable(value = ID) Integer id, Model model) {
        PharmacyDto pharmacyById = pharmacyService.findPharmacyById(id);
        List<ProductDto> productsOfPharmacy = pharmacyService.findProductsOfPharmacy(id);
        model.addAttribute(PHARMACY_BY_ID, pharmacyById);
        model.addAttribute(PRODUCTS_OF_PHARMACY, productsOfPharmacy);
        return PRODUCTS_IN_PHARMACY;
    }

    @GetMapping(PHARMACY_CREATE)
    public String addPharmacy() {
        return PHARMACY_CREATE;
    }


    @GetMapping(PHARMACY_CREATE_PHARMACY_CITY)
    public String selectCity(Model model) {
        return findCities(PAGE_NUMBER_ONE, SIZE_FIVE, ID, ASC, model);
    }

    @GetMapping(PHARMACY_CREATE_PHARMACY_CITY_PAGE_PAGE_NUMBER)
    public String findCities(@PathVariable(PAGE_NUMBER) int pageNumber, int size, String sortField,
                             String sortDir, Model model) {
        findAllCities(pageNumber, size, sortField, sortDir, model);
        return PHARMACY_CITY;
    }

    @GetMapping(PHARMACY_CREATE_PHARMACY_CITY_SAVE)
    public String saveCityForPharmacy(Model model, Integer cityId) {
        CityDto cityDto = cityService.findCityById(cityId);
        model.addAttribute(CITY_DTO, cityDto);
        return PHARMACY_CREATE;
    }

    @GetMapping(PHARMACY_CREATE_PHARMACY_CHAINS)
    public String selectChain(Model model) {
        return findChains(PAGE_NUMBER_ONE, SIZE_FIVE, ID, ASC, null, model);
    }

    @GetMapping(PHARMACY_CREATE_PHARMACY_CHAINS_PAGE_PAGE_NUMBER)
    public String findChains(@PathVariable(PAGE_NUMBER) int pageNumber, int size, String sortField,
                             String sortDir, String nameFilter, Model model) {
        findAllChains(pageNumber, size, sortField, sortDir, nameFilter, model);
        return PHARMACY_CHAINS;
    }

    @GetMapping(PHARMACY_CREATE_PHARMACY_CHAINS_SAVE)
    public String saveChainForPharmacy(Model model, Integer chainId) {
        PharmacyChainDto chainDto = pharmacyChainService.findPharmacyChainById(chainId);
        model.addAttribute(CHAIN_DTO, chainDto);
        return PHARMACY_CREATE;
    }

    @PostMapping(PHARMACY_CREATE)
    public String savePharmacy(PharmacyDto pharmacyDto) {
        pharmacyService.savePharmacy(pharmacyDto);
        return REDIRECT_PHARMACY;
    }

    @GetMapping(PHARMACY_EDIT_ID)
    public String editPharmacy(@PathVariable(value = ID) Integer id, Model model) {
        PharmacyDto pharmacyDto = pharmacyService.findPharmacyById(id);
        model.addAttribute(PHARMACY_DTO, pharmacyDto);
        return PHARMACY_EDIT;
    }

    @PostMapping(PHARMACY_EDIT_ID1)
    public String updatePharmacy(@PathVariable(ID) Integer id, PharmacyDto pharmacyDto) {
        pharmacyService.savePharmacy(pharmacyDto);
        return REDIRECT_PHARMACY;
    }

    @PostMapping(DELETE_ID1)
    public String deletePharmacy(@PathVariable(ID) Integer id) {
        pharmacyService.deletePharmacyById(id);
        return REDIRECT_PHARMACY;
    }

    private void sortedAndFilter(@PathVariable(PAGE_NUMBER) int pageNumber, int size,
                                 String sortField, String sortDir, int totalPages, long totalChains, Model model) {
        model.addAttribute(CURRENT_PAGE, pageNumber);
        model.addAttribute(SIZE, size);
        model.addAttribute(TOTAL_PAGES, totalPages);
        model.addAttribute(TOTAL_CHAINS, totalChains);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        model.addAttribute(REVERSE_SORT_DIR, sortDir.equals(ASC) ? DESC : ASC);
    }

    private void findAllCities(int pageNumber, int size, String sortField, String sortDir, Model model) {
        Page<CityDto> page = cityService.findCityWithPaginated(pageNumber, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<CityDto> cityDtoList = page.getContent();
        sortedAndFilter(pageNumber, size, sortField, sortDir, totalPages, totalItems, model);
        model.addAttribute(CITY_DTO_LIST, cityDtoList);
    }

    private void findAllChains(int pageNumber, int size, String sortField, String sortDir, String nameFilter,
                               Model model) {
        PharmacyChainFilter pharmacyChainFilter = PharmacyChainFilter.builder()
                .nameChainFilter(nameFilter)
                .build();
        Page<PharmacyChainDto> page = pharmacyChainService.findChainWithPaginated(pharmacyChainFilter, pageNumber,
                size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<PharmacyChainDto> chainDtoList = page.getContent();
        sortedAndFilter(pageNumber, size, sortField, sortDir, totalPages, totalItems, model);
        model.addAttribute(CHAIN_DTO_LIST, chainDtoList);
    }
}
