package org.scoula.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

// Spring MVC에서 자바 기반 설정(Java Config)을 사용해 웹 애플리케이션을 초기화하는 클래스
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer { // DispatcherServlet과 관련된 설정을 자동으로 등록하도록 상속을 받음 -> web.xml 없이 자바 설정 파일로 Spring 애플리케이션을 초기화할 수 있게 해준다.
    // 상속시 -> dispatcherServlet을 자동으로 등록 / root/servlet 설정 클래스 / url 매핑등을 지정할 수 있다.

    // application : 웹 전체를 구성하는 모든 요소들의 묶음. 전반적인 설정
    // -> db 연결 설정, service,dao 등 비즈니스 로직, 트랜잭션 관린 등. 전체적인 프로젝트
    @Override // Root application context configuration. Application 전반적인 공통 설정. : 루트 컨텍스트에서 사용할 설정 클래스들을 리턴하는 역할.
    protected Class<?>[] getRootConfigClasses() { // @configuration, componentscan, bean 등이 들어간다.
        // 리턴타입 : Class -> 어떤 클래스 타입인지 모를 때 사용하는 제네릭 타입. 배열 : 여러개의 클래스 정보를 담는 배열 ex. rootconfig, appconfig 등
        return new Class[] {RootConfig.class}; // rootconfig라는 클래스릐 정보를 class 배열로 만들어서 반환하는 것.
    }

    // 서블릿(spring에서는 dispatcherServlet) : 자바 기반의 웹 프로그래밍에서 클라이언트(브라우저)의 요청을 처리하는 서버쪽 프로그램.
    // 1. 브라우저에서 요청이 오면 받음
    // 2. 그 요청을 분석하고 처리함
    // 3. 결과를 html등의 형태로 응답함.
    // -> controller등록, viewresolver 설정, webmvcconfigturer 설정 등. 웹 요청을 처리하는 도구
    @Override // Servlet application context configuration. -> controller, viewresolver, webmvcconfigurer등의 설정이 들어간다.
    protected Class<?>[] getServletConfigClasses(){ // 서블릿 컨텍스트에서 사용할 설정 클래스들을 리턴하는 역할
        return new Class[] {ServletConfig.class};
    }

    // 스프링의 FrontController인 DispatcherServlet이 담당할 Url 매핑 패턴, "/" : 모든 요청에 대해 매핑
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    // dispatcherServlet에 필터를 적용
    // POST body 문자 인코딩 필터 설정- UTF-8 설정
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter(); // 사용한 필터. 스프링 제공

        characterEncodingFilter.setEncoding("UTF-8"); // 요청과 응답의 문자 인코딩을 utf-8로 설정
        characterEncodingFilter.setForceEncoding(true); // 요청과 응답 모두 강제로 utf-8로 설정

        return new Filter[] {characterEncodingFilter}; // 해당 필터를 반환한다.
    }
}
