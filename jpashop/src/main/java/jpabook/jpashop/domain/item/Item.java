package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories= new ArrayList<>();

    public void addStock1(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock1(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughStockException(("need more stock"));
        }
        this.stockQuantity = restStock;
    }

    //비즈니스 로직//

    /**
     * stock 증가 메소드
     * @param quantity
     */
    public void addStock(int quantity){
        stockQuantity += quantity;
    }

    /**
     * stock 감소 메소드
     * @param quantity
     */
    public void removeStock(int quantity){
        int restStock = stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        stockQuantity = restStock;
    }
}
