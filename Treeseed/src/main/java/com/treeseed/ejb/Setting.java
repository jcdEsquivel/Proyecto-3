package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the setting database table.
 * 
 */
@Entity
@NamedQuery(name="Setting.findAll", query="SELECT s FROM Setting s")
public class Setting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String setting;

	private String settingGroup;

	//bi-directional many-to-one association to Donorsetting
	@OneToMany(mappedBy="setting")
	private List<Donorsetting> donorsettings;

	//bi-directional many-to-one association to Nonprofitsetting
	@OneToMany(mappedBy="setting")
	private List<Nonprofitsetting> nonprofitsettings;

	public Setting() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSetting() {
		return this.setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public String getSettingGroup() {
		return this.settingGroup;
	}

	public void setSettingGroup(String settingGroup) {
		this.settingGroup = settingGroup;
	}

	public List<Donorsetting> getDonorsettings() {
		return this.donorsettings;
	}

	public void setDonorsettings(List<Donorsetting> donorsettings) {
		this.donorsettings = donorsettings;
	}

	public Donorsetting addDonorsetting(Donorsetting donorsetting) {
		getDonorsettings().add(donorsetting);
		donorsetting.setSetting(this);

		return donorsetting;
	}

	public Donorsetting removeDonorsetting(Donorsetting donorsetting) {
		getDonorsettings().remove(donorsetting);
		donorsetting.setSetting(null);

		return donorsetting;
	}

	public List<Nonprofitsetting> getNonprofitsettings() {
		return this.nonprofitsettings;
	}

	public void setNonprofitsettings(List<Nonprofitsetting> nonprofitsettings) {
		this.nonprofitsettings = nonprofitsettings;
	}

	public Nonprofitsetting addNonprofitsetting(Nonprofitsetting nonprofitsetting) {
		getNonprofitsettings().add(nonprofitsetting);
		nonprofitsetting.setSetting(this);

		return nonprofitsetting;
	}

	public Nonprofitsetting removeNonprofitsetting(Nonprofitsetting nonprofitsetting) {
		getNonprofitsettings().remove(nonprofitsetting);
		nonprofitsetting.setSetting(null);

		return nonprofitsetting;
	}

}