package org.scoula.app;

import org.scoula.config.ProjectConfig2;
import org.scoula.domain.Parrot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig2.class); // ProjectConfig2가 올바른 경로로 Import 되어 잇는지 확인하기
        // “스프링아, ProjectConfig2 클래스에 설정이 들어 있으니까, 그걸 기준으로 스프링 컨테이너를 초기화해줘!”

        Parrot p = context.getBean("miki", Parrot.class); // parrot 클래스에 있는 많은 객체 중의 miki라는 이름을 가져오게 하는 것
        // 빈이 하나만 등록되어 있으면 타립만으로도 불러올 수 있다.

        System.out.println(p.getName());
    }
}

