--MAIL ���̺�

CREATE TABLE MAIL 
(
  MAIL_NUM NUMBER NOT NULL 
, MAIL_TITLE VARCHAR2(100 BYTE) NOT NULL 
, MAIL_CONTENT VARCHAR2(3000 BYTE) 
, MAIL_SENDER VARCHAR2(50 BYTE) NOT NULL 
, MEMBER_NUM NUMBER NOT NULL 
, MAIL_FROM_DATE DATE DEFAULT SYSDATE 
, MAIL_TO_DATE DATE DEFAULT SYSDATE 
, MAIL_FILENUM NUMBER 
, MAIL_FILENAME VARCHAR2(50 BYTE) DEFAULT (null) 
, MAIL_FILEPATH VARCHAR2(1000 BYTE) 
, MAIL_FILERENAME VARCHAR2(100 BYTE) 
, CONSTRAINT MAIL_PK PRIMARY KEY 
  (
    MAIL_NUM 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX MAIL_PK ON MAIL (MAIL_NUM ASC) 
      LOGGING 
      TABLESPACE SYSTEM 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        FREELISTS 1 
        FREELIST GROUPS 1 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE SYSTEM 
PCTFREE 10 
PCTUSED 40 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  FREELISTS 1 
  FREELIST GROUPS 1 
  BUFFER_POOL DEFAULT 
) 
NOPARALLEL;

ALTER TABLE MAIL
ADD CONSTRAINT MAIL_MEMBER_MEMBER_NUM FOREIGN KEY
(
  MEMBER_NUM 
)
REFERENCES MEMBER
(
  MEMBER_NUM 
)
ENABLE;

----------------------------------------------------------------------------------
--MAIL_REC ���̺�

CREATE TABLE MAIL_REC 
(
  MAIL_NUM NUMBER NOT NULL 
, MAIL_RECEIVER VARCHAR2(30 BYTE) NOT NULL 
, REC_NUM NUMBER NOT NULL 
, REC_STATUS VARCHAR2(1 BYTE) DEFAULT 'N' 
) 
LOGGING 
TABLESPACE SYSTEM 
PCTFREE 10 
PCTUSED 40 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  FREELISTS 1 
  FREELIST GROUPS 1 
  BUFFER_POOL DEFAULT 
) 
NOPARALLEL;

ALTER TABLE MAIL_REC
ADD CONSTRAINT MAIL_REC_MAIL_MAIL_NUM FOREIGN KEY
(
  MAIL_NUM 
)
REFERENCES MAIL
(
  MAIL_NUM 
)
ENABLE;





----------------------------------------------------------
--������ MAIL_MAILNUM_SEQ
CREATE SEQUENCE MAIL_MAILNUM_SEQ
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 10000
  NOCACHE;

--������ MAIL_FILENUM_SEQ
CREATE SEQUENCE MAIL_FILENUM_SEQ
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 10000
  NOCACHE;