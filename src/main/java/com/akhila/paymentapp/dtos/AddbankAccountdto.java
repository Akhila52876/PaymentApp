package com.akhila.paymentapp.dtos;

public class AddbankAccountdto {


	private String bankAccountNo;
	private String ifscCode;
	private String bankName;
	private String branchName;
	private String isActive;
	private Double currentbalance;
	private int userid;
	
	

	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public AddbankAccountdto(String bankAccountNo, String ifscCode, String bankName, String branchName,
			String isActive ,Double currentbalance) {
		super();
		this.bankAccountNo = bankAccountNo;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.branchName = branchName;
		this.isActive = isActive;
		this.currentbalance=currentbalance;
	}
	
	
	public AddbankAccountdto() {
		
	}


	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Double getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(Double currentbalance) {
		this.currentbalance = currentbalance;
	}
	
	
}
