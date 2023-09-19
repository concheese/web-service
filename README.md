# Concheese Backend: 공연 정보 및 커뮤니티 API 서버

## 프로젝트 배경

수많은 공연들의 정보를 확인하고 찾는 작업은 번거롭고 시간이 많이 소요됩니다. 특히 다양한 공연에 관심이 있는 경우, 티켓팅 일정을 파악하고 정보를 정리하는 것은 더욱 어려운 일입니다.

또한, 공연과 관련된 정보를 다른 이들과 소통하고 공유할 수 있는 커뮤니티가 필요한데, 현재 이를 위한 적절한 플랫폼이 부족합니다.

이러한 문제들을 해결하기 위해 'concheese' 프로젝트를 시작하게 되었습니다.


## 프로젝트 개요

'concheese'는 다양한 공연 일정을 쉽게 관리하고, 사용자들끼리 소통 가능한 커뮤니티를 제공하는 웹서비스입니다.

### 주요 기능

- 다양한 공연과 티켓팅 일정을 캘린더에 정리합니다.
- 원하는 공연만 선택해서 일정을 캘린더에 관리하고, 확인할 수 있습니다.
- 사용자들 간에 공연과 관련된 정보를 공유하고 소통할 수 있는 커뮤니티를 제공합니다.

### 사용자 대상

- 공연을 좋아하고 다양한 공연 정보에 관심 있는 사용자들을 대상으로 합니다.
- 티켓팅에 참여하고자 하는 사용자들과 공연 관련 소통을 하고 싶은 사용자들에게 특히 유용합니다.

### 요구사항

### 사용 기술

- 프론트엔드: React.js
- 백엔드: SpringBoot 3.1.2
- 데이터베이스: MariaDB

### 환경 변수

- 데이터베이스 설정
    - [`.env.properties`](src/main/resources/.env.properties) 파일을 `env.properties`로 복사한 한 뒤, 다음과 같이
      파일을 수정합니다.
      ```properties
      DB_DATABASE = [MariaDB 서버 IP]:[포트]/[데이터베이스]
      DB_USER     = [MariaDB 사용자]
      DB_PASSWORD = [MariaDB 비밀번호]
      ```
      
## 설치 방법


## 사용 방법

### 커뮤니티 내 정보 글 작성



### 커뮤니티 내 자유 글 작성



### 캘린더 일정 선택



## 라이선스

이 프로젝트는 GNU Lesser General Public License v2.1 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일에서 확인하실 수 있습니다.

## 기여자

김태진|김하영|김연송| 노동영| 백유미| 지명하| 최린 
---|---|---|---|---|---|---|
[Front-End](https://github.com/2023SWA1/concheese-web)|[Front-End](https://github.com/2023SWA1/concheese-web)|[Back-End](https://github.com/2023SWA1/concheese-backend)|[Back-End](https://github.com/2023SWA1/concheese-backend) | [Back-End](https://github.com/2023SWA1/concheese-backend) | [Back-End](https://github.com/2023SWA1/concheese-backend) | [Back-End](https://github.com/2023SWA1/concheese-backend) |
[@kimtaejin3](https://github.com/kimtaejin3)|[@hayoung1242433](https://github.com/hayoung1242433)|[@mandarinnn2](https://github.com/mandarinnn2)| [@yrrho2](https://github.com/yrrho2)| [@baekyumi](https://github.com/baekyumi)|[@NULL0xFF](https://github.com/NULL0xFF)| [@lynnchoi0126](https://github.com/lynnchoi0126)|
