package com.zhidisoft.manage.entity;

public class User {

	private int id;
	private String username;
	private String password;
	private int taxerId;
	private String salt;
	private int permissionId;
	private int state;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTaxerId() {
		return taxerId;
	}

	public void setTaxerId(int taxerId) {
		this.taxerId = taxerId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(int id, String username, String password, int taxerId, String salt, int permissionId, int state,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.taxerId = taxerId;
		this.salt = salt;
		this.permissionId = permissionId;
		this.state = state;
		this.email = email;
	}

	public User(String username, String password, int taxerId, String salt, int permissionId, int state, String email) {
		super();
		this.username = username;
		this.password = password;
		this.taxerId = taxerId;
		this.salt = salt;
		this.permissionId = permissionId;
		this.state = state;
		this.email = email;
	}

	public User() {
		super();
	}

}
