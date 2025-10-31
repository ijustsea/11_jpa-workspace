package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.security.spec.ECField;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //슈더코드
            //Order order = em.find(Order.class, 1L);
            //Long memberId = order.getMemberId();
            //Member member = em.find(Member.class, memberId);
            //Member findMember = order.getMember();

            //Order order = new Order();
            //order.addOrderItem(new OrderItem());
            //em.persist(order);

            //OrderItem orderItem = new OrderItem();
            //orderItem.setOrder(order);
            //em.persist(orderItem);

            Book book = new Book();
            book.setName("JPA의 정석");
            book.setAuthor("김시연");

            em.persist(book);

            em.flush(); em.clear();
            //JPQL 타입 표현 예시

            String query = "select i from Item i"
                       + " where type(i) = Book";

            List<Item> result = em.createQuery(query, Item.class).getResultList();

            for (Item item : result) {
                System.out.println("Item 중 Book : "+ item);
            }

            tx.commit();//자동 flush 호출
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }

}
