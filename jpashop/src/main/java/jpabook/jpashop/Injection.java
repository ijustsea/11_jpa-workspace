package jpabook.jpashop;

import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

//DI : Dependency Injection 3 ways
public class Injection {

    //1.필드 주입 : @Autowired 통해 바로 필드에 의존성부여
    //특징 : 간단하지만 테스트나 유지보수에 불편하여 잘안씀
    @Autowired
    private MemberRepository memberRepository;

    //2.세터 주입 : @Autowired된 세터메소드를 호출
    //특징 : 선택가능, 다른곳에서 변경이 가능하여 잘안씀

    //실무에서 많이 쓰고 중요함.
    //3.생성자 주입 : 스프링이 생성자 호출시점에 의존성을 주입
    //특징 : final키워드로 불변성보장, 테스트 용이하여 권장됨

    //TestCode
    public static void main(String[] args){
        //MemberService ms = new MemberService(??);

    }

}
