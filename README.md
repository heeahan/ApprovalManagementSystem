# 결재시스템 -> 품의서 #

<img src = "Project_Diagram.jpg">

***⭐readme 아직 정리 안된 상태입니다***

미션1에서 품의 요청 API 일부 기능 *[품의서 생성]* 구현을 했습니다.

## 1. Database 부분 ##

### 1.1 DB, 테이블 생성 ###
테이블 **3개** 생성
- DB 테이블 설계 기반으로
- 미션1에서 **결재정보, 결재선정보, 결재첨부파일정보** 3개의 테이블 생성 및 사용
- 각 column 속성 확인
  - null 여부
  - PRIMARY KEY
  - size 등 설계서 기반
```
CREATE SCHEMA ApprovalSystem;
USE ApprovalSystem;
CREATE TABLE APPR_INF(
APPR_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
TASK_DIV VARCHAR(5),
APPR_TYP VARCHAR(5),
TITL VARCHAR(200),
CNTNT_TYP VARCHAR(1),
CNTNT VARCHAR(3000),
APPR_LN_CHG_PSBL_YN VARCHAR(1),
CALLBACK_URL VARCHAR(3000),
FRST_REG_USER_ID VARCHAR(5),
FRST_REG_DTMT DATETIME,
LAST_CHG_USER_ID VARCHAR(5),
LAST_CHG_DTMT DATETIME);

CREATE TABLE APPR_LN_INF(
APPR_LN_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
APPR_LN_SRNO BIGINT NOT NULL,
APPR_DIV VARCHAR(1),
USER_ID VARCHAR(5),
APPR_PROC VARCHAR(1),
APPR_PROC_DTMT DATETIME,
CMNT VARCHAR(1000),
APPR_ID BIGINT,
APPR_LN_TMPT_ID BIGINT,
FRST_REG_USER_ID VARCHAR(5),
FRST_REG_DTMT DATETIME,
LAST_CHG_USER_ID VARCHAR(5),
LAST_CHG_DTMT DATETIME);

CREATE TABLE APPR_ATCHD_FILE_INF(
APPR_ATCHD_FILE_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
APPR_ATCHD_FILE_SRNO BIGINT NOT NULL,
FILE_ID BIGINT NOT NULL,
FILE_NM VARCHAR(200),
FILE_PATH VARCHAR(1000),
APPR_ID BIGINT,
FRST_REG_USER_ID VARCHAR(5),
FRST_REG_DTMT DATETIME,
LAST_CHG_USER_ID VARCHAR(5),
LAST_CHG_DTMT DATETIME);
```

### 1.2 Foreign Key ###
🗝️ 이번 프로잭트에서 ***사용 안했지만***, 지정 및 조회에 대해 정리해봅니다.

*`APPR_ID`를 foreign key로 지정:*
```
ALTER TABLE APPR_LN_INF
ADD FOREIGN KEY (APPR_ID) REFERENCES APPR_INF(APPR_ID);

ALTER TABLE APPR_ATCHD_FILE_INF
ADD FOREIGN KEY (APPR_ID) REFERENCES APPR_INF(APPR_ID);
```

*foreign key 조회 및 삭제:*

*조회*
```
SELECT
    CONSTRAINT_NAME,
    TABLE_NAME,
    COLUMN_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
    CONSTRAINT_SCHEMA = 'approvalsystem'
    AND REFERENCED_TABLE_NAME IS NOT NULL;
```

*삭제*
```
ALTER TABLE appr_atchd_file_inf
DROP FOREIGN KEY appr_atchd_file_inf_ibfk_1;

ALTER TABLE appr_ln_inf
DROP FOREIGN KEY appr_ln_inf_ibfk_1;
```

*테스트 시, 테이블 클리어*
```
TRUNCATE TABLE APPR_INF;
TRUNCATE TABLE APPR_LN_INF;
TRUNCATE TABLE APPR_ATCHD_FILE_INF;
```

## 2. API 부분 ##

### 2.0 개발 환경 ###

- 언어: JAVA 21
- 프레임워크: Spring Boot 3.3.1
- 프로젝트 빌드도구: Gradle - Groovy
- IDE: IntelliJ IDEA
- 데이터베이스: MySQL
- REST API Documentation 툴: Swagger UI
- OS: Windows

### 2.1 Domain ###

- 3 tables -> 3 domain files
- 각 테이블 column 기준으로 변수 선언
- 각 변수 속성 디테일 주의

### 2.2 DTO ###

데이터 전송: DTO는 데이터 전송 시 사용되며, 대부분 Domain 객체를 기준으로 구현됩니다.

❗ **여러 개**의 객체를 받을 경우: 다수의 데이터를 처리할 때는 List를 사용하여 관리합니다.

`ApprDto.java` 파일의 일부 참고:

```
    private List<ApprLnInfDto> apprLnInfDto;
    private List<ApprAtchdFileInfDto> apprAtchdFileInfDto;
```

### 2.3 Service ###

Interface  `ApprService`, implement `ApprServiceimpl` 2개의 파일로 관리

coming soon.. (틀 잡는중) 구현 내용 정리 예정


### 2.4 Repository ###

❗ ***nativeQuery의 사용***
```
@Query(value = "순수 쿼리문", nativeQuery = true)
String getNextUserId(Long apprId); // method
```

-> Serviceimpl에서 사용 가능 -> (본 프로젝트에서)  `String getNextUserId(Long apprId);`를 통해 String인 nextUserId 받고 log로 출력

### 2.5 Controller ###

주로 예외처리 빠짐없이 처리

## 3. 어려운 점 및 해결 과정 ##

1. SrNo (for loop)
2. Log
3. LocalDateTime
