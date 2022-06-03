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

### 개발순서<br>
1. 도메인 개발
2. 서비스, 리포지토리 계층을 개발
3. 테스트 케이스를 작성해서 검증
4. 웹 계층 적용

### 패키지 구조
- com.springjpapra
  - domain
  - exception
  - repository
  - service
  - web

### 계층형 구조
- controller, web: 웹 계층
- service: 비즈니스 로직, 트랜잭션 처리
- repository: JPA를 직접 사용하는 계층, 엔티티 매니저 사용
- domain: 엔티티가 모여 있는 계층, 모든 계층에서 사용

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
> ### 도메인 분석 설계
>> 1. 요구사항 분석
>>  - 기능 목록
>>    - 회원 기능
>>      - 회원 등록
>>      - 회원 조회
>>    - 상품 기능
>>      - 상품 등록
>>      - 상품 수정
>>      - 상품 조회
>>    - 주문 기능
>>      - 상품 주문
>>      - 주문 내역 조회
>>      - 주문 취소
>>    - 기타 요구사항
>>      - 상품은 재고 관리가 필요하다.
>>      - 상품의 종류는 도서, 음반, 영화가 있다.
>>      - 상품을 카테고리로 구분할 수 있다.
>>      - 상품 주문시 배송정보를 입력할 수 있다.
>
> ### 엔티티 설계시 주의점
>> - 가급적 `@Setter`를 사용하지 말자
>>   - 변경 포인트가 많아서 유지보수가 어렵다.
>> - 모든 연관관계는 지연로딩으로 설정 -> 매우 중요!!!
>>   - 즉시로딩( EAGER )은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어려움
>>   - JPQL을 실행할 때 N+1 문제가 자주 발생  
>>   - 연관된 엔티티를 함께 DB에서 조회해야 하면, fetch join 또는 엔티티 그래프 기능을 사용 
>>   - @XToOne(OneToOne, ManyToOne) 관계는 기본이 즉시로딩이므로 직접 지연로딩으로 설정을 변경해야 함
>> - 컬렉션은 필드에서 초기화
>> - `null`문제에서 안전함
>> - 하이버네이트는 엔티티를 영속화 할 때, 하이버네이트가 제공하는 내장 컬랙션으로 변경하기 때문에 임의의 메서드에서 컬랙션을 잘못 생성하면
하이버네이트 내부 메커니즘에 문제가 발생하여 원하는 것과 다르게 동작할 수 있음
>
> ### 테이블, 컬럼명 생성 전략
>> - 스프링 부트에서 하이버네이트 기본 매핑 전략을 변경해서 실제 테이블 필드명은 다름
>>   - 하이버네이트 기존 구현: 엔티티의 필드명을 그대로 테이블의 컬럼명으로 사용
>>   - 스프링 부트 신규 설정 (엔티티(필드) -> 테이블(컬럼))
>>     1. 카멜 케이스 -> 스네이크 케이스(memberPoint -> member_point) 
>>     2. .(점) _(언더스코어)
>>     3. 대문자 소문자
>>   - 적용 2단계
>>     - 논리명 생성: 명시적으로 컬럼, 테이블명을 직접 적지 않으면 ImplicitNamingStrategy 사용
         spring.jpa.hibernate.naming.implicit-strategy : 테이블이나, 컬럼명을 명시하지 않을 때 논리명
         적용, 
>>     - 물리명 적용: spring.jpa.hibernate.naming.physical-strategy : 모든 논리명에 적용됨, 실제 테이블에 적용 
         (username usernm 등으로 회사 룰로 바꿀 수 있음)
## 추가 학습 목표<br>
- JUnit5 적용
- 로그인과 권한관리 추가