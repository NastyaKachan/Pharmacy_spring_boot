package by.academy.pharmacy_spring_boot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "name_region", unique = true)
    private String nameRegion;

    @OneToMany(mappedBy = "region", cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<City> cities = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(id, region.id) && Objects.equals(nameRegion, region.nameRegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRegion);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", nameRegion='" + nameRegion + '\'' +
                '}';
    }
}
