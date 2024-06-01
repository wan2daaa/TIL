# manifest 파일 = 정의 파일

- 파드나 서비스에 대한 설정을 k8s 에서는 매니페스트 라고 한다
- 매니페스트 파일은 yaml 파일로 작성한다
- 도커 컴포즈와 달리 k8s 에서는 manifest 파일의 이름이 지정돼 있지 않다.

## manifest 파일은 리소스 단위로 작성 한다.

- 리소스는 파드, 서비스 ,deployment, 레플리카 세트 등을 가리킴

    - 파드 리소스는 사용하지 않는다.
    - 파드를 사용하긴 할텐데 파드 항목을 작성하지 않는다고 하니까 이상하다
    - 파드 항목은 정말로 파드만을 만들 때 사용하는 항목
- **파드 항목에는 자동으로 설정된 개수를 유지하는 기능이 없다.**


- 그래서 -> 개수를 유지하는 기능은 deployment 나 레플리카 세트에서 담당하므로 파드가 아닌 **deployment 를 만들어야 함**
    - 레플리카 세트 역시 deployment 에 의해 개수가 관리되므로 항목으로 작성 안함!

### manifest 파일은 여러 파일로 분할할 수 있다

- manifest 파일은 여러 파일로 분할할 수 있다.
- 또 한 파일에 합쳐 작성해도 된다.
- 만약 한 파일로 작성할 때는 각 리소스를 '---' 로 구분한다.

## manifest 파일의 기본 구성

- apiVersion : API 그룹 및 버전
- kind       : 리소스 유형
- metadata   : 메타데이터
- spec       : 리소스 내용

### 리소스 설정 (API 그룹 및 유형)

- 리소스를 정의하려면 먼저 API 그룹과 리소스 유형을 지정해야 함

| 리소스        | API 그룹 / 버전        | 리소스 유형     |
|------------|--------------------|------------|
| 파드         | core/v1(v1으로 축약가능) | Pod        |
| 서비스        | core/v1            | Service    |
| deployment | apps/v1            | Deployment |
| 레플리카 세트    | apps/v1            | ReplicaSet |

### 메타데이터와 스펙 
- manifest 파일에는 메타데이터와 스펙을 기재한다. 

#### 메타 데이터 
- 리소스의 이름이나 레이블 을 기재 

#### 스펙 
- 리소스의 내용을 정의 
- 요약하자면 '어떤 리소스를 만들 것인가' 에 해당하는 부분 


### 레이블과 셀렉터 
- 파드나 서비스 같은 리소스에 원하는 **레이블**을 붙일 수 있다. 
- 레이블은 ket-value 쌍의 형태로 메타데이터로 설정한다.
  - 레이블을 부여하면 -> 셀렉터 기능을 사용해 특정 레이블이 부여된 파드만을 배포하는 등 **특정 파드를 선택해 설정할 수 있다!**
- 레이블로 특정 리소스를 선택할 수 있다!

- 솔직히 아직까지는 좀 헷갈린다

