use happyhouse;

create table board_name (
	gubun Integer auto_increment,
    name varchar(100),
    constraint primary key(gubun)
);

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

create table comment(
	bid integer,
	seq integer,
    content varchar(1000),
    constraint primary key(bid, seq),
    constraint foreign key(bid) references board(id)
);

insert into board_name(name) values ("공지사항");
insert into board_name(name) values ("QnA");

insert into board (title, content, userid, regtime, views, gubun)
		values ("게시글1", "내용", "co323co", now(), 0, 1);
        
select id, title, content, userid, regtime, views, gubun
		from board where gubun="1";
        
select id, title, content, userid, regtime, views, gubun
from board
where gubun = 1 and title like concat('%', "게시글", '%')
order by id desc;
        