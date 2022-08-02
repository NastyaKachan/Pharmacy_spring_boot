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
public class RegionDto implements Serializable {
    private Integer id;
    private String nameRegion;
    private List<CityDto> cities;

    @Data
    public static class CityDto implements Serializable{
        private Integer id;
        private String nameCity;
        private Integer regionDtoId;
    }
}
