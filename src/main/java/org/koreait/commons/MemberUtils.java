package org.koreait.commons;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtils {

    private final HttpSession session;

    public Member getMember() {
        return  (Member)session.getAttribute("loginMember");
    }

    public boolean isLogin() {
        // null 값이 아니면 로그인 상태다.
        return getMember() != null;
    }
}
