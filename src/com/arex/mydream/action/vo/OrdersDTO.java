package com.arex.mydream.action.vo;

public class OrdersDTO {

	private int oId;
	private int oPid; // 购买下单ID
	// private int oSid;//店面ID
	// private int oUid;//用户id
	private String oStatus;// 订单状态
	private String oStartdate;// 下单时间
	private String oEnddate;// 送达时间
	private String oAddress;// 订单地址
	
	private int pSid; //购买的店面Id
	private String gName;
	private String gPic;
	private double gPrice;
	private int pNum;
	private String uName;
	

	public int getpSid() {
		return pSid;
	}

	public void setpSid(int pSid) {
		this.pSid = pSid;
	}

	public OrdersDTO(int oId, int oPid, String oStatus, String oStartdate,
			String oEnddate, String oAddress) {
		super();
		this.oId = oId;
		this.oPid = oPid;
		this.oStatus = oStatus;
		this.oStartdate = oStartdate;
		this.oEnddate = oEnddate;
		this.oAddress = oAddress;
	}

	public OrdersDTO(int oPid, String oStatus, String oStartdate,
			String oEnddate, String oAddress) {
		super();
		this.oPid = oPid;
		this.oStatus = oStatus;
		this.oStartdate = oStartdate;
		this.oEnddate = oEnddate;
		this.oAddress = oAddress;
	}

	public OrdersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public int getoPid() {
		return oPid;
	}

	public void setoPid(int oPid) {
		this.oPid = oPid;
	}

	public String getoStatus() {
		return oStatus;
	}

	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}

	public String getoStartdate() {
		return oStartdate;
	}

	public void setoStartdate(String oStartdate) {
		this.oStartdate = oStartdate;
	}

	public String getoEnddate() {
		return oEnddate;
	}

	public void setoEnddate(String oEnddate) {
		this.oEnddate = oEnddate;
	}

	public String getoAddress() {
		return oAddress;
	}

	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgPic() {
		return gPic;
	}

	public void setgPic(String gPic) {
		this.gPic = gPic;
	}

	public double getgPrice() {
		return gPrice;
	}

	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	@Override
	public String toString() {
		return "OrderDTO [oId=" + oId + ", oPid=" + oPid + ", oStatus="
				+ oStatus + ", oStartdate=" + oStartdate + ", oEnddate="
				+ oEnddate + ", oAddress=" + oAddress + "]";
	}

}
