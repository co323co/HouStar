-- 진료소이름|구군|주소|평일운영시간|토요일운영시간|공휴일운영시간|전화번호


-- SELECT * FROM happyhouse.corona;

CREATE TABLE `happyhouse`.`corona` (
  `cname` VARCHAR(20),
  `gungu` VARCHAR(20),
  `address` VARCHAR(50),
  `otime` VARCHAR(20),
  `stime` VARCHAR(20),
  `htime` VARCHAR(20),
  `phone` VARCHAR(20),
  PRIMARY KEY (`cname`));

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/corona.csv' INTO TABLE corona FIELDS TERMINATED BY ',';

show variables like "secure_file_priv";
