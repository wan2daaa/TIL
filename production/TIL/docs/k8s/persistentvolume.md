# PersistentVolume

### accessModes
- PersistentVolumeClaim (PVC)과 PersistentVolume (PV)의 액세스 모드를 정의하는 데 사용됨
- 액세스 모드는 어떻게 그리고 몇 개의 노드에서 PV가 마운트될 수 있는지를 지정

#### accessModes 종류
1. **ReadWriteOnce (RWO)**:
    - **설명**: **하나의 노드에서만 읽기 및 쓰기 작업이 가능**
    - **용도**: **단일 인스턴스 애플리케이션 또는 Pod**에서만 사용되는 데이터 볼륨에 적합
    - **예시**: 대부분의 **데이터베이스 애플리케이션**

2. **ReadOnlyMany (ROX)**:
    - **설명**: **여러 노드에서 읽기 작업이 가능**
    - **용도**: **여러 인스턴스에서 읽기 전용으로 접근**해야 하는 데이터에 적합
    - **예시**: 여러 Pod에서 동일한 데이터를 읽기만 하는 경우

3. **ReadWriteMany (RWX)**:
    - **설명**: **여러 노드에서 읽기 및 쓰기 작업이 가능**
    - **용도**: 다중 인스턴스 애플리케이션에서 동시에 읽기 및 쓰기 작업을 수행해야 하는 경우에 적합합니다.
    - **예시**: **공유 파일 시스템**, **분산 캐시 애플리케이션** 등.

4. **ReadWriteOncePod (RWOP)**: (Kubernetes 1.22 이후 지원)
    - **설명**: **단일 Pod에서만 읽기 및 쓰기 작업이 가능**
    - **용도**: **특정 Pod에서만 볼륨을 마운트해야 하는 경우**에 적합
    - **예시**: **특정 Pod에서만 액세스해야 하는 중요한 데이터가 있는 경우**

### 예시
**`ReadWriteOnce`** 예시:
```yaml
volumeClaimTemplates:
  - metadata:
      name: mysql-data
    spec:
      accessModes: ["ReadWriteOnce"]
      resources:
        requests:
          storage: 1Gi
```


### 각 액세스 모드의 특징
- **RWO (ReadWriteOnce)**: 가장 일반적인 액세스 모드 
  - 한 번에 하나의 노드만 볼륨을 마운트할 수 있기 때문에 **대부분의 Stateful 애플리케이션에서 사용됨**
- **ROX (ReadOnlyMany)**: 여러 노드에서 읽기 전용으로 사용할 때 유용
  - **로그 파일이나 읽기 전용 데이터 파일**에 적합합니다.
- **RWX (ReadWriteMany)**: 동시에 여러 노드에서 쓰기 및 읽기가 필요한 애플리케이션에 유용
  - NFS와 같은 네트워크 파일 시스템을 사용할 때 유용

### 적용 사례
- **RWO (ReadWriteOnce)**:
    - MySQL, PostgreSQL과 같은 데이터베이스
    - 단일 인스턴스 애플리케이션
- **ROX (ReadOnlyMany)**:
    - 콘텐츠 배포 네트워크 (CDN)
    - 여러 인스턴스에서 같은 설정 파일을 읽을 때
- **RWX (ReadWriteMany)**:
    - 공유 파일 시스템
    - 분산 로그 수집 시스템
    - 컨테이너화된 CI/CD 시스템