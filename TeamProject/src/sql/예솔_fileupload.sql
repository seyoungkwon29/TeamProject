CREATE TABLE COMMUNITY_FILES (
    ID NUMBER(20) PRIMARY KEY,
    COM_NUM NUMBER(20) NOT NULL,
    ORIGINAL_FILENAME VARCHAR2(255) NOT NULL,
    STORE_FILENAME VARCHAR2(255) NOT NULL
);

CREATE SEQUENCE COMMUNITY_FILES_SEQ;

CREATE TABLE NOTICE_FILES (
    ID NUMBER(20) PRIMARY KEY,
    NOTICE_NUM NUMBER(20) NOT NULL,
    ORIGINAL_FILENAME VARCHAR2(255) NOT NULL,
    STORE_FILENAME VARCHAR2(255) NOT NULL
);

CREATE SEQUENCE NOTICE_FILES_SEQ;