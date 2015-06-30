package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cause database table.
 * 
 */
@Entity
@NamedQuery(name="Cause.findAll", query="SELECT c FROM Cause c")
public class Cause implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Nonprofit
	@OneToMany(mappedBy="causeBean")
	private List<Nonprofit> nonprofits;

	public Cause() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Nonprofit> getNonprofits() {
		return this.nonprofits;
	}

	public void setNonprofits(List<Nonprofit> nonprofits) {
		this.nonprofits = nonprofits;
	}

	public Nonprofit addNonprofit(Nonprofit nonprofit) {
		getNonprofits().add(nonprofit);
		nonprofit.setCauseBean(this);

		return nonprofit;
	}

	public Nonprofit removeNonprofit(Nonprofit nonprofit) {
		getNonprofits().remove(nonprofit);
		nonprofit.setCauseBean(null);

		return nonprofit;
	}

}