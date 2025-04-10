package com.akhila.paymentapp.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccountsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private int bankAccountId;

    @Column(name = "bank_account_no", nullable = false)
    private String bankAccountNo;

    @Column(name = "ifsc", nullable = false)
    private String ifscCode;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "bank_branch", nullable = false)
    private String branchName;

    @Column(name = "is_active")
    private String isActive;
    
    @Column(name="current_balance",nullable=false)
    private Double currentbalance;

    // Many bank accounts can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

	

    // --- Getters and Setters ---

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Double getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(Double currentbalnce) {
		this.currentbalance = currentbalnce;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

   
    @Override
    public String toString() {
        return "BankAccountsEntity [bankAccountId=" + bankAccountId 
                + ", bankAccountNo=" + bankAccountNo 
                + ", ifscCode=" + ifscCode 
                + ", bankName=" + bankName 
                + ", branchName=" + branchName 
                + ", isActive=" + isActive 
                + ", currentbalance=" +currentbalance
                + ", userId=" + (user != null ? user.getUserid() : "null") + "]";
    }
}
