```json
{
  "compilerOptions" : {              //컴파일러 옵션
    "module" : "CommonJS",           //모듈 시스템
    "target" : "ESNEXT",             // 사용할 ES 버전 
    "experimentalDecorators" : true, //데코레이터를 사용할지 여부
    "emitDecoratorMetadata" : true   // 데코레이터의 메타 데이터를 같이 내보낼지 여부
  }
}
```

- `module` : 컴파일 시 모듈 시스템 설정 가능
- `target` : 컴파일 시 사용할 ECMA 버전 (ESNEXT : 가장 최신 버전)
- 