-- ���θ� ������Ʈ ���������ϱ�
-- �����ڸ� ��������, ��ɾ�� �빮�� �ҹ��� �������� �ʴ´�.
CREATE USER sproject IDENTIFIED BY 1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP;
    
-- ���Ѻο�. connect : ����(����), resource :�ڿ�����, dba : �����ͺ��̽� ��������
--sqldb���� ����1, ����2, ���� 3�� �ο��ϰڴ�.  
 GRANT connect, resource, dba TO sproject; -- ���� ���ȶ����� dba ������ �ο����� �ʴ´�. ���忡�� �տ� 2���� ������ �ش�. 
 