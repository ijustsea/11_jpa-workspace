package food.eatery.service;

import food.eatery.domain.Customer;
import food.eatery.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    //sign up
    @Transactional(readOnly = false)
    public Long join(Customer customer){
        validateDuplicateCustomer(customer);
        customerRepository.save(customer);
        return customer.getId();
    }

    private void validateDuplicateCustomer(Customer customer) {
        List<Customer> findCustomers = customerRepository.findByName(customer.getName());
        if(!findCustomers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 고객입니다.");
        }
    }

    //all customer list
    public List<Customer> findCustomers(){
        return customerRepository.findAll();
    }



}
