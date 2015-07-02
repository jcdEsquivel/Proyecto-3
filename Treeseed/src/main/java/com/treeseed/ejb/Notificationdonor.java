package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notificationdonor database table.
 * 
 */
@Entity
@NamedQuery(name="NotificationDonor.findAll", query="SELECT n FROM NotificationDonor n")
public class NotificationDonor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Donor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idDonor")
	private Donor donor;

	//uni-directional many-to-one association to Notification
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idNotifcation")
	private Notification notification;
	
	private boolean isActive;

	public NotificationDonor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Donor getDonor() {
		return this.donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public Notification getNotification() {
		return this.notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}