package com.arex.mydream.action.vo;

public class CommentDTO {

	private int cId;
	private String cGname;// 评论商品名字
	private String cContent;// 评论的内容
	private String cDate;// 评论的时间
	private String cIp;// 评论的IP
	private String cAuthor;// 评论的作者

	public CommentDTO() {
		// TODO Auto-generated constructor stub
	}

	public CommentDTO(int cId, String cGname, String cContent, String cDate,
			String cIp, String cAuthor) {
		super();
		this.cId = cId;
		this.cGname = cGname;
		this.cContent = cContent;
		this.cDate = cDate;
		this.cIp = cIp;
		this.cAuthor = cAuthor;
	}

	public CommentDTO(String cGname, String cContent, String cDate, String cIp,
			String cAuthor) {
		super();
		this.cGname = cGname;
		this.cContent = cContent;
		this.cDate = cDate;
		this.cIp = cIp;
		this.cAuthor = cAuthor;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcGname() {
		return cGname;
	}

	public void setcGname(String cGname) {
		this.cGname = cGname;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getcIp() {
		return cIp;
	}

	public void setcIp(String cIp) {
		this.cIp = cIp;
	}

	public String getcAuthor() {
		return cAuthor;
	}

	public void setcAuthor(String cAuthor) {
		this.cAuthor = cAuthor;
	}

	@Override
	public String toString() {
		return "Comment   cId=" + cId + ", cGname=" + cGname + ", cContent="
				+ cContent + ", cDate=" + cDate + ", cIp=" + cIp + ", cAuthor="
				+ cAuthor + "   ";
	}

}
