-- 시도|구군|병원이름|병원주소|병원타입|전화번호


-- SELECT * FROM happyhouse.hospital;

CREATE TABLE `happyhouse`.`hospital` (
  `sido` VARCHAR(10),
  `gungu` VARCHAR(10),
  `hname` VARCHAR(20),
  `address` VARCHAR(50),
  `type` VARCHAR(10),
  `phone` VARCHAR(20),
  PRIMARY KEY (`phone`));
  
  LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/hospital.csv' INTO TABLE hospital FIELDS TERMINATED BY ',';
  
  drop table `happyhouse`.`hospital`;