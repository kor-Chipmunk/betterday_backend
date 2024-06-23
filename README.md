## FeelRing Backend

필링은 어제 작성한 일기로 나만의 특별한 알람을 AI 기술로 만들어줍니다.  
본 레포지토리는 모든 필링 서버 소스 코드를 관리합니다.

### Tech stacks

* Language : Java 17
* Server Framework : Spring Boot 3.2.5
* Database : PostgreSQL (for live), h2 (for local)
* ORM : Spring Data JPA
* Security : Spring Security / OAuth2

### Modules

* `core` : 코어 모듈 (응답, 예외 코드)
* `common` : 공통 유틸 모듈 (Jackson 등)
* `domain` : 도메인 모듈
* `api`
  * `external-api` : 외부 클라이언트가 요청할 API 모듈
* `usecase`
  * `core` : 외부 모듈(주로 어댑터)가 구현할 포트 모음
  * `auth-usecase` : 인증 유즈케이스 모듈
  * `diary-usecase` : 일기 유즈케이스 모듈
  * `fcm-usecase` : FCM 유즈케이스 모듈
  * `oauth2-usecase` : OAuth2 유즈케이스 모듈
* `adapter`
  * `core` : 외부 모듈이 구현할 인터페이스 모음
  * `fcm` : FCM 통신 모듈
  * `oauth2` : OAuth2 통신 모듈
  * `postgres` : PostgreSQL DB 통신 모듈
* `security`
  * `jwt-security` : JWT 토큰 모듈
  * `aes-security` : AES 암호화 모듈
  * `spring-security` : 스프링 시큐리티 모듈
