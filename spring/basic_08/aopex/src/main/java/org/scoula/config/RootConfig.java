package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {
        "org.scoula.sample.service",
        "org.scoula.advice"
})
@EnableAspectJAutoProxy // aop 기능을 활성화 하기 위한 어노테이션
public class RootConfig {

}
