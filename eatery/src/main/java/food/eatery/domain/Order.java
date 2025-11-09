package food.eatery.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name ="orders")
public class Order {
    @Id @GeneratedValue
    @Column(name ="order_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "reserv_id")
    private Reserv reserv;

    @OneToMany(mappedBy = "order",cascade = ALL)
    private List<OrderMenu> orderMenus = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order(){};

    //연관관계 편의 메소드
    public void setReserv(Reserv reserv){
        this.reserv = reserv;
        reserv.setOrder(this);
    }

    public void addOrderMenu(OrderMenu orderMenu){
        orderMenus.add(orderMenu);
        orderMenu.setOrder(this);
    }

    //주문생성
    public static Order createOrder(Reserv reserv, OrderMenu...orderMenus){
        Order order = new Order();
        order.setReserv(reserv);
        for (OrderMenu orderMenu : orderMenus) {
            order.addOrderMenu(orderMenu);
        }
        order.setStatus(OrderStatus.ORDER);
        return order;
    }

    //주문취소
    public void cancel(){
        this.setStatus(OrderStatus.CANCEL);
        for (OrderMenu orderMenu : orderMenus) {
            orderMenu.cancel();
        }
    }
    //전체주문가격조회
    public int getTotalPrice(){
        int totalPrice =0;
        for (OrderMenu orderMenu : orderMenus) {
            totalPrice += orderMenu.getTotalPrice();
        }
        return totalPrice;
    }

}
