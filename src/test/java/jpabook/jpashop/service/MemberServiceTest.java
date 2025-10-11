package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.JpashopApplication;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(false) // - 영속성 컨텍스트로 테스트말고 DB에 직접 들어가는거 확인하고 싶으면 세팅해도 되는데
                     // - 안 넣어도 됨
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        try {
            System.out.println("111");
            memberService.join(member2); // 예외가 발생해야 한다!!!
        } catch (IllegalStateException e) {
            System.out.println("2222");
            return;
        }

        // then
        fail("예외가 발생해야 한다.");

        //IllegalStateException 예외가 발생하지 않으면 테스트 실패
        // assertThrows(IllegalStateException.class, () ->
        //        memberService.join(member2));
    }
}