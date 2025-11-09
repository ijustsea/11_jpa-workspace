package food.eatery.domain.menu;

import food.eatery.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Getter @Setter
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Menu {

    @Id @GeneratedValue
    @Column(name="menu_id")
    private Long id;

    private String name;
    private int price;
    private int stock;

    //business logic : stock add, remove methods
    public void addStock(int quantity){
        stock += quantity;
    }
    public void removeStock(int quantity){
        int restStock = stock - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        stock = restStock;
    }
}
