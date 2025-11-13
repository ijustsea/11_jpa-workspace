package food.eatery.repository;

import food.eatery.domain.Reserv;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservRepository {
    private final EntityManager em;

    public void save(Reserv reserv){
        em.persist(reserv);
    }

    public Reserv findOne(Long id){
        return em.find(Reserv.class, id);
    }
    //예약자 검색기능
    public Reserv findByName(ReservSearch reservSearch){
        String jpql = "select r form Reserv r join r.customer c" +
                              " where c.name = :name " +
                              " and c.phone = :phone ";

        Reserv result = em.createQuery(jpql, Reserv.class)
                .setParameter("name", reservSearch.getCustomerName())
                .setParameter("phone", reservSearch.getCustomerPhone())
                .setMaxResults(1)
                .getSingleResult();

        return  result;
    }


}
