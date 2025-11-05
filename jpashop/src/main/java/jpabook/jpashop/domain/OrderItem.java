package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name="order_item")
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; // 주문가격
    private int count; // 주문수량

    //생성 메소드//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        //재고줄이기
        item.removeStock(count);
        return orderItem;
    }

    public void cancel() {
        // 아이템을 가져온다, 재고를 늘린다.
        getItem().addStock((count));
    }

    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }

}
