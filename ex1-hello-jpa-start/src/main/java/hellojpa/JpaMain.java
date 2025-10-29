package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.security.spec.ECField;
import java.time.LocalDateTime;
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
//            Member member = new Member();
//            member.setUsername("member1");
//
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("teamA");
//            team.getMembers().add(member);
//
//            em.persist(team);

//            Movie movie = new Movie();
//            movie.setDirector("봉준호");
//            movie.setActor("송강호");
//            movie.setName("기생충");
//            movie.setPrice(10000);
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            Item item = em.find(Item.class, movie.getId() );
//            System.out.println("findMovie : " + item.getName());

//            Member member = new Member();
//            member.setUsername("user1");
//            member.setCreateBy("kimeechan");
//            member.setCreateTime(LocalDateTime.now());
//            em.persist(member);
//
//            em.flush();
//            em.clear();
            /*
            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("name1");
            member1.setTeam(team);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("name2");

            em.persist(member2);



            em.flush();
            em.clear();
            */
//
//            //Member findMember = em.find(Member.class, member.getId());
//            Member findMember = em.getReference(Member.class, member.getId());
//
//            System.out.println("findMember : "+ findMember.getClass());
//            System.out.println("findMember.username : "+ findMember.getUsername());
//            System.out.println("after findMember : "+ findMember.getClass());
            /*
            Member refMember = em.getReference(Member.class, member1.getId());//영속
            System.out.println("refMember : " + refMember.getClass());// proxy
            refMember.getUsername();// 프록시 초기화 요청

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember : " + findMember.getClass());// proxy로 연결

            System.out.println("a==a : " + (refMember == findMember));//하나의 트랜잭션안에는 동일성 보장
            */

            //이미 영속에 있기 떄문에 그냥 실제 엔티티 반환. 이미 있어서 굳이 만들지 않는다.

            //Member m2 = em.getReference(Member.class, member2.getId());
            //System.out.println("m1==m2 : "+ (m1.getClass() == m2.getClass()));
            // m1.class = member // m2.class = proxy

            //logic(m1,m2);

            //Member refMember = em.getReference(Member.class, member1.getId());
            //System.out.println("refMember : " + refMember.getClass());

            //em.detach(refMember);//영속성 컨텍스트 중단.
            //refMember.getUsername();//LazyInitializationException

            //refMember.getUsername();//실제값을 요청하면 초기화됨
            //Hibernate.initialize(refMember);//강제 초기화
            //System.out.println("isLoaded : " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            /*

            Member m = em.find(Member.class, member1.getId());
            //DEFAULT -- 즉시로딩 EAGER
            System.out.println("m : " + m.getTeam().getClass());
            System.out.println(" ===================================== ");
            m.getTeam().getName();// 초기화 -- 지연로딩 LAZY
            System.out.println(" ===================================== ");
             */

            //List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();
            //처음에는 m만 select, //EAGER로 인식하면 Team도 select
            //즉, 즉시로딩은 JPQL에서 N+1 문제를 일으킨다. 필요하지는 쿼리를 추가로 더 가져와서 성능저하의 원인이 될수있다.
            //따라서 즉시로딩는 사용하지 않고 될수있으면 지연 로딩 FetchType.LAZY 사용할 것.
            //지연로딩을 하면서 항상 가져오고싶은 다른 테이블의 데이터들을 가져오고싶을떄는 join fetch를 쓰면된다.

            //@ManyToOne, @OneToOne DEFAULT EAGER이어서 LAZY로 수정해야한다.

            Child child1 = new Child();
            Child child2= new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);
            findParent.getChildList().remove(0);//Child Entity를 컬랙션에서 제거
            //parent에서 childList orphanRemoval = true 해놓으면 부모자식관계가 끊어지면서 고아객체로됨.
            //고아객체는 OneToOne OneToMany에서만 작성해서 사용

            //orphanRemoval = true 지우고, em.persist(child1)(child2)하고
            //em.remove(findParent)하면 부모자식 다 사라진다.
            //즉 부모를 통해 자식의 생명주기를 관리할수 있는 것이다.


            tx.commit();//자동 flush 호출
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1==m2 : "+ (m1 instanceof Member));
        System.out.println("m1==m2 : "+ (m2 instanceof Member));
    }

}
//JPA에서는 영속성 컨텍스트가 엔티티의 상태를 관리합니다.
// 영속성 컨텍스트는 엔티티를 조회할 때 해당 엔티티의 스냅샷을 만들어 둡니다.
// 그리고 트랜잭션이 종료될 때 (flush가 발생할 때) 스냅샷과 엔티티의 현재 상태를 비교하여
// 변경 사항이 있는 경우에만 데이터베이스로 update 쿼리를 보냅니다.