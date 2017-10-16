package com.arex.mydream.action.vo;

public class GowuItem {

	private int gId;
	private String gName;
	private Double gPrice;
	private String gPic;
	private int pNum;
	
	public GowuItem(int gId, String gName, Double gPrice, String gPic, int pNum) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gPrice = gPrice;
		this.gPic = gPic;
		this.pNum = pNum;
	}

	public GowuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public Double getgPrice() {
		return gPrice;
	}

	public void setgPrice(Double gPrice) {
		this.gPrice = gPrice;
	}

	public String getgPic() {
		return gPic;
	}

	public void setgPic(String gPic) {
		this.gPic = gPic;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	@Override
	public String toString() {
		return "GowuItem [gId=" + gId + ", gName=" + gName + ", gPrice="
				+ gPrice + ", gPic=" + gPic + ", pNum=" + pNum + "]";
	}

}
