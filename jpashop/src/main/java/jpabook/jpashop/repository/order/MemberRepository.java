package jpabook.jpashop.repository.order;

import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(@NotEmpty String name);
}
