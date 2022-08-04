package by.academy.pharmacy_spring_boot.controllers;

import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.filters.PharmacyChainFilter;
import by.academy.pharmacy_spring_boot.services.interfaces.PharmacyChainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static by.academy.pharmacy_spring_boot.constants.Constants.*;
import static by.academy.pharmacy_spring_boot.controllers.ProductController.ID;

@Controller
@RequiredArgsConstructor
@RequestMapping(PHARMACY_CHAIN)
public class PharmacyChainController {

    @Autowired
    private final PharmacyChainService pharmacyChainService;

    @GetMapping
    public String findChains(Model model) {
        return findChainPaginated(PAGE_NUMBER_ONE, SIZE_FIVE, ID, ASC, null, model);
    }

    @GetMapping(PAGE_PAGE_NUMBER)
    public String findChainPaginated(@PathVariable(PAGE_NUMBER) int pageNumber, int size,
                                     String sortField, String sortDir, String nameFilter,
                                     Model model) {
        PharmacyChainFilter pharmacyChainFilter = PharmacyChainFilter.builder()
                .nameChainFilter(nameFilter)
                .build();
        Page<PharmacyChainDto> page = pharmacyChainService.findChainWithPaginated(pharmacyChainFilter,
                pageNumber, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalChains = page.getTotalElements();
        List<PharmacyChainDto> pharmacyChainDtoList = page.getContent();
        sortedAndFilter(pageNumber, size, sortField, sortDir, totalPages, totalChains, model);
        model.addAttribute(NAME_FILTER, nameFilter);
        model.addAttribute(PHARMACY_CHAIN_DTO_LIST, pharmacyChainDtoList);
        return PHARMACY_CHAIN;
    }

    @GetMapping(PHARMACY_IN_CHAIN_ID)
    public String findPharmaciesInChain(@PathVariable(value = ID) Integer id, Model model) {
        PharmacyChainDto pharmacyChainById = pharmacyChainService.findPharmacyChainById(id);
        List<PharmacyDto> pharmaciesOfChain = pharmacyChainService.findPharmaciesOfChain(id);
        model.addAttribute(PHARMACY_CHAIN_BY_ID, pharmacyChainById);
        model.addAttribute(PHARMACIES_OF_CHAIN, pharmaciesOfChain);
        return PHARMACY_IN_CHAIN;
    }

    @GetMapping(CHAIN_CREATE)
    public String addChain() {
        return CHAIN_CREATE1;
    }

    @PostMapping(CHAIN_CREATE)
    public String saveChain(PharmacyChainDto pharmacyChainDto) {
        pharmacyChainService.savePharmacyChain(pharmacyChainDto);
        return REDIRECT_PHARMACY_CHAIN;
    }

    @GetMapping(CHAIN_EDIT_ID)
    public String editChain(@PathVariable(value = ID) Integer id, Model model) {
        PharmacyChainDto pharmacyChainDto = pharmacyChainService.findPharmacyChainById(id);
        model.addAttribute(PHARMACY_CHAIN_DTO, pharmacyChainDto);
        return CHAIN_EDIT;
    }

    @PostMapping(CHAIN_EDIT_ID1)
    public String updateChain(@PathVariable(ID) Integer id, PharmacyChainDto pharmacyChainDto) {
        pharmacyChainService.savePharmacyChain(pharmacyChainDto);
        return REDIRECT_PHARMACY_CHAIN;
    }

    @PostMapping(DELETE_ID)
    public String deleteChain(@PathVariable(ID) Integer id) {
        pharmacyChainService.deletePharmacyChainById(id);
        return REDIRECT_PHARMACY_CHAIN;
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
}
