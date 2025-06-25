package org.scoula.security.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class JwtProcessorTest {

    @Autowired
    JwtProcessor jwtProcessor;

    // 특정 사용자 ID에 대해서 JWT 생성
    @Test
    void generateToken() {
        String username = "user0";
        String token = jwtProcessor.generateToken(username);

        log.info(token);

        assertNotNull(token);
    }

    // 생성된 JWT에서 사용자 ID 추출
    @Test
    void getUsername() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDgzNzUwMCwiZXhwIjoxNzUwODM3NjIwfQ.3DJC21LDl2RJzLvW6xLnTQ3xA-98VRHTbFnMLPZMyIUYo1ckALfwJ1fHrryWuE7Y";
        String username = jwtProcessor.getUsername(token);

        log.info(username);

        assertNotNull(username);
    }

    // 생성된 JWT 검증
    @Test
    void validateToken() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDgzNzY0OSwiZXhwIjoxNzUwODM3NzY5fQ.MtQufSaA6uNuDxBIT0-bfEQgu-x9uIwpAsARs5s9-l02DrAoCP589Z0myRAXGM8_";
        boolean isValid = jwtProcessor.validateToken(token);

        log.info(isValid);

        assertTrue(isValid);
    }
}