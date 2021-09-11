create database happyhouse;

use happyhouse;

-- 공지사항 게시글을 관리하는 테이블입니다. --
create table notices (
	id int primary key auto_increment,
	title varchar(100),
    content varchar(1000),
    views int
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;