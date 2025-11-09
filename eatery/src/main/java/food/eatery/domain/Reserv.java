    package food.eatery.domain;

    import jakarta.persistence.*;
    import jakarta.persistence.Table;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.LocalDateTime;


    import static jakarta.persistence.CascadeType.ALL;
    import static jakarta.persistence.FetchType.LAZY;

    @Entity
    @Getter @Setter
    @Table(name = "reservs")
    public class Reserv {
        @Id
        @GeneratedValue
        @Column(name = "reserv_id")
        private Long id;

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name="customer_id")
        private Customer customer;

        @OneToOne(fetch = LAZY, cascade = ALL)
        @JoinColumn(name = "table_count_id")
        private TableCount tableCount;

        private LocalDateTime reservDate;

        @Enumerated(EnumType.STRING)
        private ReservStatus status;

        @OneToOne(fetch = LAZY, mappedBy = "reserv")
        public Order order;

        public Reserv (){ };
        //연관관계 편의 메소드
        public void setCustomer(Customer customer){
            this.customer = customer;
            customer.getReservs().add(this);
        }

        public void setTableCount(TableCount tableCount){
            this.tableCount = tableCount;
            tableCount.setReserv(this);
        }

        //예약생성 method
        public static food.eatery.domain.Reserv createReserv(Customer customer, TableCount tableCount){
            food.eatery.domain.Reserv reserv = new food.eatery.domain.Reserv();
            reserv.setCustomer(customer);
            reserv.setTableCount(tableCount);
            reserv.setReservDate(LocalDateTime.now());
            reserv.setStatus(ReservStatus.RESERV);

            return reserv;
        }
        //예약취소 method
        public void cancelReserv(){
            this.setStatus(ReservStatus.CANCEL);
        }
    }
