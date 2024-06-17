-- 쇼핑몰 프로젝트 계정생성하기
-- 관리자만 생성가능, 명령어는 대문자 소문자 구분하지 않는다.
CREATE USER sproject IDENTIFIED BY 1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP;
    
-- 권한부여. connect : 연결(접속), resource :자원생성, dba : 데이터베이스 관리권한
--sqldb에게 권한1, 권한2, 권한 3을 부여하겠다.  
 GRANT connect, resource, dba TO sproject; -- 보통 보안때문에 dba 권한은 부여하지 않는다. 현장에선 앞에 2가지 정도만 준다. 
 