package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nonprofitsetting database table.
 * 
 */
@Entity
@NamedQuery(name="NonprofitSetting.findAll", query="SELECT n FROM NonprofitSetting n")
public class NonprofitSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Nonprofit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idNonProfit")
	private Nonprofit nonprofit;

	//uni-directional many-to-one association to Setting
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idSetting")
	private Setting setting;
	
	private boolean isActive;

	public NonprofitSetting() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
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