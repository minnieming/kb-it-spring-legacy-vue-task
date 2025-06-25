package org.scoula.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.scoula.security.util.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationErrorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            super.doFilter(request, response, filterChain); // jwtAuthenticationFilter에서 저 동작이 했는데,
        } catch (ExpiredJwtException e) { // Jwt 토큰 유효시간이 만료 되었을때
            JsonResponse.sendError(response, HttpStatus.UNAUTHORIZED, "토큰의 유효시간이 지났습니다."); // json으로 에러메세지를 보내기
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            //	UnsupportedJwtException: 지원하지 않는 형식의 JWT 토큰일 때 발생해.
            //	MalformedJwtException: 구조가 잘못된(깨진) JWT 토큰일 때 발생해.
            //	SignatureException: 토큰의 서명이 유효하지 않거나 변조되었을 때 발생해.
            JsonResponse.sendError(response, HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (ServletException e) { // 서블릿 에러가 난다면,
            JsonResponse.sendError(response, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
