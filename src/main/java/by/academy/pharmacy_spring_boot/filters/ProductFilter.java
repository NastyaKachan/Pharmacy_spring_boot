package by.academy.pharmacy_spring_boot.filters;

import by.academy.pharmacy_spring_boot.dto.ProducerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter {
    private String drugNameFilter;
    private String producerFilter;
    private String pharmacyFilter;

}
