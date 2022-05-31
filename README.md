# SpringJPAPractice

--------------------------------
### [실전! 스프링 부트와 JPA 활용1](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1/dashboard) 실습 <br>

## 실습환경<br>

- Project: Gradle Project
- Spring Boot: 2.6.7
- Language: Java
- Packaging: Jar
- Java: 11
- Dependencies: Spring Web, Thymeleaf, Lombok, JUnit4, OJDBC8 + JPA
- 강의가 JUnit4를 기준으로 하기 때문에 `build.gradle`에 JUnit4로 변경 필요
  ```java
    //JUnit4 추가 
    testImplementation("org.junit.vintage:junit-vintage-engine") {
         exclude group: "org.hamcrest", module: "hamcrest-core"
     }

## 강의 내용<br>
- 프로젝트 환경설정
- 요구사항 분석
- 도메인과 테이블 설계
- 아키텍쳐 구성
- 핵심 비즈니스 로직 개발(회원, 상품, 주문)
- 테스트
- 웹 계층 개발


## 학습 내용 정리<br>

> ### 쿼리 파라미터 로그 남기기
> 1. 로그에 다음을 추가하기 org.hibernate.type : SQL 실행 파라미터를 로그로 남긴다.
> 2. 외부 라이브러리 사용</br>
     https://github.com/gavlyukovskiy/spring-boot-data-source-decorator
> ```java 
> 'implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.6' 
> ```
>> 참고 : 쿼리 파라미터를 로그로 남기는 외부 라이브러리는 시스템 자원을 사용하므로, 운영시스템에 적용하려면 꼭 성능테스트를 하고 사용하는 것이 좋다. 

## 추가 학습 목표<br>
- JUnit5 적용