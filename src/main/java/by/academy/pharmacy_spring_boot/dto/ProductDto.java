package by.academy.pharmacy_spring_boot.dto;

import by.academy.pharmacy_spring_boot.enumeration.PrescriptionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private Integer id;
    private String drugName;
    private PrescriptionEnum prescription;
    private String instruction;
    private Double price;
    private Double countAvailable;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datetimeUpdate;
    private ProducerDto producer;
    private MnnDto mnn;
    private CategoryDto category;
    private List<PharmacyDto> pharmacies;

    @Data
    public static class ProducerDto implements Serializable{
        private Integer id;
        private String name_producer;
        private String country;
    }

    @Data
    public static class MnnDto implements Serializable{
        private Integer id;
        private String mnn;
    }

    @Data
    public static class CategoryDto implements Serializable{
        private Integer id;
        private String title;
        private String types;
    }

    @Data
    public static class PharmacyDto implements Serializable{
        private Integer id;
        private String namePharmacy;
        private String address;
        private String workingHours;
        private String phoneNumber;
    }

}
