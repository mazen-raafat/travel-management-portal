package com.crossover.hibernate.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
	name = "getPurchasedTicketsUserId",
	query = "from PurchasedTickets t where t.user.userId = :userId"
	)
})

@Entity
@Table(name="purchased_tickets")
public class PurchasedTickets {

	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
	private User user;
	
	@Id
	@Column(name="ticket_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ticketId;
	
	@Column(name="from_place")
	private String fromPlace;
	
	@Column(name="to_place")
	private String toPlace;
	
	@Column(name="price")
	private double price;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="amount")
	private long amount;
	
	@Column(name="purchasing_date")
	private Date purchasingDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	
	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getPurchasingDate() {
		return purchasingDate;
	}

	public void setPurchasingDate(Date purchasingDate) {
		this.purchasingDate = purchasingDate;
	}

}
