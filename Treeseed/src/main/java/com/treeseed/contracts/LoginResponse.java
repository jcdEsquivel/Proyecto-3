package com.treeseed.contracts;


public class LoginResponse extends BaseResponse {
	
	private int idSession;
	private int idUser;
	private String type;
	private String name;
	private String firstName;
	private String lastName;
	private String img;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public LoginResponse() {
		super();
	}
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUsuario) {
		this.idUser = idUsuario;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	

}
