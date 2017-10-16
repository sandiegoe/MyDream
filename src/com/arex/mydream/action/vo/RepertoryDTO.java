package com.arex.mydream.action.vo;

public class RepertoryDTO {

	private int rId;
	private int rGid;// 商品ID
	private int rSid;// 店面ID
	private int rNum;// 库存的数量
	
	private String gType;
	private String gAddress;
	private double gPrice;
	private Integer pageNo;
	private String gName;
	

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgType() {
		return gType;
	}

	public void setgType(String gType) {
		this.gType = gType;
	}

	public String getgAddress() {
		return gAddress;
	}

	public void setgAddress(String gAddress) {
		this.gAddress = gAddress;
	}

	public double getgPrice() {
		return gPrice;
	}

	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public RepertoryDTO(int rId, int rGid, int rSid, int rNum) {
		super();
		this.rId = rId;
		this.rGid = rGid;
		this.rSid = rSid;
		this.rNum = rNum;
	}

	public RepertoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getrGid() {
		return rGid;
	}

	public void setrGid(int rGid) {
		this.rGid = rGid;
	}

	public int getrSid() {
		return rSid;
	}

	public void setrSid(int rSid) {
		this.rSid = rSid;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	@Override
	public String toString() {
		return "RepertoryDTO [rId=" + rId + ", rGid=" + rGid + ", rSid=" + rSid + ", rNum=" + rNum + "]";
	}

}
