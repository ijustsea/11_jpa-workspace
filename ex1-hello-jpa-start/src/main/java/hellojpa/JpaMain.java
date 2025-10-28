package hellojpa;

import jakarta.persistence.*;

import java.security.spec.ECField;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //요청이 있을 때 마다 만든다.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

           //List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
           //데이터베이스 DB TABLE을 보고 쿼리를 짜는게 중요한게 아니라  Member VO를 보고 쿼리를 짜는게 중요하다?
           //JPQL은 엔티티 객체를 대상으로 쿼리 / SQL은 데이터베이스 테이블을 대상으로 쿼리

           //for(Member member : result){
           //   System.out.println("member.name : " + member.getName());
           //}

           //Member findMember1 = em.find(Member.class, 102L);
           //Member findMember2 = em.find(Member.class, 102L);

           //System.out.println("result : " + (findMember1 == findMember2));

            //Member member = em.find(Member.class,150L);
            //member.setName("zzzzz777");

            //Member member = new Member(200L, "member200");
            //em.persist(member);
            //em.flush();
            //두번 실행하면, 값이 동일하고 바뀐적이 없으니 스냅샷과 엔티티의 값을 비교후 변동이 없어서 update flush가 일어나지 않는다.
            //flush : 영속성 컨텍스트의 변경내용을 데이터베이스에 반영 ~ 변경감지
            //flush 하는 3가지 방법 1.em.flush();직접호출 2.tx.commit();자동 flush 3.JPQL 쿼리 실행 자동 flush

            //Member member = em.find(Member.class, 150L); //select 발송 o
            //member.setName("AAAAA150"); //update detach로 발송 x


//            //System.out.println("==============================");
//            Member member = new Member();;
//            member.setUsername("C");
//            System.out.println("==============================");
//            em.persist(member);//identity 전략은 persist할 때 쿼리가 나간다.영속상태로 바뀜
//            System.out.println("member : "+member.getId());
//            System.out.println("==============================");
//            //SEQUENCE 전략은 쿼리를 버퍼에 담아서 한번에 처리한다.
//                Team team = new Team();
//                team.setName("TeamA");
//                em.persist(team);
//
//                Member member = new Member();
//                member.setUsername("member1");
//                member.changeTeam(team);
//
//                em.persist(member);
//                //team.addMember(member);
//                //team.getMembers().add(member);
//
//                //연관관계 편의 메소드 설정으로 생략가능 : changeTeam()
//
//                em.flush();
//                em.clear();
//
//                //1차 캐시에서 찾기
//                Team findTeam = em.find(Team.class, team.getId());
//                List<Member> members = findTeam.getMembers();

//                for(Member m : members){
//                    System.out.println("m: "+m.getUsername());
//                }

                //System.out.println(findTeam);
                //StackOverflowError : 무한 루프 조심하자

//                Member findMember = em.find(Member.class, member.getId());
//
//                List<Member> members = findMember.getTeam().getMembers();
//                for(Member m : members){
//                    System.out.println("m: "+ m.getUsername());
//                }
            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member);

            em.persist(team);

            tx.commit();//자동 flush 호출
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
//JPA에서는 영속성 컨텍스트가 엔티티의 상태를 관리합니다.
// 영속성 컨텍스트는 엔티티를 조회할 때 해당 엔티티의 스냅샷을 만들어 둡니다.
// 그리고 트랜잭션이 종료될 때 (flush가 발생할 때) 스냅샷과 엔티티의 현재 상태를 비교하여
// 변경 사항이 있는 경우에만 데이터베이스로 update 쿼리를 보냅니다.