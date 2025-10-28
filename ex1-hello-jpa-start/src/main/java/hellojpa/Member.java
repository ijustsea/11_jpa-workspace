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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

//    @Column(name="Team_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name ="TEAM_ID")
    private Team team;
    //JPA에게 관계 설명

    @OneToOne
    @JoinColumn(name="LOCKER_ID")
    private Locker locker;

    @ManyToMany
    @JoinTable(name="MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();

    public Member(){}

    public Long getId() {
        return id;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }//연관관계 편의 메소드


}