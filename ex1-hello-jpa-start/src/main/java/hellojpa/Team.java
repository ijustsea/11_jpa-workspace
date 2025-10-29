package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name="TEAM_ID")//가짜매핑. mappedBy 조회만가능... 수정, 변경 안
    private List<Member> members = new ArrayList<>();

    public Team (){}

//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }//연관관계 편의메소드

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }


}
