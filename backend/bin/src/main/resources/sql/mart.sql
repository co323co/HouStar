상가업소번호|상호명|지점명|상권업종대분류코드|상권업종대분류명|상권업종중분류코드|상권업종중분류명|상권업종소분류코드|상권업종소분류명|표준산업분류코드|표준산업분류명|시도코드|시도명|시군구코드|시군구명|행정동코드|행정동명|법정동코드|법정동명|지번코드|대지구분코드|대지구분명|지번본번지|지번부번지|지번주소|도로명코드|도로명|건물본번지|건물부번지|건물관리번호|건물명|도로명주소|구우편번호|신우편번호|동정보|층정보|호정보|경도|위도


create table mart(
	a			varchar(100) NULL,
    b			varchar(100) NULL,
    c			varchar(100) NULL,
    d			varchar(100) NULL,
    e			varchar(100) NULL,
    f			varchar(100) NULL,
    g			varchar(100) NULL,
    h			varchar(100) NULL,
    i			varchar(100) NULL,
    j			varchar(100) NULL,
    k			varchar(100) NULL,
    l			varchar(100) NULL,
    m			varchar(100) NULL,
    n			varchar(100) NULL,
    o			varchar(100) NULL,
    p			varchar(100) NULL,
    q			varchar(100) NULL,
    r			varchar(100) NULL,
    s			varchar(100) NULL,
    t			varchar(100) NULL,
    u			varchar(100) NULL,
    v			varchar(100) NULL,
    w			varchar(100) NULL,
    x			varchar(100) NULL,
    y			varchar(100) NULL,
    z			varchar(100) NULL,
    aa			varchar(100) NULL,
    bb			varchar(100) NULL,
    cc			varchar(100) NULL,
    dd			varchar(100) NULL,
    ee			varchar(100) NULL,
    ff			varchar(100) NULL,
    gg			varchar(100) NULL,
    hh			varchar(100) NULL,
    ii			varchar(100) NULL,
    jj			varchar(100) NULL,
    kk			varchar(100) NULL,
    ll			varchar(100) NULL,
    mm			varchar(100) NULL
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;


LOAD DATA local INFILE 'C:/Users/ayj/Desktop/mart/se.csv'
INTO TABLE mart
FIELDS TERMINATED BY '|'
ENCLOSED BY '"'
IGNORE 1 ROWS;

select * from mart;


