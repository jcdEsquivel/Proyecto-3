package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the donorsetting database table.
 * 
 */
@Entity
@NamedQuery(name="Donorsetting.findAll", query="SELECT d FROM Donorsetting d")
public class Donorsetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String value;

	//bi-directional many-to-one association to Donor
	@ManyToOne
	@JoinColumn(name="idDonor")
	private Donor donor;

	//bi-directional many-to-one association to Setting
	@ManyToOne
	@JoinColumn(name="idSetting")
	private Setting setting;

	public Donorsetting() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Donor getDonor() {
		return this.donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public Setting getSetting() {
		return this.setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

}