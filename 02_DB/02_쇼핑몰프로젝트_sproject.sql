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

/*
mbsp_tbl
mbsp_id, mbsp_name, mbsp_email, mbsp_password, mbsp_zipcode, mbsp_addr, mbsp_deaddr, mbsp_phone,
mbsp_nick, mbsp_receive, mbsp_point, mbsp_lastlogin, mbsp_datesub, mbsp_updatedate
pk_mbsp_id
*/


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

--테스트용 데이터
INSERT INTO product_tbl (pro_num, cat_code, pro_name, pro_price, pro_discount, pro_publisher, pro_content, pro_up_folder, pro_img, 
pro_amount, pro_buy, pro_date, pro_updatedate) 
    VALUES (seq_pro_num.NEXTVAL,6,'베이지 숄', 6000, 20, '초록초록', '<img alt="" src="/admin/product/display/beige_shawl.jpg" style="height:4622px; width:3220px" /><br />
산뜻한 베이지숄','2024\07\15','842f4ce8-7766-4484-96c5-17f72ad0e027-beige_shawl.jpg', 1,'Y', '24/07/15', '24/07/15');


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


--5. 장바구니 테이블
CREATE TABLE CART_TBL (
    CART_CODE   NUMBER,
    PRO_NUM     NUMBER  NOT NULL,
    MBSP_ID     VARCHAR(15) NOT NULL,
    CART_AMOUNT NUMBER  NOT NULL,
    CART_DATE   DATE DEFAULT SYSDATE
);
/*
cart_tbl
cart_code, pro_num, mbsp_id, cart_amount, cart_date
seq_cart_code
pk_cart_code
*/

--시퀀스 생성
CREATE SEQUENCE SEQ_CART_CODE;

-- PRIMARY KEY 생성
ALTER TABLE CART_TBL
ADD CONSTRAINT PK_CART_CODE PRIMARY KEY(CART_CODE);


COMMIT;
    


--6. 상품후기 테이블
DROP TABLE review_tbl;
CREATE TABLE review_tbl (
    re_code     NUMBER         NOT NULL,      -- 리뷰번호
    mbsp_id     VARCHAR(50)    NOT NULL,      -- 리뷰작성자 아이디
    pro_num     NUMBER         NOT NULL,      -- 상품번호
    re_title    VARCHAR(50)    NOT NULL,      -- 리뷰제목
    re_content  VARCHAR(50)    NOT NULL,      -- 리뷰내용
    re_rate     NUMBER         NOT NULL,      -- 리뷰점수(별점)
    re_date     DATE DEFAULT SYSDATE          -- 리뷰작성 날짜
);
-- review_tbl(re_code, mbsp_id, pro_num, re_title, re_content, re_rate, re_date, re_admin_reply_content, re_admin_reply_date, re_admin_reply_status)
-- SEQ_REVIEW_CODE
-- PK_RE_CODE

-- SEQUENCE 생성
DROP SEQUENCE SEQ_REVIEW_CODE;
CREATE SEQUENCE SEQ_REVIEW_CODE;

-- PRIMARY KEY 생성
ALTER TABLE review_tbl
ADD CONSTRAINT PK_RE_CODE PRIMARY KEY (re_code);

-- 컬럼 수정
ALTER TABLE review_tbl MODIFY re_content VARCHAR2(2000);
ALTER TABLE review_tbl ADD re_admin_reply_content VARCHAR2(1000);
ALTER TABLE review_tbl ADD re_admin_reply_date DATE;
ALTER TABLE review_tbl ADD re_admin_reply_status CHAR(1) DEFAULT 'N';


DESC review_tbl;


--리뷰 데이터 임시 삽입
INSERT INTO 
    review_tbl (
        re_code, 
        mbsp_id, 
        pro_num, 
        re_title, 
        re_content, 
        re_rate
        )
VALUES (
    SEQ_REVIEW_CODE.NEXTVAL, 
    'user11', 
    59, 
    '후기제목', 
    '후기내용', 
    3
    );    

COMMIT;


-- 7. 주문테이블
DROP TABLE ORDER_TBL;
CREATE TABLE ORDER_TBL(
    ord_code        NUMBER,                             --주문코드
    mbsp_id         VARCHAR2(50)    NOT NULL,           -- 주문자 아이디
    ord_name        VARCHAR2(30)    NOT NULL,           -- 주문자 이름
    ord_tel         VARCHAR2(20)    NOT NULL,           -- 주문자 연락처
    ord_regdate     DATE DEFAULT SYSDATE    NOT NULL,   -- 주문날짜
    ord_addr_zipcode  CHAR(5)         NOT NULL,         -- 배송지 우편번호
    ord_addr_basic    VARCHAR2(50)    NOT NULL,         -- 배송지 기본주소
    ord_addr_detail   VARCHAR2(50)    NOT NULL,         -- 배송지 상세주소
    ord_price       NUMBER          NOT NULL,           -- 주문금액
    ord_desc        VARCHAR2(500),                      -- 주문시 요청사항
    ord_admin_meno    VARCHAR2(500)                     -- 관리자 메모
);
/*
order_tbl, seq_ord_code, pk_ord_code
ord_code, mbsp_id, ord_name, ord_tel, ord_regdate, ord_addr_zipcode, ord_addr_basic, ord_addr_detail, 
ord_price, ord_desc, ord_admin_meno
*/

--SEQUENCE 생성
drop SEQUENCE SEQ_ORD_CODE;
CREATE SEQUENCE SEQ_ORD_CODE;

--PRIMARY KEY 생성
ALTER TABLE order_tbl
ADD CONSTRAINT PK_ORD_CODE PRIMARY KEY (ord_code);

COMMIT;

-- 컬럼 길이 변경
ALTER TABLE ORDER_TBL MODIFY ord_addr_basic VARCHAR2(100);
ALTER TABLE ORDER_TBL RENAME COLUMN ord_admin_meno TO ord_admin_memo;

-- 8. 주문상세테이블(상품정보)
DROP TABLE ORDETAIL_TBL;
CREATE TABLE ORDETAIL_TBL(
    ord_code    NUMBER,                 -- 주문번호
    pro_num     NUMBER,                 -- 상품번호
    dt_amount   NUMBER     NOT NULL,    -- 주문수량
    dt_price    NUMBER     NOT NULL     -- 주문가격
);
/*
ordetail_tbl, pk_ordetail_code
ord_code, pro_num, dt_amount, dt_price
*/


--PRIMARY KEY 생성
ALTER TABLE ordetail_tbl
ADD CONSTRAINT PK_ORDETAIL_CODE PRIMARY KEY (ord_code, pro_num);

COMMIT;


-- 9. 결제테이블
DROP TABLE payinfo;
CREATE TABLE payinfo(
    p_id        NUMBER,                     -- 결제번호
    ord_code    NUMBER          NOT NULL,   -- 주문번호
    mbsp_id     VARCHAR2(50)    NOT NULL,   -- 주문자 아이디
    paymethod   VARCHAR2(50)    NOT NULL,   -- 결제방법
    payinfo     VARCHAR2(100)   NOT NULL,   -- 결제정보
    p_price     NUMBER          NOT NULL,   --총금액
    p_status    VARCHAR2(10)    NOT NULL,   --완납/미납
    p_date      DATE DEFAULT    SYSDATE     --결제일
);

/*
payinfo, seq_payinfo_id, pk_payinfo_id
p_id, ord_code, mbsp_id, paymethod, payinfo, p_price, p_status, p_date
*/

-- SEQUENCE 생성
DROP SEQUENCE SEQ_PAYINFO_ID;
CREATE SEQUENCE SEQ_PAYINFO_ID;

-- PRIMARY KEY 생성
ALTER TABLE payinfo
ADD CONSTRAINT PK_PAYINFO_ID PRIMARY KEY (p_id);

COMMIT;

--관리자 주문관리
-- 주문정보
SELECT ot.ord_code, ot.pro_num, ot.dt_amount, ot.dt_price, p.pro_name, p.pro_up_folder, p.pro_img
FROM ordetail_tbl ot INNER JOIN product_tbl p
ON ot.pro_num = p.pro_num
WHERE  ot.ord_code = 40;


--10. 메일발송 테이블
DROP TABLE mailing_tbl;
CREATE TABLE mailing_tbl(
    mail_idx        NUMBER,                     --메일번호
    mail_title      VARCHAR2(100),              --메일제목
    mail_content    VARCHAR2(4000) NOT NULL,    --메일내용
    mail_kind       VARCHAR2(20) NOT NULL,      --메일종류
    mail_send_count NUMBER DEFAULT 0,           --메일발송횟수
    reg_date        DATE DEFAULT SYSDATE        --메일발송날짜
);
/*
mailing_tbl
mail_idx, mail_title, mail_content, mail_kind, mail_send_count, reg_date
pk_mailing_idx
seq_mailing_tbl
*/

-- PRIMARY KEY 생성
ALTER TABLE mailing_tbl
ADD CONSTRAINT PK_MAILING_IDX PRIMARY KEY (mail_idx);

-- SEQUENCE 생성
DROP SEQUENCE SEQ_MAILING_TBL;
CREATE SEQUENCE SEQ_MAILING_TBL;

COMMIT;


-- 11. 상품문의 테이블
CREATE TABLE qa_tbl (
    qa_code     NUMBER         NOT NULL,      -- 문의번호
    mbsp_id     VARCHAR(50)    NOT NULL,      -- 문의작성자 아이디
    pro_num     NUMBER         NOT NULL,      -- 상품번호
    qa_title    VARCHAR(50)    NOT NULL,      -- 문의제목
    qa_content  VARCHAR(2000)    NOT NULL,      -- 문의내용
    qa_date     DATE DEFAULT SYSDATE,          -- 문의작성 날짜
    qa_admin_reply_content VARCHAR2(1000),          -- 관리자 답변 내용
    qa_admin_reply_date DATE,                               -- 관리자 답변 날짜
    qa_admin_reply_status CHAR(1) DEFAULT 'N'       -- 관리자 답변 상태
);
/*
qa_tbl
qa_code, mbsp_id, pro_num, qa_title, qa_content, qa_date, qa_admin_reply_content, qa_admin_reply_date, qa_admin_reply_status
seq_qa_code
pk_qa_code
*/
-- SEQUENCE 생성
DROP SEQUENCE SEQ_QA_CODE;
CREATE SEQUENCE SEQ_QA_CODE;

-- PRIMARY KEY 생성
ALTER TABLE qa_tbl
ADD CONSTRAINT PK_QA_CODE PRIMARY KEY (qa_code);

DESC qa_tbl;

-- 임시데이터
INSERT INTO 
    qa_tbl (
       qa_code, 
       mbsp_id,
       pro_num, 
       qa_title,
       qa_content
        )
VALUES (
    SEQ_QA_CODE.NEXTVAL, 
    'user100', 
    141, 
    '배송문의', 
    '배송내용'
    );    

COMMIT;
