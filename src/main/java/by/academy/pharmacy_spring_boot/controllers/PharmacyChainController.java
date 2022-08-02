package by.academy.pharmacy_spring_boot.controllers;

import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PharmacyChainController {
    public static final int PAGE_NUMBER = 1;
    public static final int SIZE = 5;
    public static final String ID = "id";
    public static final String ASC = "asc";
    public static final String NAME_FILTER = "nameFilter";
    public static final String PHARMACY_CHAIN_DTO_LIST = "pharmacyChainDtoList";
    public static final String PHARMACY_CHAIN = "pharmacyChain";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String SIZE_NAME = "size";
    public static final String TOTAL_PAGES = "totalPages";
    public static final String TOTAL_CHAINS = "totalChains";
    public static final String SORT_FIELD = "sortField";
    public static final String SORT_DIR = "sortDir";
    public static final String REVERSE_SORT_DIR = "reverseSortDir";
    @Autowired
    private final PharmacyChainService pharmacyChainService;

    @GetMapping("/pharmacy-chain")
    public String findChains(Model model) {
        return findChainPaginated(PAGE_NUMBER, SIZE, ID, ASC, null, model);
    }

    @GetMapping("/pharmacy-chain/page/{pageNumber}")
    public String findChainPaginated(@PathVariable("pageNumber") int pageNumber, int size,
                                     String sortField, String sortDir, String nameFilter,
                                     Model model) {
        PharmacyChainFilter pharmacyChainFilter = PharmacyChainFilter.builder()
                .nameChainFilter(nameFilter)
                .build();
        Page<PharmacyChainDto> page = pharmacyChainService.findAllPaginatedAndFiltered(pharmacyChainFilter,
                pageNumber, size, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalChains = page.getTotalElements();
        List<PharmacyChainDto> pharmacyChainDtoList = page.getContent();
        sortedAndFilter(pageNumber, size, sortField, sortDir, totalPages, totalChains, model);
        model.addAttribute(NAME_FILTER, nameFilter);
        model.addAttribute(PHARMACY_CHAIN_DTO_LIST, pharmacyChainDtoList);
        return "pharmacy-chain";
    }

    @GetMapping("/pharmacy-chain/add_chain")
    public String addChain() {
        return "add_chain";
    }

    @PostMapping
    public String saveChain(PharmacyChainDto pharmacyChainDto) {
        pharmacyChainService.createPharmacyChain(pharmacyChainDto);
        return "redirect:/pharmacy-chain";
    }

    @GetMapping("/pharmacyChain/update_chain/{id}")
    public String updateChain(@PathVariable(value = "id") Integer id, Model model) {
        PharmacyChainDto pharmacyChainById = pharmacyChainService.findPharmacyChainById(id);
        model.addAttribute(PHARMACY_CHAIN, pharmacyChainById);
        return "update_chain";
    }

    private void sortedAndFilter(@PathVariable("pageNumber") int pageNumber, int size,
                                 String sortField, String sortDir, int totalPages, long totalChains, Model model) {
        model.addAttribute(CURRENT_PAGE, pageNumber);
        model.addAttribute(SIZE_NAME, size);
        model.addAttribute(TOTAL_PAGES, totalPages);
        model.addAttribute(TOTAL_CHAINS, totalChains);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        model.addAttribute(REVERSE_SORT_DIR, sortDir.equals("asc") ? "desc" : "asc");
    }
}
