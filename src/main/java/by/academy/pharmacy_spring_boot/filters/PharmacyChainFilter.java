package by.academy.pharmacy_spring_boot.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyChainFilter {
    private String nameChainFilter;
}
