# Spring Data JPA

### 벌크성 쿼리
- `@Modifying` 어노테이션을 붙이면, **벌크성 수정, 삭제 쿼리를 사용할 수 있다.**
- 벌크성 쿼리를 실행후, 영속성 컨텍스트를 초기화하고 싶으면, `clearAutomatically = true` 옵션을 사용한다.

### Lock 
- `@Lock(LockModeType.PESSIMISTIC_WRITE)` : **데이터베이스 락을 걸어서** 다른 사용자가 해당 데이터를 수정하지 못하게 한다.

