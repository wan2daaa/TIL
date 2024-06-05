# EmbeddedId 

- `@IdClass` 가 데이터베이스에 맞춘 방법이라면, 
- `@EmbeddedId` 는 조금더 객체지향적인 방법

```java
@Entity 
public class Parent {
    @EmbeddedId
    private ParentId id;
    
    private String name;
}

@EqualsAndHashCode
@Embeddable
public class ParentId implements Serializable {
    @Column(name = "parent_id1")
    private String id1;
    
    @Column(name = "parent_id2")
    private String id2;
}
```
## 특징 
- @Embeddable 어노테이션을 붙여야 한다
- Serializable 인터페이스를 구현해야 한다
- equals, hashCode 를 구현해야 한다
- 기본 생성자가 있어야 한다

