package com.arex.mydream.action.vo;

public class GowuDTO {

	private int gId;
	private int add;
	
	private String send_to;
	private String goumaijiaru;
	
	private String zongjia;
	private String num; //商品的数量
	private double gPrice;
	private String date;

	public String getSend_to() {
		return send_to;
	}

	public void setSend_to(String send_to) {
		this.send_to = send_to;
	}

	public String getGoumaijiaru() {
		return goumaijiaru;
	}

	public void setGoumaijiaru(String goumaijiaru) {
		this.goumaijiaru = goumaijiaru;
	}

	public GowuDTO(int gId, int add) {
		super();
		this.gId = gId;
		this.add = add;
	}

	public GowuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public int getAdd() {
		return add;
	}

	public void setAdd(int add) {
		this.add = add;
	}

	@Override
	public String toString() {
		return "GowuDTO [gId=" + gId + ", add=" + add + "]";
	}

}
