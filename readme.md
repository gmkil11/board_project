# 게시판 프로젝트

## 프로젝트 소개

이 게시판 프로젝트는 Java와 Spring Framework를 사용하여 개발된 간단한 웹 어플리케이션입니다. 사용자는 게시글을 작성하고, 수정하며, 삭제할 수 있습니다. 또한, 회원 간에 작성한 게시글의 통계를 자동으로 생성하여 매일 새벽 1시에 업데이트합니다.

## 주요 기능

1. **게시판 기능**
    - 게시글 작성, 수정, 삭제
    - 게시글 목록 조회
    - 게시글 상세 조회

2. **통계 기능**
    - 사용자별로 작성한 게시글 수 통계
    - 매일 새벽 1시에 통계 업데이트

## 사용 기술 스택

- Java
- Spring Framework
- Thymeleaf
- Hibernate (JPA)
- HTML, CSS

## 프로젝트 구조

```plaintext
|-- src
|   |-- main
|       |-- java
|           |-- org.koreait
|               |-- controllers
|               |-- entities
|               |-- models.board
|               |-- repositories
|               |-- ...
|       |-- resources
|           |-- static
|           |-- templates
|-- pom.xml
|-- application.properties
|-- README.md
