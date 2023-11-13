package org.koreait.models.member;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberInfoService implements UserDetailsService {
    // 이 메소드에서 유저 정보를 불러오는 작업을 하면 된다.
    // 여기에서 CustomUserDetails 형으로 사용자의 정보를 가져오면 된다.
    // 가져온 사용자의 정보를 유/무에 따라 예외와 사용자 정보를 리턴하면 된다.
    // 이 부분은 DB에서 유저의 정보를 가져와서 리턴해주는 작업이다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
