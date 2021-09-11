use happyhouse;

-- csv 파일 데이터 박아넣을 테이블 
create table envguidecheck(
    WRKP_NM varchar(45),			-- 업체(시설)명
    APV_PERM_MGT_NO varchar(45),	-- 인허가번호
    UPCH_COB_CODE int,				-- 업종코드
    UPCH_COB_NM varchar(45),		-- 업종명
    DRT_INSP_YMD Date,				-- 지도점검날자
    ORG_AND_TEAM_CODE varchar(30),	-- 점검기관
    SF_TEAM_NM varchar(30),			-- 점검기관명
    DRT_INSP_SE_NM varchar(10),		-- 지도점검구분
    DISPO_TGT_YN varchar(3),		-- 처분대상여부
    DRT_INSP_ITEM varchar(45), 		-- 점검사항
    DRT_INSP_RT_DTL varchar(45), 	-- 점검결과
    WRKP_NADDR varchar(100),		-- 소재지 도로명주소 
    WRKP_ADDR varchar(100) 			-- 소재지 주소
);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Jongnogu.csv'  
INTO TABLE envguidecheck 
FIELDS TERMINATED BY ','
ENCLOSED BY '"' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS;
     
create table upche(
    ucode varchar(45) primary key,	-- 인허가번호
    WRKP_NM varchar(45),			-- 업체(시설)명
    UPCH_COB_CODE int,				-- 업종코드
    UPCH_COB_NM varchar(45),		-- 업종명
    WRKP_NADDR varchar(100),		-- 소재지 도로명주소 
    WRKP_ADDR varchar(100) 			-- 소재지 주소
);

create table guidecheck(
	gcode int auto_increment primary key,
    ucode varchar(45),
    DRT_INSP_YMD Date,				-- 지도점검날자
    ORG_AND_TEAM_CODE varchar(30),	-- 점검기관
    SF_TEAM_NM varchar(30),			-- 점검기관명
    DRT_INSP_SE_NM varchar(10),		-- 지도점검구분
    DISPO_TGT_YN varchar(3),		-- 처분대상여부
    DRT_INSP_ITEM varchar(45), 		-- 점검사항
    DRT_INSP_RT_DTL varchar(45), 	-- 점검결과
	
	constraint foreign key(ucode) references upche(ucode)
);
create view envinfo_view
	as select distinct
		g.gcode code        
		,u.WRKP_NM name	
		,UPCH_COB_NM type
		,WRKP_ADDR address
	from upche u join guidecheck g on(u.ucode = g.ucode)
    group by(address);
    
select * from envguidecheck;
select * from upche;
select * from guidecheck;
insert into upche 
( 
		select APV_PERM_MGT_NO, WRKP_NM, UPCH_COB_CODE, UPCH_COB_NM, WRKP_NADDR, WRKP_ADDR
		from envguidecheck
		
) ON DUPLICATE KEY UPDATE WRKP_NADDR = VALUES(WRKP_NADDR);

insert into guidecheck(ucode, DRT_INSP_YMD ,ORG_AND_TEAM_CODE ,SF_TEAM_NM ,DRT_INSP_SE_NM ,DISPO_TGT_YN ,DRT_INSP_ITEM ,DRT_INSP_RT_DTL)
		select APV_PERM_MGT_NO, DRT_INSP_YMD ,ORG_AND_TEAM_CODE ,SF_TEAM_NM ,DRT_INSP_SE_NM ,DISPO_TGT_YN ,DRT_INSP_ITEM ,DRT_INSP_RT_DTL 
		from envguidecheck e 
		join upche u on e.APV_PERM_MGT_NO = u.ucode;

		
select * from  envinfo_view;