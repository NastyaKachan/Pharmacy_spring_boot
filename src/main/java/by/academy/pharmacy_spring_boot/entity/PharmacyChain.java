package by.academy.pharmacy_spring_boot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pharmacy_chain")
public class PharmacyChain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chain_id")
    private Integer id;

    @Column(name = "name_chain")
    private String nameChain;

    @OneToMany(mappedBy = "pharmacyChain")
    @ToString.Exclude
    private List<Pharmacy> pharmacies = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyChain that = (PharmacyChain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
