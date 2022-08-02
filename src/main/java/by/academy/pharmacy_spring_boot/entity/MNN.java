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
@Table(name = "mnn")
public class MNN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mnn_id")
    private Integer id;

    @Column(name = "mnn")
    private String mnn;

    @OneToMany(mappedBy = "mnn")
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MNN mnn = (MNN) o;
        return Objects.equals(id, mnn.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
