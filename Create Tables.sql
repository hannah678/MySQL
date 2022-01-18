create database mydb;
use mydb;
-- 테이블 생성하기
create table member(
-- 필드명 데이터타입 제약조건 기본값check
	num int primary key auto_increment, -- primary는 자동 not null
	username varchar(30) not null,
	tel varchar(15) not null,
    email varchar(50),
    birth date,
    gender char(1) default 'M',
    writedate datetime default now()
);
show tables;
desc member;
use mydb;

create table usetbl(
userid varchar(30) primary key,
username varchar(30) not null,
birth int not null,
local varchar(10) not null,
hiredate date,
mobile varchar(3),
mobile2 varchar(10),
height int,
writedate datetime
);
desc usetbl;

drop table usetbl;

create table usertbl(
userid varchar(30) primary key,
username varchar(30) not null,
birth int not null,
local varchar(10) not null,
hiredate date,
mobile varchar(3),
mobile2 varchar(10),
height int default 0,
writedate datetime
);
desc usertbl;


create table prodtbl(
	productname varchar(45) primary key not null,
	price int default 0,
    opendate datetime null,
    company varchar(45),
    stock int default 0
    );
    
    show tables;

create table buytbl(
buyno int primary key auto_increment,
userid varchar(30) not null,
productname varchar(50) not null,
groupname varchar(40) not null,
price int default 0,
amount int default 0,
buydate datetime default now(),
primary key (buyno)
);