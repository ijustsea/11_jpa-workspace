package hellojpa.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
//
//            Member member = new Member();
//            member.setUsername("홍길동");
//            member.setAge(20);
//            em.persist(member);
//
//            Member singleResult = em.createQuery("SELECT m from Member as m where m.username=:username ", Member.class)
//                    .setParameter("username", "홍길동")
//                    .getSingleResult();
//
//            System.out.println("singleResult : " + singleResult);

//            TypedQuery<Member> query1 = em.createQuery("SELECT m from Member m", Member.class);
//            TypedQuery<String> query2 = em.createQuery("SELECT m.username from Member m", String.class);
//            //TypedQuery : 반환 타입이 명확할 때 사용
//
//            Query query=em.createQuery("SELECT s.username, s.age from Member s");
//            //Query : 반환 타입이 명확하지 않을 때 사용
//
//            List<Member> resultList = query1.getResultList();
//            for(Member member1 : resultList){
//                System.out.println("member1 : " + member1.getUsername());
//            }
//
//            TypedQuery<Member> query3 = em.createQuery("SELECT m from Member m", Member.class);
//            Member result = query3.getSingleResult();
//
//            System.out.println("result : " +  result);

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Address address = new Address("서울", "강남대로", "22222");
//
//            Member member = new Member();
//            member.setUsername("멤버");
//            member.setAge(10);
//            member.setTeam(team);
//            member.setAddress(address);
//            member.setMemberType(MemberType.ADMIN);
//            em.persist(member);
//
//            Member member2 = new Member();
//            member2.setUsername("관리자");
//            member2.setAge(30);
//            member2.setAddress(address);
//            member2.setMemberType(MemberType.USER);
//            em.persist(member2);
//
//            em.flush(); em.clear();

//            String query = "select m from Member as m";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//
//            for (Member member1 : result) {
//                System.out.println("member1 : " + member1.getUsername());
//            }

//            String query = "select m.team from Member as m";
//            List<Team> teams = em.createQuery(query, Team.class).getResultList();
//
//            for (Team team1 : teams) {
//                System.out.println("team.name : " + team1.getName());
//            }

//            String query = "select m.address from Member m";
//            List<Address> addresses = em.createQuery(query, Address.class).getResultList();
//
//            for (Address address1 : addresses) {
//                System.out.println("address.city : "+ address1.getCity());
//
//            }

//            String query = "select m.id, m.age, m.username from Member m";
//            List<Object[]> result = em.createQuery(query).getResultList();
//
//            for (Object[] objects : result) {// [[차은우 , 11], [장원영 , 22]]
//                System.out.println("id : "+ objects[0]);
//                System.out.println("age : "+ objects[1]);
//                System.out.println("username : "+ objects[2]);
//            }

//            String query = "select m from Member as m order by m.age desc";
//            List<Member> resultList = em.createQuery(query, Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(5)
//                    .getResultList();
//
//            System.out.println("===========페이징 결과===========");
//            for (Member m : resultList) {
//                System.out.println("member : " + m);
//            }
//            System.out.println("===========페이징 결과===========");

//            String query = "select m from Member as m inner join m.team t";//내부 조인
//            String query = "select m from Member as m left outer join m.team t";//외부 조인

//            List<Member> members = em.createQuery(query, Member.class)
//                    .setParameter("teamName", "teamA")
//                    .getResultList();
//
//            for (Member member1 : members) {
//                System.out.println("member : " + member1);
//                System.out.println("team.name : " + member1.getTeam().getName());
//            }

//            String query = "select m,t from Member m left join m.team t on t.name = 'teamA'";
//            List<Object[]> result = em.createQuery(query).getResultList();
//            for (Object[] objects : result) {
//                Member m = (Member)objects[0];
//                Team t = (Team)objects[1];
//
//                System.out.println("member : "+ m.getUsername());
//                System.out.println("team : " + (t != null ? t.getName() : "없음"));
//            }

//            String query = "select m from Member as m" + " where m.age > (select avg(m2.age) from Member as m2)";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for (Member member1 : result) {
//                System.out.println("평균 나이보다 많은 member : "+ member1);
//            }

//            String query = "select m from Member m" +
//            " where (select count(o) from Order o where m = o.member) > 0 ";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("주문이력 있는 회원 : "+ member1);
//            }

//            String query = "select m from Member m" +
//                    " where exists (select t from m.team t where t.name = 'teamA')";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("teamA인 member : " + member1.getUsername());
//            } //exists : 서브쿼리 결과가 있으면 참. 없으면 false

//            String query = "select o from Order o" +
//                    "where o.orderAmount > AL (select p.stockAmount from Product p) ";
//            String query = "select m from Member as m" +
//                        " where m.team = ANY (select t from Team t)";
//            List<Member> members = em.createQuery(query, Member.class).getResultList();
//            for (Member member1 : members) {
//                System.out.println("팀에 속한 회원 : " + member1 );
//
//            }
//            String query = "select m.username, sub.avgAge"
//                    + " from Member m, (select avg(m2.age) as avgAge from Member m2) sub"
//                    + " where m.age > sub.avgAge";
//            List<Object[]> result = em.createQuery(query).getResultList();
//            for (Object[] objects : result) {
//                System.out.println("name : "+ objects[0]);
//                System.out.println("age : "+ objects[1]);
//            }

//            String query = "select 'HELLO', 'She''s a developer' from Member m";
//            List<Object[]> resultList = em.createQuery(query).getResultList();//하나일 때만 type 설정가능
//            for (Object[] objects : resultList) {
//                System.out.println("문자 1 :" + objects[0] + ", 문자2 : " + objects[1]);
//            }
//            String query = "select 10, 10L, 10D, 10F from Member m";
//            List<Object[]> num = em.createQuery(query).getResultList();
//
//            Object[] objects = num.get(0);
//            System.out.println("int : " + objects[0] +", long : "+ objects[1] + ", float : "+ objects[3] );

//            String query = "select m from Member m"
//                         + " where m.memberType = hellojpa.jpql.MemberType.USER";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("USER 인 회원 : " + member1);
//            } // ENUM을 String으로 직접 하드코딩 아니라 package 경로까지 적는 동적 표기 해야한다.

//            String query = "select m from Member m"
//                         + " where m.age between 20 and 40 and m.username like '%없는%'";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for (Member member1 : result) {
//                System.out.println("member : "+ member1 );
//
//            }

//            String query = "select"
//                          + " case when m.age <= 10 then '학생요금' "
//                          + "      when m.age >= 60 then '경로요금' "
//                          + "      else '일반요금' "
//                          + " end  "
//                          + " from Member m ";
//
//            List<String> strings = em.createQuery(query, String.class).getResultList();
//
//            for (String string : strings) {
//                System.out.println("요금 종류 : " + string );
//            }

//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
//            List<String> strings = em.createQuery(query, String.class).getResultList();
//            for (String string : strings) {
//                System.out.println("회원 이름 : " + string);
//            }

//            String query = "select locate('자', m.username) from Member m";
//            List<Integer> positions = em.createQuery(query).getResultList();
//            for (Integer position : positions) {
//                System.out.println("인덱스 위치 : " + position);
//            }
            Product product1 = new Product();
            product1.setName("chicken");
            product1.setPrice(20000);
            product1.setStockAmount(200);
            em.persist(product1);

            Product product2 = new Product();
            product2.setName("pizza");
            product2.setPrice(33000);
            product2.setStockAmount(150);
            em.persist(product2);

            Product product3 = new Product();
            product3.setName("sandwich");
            product3.setPrice(9000);
            product3.setStockAmount(500);
            em.persist(product3);

            Product product4 = new Product();
            product4.setName("beer");
            product4.setPrice(6000);
            product4.setStockAmount(1000);
            em.persist(product4);

            Product product5 = new Product();
            product5.setName("zerocoke");
            product5.setPrice(2500);
            product5.setStockAmount(0);
            em.persist(product5);

            Product product6 = new Product();
            product6.setName("applecider");
            product6.setPrice(3000);
            product6.setStockAmount(0);
            em.persist(product6);

            Product product7 = new Product();
            product7.setName("avocado");
            product7.setPrice(5000);
            product7.setStockAmount(100);
            em.persist(product7);

            Product product8 = new Product();
            product8.setName("frenchfries");
            product8.setPrice(7000);
            product8.setStockAmount(700);
            em.persist(product8);

            Product product9 = new Product();
            product9.setName(null);
            product9.setPrice(1000);
            product9.setStockAmount(5000);
            em.persist(product9);

            Product product10 = new Product();
            product10.setName("test");
            product10.setPrice(11000);
            product10.setStockAmount(400);
            em.persist(product10);



            Order order1 = new Order();
            order1.setOrderAmount(3);
            order1.setProduct(product1);
            order1.setAddress(new Address("사당", "마음대로", "77777"));
            em.persist(order1);

            Order order2 = new Order();
            order2.setOrderAmount(2);
            order2.setProduct(product2);
            order2.setAddress(new Address("강남", "보노보로", "33333"));
            em.persist(order2);

            Order order3 = new Order();
            order3.setOrderAmount(15);
            order3.setProduct(product4);
            order3.setAddress(new Address("강동", "멋쟁이로", "11111"));
            em.persist(order3);

            Order order4 = new Order();
            order4.setOrderAmount(500);
            order4.setProduct(product4);
            order4.setAddress(new Address("용산", "용리단길", "40404"));
            em.persist(order4);

            Order order5 = new Order();
            order5.setOrderAmount(0);
            order5.setProduct(null);
            order5.setAddress(new Address("용산", "용리단길", "40404"));
            em.persist(order5);

            Order order6 = new Order();
            order6.setOrderAmount(100);
            order6.setProduct(product7);
            order6.setAddress(new Address("홍대", "젊음의거리", "22333"));
            em.persist(order6);

            Order order7 = new Order();
            order7.setOrderAmount(1100);
            order7.setProduct(product8);
            order7.setAddress(new Address("사당", "마음대로", "77777"));
            em.persist(order7);
            //기존 것
            Team team1 = new Team();
            team1.setName("teamA");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("admin1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("admin2");
            member2.setTeam(team2);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("user1");
            member3.setTeam(team1);
            em.persist(member3);

            em.flush();em.clear();

            String query = "update Member m set m.age = 20";
            int resultcount = em.createQuery(query).executeUpdate();
            em.clear();
            Member findMember = em.find(Member.class, member1.getId());

            System.out.println("member1.age : " + findMember.getAge());

            System.out.println("resultCount : " + resultcount);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close();
    }

}

