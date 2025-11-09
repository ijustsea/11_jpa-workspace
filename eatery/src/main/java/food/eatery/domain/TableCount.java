package food.eatery.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class TableCount {

    @Id @GeneratedValue
    @Column(name = "table_count_id")
    private Long id;

    @OneToOne(mappedBy = "tableCount", fetch = LAZY)
    private Reserv reserv;

    private int tableCount;
}
