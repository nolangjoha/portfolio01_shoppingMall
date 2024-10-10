--1.ȸ������ ���̺�
CREATE TABLE MBSP_TBL(
        MBSP_ID             VARCHAR2(15)            NOT NULL,   -- ���̵�
        MBSP_NAME           VARCHAR2(30)            NOT NULL,   -- �̸�
        MBSP_EMAIL          VARCHAR2(50)            NOT NULL,   -- �̸���
        MBSP_PASSWORD       CHAR(60)               NOT NULL,    -- ��й�ȣ(��ȣȭ)
        MBSP_ZIPCODE        CHAR(5)                 NOT NULL,   -- �����ȣ
        MBSP_ADDR           VARCHAR2(100)            NOT NULL,  -- �ּ�
        MBSP_DEADDR         VARCHAR2(100)            NOT NULL,  -- ���ּ�
        MBSP_PHONE          VARCHAR2(15)            NOT NULL,   -- ����ó
        MBSP_NICK           VARCHAR2(30)            NOT NULL UNIQUE,  --�г���(�ߺ�x)
        MBSP_RECEIVE        CHAR(1)                 NOT NULL,       -- �����õ��� ����
        MBSP_POINT          NUMBER DEFAULT 0        NOT NULL,   -- ����Ʈ:����ڰ� �Է��ϴ� ������ �ƴϹǷ� ����Ʈ�� ����
        MBSP_LASTLOGIN      DATE                    NULL,       -- �ֱ� ������, ó���� �α����� �������� null�� �� �ۿ� ����. 
        MBSP_DATESUB        DATE DEFAULT SYSDATE    NOT NULL,   -- ���ʵ����
        MBSP_UPDATEDATE     DATE DEFAULT SYSDATE    NOT NULL    -- ȸ������ ������
);

/*
mbsp_tbl
mbsp_id, mbsp_name, mbsp_email, mbsp_password, mbsp_zipcode, mbsp_addr, mbsp_deaddr, mbsp_phone,
mbsp_nick, mbsp_receive, mbsp_point, mbsp_lastlogin, mbsp_datesub, mbsp_updatedate
pk_mbsp_id
*/


--�ҼҰ��� �������� pk�������� ���� �ۼ�(���̺� ���� �� �ٷ� ����)
ALTER TABLE MBSP_TBL
ADD CONSTRAINT PK_MBSP_ID PRIMARY KEY (MBSP_ID);

-- ȸ������ ���̺� ����
--DROP TABLE MBSP_TBL;
COMMIT;



--2. ������(Admin) ���̺�
CREATE TABLE ADMIN_TBL (
    ADMIN_ID    VARCHAR2(15)    PRIMARY KEY,
    ADMIN_PW    CHAR(60)    NOT NULL,
    ADMIN_VISIT_DATE    DATE
);
-- admin_tbl, admin_id, admin_pw, admin_visit_date

--������ ���� ������ ����
INSERT INTO ADMIN_TBL(ADMIN_ID, ADMIN_PW)VALUES('admin', '$2a$10$M2k.eIgxdKXrkuqzlq441ukiEeLH19YwjsgDx6NUADL/NyI3HoYCW');

COMMIT;


--3. ��ǰ���� ���̺�
CREATE TABLE product_tbl(
    pro_num         NUMBER,                             --��ǰ��ȣ
    cat_code        NUMBER               NULL,          --ī�װ� �ڵ�
    pro_name        VARCHAR(100)         NOT NULL,      --��ǰ��
    pro_price       NUMBER               NOT NULL,      --��ǰ ����
    pro_discount    NUMBER               NOT NULL,      --������
    pro_publisher   VARCHAR(100)         NOT NULL,      --������
    pro_content     VARCHAR(4000)        NOT NULL,      --��ǰ����
    pro_up_folder   VARCHAR(50)          NOT NULL,      --�̹������ε����� ���
    pro_img         VARCHAR(100)         NOT NULL,      --�̹��� ���ϸ�
    pro_amount      NUMBER               NOT NULL,      --��ǰ����
    pro_buy         CHAR(1)              NOT NULL,      --�Ǹſ��� 'Y','N'
    pro_date        DATE DEFAULT SYSDATE NOT NULL,      --��ǰ�����
    pro_updatedate  DATE DEFAULT SYSDATE NOT NULL       --��ǰ������
);
--product_tbl
/*
pro_num, cat_code, pro_name, pro_price, pro_discount, pro_publisher, pro_content, pro_up_folder, pro_img, 
pro_amount, pro_buy, pro_date, pro_updatedate
*/

--������ ����
CREATE SEQUENCE seq_pro_num;

--PRIMARY KEY ����
ALTER TABLE product_tbl
ADD CONSTRAINT PK_PRO_NUM PRIMARY KEY (PRO_NUM);

--�׽�Ʈ�� ������
INSERT INTO product_tbl (pro_num, cat_code, pro_name, pro_price, pro_discount, pro_publisher, pro_content, pro_up_folder, pro_img, 
pro_amount, pro_buy, pro_date, pro_updatedate) 
    VALUES (seq_pro_num.NEXTVAL,6,'������ ��', 6000, 20, '�ʷ��ʷ�', '<img alt="" src="/admin/product/display/beige_shawl.jpg" style="height:4622px; width:3220px" /><br />
����� ��������','2024\07\15','842f4ce8-7766-4484-96c5-17f72ad0e027-beige_shawl.jpg', 1,'Y', '24/07/15', '24/07/15');


COMMIT;

--4. ī�װ� ���̺�
CREATE TABLE category_tbl(
    CAT_CODE        NUMBER,                     -- ī�װ� �ڵ� (��� ������ ī�װ� ����)
    CAT_PRTCODE     NUMBER  NULL,               -- ����(�θ�)ī�װ�
    CAT_NAME        VARCHAR(50)     NOT NULL    -- ī�װ� �̸�
);
-- category_tbl // cat_code, cat_prtcode, cat_name

ALTER TABLE category_tbl
ADD CONSTRAINT PK_CAT_CODE PRIMARY KEY (CAT_CODE);

--1�� ī�װ�
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (1, NULL, '�Ż�ǰ');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (2, NULL, '����');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (3, NULL, '����');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (4, NULL, '�Ǽ��縮');

--1�� ī�װ� : 2(����)
--2�� ī�װ�(����) : �ƿ���, ���콺/����, Ƽ����/�ĵ�
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (5, 2, '�ƿ���');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (6, 2, '���콺&#38;����');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (7, 2, 'Ƽ����&#38;�ĵ�');  

--1�� ī�װ� : 3(����)
--2�� ī�װ�(����) : û����, ������, �ݹ���, �򸮴�
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (8, 3, 'û����');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (9, 3, '������');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (10, 3, '�ݹ���');  
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (11, 3, '�򸮴�');  

--1�� ī�װ� : 4(�Ǽ��縮)
--2�� ī�װ�(�Ǽ��縮) : ����, �縻/��Ÿŷ, �־�
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (12, 4, '����');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (13, 4, '�縻&#38;��Ÿŷ');
INSERT INTO category_tbl (cat_code, cat_prtcode, cat_name) 
    VALUES (14, 4, '�־�');  
  
COMMIT;


--5. ��ٱ��� ���̺�
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

--������ ����
CREATE SEQUENCE SEQ_CART_CODE;

-- PRIMARY KEY ����
ALTER TABLE CART_TBL
ADD CONSTRAINT PK_CART_CODE PRIMARY KEY(CART_CODE);


COMMIT;
    


--6. ��ǰ�ı� ���̺�
DROP TABLE review_tbl;
CREATE TABLE review_tbl (
    re_code     NUMBER         NOT NULL,      -- �����ȣ
    mbsp_id     VARCHAR(50)    NOT NULL,      -- �����ۼ��� ���̵�
    pro_num     NUMBER         NOT NULL,      -- ��ǰ��ȣ
    re_title    VARCHAR(50)    NOT NULL,      -- ��������
    re_content  VARCHAR(50)    NOT NULL,      -- ���䳻��
    re_rate     NUMBER         NOT NULL,      -- ��������(����)
    re_date     DATE DEFAULT SYSDATE          -- �����ۼ� ��¥
);
-- review_tbl(re_code, mbsp_id, pro_num, re_title, re_content, re_rate, re_date, re_admin_reply_content, re_admin_reply_date, re_admin_reply_status)
-- SEQ_REVIEW_CODE
-- PK_RE_CODE

-- SEQUENCE ����
DROP SEQUENCE SEQ_REVIEW_CODE;
CREATE SEQUENCE SEQ_REVIEW_CODE;

-- PRIMARY KEY ����
ALTER TABLE review_tbl
ADD CONSTRAINT PK_RE_CODE PRIMARY KEY (re_code);

-- �÷� ����
ALTER TABLE review_tbl MODIFY re_content VARCHAR2(2000);
ALTER TABLE review_tbl ADD re_admin_reply_content VARCHAR2(1000);
ALTER TABLE review_tbl ADD re_admin_reply_date DATE;
ALTER TABLE review_tbl ADD re_admin_reply_status CHAR(1) DEFAULT 'N';


DESC review_tbl;


--���� ������ �ӽ� ����
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
    '�ı�����', 
    '�ı⳻��', 
    3
    );    

COMMIT;


-- 7. �ֹ����̺�
DROP TABLE ORDER_TBL;
CREATE TABLE ORDER_TBL(
    ord_code        NUMBER,                             --�ֹ��ڵ�
    mbsp_id         VARCHAR2(50)    NOT NULL,           -- �ֹ��� ���̵�
    ord_name        VARCHAR2(30)    NOT NULL,           -- �ֹ��� �̸�
    ord_tel         VARCHAR2(20)    NOT NULL,           -- �ֹ��� ����ó
    ord_regdate     DATE DEFAULT SYSDATE    NOT NULL,   -- �ֹ���¥
    ord_addr_zipcode  CHAR(5)         NOT NULL,         -- ����� �����ȣ
    ord_addr_basic    VARCHAR2(50)    NOT NULL,         -- ����� �⺻�ּ�
    ord_addr_detail   VARCHAR2(50)    NOT NULL,         -- ����� ���ּ�
    ord_price       NUMBER          NOT NULL,           -- �ֹ��ݾ�
    ord_desc        VARCHAR2(500),                      -- �ֹ��� ��û����
    ord_admin_meno    VARCHAR2(500)                     -- ������ �޸�
);
/*
order_tbl, seq_ord_code, pk_ord_code
ord_code, mbsp_id, ord_name, ord_tel, ord_regdate, ord_addr_zipcode, ord_addr_basic, ord_addr_detail, 
ord_price, ord_desc, ord_admin_meno
*/

--SEQUENCE ����
drop SEQUENCE SEQ_ORD_CODE;
CREATE SEQUENCE SEQ_ORD_CODE;

--PRIMARY KEY ����
ALTER TABLE order_tbl
ADD CONSTRAINT PK_ORD_CODE PRIMARY KEY (ord_code);

COMMIT;

-- �÷� ���� ����
ALTER TABLE ORDER_TBL MODIFY ord_addr_basic VARCHAR2(100);
ALTER TABLE ORDER_TBL RENAME COLUMN ord_admin_meno TO ord_admin_memo;

-- 8. �ֹ������̺�(��ǰ����)
DROP TABLE ORDETAIL_TBL;
CREATE TABLE ORDETAIL_TBL(
    ord_code    NUMBER,                 -- �ֹ���ȣ
    pro_num     NUMBER,                 -- ��ǰ��ȣ
    dt_amount   NUMBER     NOT NULL,    -- �ֹ�����
    dt_price    NUMBER     NOT NULL     -- �ֹ�����
);
/*
ordetail_tbl, pk_ordetail_code
ord_code, pro_num, dt_amount, dt_price
*/


--PRIMARY KEY ����
ALTER TABLE ordetail_tbl
ADD CONSTRAINT PK_ORDETAIL_CODE PRIMARY KEY (ord_code, pro_num);

COMMIT;


-- 9. �������̺�
DROP TABLE payinfo;
CREATE TABLE payinfo(
    p_id        NUMBER,                     -- ������ȣ
    ord_code    NUMBER          NOT NULL,   -- �ֹ���ȣ
    mbsp_id     VARCHAR2(50)    NOT NULL,   -- �ֹ��� ���̵�
    paymethod   VARCHAR2(50)    NOT NULL,   -- �������
    payinfo     VARCHAR2(100)   NOT NULL,   -- ��������
    p_price     NUMBER          NOT NULL,   --�ѱݾ�
    p_status    VARCHAR2(10)    NOT NULL,   --�ϳ�/�̳�
    p_date      DATE DEFAULT    SYSDATE     --������
);

/*
payinfo, seq_payinfo_id, pk_payinfo_id
p_id, ord_code, mbsp_id, paymethod, payinfo, p_price, p_status, p_date
*/

-- SEQUENCE ����
DROP SEQUENCE SEQ_PAYINFO_ID;
CREATE SEQUENCE SEQ_PAYINFO_ID;

-- PRIMARY KEY ����
ALTER TABLE payinfo
ADD CONSTRAINT PK_PAYINFO_ID PRIMARY KEY (p_id);

COMMIT;

--������ �ֹ�����
-- �ֹ�����
SELECT ot.ord_code, ot.pro_num, ot.dt_amount, ot.dt_price, p.pro_name, p.pro_up_folder, p.pro_img
FROM ordetail_tbl ot INNER JOIN product_tbl p
ON ot.pro_num = p.pro_num
WHERE  ot.ord_code = 40;


--10. ���Ϲ߼� ���̺�
DROP TABLE mailing_tbl;
CREATE TABLE mailing_tbl(
    mail_idx        NUMBER,                     --���Ϲ�ȣ
    mail_title      VARCHAR2(100),              --��������
    mail_content    VARCHAR2(4000) NOT NULL,    --���ϳ���
    mail_kind       VARCHAR2(20) NOT NULL,      --��������
    mail_send_count NUMBER DEFAULT 0,           --���Ϲ߼�Ƚ��
    reg_date        DATE DEFAULT SYSDATE        --���Ϲ߼۳�¥
);
/*
mailing_tbl
mail_idx, mail_title, mail_content, mail_kind, mail_send_count, reg_date
pk_mailing_idx
seq_mailing_tbl
*/

-- PRIMARY KEY ����
ALTER TABLE mailing_tbl
ADD CONSTRAINT PK_MAILING_IDX PRIMARY KEY (mail_idx);

-- SEQUENCE ����
DROP SEQUENCE SEQ_MAILING_TBL;
CREATE SEQUENCE SEQ_MAILING_TBL;

COMMIT;


-- 11. ��ǰ���� ���̺�
CREATE TABLE qa_tbl (
    qa_code     NUMBER         NOT NULL,      -- ���ǹ�ȣ
    mbsp_id     VARCHAR(50)    NOT NULL,      -- �����ۼ��� ���̵�
    pro_num     NUMBER         NOT NULL,      -- ��ǰ��ȣ
    qa_title    VARCHAR(50)    NOT NULL,      -- ��������
    qa_content  VARCHAR(2000)    NOT NULL,      -- ���ǳ���
    qa_date     DATE DEFAULT SYSDATE,          -- �����ۼ� ��¥
    qa_admin_reply_content VARCHAR2(1000),          -- ������ �亯 ����
    qa_admin_reply_date DATE,                               -- ������ �亯 ��¥
    qa_admin_reply_status CHAR(1) DEFAULT 'N'       -- ������ �亯 ����
);
/*
qa_tbl
qa_code, mbsp_id, pro_num, qa_title, qa_content, qa_date, qa_admin_reply_content, qa_admin_reply_date, qa_admin_reply_status
seq_qa_code
pk_qa_code
*/
-- SEQUENCE ����
DROP SEQUENCE SEQ_QA_CODE;
CREATE SEQUENCE SEQ_QA_CODE;

-- PRIMARY KEY ����
ALTER TABLE qa_tbl
ADD CONSTRAINT PK_QA_CODE PRIMARY KEY (qa_code);

DESC qa_tbl;

-- �ӽõ�����
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
    '��۹���', 
    '��۳���'
    );    

COMMIT;
