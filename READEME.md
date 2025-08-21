# 🏙️ CityFood - 우리 동네 맛집 지도

CityFood는 Spring Boot와 JPA를 기반으로 개발된 웹 애플리케이션으로, 사용자가 지역별 맛집을 등록하고 다양한 조건으로 검색할 수 있는 기능을 제공합니다.

## ✨ 주요 기능

- **맛집 CRUD**: 맛집 정보를 등록, 수정, 삭제할 수 있습니다.
- **페이징을 지원하는 맛집 목록 조회**: 전체 맛집 목록을 페이지 단위로 나누어 효율적으로 조회합니다.
- **다양한 조건의 맛집 검색**: 지역, 맛집 이름(키워드), 카테고리 등 여러 조건으로 맛집을 필터링하고 검색할 수 있습니다.
- **CQRS (Command Query Responsibility Segregation)**: CUD(생성, 수정, 삭제)를 담당하는 `Command`와 R(조회)을 담당하는 `Query`의 역할을 컨트롤러와 서비스 레벨에서 분리하여 시스템의 복잡성을 낮추고 유지보수성을 높였습니다.
- **성능 최적화**: `@EntityGraph`를 활용하여 연관된 엔티티를 함께 조회함으로써 N+1 쿼리 문제를 해결하고 데이터베이스 조회 성능을 최적화했습니다.

## 🛠️ 기술 스택

### Backend
- Java 21
- Spring Boot 3.5.4
- Spring Data JPA / Hibernate
- MariaDB

### Frontend
- HTML5
- CSS3
- JavaScript (ES6+)
- Thymeleaf

### Build Tool
- Gradle

## 🚀 시작하기

프로젝트를 로컬 환경에서 실행하는 방법입니다.

### 1. 사전 준비

- Java 21 (JDK)
- MariaDB

### 2. 프로젝트 클론

```bash
git clone https://github.com/ohhyeseong/City-Food.git
cd City-Food
```

### 3. 데이터베이스 설정

MariaDB에 접속하여 아래 쿼리를 실행해 주세요.

```sql
-- 1. 'foodmap' 데이터베이스 생성
CREATE DATABASE foodmap CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 2. 'appuser' 사용자 생성 및 권한 부여
CREATE USER 'appuser'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON foodmap.* TO 'appuser'@'localhost';
FLUSH PRIVILEGES;
```
> `application.yml` 파일에 위 설정과 동일한 정보가 이미 입력되어 있습니다.

### 4. 애플리케이션 실행

아래 Gradle 명령어를 사용하거나, 선호하는 IDE(IntelliJ, VSCode 등)에서 직접 실행할 수 있습니다.

```bash
./gradlew bootRun
```

### 5. 접속

애플리케이션 실행 후, 웹 브라우저에서 `http://localhost:8082` 주소로 접속하세요.

## 📝 API 명세

| Method | URL | 설명 |
| :--- | :--- | :--- |
| `GET` | `/api/places` | 맛집 목록 조회 (검색 및 페이징 지원) |
| `GET` | `/api/places/{id}` | 특정 맛집 상세 정보 조회 |
| `POST` | `/api/places` | 새로운 맛집 등록 |
| `PATCH`| `/api/places/{id}` | 특정 맛집 정보 수정 |
| `DELETE`| `/api/places/{id}` | 특정 맛집 삭제 |

## 🔮 추후 개선 사항

- [ ] 사용자 인증/인가 기능 추가 (Spring Security)
- [ ] 맛집 상세 정보 페이지 구현
- [ ] 프론트엔드 UI/UX 개선 (CSS 프레임워크 도입 등)
- [ ] 단위 테스트 및 통합 테스트 코드 작성 (Kotest, MockK 등)
