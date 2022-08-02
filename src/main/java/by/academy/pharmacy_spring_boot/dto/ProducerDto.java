package by.academy.pharmacy_spring_boot.dto;

import by.academy.pharmacy_spring_boot.enumeration.PrescriptionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProducerDto implements Serializable {
    private Integer id;
    private String name_producer;
    private String country;
    private List<ProductDto> products;

    @Data
    public static class ProductDto implements Serializable{
        private Integer id;
        private String drugName;
        private PrescriptionEnum prescription;
        private String instruction;
        private Double price;
        private Double countAvailable;
        private Date dateTimeUpdate;
    }
}
