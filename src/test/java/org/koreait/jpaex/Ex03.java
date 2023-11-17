package org.koreait.jpaex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.koreait.entities.BoardData;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class Ex03 {

    @PersistenceContext
    private EntityManager em;

    @Test
    void test1() {
        BoardData data = BoardData.builder()
                .subject("제목")
                .content("내용")
                .build();

        em.persist(data);
        em.flush();

        BoardData data2 = em.find(BoardData.class, data.getSeq());
        System.out.println(data2);
        System.out.printf("getCreated() : %S \n getModifiedAt(): %s", data2.getCreatedAt(), data2.getModifiedAt());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        data2.setSubject("(수정)제목");
        em.flush();

         data2 = em.find(BoardData.class, data.getSeq());
        System.out.println(data2);
        System.out.printf("getCreated() : %S \n getModifiedAt(): %s", data2.getCreatedAt(), data2.getModifiedAt());
    }

}
