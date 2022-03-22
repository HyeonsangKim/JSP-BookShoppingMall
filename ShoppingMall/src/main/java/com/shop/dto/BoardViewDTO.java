package com.shop.dto;

import java.sql.Date;

public class BoardViewDTO extends BoardDTO{
	private int cmtCount;
	
	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	public BoardViewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardViewDTO(int id, String title, String writerId,String nickname, Date regdate, String hit, String files,int cmtCount) {
		super(id, title, writerId, nickname,regdate, hit, files, "");
		this.cmtCount = cmtCount;
	}
}
