package com.arex.mydream.action.vo;

public class GoodsDTO {

	private int gId;
	private String gName;// 商品的名字
	private String gType;// 商品类型
	private Double gPrice; // 商品价格
	private String gAddress;// 商品产地
	private String gPic;// 商品图片
	private String gDescribe; // 商品描述
	
	
	private String sName;
	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	private String rnum;

	public String getRnum() {
		return rnum;
	}

	public GoodsDTO(int gId, String gName, String gType, Double gPrice, String gAddress, String gPic,
			String gDescribe) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gType = gType;
		this.gPrice = gPrice;
		this.gAddress = gAddress;
		this.gPic = gPic;
		this.gDescribe = gDescribe;
	}

	public GoodsDTO() {
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

	public String getgType() {
		return gType;
	}

	public void setgType(String gType) {
		this.gType = gType;
	}

	public Double getgPrice() {
		return gPrice;
	}

	public void setgPrice(Double gPrice) {
		this.gPrice = gPrice;
	}

	public String getgAddress() {
		return gAddress;
	}

	public void setgAddress(String gAddress) {
		this.gAddress = gAddress;
	}

	public String getgPic() {
		return gPic;
	}

	public void setgPic(String gPic) {
		this.gPic = gPic;
	}

	public String getgDescribe() {
		return gDescribe;
	}

	public void setgDescribe(String gDescribe) {
		this.gDescribe = gDescribe;
	}

	@Override
	public String toString() {
		return "GoodsDTO [gId=" + gId + ", gName=" + gName + ", gType=" + gType + ", gPrice=" + gPrice + ", gAddress="
				+ gAddress + ", gPic=" + gPic + ", gDescribe=" + gDescribe + "]";
	}

}
