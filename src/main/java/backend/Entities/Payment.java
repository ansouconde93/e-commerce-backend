package backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String cardNumber;
    private String cardType;
    private String cardPassword;
    private double amount;
    @OneToOne(mappedBy = "payment")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Commande order;

}
