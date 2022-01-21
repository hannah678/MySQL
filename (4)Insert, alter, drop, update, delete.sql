use demo;
show tables;
desc emp2;
select * from emp2;

-- 테이블에 필드를 추가한다.
-- emp2 테이블에 연락처를 저장할 필드 추가
alter table emp2 add tel varchar(15);

-- 필드의 크기를 수정하기
-- job을 varchar(20)로 바꾸기
alter table emp2 modify job varchar(20);

-- 컬럼명 변경하기
-- tel -> mobile로 컬럼명 변경하기
alter table emp2 change tel mobile varchar(20);
desc emp2;

-- 필드 삭제하기
-- job 삭제하기
alter table emp2 drop column job;

-- emp2테이블 지우기
drop table emp2;
show tables;

delete from emp where empno in(1111, 2222, 3333);
commit;

select @@autocommit;

-- 제약조건 확인하기
select * from information_schema.table_constraints
where table_name in('emp', 'dept', 'salgrade');

select* from emp;

-- unique: 유일한 값일 때
-- ename을 unique 제약조건 설정(동명이인 제한)
alter table emp add constraint emp_uq_ename unique(ename);
insert into emp(empno, ename) values(8888, 'smith'); -- Error Code: 1062. Duplicate entry 'smith' for key 'emp.emp_uq_ename'

-- 제약조건 삭제
alter table emp drop constraint emp_uq_ename;

-- check: 특정 데이터만 등록되도록 제약조건을 설정한다.
alter table emp add constraint emp_ck_sal check(sal>500 and sal<8000);
update emp set sal=8000 where ename='smith';  -- Error Code: 3819. Check constraint 'emp_ck_sal' is violated.
update emp set sal=7999 where ename='smith';  -- ok

alter table emp add constraint emp_ck_comm check( comm in(100, 200, 300, 400)); -- error: 현재 있는 데이터에 위배
update emp set comm=100;
alter table emp add constraint emp_ck_comm check( comm in(100, 200, 300, 400)); -- ok

update emp set comm=200 where sal>3000;
update emp set comm=500 where sal<2000; -- Error Code: 3819. Check constraint 'emp_ck_comm' is violated.

-- primary key 설정
-- dept테이블 deptno필드를
alter table dept add constraint dept_pk_deptno primary key(deptno);

-- 제약조건 확인하기
select* from information_schema.table_constraints
where table_name in ('emp', 'dept', 'salgrade');

insert into dept values(50, '총무부', '서울');
 
-- 외래키 설정
-- emp 테이블의 deptno는 dept테이블의 deptno를 참조한다.
alter table emp add constraint emp_fk_deptno foreign key(deptno)
references dept(deptno);

select * from dept;
select * from emp;

-- emp에서 martin을 50번부서로 이동
update emp set deptno=50 where ename='martin';
update emp set deptno=35 where ename='jones'; -- Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails (`demo`.`emp`, CONSTRAINT `emp_fk_deptno` FOREIGN KEY (`DEPTNO`) REFERENCES `dept` (`DEPTNO`))
-- 외래키인 dept 테이블에 35번 부서가 없으므로 에러

delete from dept where deptno=20; -- Error Code: 1451. Cannot delete or update a parent row: a foreign key constraint fails (`demo`.`emp`, CONSTRAINT `emp_fk_deptno` FOREIGN KEY (`DEPTNO`) REFERENCES `dept` (`DEPTNO`))

alter table emp drop constraint emp_fk_deptno;

-- on delete cascade: 외래키가 다른 테이블에서 쓰이고 있어도 삭제 가능.(다른 테이블의 데이터도 삭제됨)
alter table emp add constraint emp_fk_deptno foreign key(deptno)
references dept(deptno) on delete cascade;

delete from dept where deptno=20; -- ok 

select avg(sal) from emp;
select * from emp where sal>2215;
select * from emp where sal>(select avg(sal) from emp); -- 위의 쿼리문과 동일

delete from emp;

select * from information_schema.table_constraints where table_name in ('emp', 'dept', 'salgrade');

alter table emp drop constraint emp_ck_comm;
alter table emp drop constraint emp_ck_sal;

delete from dept where deptno=20;
insert into dept values(20,'인사부','경기도');

INSERT INTO EMP VALUES(7369, 'SMITH',  'CLERK', 7902, '1980-12-17', 800, NULL, 20);
INSERT INTO EMP VALUES(7499, 'ALLEN',  'SALESMAN',  7698, '1981-02-20', 1600,  300, 30);
INSERT INTO EMP VALUES(7521, 'WARD',   'SALESMAN',  7698, '1981-02-22', 1250,  500, 30);
INSERT INTO EMP VALUES(7566, 'JONES',  'MANAGER',   7839, '1981-04-02',  2975, NULL, 20);
INSERT INTO EMP VALUES(7654, 'MARTIN', 'SALESMAN',  7698,'1981-09-28', 1250, 1400, 30);
INSERT INTO EMP VALUES(7698, 'BLAKE',  'MANAGER',   7839,'1981-05-01', 2850, NULL, 30);
INSERT INTO EMP VALUES(7782, 'CLARK',  'MANAGER',   7839,'1981-06-09', 2450, NULL, 10);
INSERT INTO EMP VALUES(7788, 'SCOTT',  'ANALYST',   7566, '1982-12-09', 3000, NULL, 20);
INSERT INTO EMP VALUES(7839, 'KING',   'PRESIDENT', NULL, '1981-11-17', 5000, NULL, 10);
INSERT INTO EMP VALUES(7844, 'TURNER', 'SALESMAN',  7698, '1981-09-08',  1500,    0, 30);
INSERT INTO EMP VALUES(7876, 'ADAMS',  'CLERK', 7788, '1983-01-12', 1100, NULL, 20);
INSERT INTO EMP VALUES(7900, 'JAMES',  'CLERK',     7698, '1981-12-03',   950, NULL, 30);
INSERT INTO EMP VALUES(7902, 'FORD',   'ANALYST',   7566, '1981-12-03',  3000, NULL, 20);
INSERT INTO EMP VALUES(7934, 'MILLER', 'CLERK',     7782, '1982-01-23', 1300, NULL, 10);

select*from emp;
select*from dept;



