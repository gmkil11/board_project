package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.MemberRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardScheduleService {

    private final MemberRepository memberRepository;

    private final BoardDataRepository boardDataRepository;

    @Scheduled(cron="0 0 1 * * *")
    public void process() {
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            Long userNo = member.getUserNo();
            long postCount = boardDataRepository.countByMember_UserNo(userNo);


            System.out.println("사용자 " + userNo + "은(는) " + postCount + "개의 글을 작성했습니다.");
        }
    }
}