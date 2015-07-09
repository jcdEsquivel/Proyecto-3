package com.treeseed.pojo;

public class CatalogPOJO {

		private static final long serialVersionUID = 1L;

		private int id;

		private String description;

		private String english;

		private String name;

		private String spanish;

		private String type;
		
		private boolean isActive;
		
		private String message;

		public CatalogPOJO() {
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
		
		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
		
		public String getMessage() {
			return message;
		}

		public void setMessage(String menssage) {
			this.message = menssage;
		}
}
