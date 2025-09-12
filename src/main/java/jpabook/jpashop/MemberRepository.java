package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// DAO아 비슷함
// 스프링이 제공하고 컴포넌트 스캔의 대상
// 그래서 자동으로 스프링 빈에 등록됨
@org.springframework.stereotype.Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 객체 return이 아닌 id만 리턴하는 이유
    // command와 query를 분리해라
    // 사이드 이펙트를 줄이기 위해, id 조회할 수 있게
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
