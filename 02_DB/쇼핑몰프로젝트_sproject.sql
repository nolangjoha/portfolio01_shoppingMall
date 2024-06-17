--1.회원가입 테이블
CREATE TABLE MBSP_TBL(
        MBSP_ID             VARCHAR2(15)            NOT NULL,   -- 아이디
        MBSP_NAME           VARCHAR2(30)            NOT NULL,   -- 이름
        MBSP_EMAIL          VARCHAR2(50)            NOT NULL,   -- 이메일
        MBSP_PASSWORD       CHAR(60)               NOT NULL,    -- 비밀번호(암호화)
        MBSP_ZIPCODE        CHAR(5)                 NOT NULL,   -- 우편번호
        MBSP_ADDR           VARCHAR2(100)            NOT NULL,  -- 주소
        MBSP_DEADDR         VARCHAR2(100)            NOT NULL,  -- 상세주소
        MBSP_PHONE          VARCHAR2(15)            NOT NULL,   -- 연락처
        MBSP_NICK           VARCHAR2(30)            NOT NULL UNIQUE,  --닉네임(중복x)
        MBSP_RECEIVE        CHAR(1)                 NOT NULL,       -- 마케팅동의 수신
        MBSP_POINT          NUMBER DEFAULT 0        NOT NULL,   -- 포인트:사용자가 입력하는 정보가 아니므로 디폴트값 설정
        MBSP_LASTLOGIN      DATE                    NULL,       -- 최근 접속일, 처음엔 로그인을 안했으니 null일 수 밖에 없다. 
        MBSP_DATESUB        DATE DEFAULT SYSDATE    NOT NULL,   -- 최초등록일
        MBSP_UPDATEDATE     DATE DEFAULT SYSDATE    NOT NULL    -- 회원정보 갱신일
);

--소소관리 차원에서 pk생성구문 따로 작성(테이블 생성 후 바로 실행)
ALTER TABLE MBSP_TBL
ADD CONSTRAINT PK_MBSP_ID PRIMARY KEY (MBSP_ID);

-- 회원가입 테이블 삭제
--DROP TABLE MBSP_TBL;

COMMIT;
