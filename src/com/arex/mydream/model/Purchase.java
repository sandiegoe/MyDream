package com.arex.mydream.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "purchase")
@Entity
public class Purchase {

	private int pId; // 购买下单ID
	private int pSid; // 店面ID
	private int pGid; // 果品ID
	private int pUid; // 买家ID
	private int pNum; // 数量

	public Purchase(int pId, int pSid, int pGid, int pUid, int pNum) {
		super();
		this.pId = pId;
		this.pSid = pSid;
		this.pGid = pGid;
		this.pUid = pUid;
		this.pNum = pNum;
	}

	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getpSid() {
		return pSid;
	}

	public void setpSid(int pSid) {
		this.pSid = pSid;
	}

	public int getpGid() {
		return pGid;
	}

	public void setpGid(int pGid) {
		this.pGid = pGid;
	}

	public int getpUid() {
		return pUid;
	}

	public void setpUid(int pUid) {
		this.pUid = pUid;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	@Override
	public String toString() {
		return "Purchase [pId=" + pId + ", pSid=" + pSid + ", pGid=" + pGid
				+ ", pUid=" + pUid + ", pNum=" + pNum + "]";
	}

}
