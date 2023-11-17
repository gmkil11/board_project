package org.koreait.jpaex;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.commons.constants.MemberType;
import org.koreait.entities.Member;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@TestPropertySource(locations = "classpath:application-test.yml")
public class Ex01 {

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init() {
        Member member = new Member();
//        member.setUserNo(1L);
        member.setEmail("user01@test.org");
        member.setUserNm("사용자01");
        member.setPassword("123456");
        member.setMobile("01000000000");
        member.setMtype(MemberType.USER);

        em.persist(member); // 변화 감지 상태
        em.flush();
        em.clear(); // 영속성 비우기
    }

    @Test
    void test1() {
        Member member = new Member();
//        member.setUserNo(1L);
        member.setEmail("user01@test.org");
        member.setUserNm("사용자01");
        member.setPassword("123456");
        member.setMobile("01000000000");
        member.setMtype(MemberType.USER);

        em.persist(member);
        em.flush();

        member.setUserNm("(수정)사용자01");
        em.flush();
    }

    @Test
    void test2() {
        Member member = em.find(Member.class, 1L); // DB -> 영속성 콘텍스트로 추가
        System.out.println(member);

        Member member2 = em.find(Member.class, 1L); // 영속성 컨텍스트 -> 조회
        System.out.println(member2);

        TypedQuery<Member> query = em.createQuery("SELECT m FROM Users AS m WHERE m.email LIKE :key", Member.class);
        query.setParameter("key", "%user%");
        Member member3 = query.getSingleResult();

        System.out.println(member3);

        member3.setUserNm("(수정)사용자명1");
        em.flush();

    }

    @Test
    void test3 () {

    }
}
