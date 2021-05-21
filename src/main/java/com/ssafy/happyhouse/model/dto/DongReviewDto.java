package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DongReviewDto : 동 리뷰 저장", description = "dongcode, userid, (인프라, 대중교통, 안전, 건강, 학군, 환경) 에 대한 평점, 리뷰내용 을 담고 있습니다.")
public class DongReviewDto {
	
	@ApiModelProperty("dongcode")
	private String dongcode;
	@ApiModelProperty("user id")
	private String userid;
	@ApiModelProperty("인프라 평점")
	private int infra; 
	@ApiModelProperty("대중교통 평점")
	private int trans;
	@ApiModelProperty("안전 평점")
	private int safety;
	@ApiModelProperty("건강 평점")
	private int health;
	@ApiModelProperty("학군 평점")
	private int school;
	@ApiModelProperty("환경 평점")
	private int environment;
	@ApiModelProperty("리뷰 내용")
	private String content;

	
	public DongReviewDto() {}

	public DongReviewDto(String dongcode, String userid, int infra, int trans, int safety, int health, int school,
			int environment, String content) {
		super();
		this.dongcode = dongcode;
		this.userid = userid;
		this.infra = infra;
		this.trans = trans;
		this.safety = safety;
		this.health = health;
		this.school = school;
		this.environment = environment;
		this.content = content;
	}


	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getInfra() {
		return infra;
	}
	public void setInfra(int infra) {
		this.infra = infra;
	}
	public int getTrans() {
		return trans;
	}
	public void setTrans(int trans) {
		this.trans = trans;
	}
	public int getSafety() {
		return safety;
	}
	public void setSafety(int safety) {
		this.safety = safety;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getSchool() {
		return school;
	}
	public void setSchool(int school) {
		this.school = school;
	}
	public int getEnvironment() {
		return environment;
	}
	public void setEnvironment(int environment) {
		this.environment = environment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "DongReviewDto [dongcode=" + dongcode + ", userid=" + userid + ", infra=" + infra + ", trans=" + trans
				+ ", safety=" + safety + ", health=" + health + ", school=" + school + ", environment=" + environment
				+ ", content=" + content + "]";
	}


}
