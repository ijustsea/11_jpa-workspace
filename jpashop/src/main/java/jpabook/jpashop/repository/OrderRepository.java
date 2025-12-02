package jpabook.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    //검색기능
    public List<Order> findAlls(OrderSearch orderSearch){
        String jpql = "select o from Order o join o.member m";

        List<Order> result = em.createQuery(jpql +
                                " where o.status = :status" +
                                " and m.name like :name"
                        , Order.class)
                        .setParameter("status", orderSearch.getOrderStatus())
                        .setParameter("name", orderSearch.getMemberName())
                        .setMaxResults(1000)
                        .getResultList();

        return result;
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000); //최대 1000건

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }

   public List<Order> findAll(OrderSearch orderSearch){
       QOrder order = QOrder.order;
       QMember member = QMember.member;

       JPAQueryFactory query = new JPAQueryFactory(em);
       return query
               .select(order)
               .from(order)
               .join(order.member, member)
               .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch, member))
               .limit(1000)
               .fetch();

   }

   private  BooleanExpression nameLike(OrderSearch orderSearch, QMember member){
        if(!StringUtils.hasText(orderSearch.getMemberName())){
            return  null;
        }
        return member.name.contains(orderSearch.getMemberName());

   }

   private BooleanExpression statusEq(OrderStatus statusCond) {
        if(statusCond == null){
            return null;
        }
        return QOrder.order.status.eq(statusCond);

   }




    public List<Order> findAllWithMemberDelivery() {
        List<Order> result = em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class
        ).getResultList();
        return result;
    }

    public List<Order> findAllWithItem() {
        return em.createQuery(
                "select distinct o from Order o" +
                        " join fetch o.member m " +
                        " join fetch o.delivery d " +
                        " join fetch o.orderItems oi " +
                        " join fetch oi.item i ", Order.class)
                .setFirstResult(1)
                .setMaxResults(100)
                .getResultList();
    }

    //먼저 ToOne의 관계를 패치조인한다 페이징!
    public List<Order> findAllWithItem(int offset, int limit) {
        List<Order> result = em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class
        )
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return result;
    }


}
