# 콘치즈 프로젝트 코드 구조 안내서

Spring Boot 프로젝트에서 Domain-Driven Design (DDD) 패턴을 따르는 경우, 코드를 효과적으로 구성하는 것은 유지 관리 및 확장성을 위해 중요합니다. 이 안내서는 해당 프로젝트에 대한 권장
코드 구조를 개요와 함께 제공하며, DDD 특정 도메인 구성, 테스팅, 리소스 관리를 강조합니다. 이러한 구조는 코드의 가독성, 유지 관리성 및 관심사의 분리를 향상시키는 데 목적이 있습니다.

## 프로젝트 구조 개요

프로젝트 구조는 DDD 패턴을 수용하도록 설계되었으며, 다양한 도메인과 해당 구성 요소를 분리합니다. 또한 테스트 파일, 애플리케이션 구성, 데이터베이스 스키마 및 환경 속성의 명확한 분할을 포함합니다.

```plaintext
src/
├── main/
│   ├── java/
│   │   └── net/
│   │       └── concheese/
│   │           └── server/
│   │               ├── domain1/
│   │               │   ├── controller/
│   │               │   ├── model/
│   │               │   ├── repository/
│   │               │   ├── service/
│   │               │   └── ...
│   │               ├── domain2/
│   │               │   ├── controller/
│   │               │   ├── model/
│   │               │   ├── repository/
│   │               │   ├── service/
│   │               │   └── ...
│   │               └── ...
│   ├── resources/
│   │   └── application.yaml
    │   └── ...
└── test/
    ├── java/
    │   └── net/
    │       └── concheese/
    │           └── server/
    │               ├── domain1/
    │               │   ├── ...
    │               ├── domain2/
    │               │   ├── ...
    │               └── ...
    ├── resources/
    │   └── ...
```

## 도메인 구조

프로젝트 내 각 도메인은 명확한 관심사 분리를 보장하기 위해 일관된 구조를 따르도록 설계되어야 합니다.

```plaintext
domainX/
├── controller/
│   ├── Entity1Controller.java
│   ├── Entity2Controller.java
│   └── ...
├── model/
│   ├── Entity1.java
│   ├── Entity2.java
│   └── ...
├── repository/
│   ├── Entity1Repository.java
│   ├── Entity2Repository.java
│   └── ...
├── service/
│   ├── Entity1Service.java
│   ├── Entity2Service.java
│   └── ...
└── ...
```

* `controller/`: 사용자의 HTTP 요청을 처리하고, 서비스 레이어와 상호 작용하여 데이터를 반환하거나 적절한 응답을 전송하는 역할을 합니다.
* `model/`: 도메인 특정 엔티티 또는 값 객체를 포함합니다.
* `repository/`: 도메인에 대한 데이터 액세스 및 데이터베이스 상호 작용을 관리합니다.
* `service/`: 비즈니스 로직을 포함하며, 다양한 컴포넌트 간 상호 작용을 조율합니다.

## 테스팅

메인 소스 코드와 일관성을 유지하도록 테스트에도 유사한 구조를 따르십시오.

```plaintext
domainX/
├── ...
└── service/
    ├── Entity1ServiceTest.java
    ├── Entity2ServiceTest.java
    └── ...
```

## 리소스

데이터베이스 스키마, 환경 속성 및 애플리케이션 구성 파일과 같은 리소스는 다음과 같이 구성해야 합니다.

* `src/main/resources/application.yaml`: Spring Boot의 주요 애플리케이션 구성을 포함합니다.
* `src/main/resources/env.properties`: 환경별 속성을 저장합니다.

---

본 문서는 ChatGPT를 사용하여 작성되었습니다. 