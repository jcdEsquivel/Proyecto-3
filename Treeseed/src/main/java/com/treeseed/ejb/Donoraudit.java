package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the donoraudit database table.
 * 
 */
@Entity
@NamedQuery(name="Donoraudit.findAll", query="SELECT d FROM Donoraudit d")
public class Donoraudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String action;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_time")
	private Date dataTime;

	private int donorId;

	private String entity;

	private String iP;

	private String lastState;

	private String state;

	public Donoraudit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDataTime() {
		return this.dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public int getDonorId() {
		return this.donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getIP() {
		return this.iP;
	}

	public void setIP(String iP) {
		this.iP = iP;
	}

	public String getLastState() {
		return this.lastState;
	}

	public void setLastState(String lastState) {
		this.lastState = lastState;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}