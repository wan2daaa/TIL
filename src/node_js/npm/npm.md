# npm

- npm(node package manager)은 자바스크립트용 패키지 매니저
- 유저가 만든 패키지를 등록하는 저장소를 의미
- CLI를 의미
- Node.js 설치시 같이 설치됨

- 패키지 매니저는 = 프로젝트에 필요한 의존성 패키지를 관리하는 프로그램
- 의존성 패키지는 = 해당 프로젝트를 실행하는 데 꼭 필요한 라이브러리와 모듈들

## 패키지와 모듈

- Node.js 에서 패키지는 `package.json`으로 정의한 파일 또는 디렉터리

1. package.json 파일이 있는 디렉터리
2. 1번을 압축한 파일
3. 2번을 내려받을 수 있는 URL 주소
4. 3번 정보를 가지고 npm 저장소에 `<패키지명>@<버전>`으로 등록된 것
5. <패키지명>만 있는 경우는 5번에서 latest 태그를 가리킴
6. 1번을 결과로 주는 깃 URL

- package.json으로 정의한 코드 뭉치 == 패키지
- 모듈은 node_modules 디렉터리 아래에 있는 파일 또는 디렉터리
- node_modules에 있는 파일이나 디렉터리는 `require()`함수로 읽을 수 있다

> CommonsJS는 브라우저 뿐아니라 **서버 애플리케이션에서도 모듈 기능을 제공하기 위해 나온 모듈 규약**
>
> - ES6가 나오기 전에 많이 사용, 그리고, **Node.js의 기본 값**
> - 패키지를 임포트할 때 `CommonJS(CJS)`는 `require()` 함수를 사용
> - ES Module(ESM)은 `import` 키워드로 import
> - package.json 파일에 `type` 속성을 `module`로 설정하면 ESM 사용 가능
> - Node.js 에서 ESM을 사용하면 -> 프론트엔드의 코드를 백엔드에서도 쉽게 가져가 사용할 수 있는 장점

- 패키지로 만들면 npm 레지스트리에 등록할 수 있다.

- require() 함수를 사용할 때 단순히 현재 디렉터리의 node_modules만 읽는 건 아님
- module.path에 있는 경로를 따라서 모듈을 찾음
- 상위 디렉터리에 있는 패키지를 계속 타고 올라가면서 node_modules를 확인하면서 굉장히 많은 I/O를 수행
- `require()` 함수는 그래서 무겁다!
    - 이 문제를 해결한 yarn

## package.json 파일을 만들기

- `npm init` 명령어로 package.json 파일을 만들 수 있다.

## 패키지 버전

- Node.js 패키지들은 시멘틱 버전을 사용
- 시멘틱 버전을 사용하지 않으면 패키지 등록 X

```text
<MAJOR>.<MINOR>.<PATCH> 형식
```

- 기존 버전과 호환 X -> `MAJOR`를 올림
- 기존 버전과 호환 O + 새로운 기능 추가 -> `MINOR`를 올림
- 기존 버전과 호환 O + 버그 수정 -> `PATCH`를 올림

### package.json의 구성요소

- `name`: 패키지명
- `version`: 패키지 버전
- `config`: 패키지의 스크립트에서 사용할 환경 변수들
- `dependencies`: 의존성 패키지명과 버전을 앱의 형태로 관리
- `devDependencies`: 테스트시 필요한 의존성 패키지명과 버전을 앱의 형태로 관리
- `peerDependencies`: 다른 패키지에서 직접 requeire로 불러오는 것은 아니지만, 필요한 패키지를 만들어야 하는 경우 사용 . 주로 플러그인의 호환성 정보를 나타날때 사용
- `engines` : 동작 가능한 node 버전을 지정

> 패키지 버전 스코프
> - 일종의 네임스페이스
> - **@ 로 이름이 시작**, 가장 유명한 스코프로 @types
> - 타입스크립트의 타입 정의 패키지는 거의 대부분이 @types 스코프로 시작

## 스크립트 기능과 NPX

- npm은 명령어를 지정해 실행하는 스크립트 기능으로 제공
- 스크립트 기능은 앱 시작, 중지, 빌드, 배포, 테스트 등의 명령어를
  터미널에 매번 입력하지 않고, **package.json에 정의해서 간편하게 명령어 실행하는 기능**

### 스크립트 실행 전과 후 실행될 스크립트 지정

```json
{
  "name": "test-script",
  "version": "1.0.0",
  "scripts": {
    "preHello": "echo '안녕하세요'",
    "hello": "echo 'Hello World!'",
    "postHello": "echo '안녕히 가세요'"
  }
}
```
- `npm run hello` 를 입력하면, pre -> hello -> post 순서로 실행됩니다!

### NPX
- Node Package execute의 약자 
- Node 패키지 실행자 라고도 함 
- 이런 패키지들을 실행하려면 node_modules/.bin 디렉터리에 있는 실행 파일을 실행해야 함
- `npx`를 사용하면 경로 생략해 실행 가능!



## 패키지 잠금 
- npm5 버전부터 등장한 `package-lock.json`은 node_modules 나 package.json을 변경하는 모든 명령 (`npm install, npm update, npm uninstall`)이 실행되면 함께 변경 


- package.json에 버전을 설정할 때 -> 특정 버전이 아니라 **버전 범위를 설정하면** -> 패키지를 설치하는 시점에 다른 버전이 설치 됨!
- 이런 문제를 해결하기 위해 **고정된 버전 사용하는 것이 좋음!**
- `package-lock.json`은 **패키지의 버전을 고정**시키는 파일
- `npm ci`를 활용하여 `package-lock.json`에 있는 버전을 기반으로 패키지를 설치 

## npm의 대안 yarn 

##### npm의 문제 
- 용량 문제, 패키지 내려받기 속도 문제, 보안 문제 

- yarn은 페이스북에서 만든 패키지 관리 프로그램 
- yarn은 버전 1과 yarn berry라고 부르는 버전 2가 있음 
  - 버전 1 : npm과 거의 유사, 패키지 설치가 조금 더 빠른 정도 
  - 버전 2: PnP(plug n play)전략 사용 , node_modules 사용 X , 의존성 패키지 관리 
  
> **PNP 전략**
> - 패키지를 적절한 위치에 꽂으면(plug) 바로 실행(play)하게 단순화
> - 이를 위해 node_modules 디렉터리 사용 X 
> - 의존성 찾기는 .pnp.cjs 파일 사용
> - 실제 의존성 패키지 파일은 압축 파일 형태로 `.yarn`디렉터리에 저장
> - `cache` 디렉터리 안에 있는 패키지들은 트리 구조 X / 모두 끌어올림(hoisting)되어 평평하게 저장
> - 추후에 서버 배포 시 패키지 설치 안해도 됨 
>   - 이것을 제로 인스톨 이라고 함
> - 제로 인스톨하면, 서버에 소스 코드를 배포할 때 패키지도 같이 설치
> 
> - 서버의 가동속도도 빨라짐
> - 의존성 패키지 위치를 찾는 시간만큼 빨리 가동됨

- yarn berry를 설정
```shell
corepack enable #corepack 활성화.  
yarn init -2 # yarn 초기화
```

> **코어팩**
> - Node.js 에 npm 이외의 패키지 매니저를 사용하는 기능 
> - yarn, pnpm을 지원 

