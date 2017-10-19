package com.arex.mydream.action.vo;

public class ActivityDTO {

	private int aId; // 活动Id
	private int aGid;// 商品id
	private double aPrice;// 活动价格
	private String aDate; // 活动的日期
	private String aPic;// 活动的图片
	
	private String gName;
	private double gPrice;
	private int pageNo;

	public ActivityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityDTO(int aId, int aGid, double aPrice, String aDate,
			String aPic) {
		super();
		this.aId = aId;
		this.aGid = aGid;
		this.aPrice = aPrice;
		this.aDate = aDate;
		this.aPic = aPic;
	}

	public ActivityDTO(int aGid, double aPrice, String aDate, String aPic) {
		super();
		this.aGid = aGid;
		this.aPrice = aPrice;
		this.aDate = aDate;
		this.aPic = aPic;
	}

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public int getaGid() {
		return aGid;
	}

	public void setaGid(int aGid) {
		this.aGid = aGid;
	}

	public double getaPrice() {
		return aPrice;
	}

	public void setaPrice(double aPrice) {
		this.aPrice = aPrice;
	}

	public String getaDate() {
		return aDate;
	}

	public void setaDate(String aDate) {
		this.aDate = aDate;
	}

	public String getaPic() {
		return aPic;
	}

	public void setaPic(String aPic) {
		this.aPic = aPic;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public double getgPrice() {
		return gPrice;
	}

	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		return "ActivityDTO [aId=" + aId + ", aGid=" + aGid + ", aPrice="
				+ aPrice + ", aDate=" + aDate + ", aPic=" + aPic + "]";
	}

}
