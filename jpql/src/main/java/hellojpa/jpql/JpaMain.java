package hellojpa.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Member member = new Member();
            member.setUsername("홍길동");
            member.setAge(20);
            em.persist(member);

            Member singleResult = em.createQuery("SELECT m from Member as m where m.username=:username ", Member.class)
                    .setParameter("username", "홍길동")
                    .getSingleResult();

            System.out.println("singleResult : " + singleResult);

//            TypedQuery<Member> query1 = em.createQuery("SELECT m from Member m", Member.class);
//            TypedQuery<String> query2 = em.createQuery("SELECT m.username from Member m", String.class);
//            //TypedQuery : 반환 타입이 명확할 때 사용
//
//            Query query=em.createQuery("SELECT s.username, s.age from Member s");
//            //Query : 반환 타입이 명확하지 않을 때 사용
//
//            List<Member> resultList = query1.getResultList();
//            for(Member member1 : resultList){
//                System.out.println("member1 : " + member1.getUsername());
//            }
//
//            TypedQuery<Member> query3 = em.createQuery("SELECT m from Member m", Member.class);
//            Member result = query3.getSingleResult();
//
//            System.out.println("result : " +  result);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close();
    }

}

