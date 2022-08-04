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
@Table(name = "producer")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id")
    private Integer id;

    @Column(name = "name_producer")
    private String name_producer;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id) && Objects.equals(name_producer, producer.name_producer) && Objects.equals(country, producer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name_producer, country);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name_producer='" + name_producer + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
