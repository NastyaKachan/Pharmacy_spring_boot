package by.academy.pharmacy_spring_boot.entity;

import by.academy.pharmacy_spring_boot.enumeration.PrescriptionEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "drug_name")
    private String drugName;

    @Column(name = "prescription")
    @Enumerated(EnumType.STRING)
    private PrescriptionEnum prescription;

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "price")
    private Double price;

    @Column(name = "count_available")
    private Double countAvailable;

    @Column(name = "datetime_update")
    private Date datetimeUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mnn_id")
    private MNN mnn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "pharmacy_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacy_id"))
    @ToString.Exclude
    private List<Pharmacy> pharmacies = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(drugName, product.drugName) && prescription == product.prescription && Objects.equals(instruction, product.instruction) && Objects.equals(price, product.price) && Objects.equals(countAvailable, product.countAvailable) && Objects.equals(datetimeUpdate, product.datetimeUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, drugName, prescription, instruction, price, countAvailable, datetimeUpdate);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", drugName='" + drugName + '\'' +
                ", prescription=" + prescription +
                ", instruction='" + instruction + '\'' +
                ", price=" + price +
                ", countAvailable=" + countAvailable +
                ", datetimeUpdate=" + datetimeUpdate +
                '}';
    }
}
