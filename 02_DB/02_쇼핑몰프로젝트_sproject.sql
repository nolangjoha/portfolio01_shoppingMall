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



