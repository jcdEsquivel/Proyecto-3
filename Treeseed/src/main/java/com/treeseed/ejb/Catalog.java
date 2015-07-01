package com.treeseed.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catalog database table.
 * 
 */
@Entity
@NamedQuery(name="Catalog.findAll", query="SELECT c FROM Catalog c")
public class Catalog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String english;

	private String name;

	private String spanish;

	private String type;

	//bi-directional many-to-one association to Donor
	@OneToMany(mappedBy="catalog1")
	private List<Donor> donors1;

	//bi-directional many-to-one association to Donor
	@OneToMany(mappedBy="catalog2")
	private List<Donor> donors2;

	//bi-directional many-to-one association to Nonprofit
	@OneToMany(mappedBy="catalog1")
	private List<Nonprofit> nonprofits1;

	//bi-directional many-to-one association to Nonprofit
	@OneToMany(mappedBy="catalog2")
	private List<Nonprofit> nonprofits2;

	public Catalog() {
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

	public String getEnglish() {
		return this.english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpanish() {
		return this.spanish;
	}

	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Donor> getDonors1() {
		return this.donors1;
	}

	public void setDonors1(List<Donor> donors1) {
		this.donors1 = donors1;
	}

	public Donor addDonors1(Donor donors1) {
		getDonors1().add(donors1);
		donors1.setCatalog1(this);

		return donors1;
	}

	public Donor removeDonors1(Donor donors1) {
		getDonors1().remove(donors1);
		donors1.setCatalog1(null);

		return donors1;
	}

	public List<Donor> getDonors2() {
		return this.donors2;
	}

	public void setDonors2(List<Donor> donors2) {
		this.donors2 = donors2;
	}

	public Donor addDonors2(Donor donors2) {
		getDonors2().add(donors2);
		donors2.setCatalog2(this);

		return donors2;
	}

	public Donor removeDonors2(Donor donors2) {
		getDonors2().remove(donors2);
		donors2.setCatalog2(null);

		return donors2;
	}

	public List<Nonprofit> getNonprofits1() {
		return this.nonprofits1;
	}

	public void setNonprofits1(List<Nonprofit> nonprofits1) {
		this.nonprofits1 = nonprofits1;
	}

	public Nonprofit addNonprofits1(Nonprofit nonprofits1) {
		getNonprofits1().add(nonprofits1);
		nonprofits1.setCatalog1(this);

		return nonprofits1;
	}

	public Nonprofit removeNonprofits1(Nonprofit nonprofits1) {
		getNonprofits1().remove(nonprofits1);
		nonprofits1.setCatalog1(null);

		return nonprofits1;
	}

	public List<Nonprofit> getNonprofits2() {
		return this.nonprofits2;
	}

	public void setNonprofits2(List<Nonprofit> nonprofits2) {
		this.nonprofits2 = nonprofits2;
	}

	public Nonprofit addNonprofits2(Nonprofit nonprofits2) {
		getNonprofits2().add(nonprofits2);
		nonprofits2.setCatalog2(this);

		return nonprofits2;
	}

	public Nonprofit removeNonprofits2(Nonprofit nonprofits2) {
		getNonprofits2().remove(nonprofits2);
		nonprofits2.setCatalog2(null);

		return nonprofits2;
	}

}