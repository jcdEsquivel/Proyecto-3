package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notificationdonor database table.
 * 
 */
@Entity
@NamedQuery(name="Notificationdonor.findAll", query="SELECT n FROM Notificationdonor n")
public class Notificationdonor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Donor
	@ManyToOne
	@JoinColumn(name="idDonor")
	private Donor donor;

	//bi-directional many-to-one association to Notification
	@ManyToOne
	@JoinColumn(name="idNotifcation")
	private Notification notification;

	public Notificationdonor() {
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

}