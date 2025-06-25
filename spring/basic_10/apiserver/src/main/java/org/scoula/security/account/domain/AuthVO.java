package org.scoula.security.account.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AuthVO implements GrantedAuthority { // 사용자의 권한(역할)을 표현하기 위한 인터페이스

    // 권한 테이블에 대응하는 AuthVO 클래스 정의 : GrantedAuthority 인터페이스 구현
    private String username;
    private String auth;

    @Override
    public String getAuthority() {
        return auth;
    }
}
