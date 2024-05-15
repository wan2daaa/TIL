# 서킷 브레이커 패턴 

- MSA에서 서비스간 장애 전파를 막는 패턴

MSA는 시스템을 여러 서비스로 나누어서 서비스 컴포넌트 간에 호출하는 개념을 가지고 있다.

MSA 환경에서 큰 단점이 하나의 컴포넌트가 느려지거나 장애가 나면, 그 장애가 난 컴포넌트를 **호출하는 종속된 컴포넌트까지 장애가 전파되는 특성**을 가지고 있다. 

### 장애 전파를 막는 서킷 브레이커 

- 기본적인 원리는 다음과 같다. 
  - 서비스간 호출 중간에 서킷 브레이커를 설치한다. 
  - 모든 호출은 서킷브레이커를 통하게 되고, 
    - 문제없을때는 트래픽을 bypass 한다.
    - 만약, 호출을 받는 서비스의 문제를 서킷 브레이커가 감지하면 
    - -> 호출을 끊어서 요청을 기다리지 않도록해서 장애가 전파하는 것을 방지한다. 
      - 여기서 더 발전한 방법이 fall-back 메시징인데, 장애난 서비스가 응답을 제대로 못할 때, 
        - 서킷 브레이커가 룰에 따라서 다른 메세지를 리턴하게 하는 방법


#### 서킷 브레이커 라이브러리들 
- 넷플릭스의 Hystrix (현재 개발중단하고, 유지보수만 한다고 한다.)
  - https://github.com/Netflix/Hystrix
```text
Hystrix(1.5.18 버전)는 넷플릭스의 기존 애플리케이션에 대한 요구 사항을 충족할 만큼 안정적입니다. 
한편, 저희는 사전 구성된 설정(예: 적응형 동시성 제한)이 아닌 애플리케이션의 실시간 성능에 반응하는 보다 적응적인 구현에 초점을 맞추고 있습니다. 
Hystrix와 같은 솔루션이 적합한 경우에는 기존 애플리케이션에 Hystrix를 계속 사용하고, 새로운 내부 프로젝트에는 resilience4j와 같은 개방적이고 활발한 프로젝트를 활용할 계획입니다. 
다른 기업들에게도 같은 방법을 권장하기 시작했습니다.
```

- Resilience4j
  - https://github.com/resilience4j/resilience4j



https://bcho.tistory.com/1247

https://hudi.blog/circuit-breaker-pattern/
