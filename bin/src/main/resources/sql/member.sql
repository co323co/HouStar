create table members(
	userId			varchar(100) primary key,
	userPwd			varchar(100),
	userName		varchar(100),
	userAddress		varchar(300),
    userPhone		varchar(30)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

insert into members
value ("1","1", "ayj", "경기도싸피사피","010-3381-8655");
select * from members;



