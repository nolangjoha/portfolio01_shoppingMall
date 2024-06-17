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
