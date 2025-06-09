package org.scoula.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person3 {
    private String name = "Ella";
    private final Parrot2 parrot2; // final을 붙이지 않으면 this.parrot2=parrot2 처럼 값을 바꿔도 알아차리기 어렵다.
    // 컴파일 시점에서 한번만 초기화 된다! 다른 값이 수정이 되지 않아서 안전하기 때문에 필수는 아니지만 이렇게 사용한다.
    // run을 멈추고 수정후 다시 시작하면 그 부분은 반영이 된다.

    @Autowired // 생성자 주입 방식 -> 이 방법을 가장 많이 사용한다.
    public Person3(Parrot2 parrot2) { // Parrot2를 new 하지 않아도 자동으로 넣어준다.
        this.parrot2 = parrot2;
    }
}
