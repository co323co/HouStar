-- 게시판, 댓글 관리
-- board, board_name, comment

create database IF NOT EXISTS happyhouse;
use happyhouse;

DROP TABLE IF EXISTS board_name;
create table board_name (
	gubun Integer auto_increment,
    name varchar(100),
    constraint primary key(gubun)
);

DROP TABLE IF EXISTS board;
create table board	(
    id Integer auto_increment,
	title varchar(100),
    content varchar(1000),
    userid varchar(100),
    regtime date,
    views Integer,
    gubun Integer,
    constraint primary key(id),
	constraint foreign key(userid) references members(userid),
    constraint foreign key(gubun) references board_name(gubun)
);

DROP TABLE IF EXISTS comment;
create table comment(
	bid integer,
	seq integer, -- 해당 게시글에서의 댓글 순서
    content varchar(1000),
    userid varchar(100),
    regtime date,
    constraint primary key(bid, seq),
    constraint foreign key(userid) references members(userid),
    constraint foreign key(bid) references board(id)
);
