-- 테이블 생성 (COLUMN : 14)
CREATE TABLE MEMBER(
    MEMBER_NUM NUMBER(20) CONSTRAINT PK_PROJECT PRIMARY KEY,   -- 사번
    MEMBER_NAME VARCHAR2(20) NOT NULL,  -- 이름
	DIV_NAME    VARCHAR2(20) NOT NULL,  -- 부서이름
    RANK        VARCHAR2(20) NOT NULL,  -- 직급
    ADDRESS     VARCHAR2(80),           -- 주소
    PHONE       VARCHAR2(20),           -- 전화번호
    MAIL        VARCHAR2(50),           -- 메일
    HIRE_DATE   VARCHAR2(20) NOT NULL,  -- 입사일
    RETIRE_DATE VARCHAR2(20),           -- 퇴사일
    SSN         VARCHAR2(20) NOT NULL,  -- 주민번호
    PASSWORD    VARCHAR2(20) NOT NULL,  -- 비밀번호
    GENDER      VARCHAR2(20),           -- 성별
    PHOTO       VARCHAR2(20),           -- 사진
    ANNUAL_LEAVE NUMBER(20) DEFAULT 20
) ;

-- INSERT DATA
INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1001, '서가람', '인사', '팀장', '경기도 군포시 산본동 1052번지 대림아파트 724-301호', '010-2034-5958', 'kowi873@everyware.com', to_date('02-03-2014', 'dd-mm-yyyy'), NULL, '900530-2124663', '1001', '여자', 1001, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1002, '이지나', '기획', '팀장', '서울시 양천구 목동 신시가지 716동 103호', '010-6990-4232', 'leals8l@everyware.com', to_date('01-03-2015', 'dd-mm-yyyy'), NULL, '900419-2131911', '1002', '여자', 1002, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1003, '차주영', '총무', '팀장', '서울시 노원구 중계본동 진로대림아파트 1003', '010-9123-5546', 'e7drzggap2@everyware.com', to_date('02-03-2018', 'dd-mm-yyyy'), NULL, '910117-1159023', '1003', '남자', 1003, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1004, '박하나', '기획', '사원', '서울시 구로구 개봉동 321-28', '010-2319-3325', 'fpcultq7@everyware.com', to_date('02-03-2018', 'dd-mm-yyyy'), NULL, '941220-2116413', '1004', '여자', 1004, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1005, '심유경', '인사', '사원', '서울시 송파구 신천동 11 삼미아파트 26-810', '010-7715-6946', 'liqbs8@everyware.com', to_date('02-03-2019', 'dd-mm-yyyy'), NULL, '930704-2124881', '1005', '여자', 1005, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1006, '조정은', '영업', '팀장', '서울시 서초구 반포2동 18-21 주공아파트 234-409', '010-7784-5100', 'ugqlq5@everyware.com', to_date('02-03-2019', 'dd-mm-yyyy'), NULL, '960725-2103028', '1006', '여자', 1006, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1007, '김현욱', '기획', '사원', '경기 과천 부림동 41 주공아파트 318-901', '010-9021-2993', '57pxl0@everyware.com', to_date('02-03-2020', 'dd-mm-yyyy'), NULL, '940908-1134172', '1007', '남자', 1007, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1008, '심주환', '총무', '사원', '서울시 강서구 방화 3동 방화 5단지 504-1001', '010-4841-1394', 'exofc2@everyware.com', to_date('02-03-2020', 'dd-mm-yyyy'), NULL, '920401-1169227', '1008', '남자', 1008, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1009, '강재이', '영업', '사원', '경기도 성남시 분당구 이매동 성지아파트 701-202', '010-8002-5017', 'eiuhtv@everyware.com', to_date('02-03-2020', 'dd-mm-yyyy'), NULL, '931207-2114453', '1009', '여자', 1009, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1010, '백준용', '총무', '사원', '서울시 영등포구 여의도동 한양아파트B동 304', '010-7336-2316', 'zmee60@everyware.com', to_date('02-03-2020', 'dd-mm-yyyy'), NULL, '930601-1107352', '1010', '남자', 1010, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1011, '서준우', '총무', '사원', '경기도 고양시 일산구 주엽동문촌마을1406 1103호', '010-8939-2497', 'fivtos@everyware.com', to_date('02-03-2021', 'dd-mm-yyyy'), NULL, '930815-1159069', '1011', '남자', 1011, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1012, '박인량', '영업', '사원', '서울시 서초구 서초동 1315 번지 진흥아파트 1동 1505', '010-9552-5081', '9wijzi@everyware.com', to_date('02-03-2021', 'dd-mm-yyyy'), NULL, '930804-1154644', '1012', '남자', 1012, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1013, '김정원', '영업', '사원', '서울시 강남구 도곡2동 개포럭키아파트 1-606', '010-2650-1386', 'uyzii6@everyware.com', to_date('21-03-2021', 'dd-mm-yyyy'), NULL, '971014-2139250', '1013', '여자', 1013, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1014, '차은우', '인사', '사원', '서울시 강동구 명일동 42 우성아파트 8-1002', '010-8949-9580', 'mhv931@everyware.com', to_date('22-03-2021', 'dd-mm-yyyy'), NULL, '920316-1156283', '1014', '남자', 1014, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1015, '장형일', '기획', '사원', '서울시 마포구 성산2동', '010-2904-6482', '5fify3@everyware.com', to_date('02-06-2021', 'dd-mm-yyyy'), NULL, '960113-1135025', '1015', '남자', 1015, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1016, '주윤진', '인사', '사원', '서울시 동대문구 답십리동 29-42', '010-3169-9559', 'ibtpog@everyware.com', to_date('09-06-2022', 'dd-mm-yyyy'), NULL, '951028-1140536', '1016', '남자', 1016, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1017, '채영준', '인사', '사원', '서울시 마포구 합정동 438-15', '010-7748-8009', 'qgbdo2@everyware.com', to_date('02-03-2022', 'dd-mm-yyyy'), NULL, '940219-1171210', '1017', '남자', 1017, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1018, '강형택', '기획', '사원', '경기도 고양시 일산구 주엽2동 문촌마을 18단지 대원아파트', '010-6812-2308', 'vrobaod@everyware.com', to_date('05-03-2022', 'dd-mm-yyyy'), NULL, '971206-1133071', '1018', '남자', 1018, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1019, '김준호', '총무', '사원', '경기도 안양시 동안구 비산3동 삼성래미안 113-702', '010-3102-3320', 'vqxsb0@everyware.com', to_date('02-05-2022', 'dd-mm-yyyy'), NULL, '930120-1132985', '1019', '남자', 1019, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1020, '공지영', '총무', '사원', '서울시 은평구 응암동 53-13', '010-2335-2302', '9nwwcd@everyware.com', to_date('02-03-2023', 'dd-mm-yyyy'), NULL, '981001-2132521', '1020', '여자', 1020, DEFAULT);

INSERT INTO MEMBER (MEMBER_NUM, MEMBER_NAME, DIV_NAME, RANK, ADDRESS, PHONE, MAIL, HIRE_DATE, RETIRE_DATE, SSN, PASSWORD, GENDER, PHOTO, ANNUAL_LEAVE)
VALUES (1021, '황재윤', '영업', '사원', '서울시 서초구 서초동 신동아아파트 7-308', '010-7216-4289', '04argm@everyware.com', to_date('02-03-2023', 'dd-mm-yyyy'), NULL, '951101-1160725', '1021', '남자', 1021, DEFAULT);

COMMIT;