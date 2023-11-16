package org.koreait.configs;

import org.koreait.models.member.MemberInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    // 간단한 매핑을 통해 특정 필드에 지금 로그인한 사람의 정보로 등록자를 자동으로 입력 해줄 수 있다.
    @Override
    public Optional<String> getCurrentAuditor() {

        String email= null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Object principal = auth.getPrincipal(); // 비회원 - String(문자열) : anonymousUser, 회원 UserDetails 구현 객체

        if(auth != null && auth.getPrincipal() instanceof MemberInfo){
            MemberInfo member = (MemberInfo) auth.getPrincipal();
            email = member.getEmail();
        }

        return Optional.ofNullable(email);
    }
}
