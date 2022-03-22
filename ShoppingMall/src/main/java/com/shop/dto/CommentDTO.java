package com.shop.dto;

public class CommentDTO {
	private int id;
	private String content;
	private String regdate;
	private String writerId;
	private int noticeId;
	private int ngroup;
	private int step;
	private int indent;
	
	public CommentDTO() {
		
	}
	
	public CommentDTO(int id, String content, String regdate, String writerId, int noticeId, int ngroup, int step,
			int indent) {
		
		this.id = id;
		this.content = content;
		this.regdate = regdate;
		this.writerId = writerId;
		this.noticeId = noticeId;
		this.ngroup = ngroup;
		this.step = step;
		this.indent = indent;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public int getNgroup() {
		return ngroup;
	}
	public void setNgroup(int ngroup) {
		this.ngroup = ngroup;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
}
