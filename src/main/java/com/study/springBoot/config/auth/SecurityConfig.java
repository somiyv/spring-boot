package com.study.springBoot.config.auth;

import com.study.springBoot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션 disable
                .and()
                .authorizeRequests() // url별 권한 관리를 설정하는 옵션의 시작점.
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll() // 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 권한 관리 대상을 지정하는 옵션.
                .anyRequest().authenticated() // 설정된 값들 이외의 나머지 url은 인증된(authenticated)사용자에게만 허용한다
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 성공시 "/"로 이동.
                .and()
                .oauth2Login() // oauth2로그인 기능에 대한 여러 설정의 진입점.
                .userInfoEndpoint() // oauth2로그인 성공 이후 사용자 정보를 가져올때의 설정을 담당.
                .userService(customOAuth2UserService); // 소셜 로그인 성공시 후속 조치를 진행할 UserService인터페이스의 구현체를 등록.
    }
}
