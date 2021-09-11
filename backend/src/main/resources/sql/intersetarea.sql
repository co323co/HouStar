use happyhouse;

drop table interestarea;
create table interestarea(
	code varchar(30) primary key,
	userid varchar(100),
    
	-- foreign key (userid) references members(userid)
	-- ,foreign key (code) references baseaddress(code)
);

drop table members;

select * from interestarea;

insert into interestarea values (
(select code from baseaddress where dong ="무교동"),1);

delete from interestarea where code = 1111010200;

select dong
from baseaddress
where code in (select code from interestarea where userid = 1);





