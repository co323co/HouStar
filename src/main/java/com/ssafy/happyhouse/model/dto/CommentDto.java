package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : 댓글 정보", description = "게시글 id, (해당 게시글에서의) 댓글 순서, 내용, 작성자id, 작성시간을 담고있습니다")
public class CommentDto {

	@ApiModelProperty("게시글 id")
	int bid;
	@ApiModelProperty("해당 게시글에서 댓글의 순서")
	int seq;
	@ApiModelProperty("댓글 내용")
	String content;
	@ApiModelProperty("작성자 id")
	String userid;
	@ApiModelProperty("댓글 작성 시간")
	String regtime;
	
	public CommentDto() {}

	public CommentDto(int bid, int seq, String content, String userid, String regtime) {
		super();
		this.bid = bid;
		this.seq = seq;
		this.content = content;
		this.userid = userid;
		this.regtime = regtime;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	@Override
	public String toString() {
		return "CommentDto [bid=" + bid + ", seq=" + seq + ", content=" + content + ", userid=" + userid + ", regtime="
				+ regtime + "]";
	}

	
}
