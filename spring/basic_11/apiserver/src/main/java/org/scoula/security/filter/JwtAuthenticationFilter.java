package org.scoula.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.security.util.JwtProcessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // forward 하게 되더라도 요청당 한번만 필터가 동작하도록 해준다.

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtProcessor jwtProcessor; // 토큰을 만들고 확인하는 코드
    private final UserDetailsService userDetailsService; // 인터페이스. userdetails

    // 토큰에서 유저의 정보를 가져오기
    private Authentication getAuthentication(String token) {

        String username = jwtProcessor.getUsername(token); // jwt 토큰에서 username을 추출하는 메서드

        UserDetails principal = userDetailsService.loadUserByUsername(username); // username을 넣어서 정보를 principal에 넣기

        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities()); // 새로운 객체를 만들어서 정보 넘기기
    }

    // 1. request에서 토큰이 잘 들어왔는지 확인
    // 2. getAuthentication() 메서드를 사용해서 유저의 정보를 contextHolder에 넣기
    // 3. 다음 필터에게 제어를 넘기기
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER); // 받아온 request에서 헤더에 authorization을 가져오기

        // 헤더에 토큰이 올바르게 들어왔는지 조건을 넣어서 확인하기
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {

            String token = bearerToken.substring(BEARER_PREFIX.length()); // 앞부분 자르기

            Authentication authentication = getAuthentication(token); // 유저의 정보를 넣기
            SecurityContextHolder.getContext().setAuthentication(authentication); // contextholder에 유저의 정보를 저장하기 -> securitycontext에 저장하면 이후 인증된 사용자 정보에 접근 가능

        }
        super.doFilter(request, response, filterChain); // 다음 필터에게 제어를 넘기는 것
    }
}
