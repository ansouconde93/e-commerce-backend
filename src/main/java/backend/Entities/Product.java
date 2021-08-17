package backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduct;
    private String name;
    private float price;
    @Column(length = 4048)
    private String description;
    private String photoname;
    private boolean promotion;
    private boolean selected;
    private boolean avaible;
    @Transient
    private int quantite = 1;
    @ManyToOne
    private Category category ;
}
