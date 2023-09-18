-- ���̺� ���� (COLUMN : 14)
CREATE TABLE MEMBER(
    MEMBER_NUM NUMBER(20) CONSTRAINT PK_PROJECT PRIMARY KEY,   -- ���
    MEMBER_NAME VARCHAR2(20) NOT NULL,  -- �̸�
	DIV_NAME    VARCHAR2(20) NOT NULL,  -- �μ��̸�
    RANK        VARCHAR2(20) NOT NULL,  -- ����
    ADDRESS     VARCHAR2(80),           -- �ּ�
    PHONE       VARCHAR2(20),           -- ��ȭ��ȣ
    MAIL        VARCHAR2(50),           -- ����
    HIRE_DATE   VARCHAR2(20) NOT NULL,  -- �Ի���
    RETIRE_DATE VARCHAR2(20),           -- �����
    SSN         VARCHAR2(20) NOT NULL,  -- �ֹι�ȣ
    PASSWORD    VARCHAR2(20) NOT NULL,  -- ��й�ȣ
    GENDER      VARCHAR2(20),           -- ����
    PHOTO       VARCHAR2(20),           -- ����
    ANNUAL_LEAVE NUMBER(20) DEFAULT 20
) ;

-- INSERT DATA
INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1001, '������', '�λ�', '����', '��⵵ ������ �꺻�� 1052���� �븲����Ʈ 724-301ȣ', '010-2034-5958', 'kowi873@everyware.com', to_date('02-03-2014', 'dd-mm-yyyy'), NULL, '900530-2124663', '1001', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1002, '������', '��ȹ', '����', '����� ��õ�� �� �Žð��� 716�� 103ȣ', '010-6990-4232', 'leals8l@everyware.com', to_date('01-03-2015', 'dd-mm-yyyy'), NULL, '900419-2131911', '1002', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1003, '���ֿ�', '�ѹ�', '����', '����� ����� �߰躻�� ���δ븲����Ʈ 1003', '010-9123-5546', 'e7drzggap2@everyware.com', to_date('02-03-2018', 'dd-mm-yyyy'), NULL, '910117-1159023', '1003', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1004, '���ϳ�', '��ȹ', '���', '����� ���α� ������ 321-28', '010-2319-3325', 'fpcultq7@everyware.com', to_date('02-03-2018', 'dd-mm-yyyy'), NULL, '941220-2116413', '1004', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1005, '������', '�λ�', '���', '����� ���ı� ��õ�� 11 ��̾���Ʈ 26-810', '010-7715-6946', 'liqbs8@everyware.com', to_date('02-03-2019', 'dd-mm-yyyy'), NULL, '930704-2124881', '1005', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1006, '������', '����', '����', '����� ���ʱ� ����2�� 18-21 �ְ�����Ʈ 234-409', '010-7784-5100', 'ugqlq5@everyware.com', to_date('02-03-2019', 'dd-mm-yyyy'), NULL, '960725-2103028', '1006', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1007, '������', '��ȹ', '���', '��� ��õ �θ��� 41 �ְ�����Ʈ 318-901', '010-9021-2993', '57pxl0@everyware.com', to_date('02-03-2020', 'dd-mm-yyyy'), NULL, '940908-1134172', '1007', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1008, '����ȯ', '�ѹ�', '���', '����� ������ ��ȭ 3�� ��ȭ 5���� 504-1001', '010-4841-1394', 'exofc2@everyware.com', to_date('02-03-2020', 'dd-mm-yyyy'), NULL, '920401-1169227', '1008', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1009, '������', '����', '���', '��⵵ ������ �д籸 �̸ŵ� ��������Ʈ 701-202', '010-8002-5017', 'eiuhtv@everyware.com', to_date('02-03-2020', 'dd-mm-yyyy'), NULL, '931207-2114453', '1009', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1010, '���ؿ�', '�ѹ�', '���', '����� �������� ���ǵ��� �Ѿ����ƮB�� 304', '010-7336-2316', 'zmee60@everyware.com', to_date('02-03-2020', 'dd-mm-yyyy'), NULL, '930601-1107352', '1010', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1011, '���ؿ�', '�ѹ�', '���', '��⵵ ���� �ϻ걸 �ֿ������̸���1406 1103ȣ', '010-8939-2497', 'fivtos@everyware.com', to_date('02-03-2021', 'dd-mm-yyyy'), NULL, '930815-1159069', '1011', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1012, '���η�', '����', '���', '����� ���ʱ� ���ʵ� 1315 ���� �������Ʈ 1�� 1505', '010-9552-5081', '9wijzi@everyware.com', to_date('02-03-2021', 'dd-mm-yyyy'), NULL, '930804-1154644', '1012', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1013, '������', '����', '���', '����� ������ ����2�� ������Ű����Ʈ 1-606', '010-2650-1386', 'uyzii6@everyware.com', to_date('21-03-2021', 'dd-mm-yyyy'), NULL, '971014-2139250', '1013', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1014, '������', '�λ�', '���', '����� ������ ���ϵ� 42 �켺����Ʈ 8-1002', '010-8949-9580', 'mhv931@everyware.com', to_date('22-03-2021', 'dd-mm-yyyy'), NULL, '920316-1156283', '1014', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1015, '������', '��ȹ', '���', '����� ������ ����2��', '010-2904-6482', '5fify3@everyware.com', to_date('02-06-2021', 'dd-mm-yyyy'), NULL, '960113-1135025', '1015', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1016, '������', '�λ�', '���', '����� ���빮�� ��ʸ��� 29-42', '010-3169-9559', 'ibtpog@everyware.com', to_date('09-06-2022', 'dd-mm-yyyy'), NULL, '951028-1140536', '1016', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1017, 'ä����', '�λ�', '���', '����� ������ ������ 438-15', '010-7748-8009', 'qgbdo2@everyware.com', to_date('02-03-2022', 'dd-mm-yyyy'), NULL, '940219-1171210', '1017', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1018, '������', '��ȹ', '���', '��⵵ ���� �ϻ걸 �ֿ�2�� ���̸��� 18���� �������Ʈ', '010-6812-2308', 'vrobaod@everyware.com', to_date('05-03-2022', 'dd-mm-yyyy'), NULL, '971206-1133071', '1018', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1019, '����ȣ', '�ѹ�', '���', '��⵵ �Ⱦ�� ���ȱ� ���3�� �Ｚ���̾� 113-702', '010-3102-3320', 'vqxsb0@everyware.com', to_date('02-05-2022', 'dd-mm-yyyy'), NULL, '930120-1132985', '1019', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1020, '������', '�ѹ�', '���', '����� ���� ���ϵ� 53-13', '010-2335-2302', '9nwwcd@everyware.com', to_date('02-03-2023', 'dd-mm-yyyy'), NULL, '981001-2132521', '1020', '����', NULL, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1021, 'Ȳ����', '����', '���', '����� ���ʱ� ���ʵ� �ŵ��ƾ���Ʈ 7-308', '010-7216-4289', '04argm@everyware.com', to_date('02-03-2023', 'dd-mm-yyyy'), NULL, '951101-1160725', '1021', '����', NULL, DEFAULT);

COMMIT;