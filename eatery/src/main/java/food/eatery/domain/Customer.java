package food.eatery.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Customer {
    @Id @GeneratedValue
    @Column(name="customer_id")
    private Long id;

    private String name;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Reserv> reservs = new ArrayList<>();

}
