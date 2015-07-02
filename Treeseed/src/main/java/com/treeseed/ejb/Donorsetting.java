package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the donorsetting database table.
 * 
 */
@Entity
@NamedQuery(name="DonorSetting.findAll", query="SELECT d FROM DonorSetting d")
public class DonorSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String value;
	
	private boolean isActive;

	//bi-directional many-to-one association to Donor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idDonor")
	private Donor donor;

	//uni-directional many-to-one association to Setting
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idSetting")
	private Setting setting;

	public DonorSetting() {
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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}