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



--2. 관리자(Admin) 테이블
CREATE TABLE ADMIN_TBL (
    ADMIN_ID    VARCHAR2(15)    PRIMARY KEY,
    ADMIN_PW    CHAR(60)    NOT NULL,
    ADMIN_VISIT_DATE    DATE
);
-- admin_tbl, admin_id, admin_pw, admin_visit_date

--관리자 계정 데이터 삽입
INSERT INTO ADMIN_TBL(ADMIN_ID, ADMIN_PW)VALUES('admin', '$2a$10$M2k.eIgxdKXrkuqzlq441ukiEeLH19YwjsgDx6NUADL/NyI3HoYCW');

COMMIT;


--3. 상품정보 테이블
CREATE TABLE product_tbl(
    pro_num         NUMBER,                             --상품번호
    cat_code        NUMBER               NULL,          --카테고리 코드
    pro_name        VARCHAR(100)         NOT NULL,      --상품명
    pro_price       NUMBER               NOT NULL,      --상품 가격
    pro_discount    NUMBER               NOT NULL,      --할인율
    pro_publisher   VARCHAR(100)         NOT NULL,      --제조사
    pro_content     VARCHAR(4000)        NOT NULL,      --상품설명
    pro_up_folder   VARCHAR(50)          NOT NULL,      --이미지업로드폴더 경로
    pro_img         VARCHAR(100)         NOT NULL,      --이미지 파일명
    pro_amount      NUMBER               NOT NULL,      --상품수량
    pro_buy         CHAR(1)              NOT NULL,      --판매여부 'Y','N'
    pro_date        DATE DEFAULT SYSDATE NOT NULL,      --상품등록일
    pro_updatedate  DATE DEFAULT SYSDATE NOT NULL       --상품수정일
);
--product_tbl
/*
pro_num, cat_code, pro_name, pro_price, pro_discount, pro_publisher, pro_content, pro_up_folder, pro_img, 
pro_amount, pro_buy, pro_date, pro_updatedate
*/

--시퀀스 생성
CREATE SEQUENCE seq_pro_num;

--PRIMARY KEY 생성
ALTER TABLE product_tbl
ADD CONSTRAINT PK_PRO_NUM PRIMARY KEY (PRO_NUM);

COMMIT;

--4. 카테고리 테이블
CREATE TABLE category_tbl(
    CAT_CODE        NUMBER,                     -- 카테고리 코드 (모든 레벨의 카테고리 저장)
    CAT_PRTCODE     NUMBER  NULL,               -- 상위(부모)카테고리
    CAT_NAME        VARCHAR(50)     NOT NULL    -- 카테고리 이름
);
-- category_tbl // cat_code, cat_prtcode, cat_name

ALTER TABLE category_tbl
ADD CONSTRAINT PK_CAT_CODE PRIMARY KEY (CAT_CODE);

--1차 카테고리
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (1, NULL, '신상품');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (2, NULL, '상의');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (3, NULL, '하의');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (4, NULL, '악세사리');

--1차 카테고리 : 2(상의)
--2차 카테고리(상의) : 아우터, 블라우스/셔츠, 티셔츠/후드
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (5, 2, '아우터');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (6, 2, '블라우스&#38;셔츠');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (7, 2, '티셔츠&#38;후드');  

--1차 카테고리 : 3(하의)
--2차 카테고리(하의) : 청바지, 슬랙스, 반바지, 츄리닝
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (8, 3, '청바지');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (9, 3, '슬랙스');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (10, 3, '반바지');  
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (11, 3, '츄리닝');  

--1차 카테고리 : 4(악세사리)
--2차 카테고리(악세사리) : 가방, 양말/스타킹, 주얼리
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (12, 4, '가방');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (13, 4, '양말&#38;스타킹');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (14, 4, '주얼리');  
  
COMMIT;



