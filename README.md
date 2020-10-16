# My_Library
개인 서적 관리 프로그램

여기에 배지 넣으면 좋을듯   
만든 이유도 넣어야 하지 않을까 

<br>

## 목차
---
1. [개발 환경](#개발-환경) 
2. [디렉토리 구조](#디렉토리-구조])
3. [URI Table](#URI-Table)
4. [ERD](#ERD)
5. [UI](#UI)
6. [My Library](#My-Library)

<br>

## 개발 환경
---
1. IDE : Eclipse
```
version : 2020-06 (4.16.0)
```
2. JDK
```
jdk-1.8.0_231
```
3. WAS
```
Tomcat 9.0.37 
```
3. dependencies
```java
// spring framework
springframework-version : 5.0.7

// spring-security
spring-security-web : 5.0.7
spring-security-config : 5.0.7
spring-security-core : 5.0.7
spring-security-taglibs : 5.0.7

// servlet
javax.servlet : 3.1.0

// junit
junit : 4.12

// database
HikariCP : 2.7.8
mybatis : 3.4.6
mybatis-spring : 1.3.2

// jackson
jackson-annotation : 2.9.2
jackson-core : 2.9.6
jackson-databind : 2.9.6

// lombok
lombok : 1.18.0
```
<br>

## 디렉토리 구조
---

<img src="image/tree_structure.png" height="750">

<br>
<br>

## URI Table
---
<br>

`BookController`

|Task|URL|Method|Parameter|Form|URL 이동|
|-----|------|------|-------|-----|------|
|내 서재 전체 목록|/book/|GET|-|-|-|
|검색화면 이동|/book/search|GET|-|-|-|
|내 서제 검색|/book/result|GET|-|-|-|
|책 상세조회|/book/one|GET|bno|-|-|
|책 삭제|/book/remove|POST|bno|-|이동|

<br>    

`ApiController`

|Task|URL|Http 전송방식|비고|
|-----|------|------|-------|-----|------|
|책 등록|/api/addbook|POST|-|-|-|
|책 조회|/api/:isbn|GET|-|-|-|

<br>

`ReviewController`

|Task|URL|Http 전송방식|비고|
|-----|------|------|-------|-----|------|
|서평 조회|/review/new|POST|-|-|-|
|서평 등록|/review/:bno|GET|-|-|-|
|서평 수정|/review/:bno|PUT|-|-|-|
|서평 삭제|/review/:bno|DELETE|-|-|-|

<br>

`SentenceController`

|Task|URL|Http 전송방식|비고|
|-----|------|------|-------|-----|------|
|문장수집 조회|/sentence/new|POST|-|-|-|
|문장수집 등록|/sentence/:bno|GET|-|-|-|
|페이지|/sentence/:bno/:page|GET|개발중|-|-|
|문장수집 수정|/sentence/:bno|PUT|-|-|-|
|문장수집 삭제|/sentence/:bno|DELETE|-|-|-|

<br>

`CommonController`

|Task|URL|Http 전송방식|비고|
|-----|------|------|-------|-----|------|
|로그인|/customLogin|GET|-|-|-|
|로그아웃|/customLogout|GET|-|-|-|
|Access Denied|/accessError|GET|-|-|-|


<br>

## ERD
---



<br>

## My Library
---

[사이트 바로가기](http://13.125.186.107:8080/book/)