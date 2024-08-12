# StatefulSet

- 기본 팟들과 다르게 **상태를 가지고 있는 애플리케이션을 배포하고 관리할 때 사용하는 리소스**!
- e.x. DB, Message Queue, jenkins ...

## 특징 
1. **Pod 의 고유성**
   - 각 Pod 들은 고유한 이름과 번호(순번)을 가짐 
     - e.x. statefulset 이름이 'sts-app'이고 replicas 가 3개 일때, 
       - Pod 이름은 : sts-app-0, sts-app-1, sts-app-2
   - 이 고유성은 Pod이 재시작 되거나 재생성 되어도 유지된다!
2. **순차적인 배포 및 업데이트**
   - Pod의 배포 및 업데이트는 순차적으로 이루어짐
   - 즉, 'sts-app-0'이 생성된 후에 'sts-app-1'이 생성됨!
   - 삭제할 때에는 역순으로 진행됨
   - Pod들 간의 순서가 중요한 경우에 유용하다!
3. **고유한 영구 볼륨**
   - 각 Pod 들은 자신의 고유한 영구 볼륨을 가진다 
     - 이를 통해서 Pod이 재생성되어도 동일한 데이터를 유지!
     - e.x. jenkins를 stateless 로 배포하니까, 업데이트하면서 재시작되면 다시 시작되더라... 저도 알고 싶지 않았어요...
4. **서비스와의 연계**
   - StatefulSet은 각 Pod에 대한 개별 dns 주소를 제공하는 Headless Service와 함께 사용해야 한다!
     - e.x. 'sts-app-0'은 'sts-app-0.service-name' 과 같은 주소를 가진다!

## StatefulSet 예시

```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: sts-app
spec:
  serviceName: "sts-service"
  replicas: 3
  selector:
    matchLabels:
      app: sts-app
  template:
    metadata:
      labels:
        app: sts-app
    spec:
      containers:
      - name: app-container
        image: my-app-image
        ports:
        - containerPort: 80
        volumeMounts:
        - name: app-storage
          mountPath: /mnt/storage
  volumeClaimTemplates:
  - metadata:
      name: app-storage
    spec:
      accessModes: [ "ReadWriteOnce" ]
      resources:
        requests:
          storage: 1Gi # 100Mi 1Ti ...
```

- 위 예시에서 dns 주소는 'sts-app-0.sts-service' 와 같이 생성된다!