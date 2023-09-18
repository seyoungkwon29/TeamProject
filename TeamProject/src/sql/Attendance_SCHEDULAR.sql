BEGIN
  DBMS_SCHEDULER.create_job (
    job_name        => 'INSERT_MEMBER_SCHEDULAR',
    job_type        => 'PLSQL_BLOCK',
    job_action      => 'DECLARE
                          l_employee_number NUMBER;
                        BEGIN
                          IF TO_CHAR(SYSDATE, ''D'') BETWEEN 2 AND 6 THEN -- ������(2)���� �ݿ���(6)����
                            FOR i IN 1001..1021 LOOP
                              SELECT member_seq.nextval INTO l_employee_number FROM dual;
                              INSERT INTO attendance (att_no, member_num, att_date, att_start, att_fin, att_total, att_status)
                              VALUES (l_employee_number, i, TRUNC(SYSDATE), NULL, NULL, NULL, ''���'');
                            END LOOP;
                          END IF;
                        END;',
    start_date      => TRUNC(SYSDATE) + 1, -- ���� ���� 12�ÿ� �۾� ����
    repeat_interval => 'FREQ=DAILY; BYHOUR=00; BYMINUTE=00; BYSECOND=0',
    enabled         => TRUE
  );
END;
/

-- CREATE MEMBER_SEQ FROM DUAL;