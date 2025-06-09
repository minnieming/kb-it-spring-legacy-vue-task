package org.scoula.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person2 {
    private String name = "Ella";

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Autowired // @autowired의 필드 주입
    // autowired는 new, set의 역할을 한다. | new로 선언하지 않은 상태에서 한다면 null 에러가 난다.
    // 이 경우 set을 해주지 않으면 동일하게 null이 나타난다.
    private Parrot2 parrot2;
    // 기본적으로 클래스를 사용하기 위해서 new로 선언을 해줘야 하고, set으로 초기값을 세팅해줘야 에러없이 사용할 수 있다.
    // (객체나 get메서드에서 이미 값을 넣은 하드코딩은 바로 값이 나올수가 있긴 하다.)

    public Parrot2 getParrot2() { // 이건 person2안에 있는 getter/setter 이므로 필요하다. (autowired와는 별개다)
        return parrot2;
    }

    public void setParrot2(Parrot2 parrot2) {
        this.parrot2 = parrot2;
    }

}
