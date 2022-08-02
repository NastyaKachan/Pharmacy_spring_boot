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
public class PharmacyDto implements Serializable {
    private Integer id;
    private String namePharmacy;
    private String address;
    private String workingHours;
    private String phoneNumber;
    private PharmacyChainDto pharmacyChain;
    private CityDto city;
    private List<ProductDto> products;

    @Data
    public static class PharmacyChainDto implements Serializable {
        private Integer id;
        private String nameChain;
    }

    @Data
    public static class CityDto implements Serializable {
        private Integer id;
        private String nameCity;
        private Integer regionDtoId;
    }

    @Data
    public static class ProductDto implements Serializable {
        private Integer id;
        private String drugName;
        private PrescriptionEnum prescription;
        private String instruction;
        private Double price;
        private Double countAvailable;
        private Date datetimeUpdate;
    }
}
