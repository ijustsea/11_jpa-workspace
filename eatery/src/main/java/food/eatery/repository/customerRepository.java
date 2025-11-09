package food.eatery.repository;

import food.eatery.domain.Customer;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class customerRepository {
    private final EntityManager em;
    //save
    public void save(Customer customer){
        em.persist(customer);
    }
    //findOne
    public Customer findOne(Long id){
        return em.find(Customer.class, id);
    }
    //findAll
    public List<Customer> findAll(){
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }
    //findByName
    public List<Customer> findByName(String name){
        return em.createQuery("select c from Customer c where c.name = :name", Customer.class)
                .setParameter("name", name)
                .getResultList();
    }
}
