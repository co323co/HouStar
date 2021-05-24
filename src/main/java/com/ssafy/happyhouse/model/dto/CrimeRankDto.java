package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CrimeRankDto : 안전등급 저장 등급이 높을수록 좋지 않다.", description = "traffic_accident : 교통사고, fire : 화재, crime : 범죄, lifesafety : 생활안전, suicid : 자살, epidemic : 감염병")
public class CrimeRankDto {
	
	@ApiModelProperty("traffic_accident")
	private  int traffic_accident;
	@ApiModelProperty("fire")
	private  int fire;
	@ApiModelProperty("crime")
	private  int crime;
	@ApiModelProperty("lifesafety")
	private  int lifesafety;
	@ApiModelProperty("suicid")
	private  int suicid;
	@ApiModelProperty("epidemic")
	private  int epidemic;
	
	public CrimeRankDto() {
		super();
	}

	public CrimeRankDto(int traffic_accident, int fire, int crime, int lifesafety, int suicid, int epidemic) {
		super();
		this.traffic_accident = traffic_accident;
		this.fire = fire;
		this.crime = crime;
		this.lifesafety = lifesafety;
		this.suicid = suicid;
		this.epidemic = epidemic;
	}
	public int getTraffic_accident() {
		return traffic_accident;
	}
	public void setTraffic_accident(int traffic_accident) {
		this.traffic_accident = traffic_accident;
	}
	public int getFire() {
		return fire;
	}
	public void setFire(int fire) {
		this.fire = fire;
	}
	public int getCrime() {
		return crime;
	}
	public void setCrime(int crime) {
		this.crime = crime;
	}
	public int getLifesafety() {
		return lifesafety;
	}
	public void setLifesafety(int lifesafety) {
		this.lifesafety = lifesafety;
	}
	public int getSuicid() {
		return suicid;
	}
	public void setSuicid(int suicid) {
		this.suicid = suicid;
	}
	public int getEpidemic() {
		return epidemic;
	}
	public void setEpidemic(int epidemic) {
		this.epidemic = epidemic;
	}
	@Override
	public String toString() {
		return "CrimeRankDto [traffic_accident=" + traffic_accident + ", fire=" + fire + ", crime=" + crime
				+ ", lifesafety=" + lifesafety + ", suicid=" + suicid + ", epidemic=" + epidemic + "]";
	}
	
	
	
}
