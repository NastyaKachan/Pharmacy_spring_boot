package by.academy.pharmacy_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDto implements Serializable {
    private Integer id;
    private String nameCity;
    private Integer regionDtoId;
    private List<PharmacyDto> pharmacies;
    private RegionDto region;

    @Data
    public static class PharmacyDto implements Serializable{
        private Integer id;
        private String namePharmacy;
        private String address;
        private String workingHours;
        private String phoneNumber;
    }

    @Data
    public static class RegionDto implements Serializable{
        private Integer id;
        private String nameRegion;
    }
}
