package org.scoula.main;

import org.scoula.beans.Parrot;
import org.scoula.beans.Person;
import org.scoula.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person ps = context.getBean(Person.class);
        Parrot pr = context.getBean(Parrot.class);

        System.out.println("Person's name : " + ps.getName());
        System.out.println("Parrot's name : " + pr.getName());
        System.out.println("Person's parrot : " + ps.getParrot()); // 자동으로 toString() 메서드를 호출 -> 문자열로 변환해서 출력
    }
}
