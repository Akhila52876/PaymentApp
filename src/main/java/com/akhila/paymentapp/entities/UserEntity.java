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
@Table(name="user_details")
public class UserEntity {

	 @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name="user-id")
	    private int userid;
	    @Column(name="user_name")
	    private String username;

	    @Column(name="password")
	    private String password;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "lastname")
	    private String lastName;

	    @Column(name = "phone_number")
	    private int phoneNumber;

	    @Column(name = "email")
	    private String email;

	   
	    public UserEntity(int userid, String username, String password, String firstName, String lastName,
				int phoneNumber, String email) {
			super();
			this.userid = userid;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.email = email;
		}

		@Override
		public String toString() {
			return "UserEntity [userid=" + userid + ", username=" + username + ", password=" + password + ", firstName="
					+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
		}

	
	}

	

