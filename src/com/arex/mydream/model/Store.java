package com.arex.mydream.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store {

	private int sId;
	private String sName; // 店面名字
	private String sPhone;// 店面的电话
	private String sAddress;// 店面的位置
	private int suid; // 店主ID

	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Store(String sName, String sPhone, String sAddress, int suid) {
		super();
		this.sName = sName;
		this.sPhone = sPhone;
		this.sAddress = sAddress;
		this.suid = suid;
	}
	
	public Store(int sId, String sName, String sPhone, String sAddress, int suid) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sPhone = sPhone;
		this.sAddress = sAddress;
		this.suid = suid;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
		return "Store [sId=" + sId + ", sName=" + sName + ", sPhone=" + sPhone
				+ ", sAddress=" + sAddress + ", suid=" + suid + "]";
	}

}
