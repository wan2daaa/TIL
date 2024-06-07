# OSIV

- Open Session In View 
- 영속성 컨텍스트를 뷰까지 열어둔다는 뜻 
- 영속성 컨텍스트가 살아있으면 -> 엔티티는 영속상태로 유지 
  - -> 뷰에서도 지연 로딩을 사용가능

### 스프링 OSIV : 비즈니스 계층 트랜잭션 

- OSIV 를 사용하기는 하지만, 트랜잭션은 비즈니스 계층에서만 사용되어짐 

### open-in-view 는 항상 조심하자

- api 요청부터 응답까지 영속성 컨텍스트가 유지되어서 
- 트랜잭션이 끝나도 DB connection 이 반납되지 않는 경우가 생긴다...



https://medium.com/frientrip/spring-boot%EC%9D%98-open-in-view-%EA%B7%B8-%EC%9C%84%ED%97%98%EC%84%B1%EC%97%90-%EB%8C%80%ED%95%98%EC%97%AC-83483a03e5dc

https://www.baeldung.com/spring-open-session-in-view