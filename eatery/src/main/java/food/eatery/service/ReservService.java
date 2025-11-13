package food.eatery.service;

import food.eatery.domain.*;
import food.eatery.repository.CustomerRepository;
import food.eatery.repository.OrderRepository;
import food.eatery.repository.ReservRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservService {

    private final ReservRepository reservRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    //예약
    @Transactional
    public Long reserv(Long customerId, int tableCounts, Long OrderMenuId){
        Customer customer = customerRepository.findOne(customerId);


        TableCount tableCount = new TableCount();
        tableCount.setTableCounts(tableCounts);

        Reserv reserv = Reserv.createReserv(customer, tableCount);
        reservRepository.save(reserv);

        Order order = Order.createOrder(reserv, new OrderMenu[0]);

        return reserv.getId();
    }

}
