package food.eatery.domain;

import food.eatery.domain.menu.Menu;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "order_menu")
public class OrderMenu {
    @Id @GeneratedValue
    @Column(name = "order_menu_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int orderPrice;
    private int count;

    //생성메소드
    public static OrderMenu createOrderMenu(Menu menu, int orderPrice, int count){
        OrderMenu orderMenu = new OrderMenu();

        orderMenu.setMenu(menu);
        orderMenu.setOrderPrice(orderPrice);
        orderMenu.setCount(count);

        //재고줄이기
        menu.removeStock(count);

        return orderMenu;
    }
    //취소메소드
    public void cancel(){
        getMenu().addStock(count);
    }
    //주문가격조회
    public int getTotalPrice(){
        return getOrderPrice()*getCount();
    }
}
