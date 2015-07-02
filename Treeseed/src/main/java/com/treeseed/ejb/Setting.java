package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;


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
	
	private boolean isActive;

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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}