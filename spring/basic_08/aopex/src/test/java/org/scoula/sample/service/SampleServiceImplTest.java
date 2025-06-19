package org.scoula.sample.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class) // junit에서 사용하는 어노테이션. spring 기능 (Di, aop, 트랜잭션 등) 연결해준다.
@ContextConfiguration(classes = {RootConfig.class}) // 테스트할때 사용할 스프링 설정파일을 지정하기
class SampleServiceImplTest {

    @Autowired
    private SampleService service; // 터스트는 sapleService에서 하는게 맞다.

    @Test
    void doAdd() throws Exception {
        log.info(service.doAdd("123", "456"));
    }
}