package jpabook.jpashop.service;

import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Member;

import jpabook.jpashop.repository.order.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param member
     * @return
     */
    @Transactional(readOnly = false)
    public Long join(Member member){
        //중복회원 검증 메소드
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    @Transactional(readOnly = false)
    public void validateDuplicateMember(Member member) {
        // 중복이면 exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원전체조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Member findOne(Long id){
        return memberRepository.findById(id).get();
    }

    @Transactional
    public void update(Long id, @NotEmpty String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }
}
