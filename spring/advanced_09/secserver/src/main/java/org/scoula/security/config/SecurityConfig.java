package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@Log4j2
@EnableWebSecurity // spring security에서 웹 보안 설정을 활성화하는 어노테이션
@MapperScan(basePackages = {"org.scoula.security.account.mapper"}) // 여기서 userdetailsmapper가 검색되도록 설정 추가하기
@ComponentScan(basePackages = {"org.scoula.security"})
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { // spring security를 위한 기본 골격으로 Extends 함.

    private final UserDetailsService userDetailsService;

    // passwordEncoder 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // UTF-8 문자셋 인코딩 필터
    public CharacterEncodingFilter encodingFilter() {

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();

        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        return encodingFilter;
    }

    @Override
    public void configure (HttpSecurity http) throws Exception {

        // csrf 필터 앞에 배치하기
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        // all, member, admin 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security/all").permitAll()
                .antMatchers("/security/member").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')")
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')");

        // 로그인 안한 상태에서, 접근권한이 없는 경우 -> 로그인 페이지(/Login)로 이동하도록 설정하기
        http.formLogin() // form 로그인 설정 활성화 (나머지는 모두 디폴트)
        // 로그인페이지를 커스터마이징한 페이지로 운영하기
                .loginPage("/security/login") // 프론트 경로
                .loginProcessingUrl("/security/login") // jsp에서 링크를 눌렀을때 보내지는 경로
                .defaultSuccessUrl("/"); // 성공했을때 보내지는 경로

        // 로그아웃 처리 설정
        http.logout()
                .logoutUrl("/security/logout") // 프론트에서 이동할 경로
                .invalidateHttpSession(true) // 로그아웃시 세션 무효화 설정
                .deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
                .logoutSuccessUrl("/security/logout"); // 로그아웃 성공실 이동할 경로



    }

    // 메모리 기반의 사용자 정보 등록하기 -> 테스트나 간단하게 쓸때 사용한다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // 사용자 정보를 어디서 얻을지 설정하는 클래스 | 인증

        log.info ("memory database configure ..........................................");

//        auth.inMemoryAuthentication()
//                .withUser("admin") // 아이디
////                .password("{noop}1234") // {noop} == 암호화 되어 있지 않음
//                // 메모리 등록 유저의 비빌번호를 암호화된 비밀번호로 대체
//                .password("$2a$10$7NVuOZra2b8KvW68RdXgkOaKG1Y5gBquRX9ZYacTtmTe8L7Jkkft.")
//                .roles("ADMIN", "MEMBER");
//
//        auth.inMemoryAuthentication()
//                .withUser("member")
////                .password("{noop}1234")
//                .password("$2a$10$VMUk4Vy..fjAQsbUYT2ZPOGQ8f5Xtj1zWehzwxk5BORLXsIKw64S.")
//                .roles("MEMBER");

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());


    }

}
