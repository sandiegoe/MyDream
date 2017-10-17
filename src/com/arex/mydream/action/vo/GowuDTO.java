package com.arex.mydream.action.vo;

public class GowuDTO {

	private int gId;
	private int add;
	
	private String send_to;
	private String goumaijiaru;
	
	private String zongjia;
	public String getZongjia() {
		return zongjia;
	}

	public void setZongjia(String zongjia) {
		this.zongjia = zongjia;
	}

	public double getgPrice() {
		return gPrice;
	}

	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String pNum; //商品的数量
	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

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
