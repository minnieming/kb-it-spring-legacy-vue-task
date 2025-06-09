package org.scoula.config;

import org.scoula.domain.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정한다는 의미
public class ProjectConfig {

    @Bean // 주입을 하기 위해. 메서드 단위로 주입을 할때는 Bean을 사용한다.
    Parrot parrot() {
        var p = new Parrot(); // var : 변수를 선언할때 사용하는 키워드
        p.setName("Koko");
        return p;
    }

}
