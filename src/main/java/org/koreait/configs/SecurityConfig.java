package org.koreait.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.koreait.models.member.LoginFailureHandler;
import org.koreait.models.member.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(FileUploadConfig.class)
public class SecurityConfig {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /* 인증 설정 로그인 S */
        http.formLogin(f -> {
            // 로그인 페이지를 매핑해주면 자동으로 포스트 매소드를 처리를 해줌
            f.loginPage("/member/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginFailureHandler());


        }); // DSL

        // 로그아웃 페이지를 매핑해주면 자동으로 로그아웃 메소드를 처리를 해줌
        http.logout(c -> c.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/login"));

        /* 인증 설정 로그인 E */

        // iframe에 자바스크립트를 적용하기 위해서 사용
        http.headers(c -> {
            // iframe으로 넘길때 같은 출처 쪽이면 허용을 한다.
            c.frameOptions(o -> o.sameOrigin());
        });

        /* 인가 설정 - 접근 통제 S */
        http.authorizeHttpRequests(c -> {

            c.requestMatchers("/mypage/**").authenticated() // 회원 전용 (로그인한 회원만 접근 가능)
                    .requestMatchers("/admin/**").hasAuthority("ADMIN") // 관리자 권한만 접근 가능
                    .anyRequest().permitAll(); // 나머지 페이지는 권한 필요 없음
        });

        http.exceptionHandling(c -> {
            c.authenticationEntryPoint((req, resp, e) -> {
                String URI = req.getRequestURI();
                if (URI.indexOf("/admin") != -1){ // 관리자 페이지 -> 401 응답 코드 페이지
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NOT AUTHORIZED");

                } else { // 회원 전용 페이지(예 -> /mypage ) -> 로그인 페이지 이동
                    String URL = req.getContextPath() + "/member/login";
                    resp.sendRedirect(URL);
                }
            });
        });
        /* 인가 설정 - 접근 통제 E */




        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 시큐리티 설정이 적용이 될 필요가 없는 경로를 설정
        return w -> w.ignoring().requestMatchers(
                "/front/css/**",
                "/front/js/**",
                "/front/img/**",

                "/mobile/css/**",
                "/mobile/js/**",
                "/mobile/img/**",
                // 인가 설정 목록에 admin만 접근 가능하게 해놓은 설정 권한이 없어도 접근 가능하게 하려고 설정해놓음
                "/admin/css/**",
                "/admin/js/**",
                "/admin/img/**",

                "/common/css/**",
                "/common/js/**",
                "/common/img/**",
                fileUploadConfig.getUrl()+"**");


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
