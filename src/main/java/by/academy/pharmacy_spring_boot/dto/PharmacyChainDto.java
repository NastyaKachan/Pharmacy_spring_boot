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
public class PharmacyChainDto implements Serializable {
    private Integer id;
    private String nameChain;
    private List<PharmacyDto> pharmacies;

    @Data
    public static class PharmacyDto implements Serializable{
        private Integer id;
        private String namePharmacy;
        private String address;
        private String workingHours;
        private String phoneNumber;
    }
}
