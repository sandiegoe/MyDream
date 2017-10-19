package com.arex.mydream.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity")
public class Activity {

	private int aId; // 活动Id
	private int aGid;// 商品id
	private double aPrice;// 活动价格
	private String aDate; // 活动的日期
	private String aPic;// 活动的图片

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activity(int aId, int aGid, double aPrice, String aDate, String aPic) {
		super();
		this.aId = aId;
		this.aGid = aGid;
		this.aPrice = aPrice;
		this.aDate = aDate;
		this.aPic = aPic;
	}

	public Activity(int aGid, double aPrice, String aDate, String aPic) {
		super();
		this.aGid = aGid;
		this.aPrice = aPrice;
		this.aDate = aDate;
		this.aPic = aPic;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@Override
	public String toString() {
		return "Activity [aId=" + aId + ", aGid=" + aGid + ", aPrice=" + aPrice
				+ ", aDate=" + aDate + ", aPic=" + aPic + "]";
	}

}
