package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transparencyreport database table.
 * 
 */
@Entity
@NamedQuery(name="Transparencyreport.findAll", query="SELECT t FROM Transparencyreport t")
public class Transparencyreport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double amountIn;

	private double amountOut;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;

	private String description;

	private String password;

	//bi-directional many-to-one association to Nonprofit
	@ManyToOne
	@JoinColumn(name="idNonProfit")
	private Nonprofit nonprofit;

	public Transparencyreport() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmountIn() {
		return this.amountIn;
	}

	public void setAmountIn(double amountIn) {
		this.amountIn = amountIn;
	}

	public double getAmountOut() {
		return this.amountOut;
	}

	public void setAmountOut(double amountOut) {
		this.amountOut = amountOut;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

}