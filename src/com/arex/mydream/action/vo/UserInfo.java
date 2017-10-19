package com.arex.mydream.action.vo;

public class UserInfo {

	private int uId; // 用户ID
	private String uName; // 用户名
	private String uPwd; // 用户密码
	private String uPwd1;//确认密码
	private String sex; // 性别
	private String uBir; // 生日
	private String uPhone; // 电话
	private String uMail; // 邮箱
	private String uAddress; // 常用地址
	private String uFlag; // 权限 普通用户 卖家 后台管理
	private String uLevel;// 会员等级
	private String rand;//验证码
	
	private int pageNo;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getuBir() {
		return uBir;
	}
	public void setuBir(String uBir) {
		this.uBir = uBir;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuMail() {
		return uMail;
	}
	public void setuMail(String uMail) {
		this.uMail = uMail;
	}
	public String getuAddress() {
		return uAddress;
	}
	public void setuAddress(String uAddress) {
		this.uAddress = uAddress;
	}
	public String getuFlag() {
		return uFlag;
	}
	public void setuFlag(String uFlag) {
		this.uFlag = uFlag;
	}
	public String getuLevel() {
		return uLevel;
	}
	public void setuLevel(String uLevel) {
		this.uLevel = uLevel;
	}
	public String getuPwd1() {
		return uPwd1;
	}
	public void setuPwd1(String uPwd1) {
		this.uPwd1 = uPwd1;
	}
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
}
