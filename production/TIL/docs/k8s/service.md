# service 

- 서비스는 외부와 내부에서 접근할 수 있도록 하는 방법 을 선언하는 곳 
- 여러가지 type 이 있다 

## service yaml 파일 설명 
```yaml
# 서비스의 역할은 파드로 들어오는 요청을 관리하는 것!
apiVersion: v1
kind: Service
metadata:
  name: test-service  # 서비스 이름
spec:
  selector:            # 셀렉터 설정
    app: test-deploy
  type: ClusterIP      # 서비스의 유형 (ClusterIP (default), NodePort, LoadBalancer)
  ports:               # 포트 설정
    - port: 8099       # 서비스 포트
      targetPort: 8080 # 파드 포트
      protocol: TCP
      nodePort: 30080  # 워커 노드의 포트 (30000 ~ 32767) localhost:30080 <- 이렇게 접근 가능
```

## service type

### ClusterIP
- 기본 서비스 type
- **내부 통신 용도** : 클러스터 내부에서만 접근 가능한 서비스 , 외부 접근 X 
- **서비스 접근 방법**: 클러스터 내부의 다른 Pod 들이 특정 서비스에 접근할 수 있도록 클러스터 IP 주소와 포트를 할당
- **용도** : 일반적으로 클러스터 내부에서의 서비스간 통신에 사용됨 

```yaml
apiVersion: v1
kind: Service
metadata:
  name: my-service
spec:
  selector:
    app: MyApp
  ports:
    - protocol: TCP
      port: 80
      targetPort: 9376

```

### NodePort 
- **외부 통신 용도**: 클러스터 외부에서도 접근할 수 있는 서비스 type
- 클러스터의 각 노드에서 특정 포트를 열어 외부 트래픽을 서비스로 라우팅 가능 
- **서비스 접근 방법**: NodePort는 클러스터의 각 노드에 동일한 포트를 할당 
  - 이 포트는 30000~32767 범위 내에서 선택, 
  - 클러스터 외부에서 이 포트로 접근하면 노드가 요청을 적절한 Pod 으로 라우팅함   
- **용도** : 외부에서 직접 접근해야 하는 경우, 

```yaml
apiVersion: v1
kind: Service
metadata:
  name: my-service
spec:
  type: NodePort
  selector:
    app: MyApp
  ports:
    - protocol: TCP
      port: 80
      targetPort: 9376
      nodePort: 30007
```

### LoadBalancer
- **외부 접근 용**: 클라우드 환경에서 클러스터 외부에서 서비스에 접근할 수 있도록 하기 위해 사용 
  - 외부 클라이언트가 LoadBalancer IP를 통해 서비스에 접근 가능 
- **자동 로드 밸런싱**: LoadBalancer 서비스는 클라우드 제공업체(aws, gcp, azure)의 로드밸런서를 자동으로 생성해서 클러스터의 여러 노드에 트래픽을 분산함
    - 이로 인해서 클라이언트 요청은 -> 클러스터의 여러 노드에 분산되어 처리 됨 
- **쉽고 편리한 설정**: 클라우드 환경에서는 LoadBalancer 서비스를 사용하면 별도의 로드 밸런서 설정 없이 자동으로 클라우드 제공업체의 로드 밸런서가 생성됨

```yaml
apiVersion: v1
kind: Service
metadata:
  name: my-service
spec:
  type: LoadBalancer
  selector:
    app: MyApp
  ports:
    - protocol: TCP
      port: 80
      targetPort: 9376

```

### type 요약
ClusterIP는 클러스터 내부에서의 통신을 위해 사용됩니다. 클러스터 외부에서는 접근할 수 없습니다.
NodePort는 클러스터 외부에서 특정 노드와 포트를 통해 서비스에 접근할 수 있도록 합니다.
LoadBalancer는 클라우드 제공업체의 로드 밸런서를 사용하여 클러스터 외부에서 쉽게 서비스에 접근할 수 있도록 합니다. 클라이언트 요청은 클러스터의 여러 노드로 분산됩니다.



## service 의 metadata name을 url 로 접근 가능

- mysql-service
```yaml
# mysql-service.yaml
apiVersion: v1
kind: Service
metadata:
  name: mysql-svc    # <- 이 이름으로 http://mysql-svc 로 접근 가능
spec:
  selector:
    app: mysql
  ports:
  - protocol: TCP
    port: 3306       # http://mysql-svc:3306 으로 접근 가능
    targetPort: 3306
  type: ClusterIP

```

- spring boot deploy
```yaml
# springboot-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboot
spec:
  selector:
    matchLabels:
      app: springboot
  replicas: 1
  template:
    metadata:
      labels:
        app: springboot
    spec:
      containers:
      - name: springboot
        image: your-springboot-image
        env:
        - name: SPRING_DATASOURCE_URL
          value: jdbc:mysql://mysql-svc:3306/db-name
        - name: SPRING_DATASOURCE_USERNAME
          value: root
        - name: SPRING_DATASOURCE_PASSWORD
          value: root_password
        ports:
        - containerPort: 8080
```
