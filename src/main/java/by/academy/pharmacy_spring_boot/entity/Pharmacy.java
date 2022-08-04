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
@Table(name = "pharmacy")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pharmacy_id")
    private Integer id;

    @Column(name = "name_pharmacy")
    private String namePharmacy;

    @Column(name = "address")
    private String address;

    @Column(name = "working_hours")
    private String workingHours;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chain_id")
    private PharmacyChain pharmacyChain;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pharmacy_product",
            joinColumns = @JoinColumn(name = "pharmacy_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return Objects.equals(id, pharmacy.id) && Objects.equals(namePharmacy, pharmacy.namePharmacy) && Objects.equals(address, pharmacy.address) && Objects.equals(workingHours, pharmacy.workingHours) && Objects.equals(phoneNumber, pharmacy.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namePharmacy, address, workingHours, phoneNumber);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", namePharmacy='" + namePharmacy + '\'' +
                ", address='" + address + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
