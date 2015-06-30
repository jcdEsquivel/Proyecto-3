package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notificationnonprofit database table.
 * 
 */
@Entity
@NamedQuery(name="Notificationnonprofit.findAll", query="SELECT n FROM Notificationnonprofit n")
public class Notificationnonprofit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Nonprofit
	@ManyToOne
	@JoinColumn(name="idNonProfit")
	private Nonprofit nonprofit;

	//bi-directional many-to-one association to Notification
	@ManyToOne
	@JoinColumn(name="idNotifcation")
	private Notification notification;

	public Notificationnonprofit() {
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

	public Notification getNotification() {
		return this.notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}