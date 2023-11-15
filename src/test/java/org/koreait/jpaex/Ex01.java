package org.koreait.jpaex;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.koreait.commons.constants.MemberType;
import org.koreait.entities.Member;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
public class Ex01 {

    @PersistenceContext
    private EntityManager em;

    @Test
    void test1() {
        Member member = new Member();
        member.setUserNo(1L);
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
}
