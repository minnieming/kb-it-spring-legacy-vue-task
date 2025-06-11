package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// 스프링부트가 아닌 화녁ㅇ에서 우리가 직접 웹 설정을 해줘야 할때 사용된다.
// spring mvc 설정 파일
@EnableWebMvc // Spring에서 mvc 기능을 활성화한다는 의미 : dispatcherservlet등 핵심적인 컴포넌트 등록 (스프링 레거시에서 사용)
@ComponentScan(basePackages = "org.scoula.controller")
public class ServletConfig implements WebMvcConfigurer { // spring mvc의 다양한 설정을 커스터마이징 할 수 있게 한다. : 정적 리소스, 뷰 리졸버, 메시지 컨버터, 인터셉터 등

    // Handler : 어떤 요청(이벤트, 동작 등)이 들어왔을 때 그걸 처리하는 역할을 맡은 놈. “뭔가 일어나면 그걸 맡아서 처리하는 사람”이 핸들러야. : 어떤 요청/이벤트가 발생했을 때, 그걸 처리하는 함수 또는 객체

    // 1. 정적 리소스 (CSS, JS, 이미지 등) -> addResourceHandler에서 처리
    @Override // Html/CSS/Js등 정적리소스를 처리할 핸들러를 등록하는 메서드.
    // 정적파일은 dispatcherServlet을 거치지 않고 바로 반환하는게 성능상으로 유리 -> 바로 반환하도록 설정하는 메서드!
    public void addResourceHandlers(ResourceHandlerRegistry registry) { // ResourceHandlerRegistry : 스프링이 제공하는 클래스. 정적리소스 핸들러 등록하는 역할 : url 패턴 -> 물리적 경로 연결을 등록해주는 도구
        registry.addResourceHandler("/resources/**") // url이 /resources/로 시작하는 모든 경로 : 클라이언트가 요청한 Url 경로 패턴
                .addResourceLocations("/resources"); // webapp/resources/ 경로로 매핑 -> 실제 서버안에서의 물리적인 경로를 말한다. Spring MVC에서는 src/main/webapp/가 실제 웹 루트 디렉토리이기 때문에 뒤에 붙는거다!
        // 클라이언트 요청: /resources/style.css
        // 실제 물리적 위치: src/main/webapp/resources/style.css
        // /resources/** 이 패턴으로 들어온 요청은 -> 실제로 webapp/resources/ 폴더에서 파일을 찾아라 라는 설정이다 (각각)
    }

    // 2. 동적으로 처리되는 파일 (JSP 등) -> viewResolver가 처리
    @Override // viewResolver를 어떻게 설정할지 알려주는 메서드 : 컨트롤러가 반환한 '문자열'을 어떤 jsp 파일로 연결할지 정하는 부분
    public void configureViewResolvers(ViewResolverRegistry registry) { // Spring MVC에서 “뷰 리졸버(ViewResolver)“들을 등록할 수 있게 해주는 통로. 직접 어떤  ViewResolver를 사용할지 설정할 수 있는 통로. jsp를 위한 리졸버

        // Viewresolver로 세팅하기 위한 것
        InternalResourceViewResolver bean = new InternalResourceViewResolver(); // Jsp를 뷰로 사용하는 리졸버 : Jsp를 위한 viewResolver 객체를 생성한 것

        bean.setViewClass(JstlView.class); // Jsp가 jstl 지원 | JSTL을 사용하기 위한 JSP 뷰 클래스. -> 반환하는 jsp 파일은 Jstl 태그를 쓴다는 뜻. jstlview.class = jstl 태그가 들어간 jsp를 렌더링 할 수 있도록 도와주는 클래스 bean.setPrefix("/WEB-INF/views/"); // JSP 파일이 저장된 경로의 앞부분.
        bean.setPrefix("/WEB-INF/views/"); // jsp 위치
        bean.setSuffix(".jsp"); // JSP 파일의 확장자.

        // (매개변수) Viewresolver로 등록하기
        registry.viewResolver(bean); // 등록
    }

    // 3. Json 응답 등 -> @ResponseBody, @RestController가 처리한다.
}
