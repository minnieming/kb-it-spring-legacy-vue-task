package org.scoula.app;

import org.scoula.config.ProjectConfig;
import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class); // configuration은 해당 클래스에 있으니 설정은 여기서 읽어줘

        Parrot p = context.getBean(Parrot.class); // bean을 추출. 등록되어 있는 Parrot 객체를 꺼내줘
        System.out.println(p.getName());
    }
}
