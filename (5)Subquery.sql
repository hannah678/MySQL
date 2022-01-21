use demo;
select * from emp;

-- 서브쿼리
select * from emp where sal> (select avg(sal) from emp);

-- emp테이블의 급여 jones의 급여보다 많이 받는 사원 중 부서가 20번인 사원을 선택하라.
select * from emp where sal> (select sal from emp where ename='jones') and deptno=20;

-- manager 업무를 하는 사원의 평균 급여보다 작게 받는 사원을 선택하라.
select * from emp where sal < (select avg(sal) from emp where job='manager');

-- [문제1] 사원테이블에서 사원명, 입사일, 담당업무, 부서코드를 선택하되 ‘ADAMS’와 같은 업무를 하는 사원이거나 
-- ‘SCOTT’과 같은 부서인 사원을 선택하라.
select ename from emp where job = (select job from emp where ename='adams') 
or deptno= (select deptno from emp where ename='scott');

-- [문제2] 사원테이블에서 사원번호가 7521인 사원과 업무가 같고 급여가 7934인 사원보다 많은 
-- 사원의 사번,이름,업무,입사일자,급여를 출력하세요.
SELECT empno,ename,job,hiredate, sal FROM EMP WHERE job =(SELECT job FROM EMP WHERE empno=7521) 
AND sal > (SELECT sal FROM EMP WHERE empno=7934);

-- [실습] 사원테이블에서 사원의 급여가 30번부서의 최소급여보다 많이 받는 부서를 선택하라.
select deptno, min(sal) from emp group by deptno;
select deptno, min(sal) from emp group by deptno
having min(sal) > (select min(sal) from emp where deptno=30);

-- [문제1] 사원테이블의 사원의 급여가 10번부서의 평균급여보다 많이 받는 업무를 하는 업무별 평균급여를 선택하라.
select job, deptno, avg(sal) from emp group by job having avg(sal) > (select avg(sal) from emp where deptno=10);

-- 다중 행 서브 쿼리: 하나 이상의 행을 Return하는 Subquery
-- IN()
-- 부서별 최고 급여와 같은 급여를 받는 사원
select max(sal) from emp group by deptno;
select ename, job, deptno, sal from emp where sal in(5000, 3000, 2850);

select ename, job, deptno, sal from emp where sal in(select max(sal) from emp group by deptno); -- 위와 동일

-- ANY(): 서브쿼리의 결과값 중 '어느 하나의 값이라도' 만족이 되면 결과값을 반환한다.
-- [실습] 업무가 'SALESMAN'인 사원의 최소급여보다 많으면서 부서번호가 20번이 아닌 사원의 이름과 급여, 부서코드를 출력하라.
SELECT ename, sal, deptno FROM emp WHERE deptno !=20
AND sal >ANY(SELECT sal FROM emp WHERE job='SALESMAN');

-- ALL(): 서브쿼리의 결과 값 중 모든 결과 값이 만족 되어야만 결과값을 반환한다.
-- [실습] 업무가 'SALESMAN'인 사원의 최대급여보다 많으면서 부서번호가 20번이 아닌 사원의 이름과 급여를 출력하라.
SELECT ename, sal FROM emp WHERE deptno != 20
AND sal>ALL(SELECT sal FROM emp WHERE job='SALESMAN');

-- EXIST(): 서브쿼리의 데이터가 존재하는가의 여부를 먼저 따져 존재하는 값들만을 결과로 반환
-- [실습] 사원을 관리할 수 있는 사원의 정보를 선택한다.
SELECT empno, ename, sal FROM emp e
WHERE EXISTS (SELECT empno FROM emp WHERE e.empno = mgr);

-- [문제] emp 테이블의 사원 중 사원명, 담당업무, 입사일, 급여를 선택하되
-- scott이 속한 부서의 평균급여보다 많이 받는 사원과 manager업무를 하는 사원을 선택하라.
-- 단, 사원명을 오름차순으로 정렬하여 출력하라.
select ename, job, hiredate, sal from emp
where sal > (select avg(sal) from emp 
where deptno=(select deptno from emp where ename='scott')) or job='manager'
order by ename asc;

-- 다중 열 서브쿼리: 서브쿼리의 결과값이 두개 이상의 컬럼을 반환하는 서브쿼리
-- 쌍비교 서브쿼리
-- 30번 부서의 보너스를 받는 사원은(급여, 부서코드)?
select * from emp where (sal, deptno)
in (select sal, deptno from emp where deptno=30 and comm is not null);

-- from 절에 서브쿼리 문 사용하기
-- 급여가 20부서의 평균 급여보다 크고 사원을 관리하는 사원으로서 20부서에 속하지 않은 사원의 정보
SELECT b.empno, b.ename, b.job, b.sal, b.deptno, b.mgr
FROM (SELECT empno FROM emp WHERE sal >(SELECT AVG(sal) FROM emp WHERE deptno = 20)) a, emp b
WHERE a.empno = b.empno AND b.mgr is NOT NULL AND b.deptno != 20;





