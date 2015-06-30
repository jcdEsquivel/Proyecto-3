package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String lenguaje;

	private String message;

	private String name;

	//bi-directional many-to-one association to Notificationdonor
	@OneToMany(mappedBy="notification")
	private List<Notificationdonor> notificationdonors;

	//bi-directional many-to-one association to Notificationnonprofit
	@OneToMany(mappedBy="notification")
	private List<Notificationnonprofit> notificationnonprofits;

	public Notification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLenguaje() {
		return this.lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Notificationdonor> getNotificationdonors() {
		return this.notificationdonors;
	}

	public void setNotificationdonors(List<Notificationdonor> notificationdonors) {
		this.notificationdonors = notificationdonors;
	}

	public Notificationdonor addNotificationdonor(Notificationdonor notificationdonor) {
		getNotificationdonors().add(notificationdonor);
		notificationdonor.setNotification(this);

		return notificationdonor;
	}

	public Notificationdonor removeNotificationdonor(Notificationdonor notificationdonor) {
		getNotificationdonors().remove(notificationdonor);
		notificationdonor.setNotification(null);

		return notificationdonor;
	}

	public List<Notificationnonprofit> getNotificationnonprofits() {
		return this.notificationnonprofits;
	}

	public void setNotificationnonprofits(List<Notificationnonprofit> notificationnonprofits) {
		this.notificationnonprofits = notificationnonprofits;
	}

	public Notificationnonprofit addNotificationnonprofit(Notificationnonprofit notificationnonprofit) {
		getNotificationnonprofits().add(notificationnonprofit);
		notificationnonprofit.setNotification(this);

		return notificationnonprofit;
	}

	public Notificationnonprofit removeNotificationnonprofit(Notificationnonprofit notificationnonprofit) {
		getNotificationnonprofits().remove(notificationnonprofit);
		notificationnonprofit.setNotification(null);

		return notificationnonprofit;
	}

}