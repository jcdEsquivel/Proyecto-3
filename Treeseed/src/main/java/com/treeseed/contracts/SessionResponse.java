package com.treeseed.contracts;


public class SessionResponse extends BaseResponse {
	
	private int idSession;
	private int idUserType;
	private String type;

	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public SessionResponse() {
		super();
	}
	
	public int getIdUserType() {
		return idUserType;
	}

	public void setIdUserType(int idUsuarioType) {
		this.idUserType = idUsuarioType;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	

}
