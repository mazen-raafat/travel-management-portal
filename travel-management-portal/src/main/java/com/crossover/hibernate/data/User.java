package com.crossover.hibernate.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
	name = "getUserByEmail",
	query = "from User u where u.email = :email"
	)
})

@Entity
@Table(name="app_users")
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	@Column(name="user_email")
	private String email;
	@Column(name="user_password")
	private String password;
	@Column(name="user_paypallets_account_id")
	private String paypalletAccountId;
	@Column(name="user_paypallets_account_amount")
	private Double paypalletAccountAmount;
	@Column(name="user_paypallets_account_currency")
	private String paypalletAccountCurrency;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private Set<PurchasedTickets> purchasedTickets = new HashSet<PurchasedTickets>(0);

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaypalletAccountId() {
		return paypalletAccountId;
	}

	public void setPaypalletAccountId(String paypalletAccountId) {
		this.paypalletAccountId = paypalletAccountId;
	}

	public Double getPaypalletAccountAmount() {
		return paypalletAccountAmount;
	}

	public void setPaypalletAccountAmount(Double paypalletAccountAmount) {
		this.paypalletAccountAmount = paypalletAccountAmount;
	}

	public String getPaypalletAccountCurrency() {
		return paypalletAccountCurrency;
	}

	public void setPaypalletAccountCurrency(String paypalletAccountCurrency) {
		this.paypalletAccountCurrency = paypalletAccountCurrency;
	}

	public Set<PurchasedTickets> getPurchasedTickets() {
		return purchasedTickets;
	}

	public void setPurchasedTickets(Set<PurchasedTickets> purchasedTickets) {
		this.purchasedTickets = purchasedTickets;
	}

}
