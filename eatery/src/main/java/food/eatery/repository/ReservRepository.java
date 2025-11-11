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



}
