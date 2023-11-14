package org.koreait.configs;

import org.koreait.models.member.LoginFailureHandler;
import org.koreait.models.member.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableConfigurationProperties(FileUploadConfig.class)
public class SecurityConfig {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
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

        // iframe에 자바스크립트를 적용하기 위해서 사용
        http.headers(c -> {
            // iframe으로 넘길때 같은 출처 쪽이면 허용을 한다.
            c.frameOptions(o -> o.sameOrigin());
        });




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
