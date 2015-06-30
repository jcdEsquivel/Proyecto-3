package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usergeneral database table.
 * 
 */
@Entity
@NamedQuery(name="Usergeneral.findAll", query="SELECT u FROM Usergeneral u")
public class Usergeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private byte isActive;

	private String password;

	//bi-directional many-to-one association to Donor
	@ManyToOne
	@JoinColumn(name="idDonor")
	private Donor donor;

	//bi-directional many-to-one association to Nonprofit
	@ManyToOne
	@JoinColumn(name="idNonProfit")
	private Nonprofit nonprofit;

	public Usergeneral() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Donor getDonor() {
		return this.donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

}