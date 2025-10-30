package hellojpa;

import jakarta.persistence.*;
//import jakarta.persistence.Table;
//
//@Entity(name = "Member")
//public class Member {
//
//    @Id
//    private Long id;
//    @Column(unique = true, length = 10)
//    private String name;
//    public Member() { }
//
//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Member{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static jakarta.persistence.CascadeType.ALL;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name="MEMBER_ID")
    )
    @Column(name="FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name="ADDRESS",
//            joinColumns = @JoinColumn(name="MEMBER_ID")
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();


//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "MEMBER_ID")
//    private List<AddressEntity> addressHistory = new ArrayList<>();
//    //ENTITY 승격?  : AddressEntity
//
    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }



//
//    public List<Address> getAddressHistory() {
//        return addressHistory;
//    }
//
//    public void setAddressHistory(List<Address> addressHistory) {
//        this.addressHistory = addressHistory;
//    }

//    @Embedded
//    private Period workPeriod;
//
//    @Embedded
//    private Address homeAddress;
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name= "city", column = @Column(name = "work_city")),
//            @AttributeOverride(name= "street", column = @Column(name = "work_street")),
//            @AttributeOverride(name= "zipcode", column = @Column(name = "work_zipcode"))
//    })
//    private Address workAddress;

//    @Column(name="Team_ID")
//    private Long teamId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name ="TEAM_ID")
//    private Team team;
//    //JPA에게 관계 설명
//
//    @OneToOne
//    @JoinColumn(name="LOCKER_ID")
//    private Locker locker;
//
//    @ManyToMany
//    @JoinTable(name="MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();
//
//    public Member(){}
//
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }//연관관계 편의 메소드


}