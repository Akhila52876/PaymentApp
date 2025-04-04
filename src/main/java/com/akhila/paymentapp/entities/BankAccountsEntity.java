package com.akhila.paymentapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="BankAccounts")

public class BankAccountsEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bank_account_id")
	private int bankaccountid;
	@Column(name="bank_account_no")
	private String bankaccountno;
	@Column(name="ifsc")
	private String ifsc;
	@Column(name="bank_name")
	private String bankname;
	@Column(name="bank_branch")
	private String branch;
	@Column(name="isactive")
	private String isActive;
	
	@Override
	public String toString() {
		return "BankAccountsEntity [bankaccountid=" + bankaccountid + ", bankaccountno=" + bankaccountno + ", ifsc="
				+ ifsc + ", bankname=" + bankname + ", branch=" + branch + ", isActive=" + isActive + "]";
	}

	
}
