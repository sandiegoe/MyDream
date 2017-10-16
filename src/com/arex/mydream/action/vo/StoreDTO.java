package com.arex.mydream.action.vo;

public class StoreDTO {

	private int sId;
	private String sName; // 店面名字
	private String sPhone;// 店面的电话
	
	public StoreDTO(String sName, String sPhone, String sAddress, int suid) {
		super();
		this.sName = sName;
		this.sPhone = sPhone;
		this.sAddress = sAddress;
		this.suid = suid;
	}

	private String sAddress;// 店面的位置
	private int suid; // 店主ID

	public StoreDTO(int sId, String sName, String sPhone, String sAddress, int suid) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sPhone = sPhone;
		this.sAddress = sAddress;
		this.suid = suid;
	}

	public StoreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public String getsAddress() {
		return sAddress;
	}

	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	public int getSuid() {
		return suid;
	}

	public void setSuid(int suid) {
		this.suid = suid;
	}

	@Override
	public String toString() {
		return "StoreDTO [sId=" + sId + ", sName=" + sName + ", sPhone=" + sPhone + ", sAddress=" + sAddress + ", suid="
				+ suid + "]";
	}

}
