### [Ewha-thon]

# **🌸 BaeullimFlower : 배울림꽃  🌸**

## ***🍯 MeltingPot Team - BackEnd 🍯***   

📜 API 명세서
--------------------------
https://www.notion.so/API-9042d97ee2104605b651d191461471cc   
![5E9ED529-5995-409C-A5BD-A4751614BD2E](https://github.com/Ewha-thon-Melting-Pot/EwhaSinmungo-Back/assets/112189780/b0190002-4898-4c32-8915-f02fb7351716)

☁ ERD
--------------------------
![21E168FA-BE38-42A0-B38D-BB1C28A5E048](https://github.com/Ewha-thon-Melting-Pot/EwhaSinmungo-Back/assets/112189780/caa67203-5c3d-4999-9158-45c72bad22e1)


📍 Commit Convention
--------------------------
### TAG : 메시지
|태그 이름|설명|
|---|---|
|feat|새로운 기능 추가|
|fix|버그, 오류 수정|
|sytle|코드 포맷팅, 오타 수정, 주석 수정 및 삭제 등|
|docs|문서 수정|
|chore|빌드 및 패키지 수정 및 삭제|
|refactor|코드 리팩토링|

⚙ 기술 아키텍처
--------------------------
### 사용 스택
|통합 개발 환경|IntelliJ|
|---|---|
|Spring 버전|2.7.11|
|데이터베이스|MySQL|
|배포|AWS EC2(Ubuntu),S3, CodeDeploy|
|Project 빌드 관리 도구|Gradle|
|CI/CD 툴|Github Actions|
|ERD 다이어그램 툴|ERD Cloud|
|Java version|Java 17|

## 아키텍처 구조
![07BE84BE-41B9-4B9A-BD9C-0498545CF5B3](https://github.com/Ewha-thon-Melting-Pot/EwhaSinmungo-Back/assets/112189780/3fe7c5f2-c418-4730-9751-de1558168ff8)



📁 프로젝트 폴더 구조
--------------------------
```
├── main
│   ├── java
│   │   └── melting_pot
│   │       └── ewha_sinmungo
│   │           ├── EwhaSinmungoApplication.java
│   │           ├── global
│   │           │   ├── BaseTimeEntity.java
│   │           │   ├── apiResponse
│   │           │   │   ├── ApiResponse.java
│   │           │   │   ├── Message.java
│   │           │   │   ├── code
│   │           │   │   │   ├── BaseCode.java
│   │           │   │   │   ├── BaseErrorCode.java
│   │           │   │   │   ├── ErrorReasonDTO.java
│   │           │   │   │   ├── ReasonDTO.java
│   │           │   │   │   └── status
│   │           │   │   │       ├── ErrorStatus.java
│   │           │   │   │       └── SuccessStatus.java
│   │           │   │   └── exception
│   │           │   │       ├── GeneralException.java
│   │           │   │       └── handler
│   │           │   │           └── ErrorHandler.java
│   │           │   ├── config
│   │           │   │   ├── CorsConfig.java
│   │           │   │   ├── SecurityConfig.java
│   │           │   │   └── SecurityUtil.java
│   │           │   └── jwt
│   │           │       ├── JwtAuthenticationFilter.java
│   │           │       ├── JwtToken.java
│   │           │       └── JwtTokenProvider.java
│   │           ├── member
│   │           │   ├── controller
│   │           │   │   └── MemberController.java
│   │           │   ├── dto
│   │           │   │   ├── LoginRequestDto.java
│   │           │   │   ├── LoginResponseDto.java
│   │           │   │   ├── SignUpResponseDto.java
│   │           │   │   └── SignupRequestDto.java
│   │           │   ├── entity
│   │           │   │   └── Member.java
│   │           │   ├── repository
│   │           │   │   └── MemberRepository.java
│   │           │   └── service
│   │           │       ├── CustomUserDetailsService.java
│   │           │       └── MemberService.java
│   │           ├── notice
│   │           │   └── entity
│   │           │       └── Notice.java
│   │           ├── post
│   │           │   ├── controller
│   │           │   │   └── PostController.java
│   │           │   ├── converter
│   │           │   │   └── PostConverter.java
│   │           │   ├── dto
│   │           │   │   ├── requestDto
│   │           │   │   │   └── PostRequestDTO.java
│   │           │   │   └── responseDto
│   │           │   │       └── PostResponseDTO.java
│   │           │   ├── entity
│   │           │   │   ├── Category.java
│   │           │   │   ├── Post.java
│   │           │   │   ├── PostUrl.java
│   │           │   │   └── Status.java
│   │           │   ├── repository
│   │           │   │   └── PostRepository.java
│   │           │   └── service
│   │           │       └── PostService.java
│   │           ├── result
│   │           │   └── entity
│   │           │       └── Result.java
│   │           └── vote
│   │               ├── controller
│   │               │   └── VoteController
│   │               ├── entity
│   │               │   └── Vote.java
│   │               ├── reporitory
│   │               │   └── VoteRepository.java
│   │               └── service
│   │                   └── VoteService.java
│   └── resources
│       └── application.yml
└── test
    └── java
        └── melting_pot
            └── ewha_sinmungo
                └── EwhaSinmungoApplicationTests.java
```
