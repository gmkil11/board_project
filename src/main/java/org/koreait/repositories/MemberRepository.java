package org.koreait.repositories;

import org.koreait.entities.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {

    // NPE를 방지하기 위해서 Optional 로 감싸줌
    Optional<Member> findByEmail(String email);


}
