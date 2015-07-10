package com.treeseed.pojo;

<<<<<<< HEAD
import java.sql.Date;

public class DonorPOJO {
	public DonorPOJO() {
		super();
	}
	private int id;

	private String name;
	
	private String lastName;
	
	private Date dateTime;
	
	private boolean isActive;
	
	private String profilePicture;
	
	private String webPage;

	private String country;

	private String description;
	
	private UserGeneralPOJO userGeneral;
	
	private String type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserGeneralPOJO getUserGeneral() {
		return userGeneral;
	}

	public void setUserGeneral(UserGeneralPOJO userGeneral) {
		this.userGeneral = userGeneral;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
=======
import com.treeseed.ejb.Donor;

public class DonorPOJO {
	
	private int id;

	private String description;

	private String lastName;

	private String name;

	private String profilePicture;

	private String webPage;
	
	private int country;
	
	private DonorPOJO father;
	
	private int type;
	
	private UserGeneralPOJO userGeneral;
	
	public UserGeneralPOJO getUserGeneral() {
	  return userGeneral;
	 }

	 public void setUserGeneral(UserGeneralPOJO userGeneral) {
	  this.userGeneral = userGeneral;
	 }
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCatalog() {
		return country;
	}

	public DonorPOJO getFatherDonor() {
		return father;
	}

	public void setFatherDonor(DonorPOJO fatherDonor) {
		this.father = fatherDonor;
	}

	public void setCatalog(int catalog) {
		this.country = catalog;
	}

	public DonorPOJO() {
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

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getWebPage() {
		return this.webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

}
>>>>>>> 7f78fdbd2956e596ee831c8915fe96ef48982f75
