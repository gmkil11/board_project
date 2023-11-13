package org.koreait.models.member;

import lombok.Builder;
import lombok.Data;
import org.koreait.entities.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class MemberInfo implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 계정이 갖고있는 권한 목록을 리턴한다.
        return authorities;
    }

    @Override
    public String getPassword() {
        // 계정의 비밀번호를 리턴한다.
        return password;
    }

    @Override
    public String getUsername() {
        // 계정의 이름을 리턴한다.
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
        return true;
    }
}
