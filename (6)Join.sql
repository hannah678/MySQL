use emp;
desc salgrade;
 
 -- 비동등 조인
 SELECT E.EMPNO, E.ENAME, E.MGR, E2.EMPNO "MGR NO", E2.ENAME "MGR NAME"
FROM EMP E JOIN EMP E2 ON E.MGR=E2.EMPNO;

-- emp테이블에서 사원명, 급여, 호봉을 선택하되,
-- 담당업무가 Manager이거나 Salesman인 사원 중 사원명에 A가 포함된 사원을 선택하라.
select e.ename, e.sal, s.grade from emp e join salgrade s  
on e.sal between losal and hisal
where e.job in('Manager', 'salesman') and e.ename like'%a%';

select e.ename, e.sal, s.grade from emp e join salgrade s  
on e.sal between losal and hisal
and e.job in('Manager', 'salesman') and e.ename like'%a%'; -- 위와 동일

-- 동등조인
-- 사원명, 급여, 부서코드(emp, dept), 부서명, 호봉을 선택하라.
select e.ename, e.sal, d.deptno, d.dname, s.grade
from emp e join dept d 
on e.deptno = d.deptno
join salgrade s
on e.sal between s.losal and s.hisal;

-- 사원번호, 사원명, 입사일, 급여, 부서명, 호봉, 호봉의 최고급여를 선택하라.
-- 단, 급여가 2500불 이상인 사원을 급여순으로 내림차순하여 선택하라.
select e.empno, e.ename, e.hiredate, e.sal, d.deptno, d.dname, s.grade, s.hisal
from emp e join dept d
on e.deptno = d.deptno
join salgrade s
on e.sal between s.losal and s.hisal
where e.sal >= 2500 
order by sal desc;









