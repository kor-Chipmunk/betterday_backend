## Betterday Backend

어제 작성한 일기로 나만의 특별한 알람을 AI 기술로 만들어줍니다.  
본 레포지토리는 모든 필링 서버 소스 코드를 관리합니다.

### Tech stacks

* Language : Java 17
* Server Framework : Spring Boot 3.3.3
* Database : PostgreSQL (for live), h2 (for local)
* ORM : Spring Data JPA
* Security : Spring Security / OAuth2
* Event : Spring Application Event / AWS SNS / AWS SQS

### Architecture

TBA

### Modules

* `core` : 코어 모듈 (응답, 예외 코드)
* `common` : 공통 유틸 모듈 (Jackson 등)
* `domain` : 도메인 모듈
* `api`
    * `external-api` : 외부 클라이언트가 요청할 REST API 모듈
* `usecase`
    * `core` : 외부 모듈(주로 어댑터)가 구현할 포트 모음
    * `alarm-usecase` : 알람 유즈케이스 모듈
    * `auth-usecase` : 인증 유즈케이스 모듈
    * `diary-usecase` : 일기 유즈케이스 모듈
    * `oauth2-usecase` : OAuth2 유즈케이스 모듈
    * `user-usecase` : 유저 유즈케이스 모듈
    * `weekly-report-usecase` : 주간 리포트 유즈케이스 모듈
* `adapter`
    * `core` : 외부 모듈이 구현할 인터페이스 모음
    * `aws-sns` : AWS SNS 이벤트 발행 모듈
    * `aws-sqs` : AWS SQS 이벤트 수신 모듈
    * `oauth2` : OAuth2 통신 모듈
    * `postgres` : PostgreSQL DB 통신 모듈
    * `replicate` : Replicate API 통신 모듈
    * `spring-event` : Spring Application 이벤트 발행 모듈
* `security`
    * `aes-security` : AES 암호화 모듈
    * `jasypt-security` : Jasypt 암호화 모듈
    * `jwt-security` : JWT 토큰 모듈
    * `spring-security` : 스프링 시큐리티 모듈

### How to run

```bash
# Build
$ ./gradlew clean build bootJar -Djasypt.encryptor.password=${JASYPT_PASSWORD}

# Local
$ java -Xms512m -Xmx1024m -Dspring.profiles.active=local -Djasypt.encryptor.password=${JASYPT_PASSWORD} -Duser.timezone=UTC -jar ./api/external-api/build/libs/external-api.jar

# Production
$ java -Xms512m -Xmx1024m -Dspring.profiles.active=prod -Djasypt.encryptor.password=${JASYPT_PASSWORD} -Duser.timezone=UTC -jar ./api/external-api/build/libs/external-api.jar
```
