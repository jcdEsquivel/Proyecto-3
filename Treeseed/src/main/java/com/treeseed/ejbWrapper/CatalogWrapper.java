package com.treeseed.ejbWrapper;

import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;

public class CatalogWrapper extends ParentUserWrapper {
	
	private Catalog wrapperObject;

	public CatalogWrapper() {
		super();
		setWrapperObject(new Catalog());
	}
	
	public CatalogWrapper(int id, String description, String english, String name,
			String spanish, String type, boolean isActive) {
		super();		
		this.setWrapperObject(new Catalog());
		this.setId(id);
		this.setDescription(description);
		this.setEnglish(english);
		this.setName(name);
		this.setSpanish(spanish);
		this.setType(type);
		this.setActive(isActive);
	}


	public CatalogWrapper(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	
	public Catalog getWrapperObject() {
		return wrapperObject;
	}

	private void setWrapperObject(Catalog catalog) {
		this.wrapperObject = catalog;
	}

	public int getId() {
		return wrapperObject.getId();
	}

	public void setId(int id) {
		wrapperObject.setId(id);
	}

	public String getDescription() {
		return wrapperObject.getDescription();
	}

	public void setDescription(String description) {
		wrapperObject.setDescription(description);
	}

	public String getEnglish() {
		return wrapperObject.getEnglish();
	}

	public void setEnglish(String english) {
		wrapperObject.setEnglish(english);
	}

	public String getName() {
		return wrapperObject.getName();
	}

	public void setName(String name) {
		wrapperObject.setName(name);
	}

	public int hashCode() {
		return wrapperObject.hashCode();
	}

	public String getSpanish() {
		return wrapperObject.getSpanish();
	}

	public void setSpanish(String spanish) {
		wrapperObject.setSpanish(spanish);
	}

	public String getType() {
		return wrapperObject.getType();
	}

	public void setType(String type) {
		wrapperObject.setType(type);
	}

	public boolean isActive() {
		return wrapperObject.isActive();
	}

	public void setActive(boolean isActive) {
		wrapperObject.setActive(isActive);
	}

	public boolean equals(Object obj) {
		return wrapperObject.equals(obj);
	}

	public String toString() {
		return wrapperObject.toString();
	}
	
	

}
