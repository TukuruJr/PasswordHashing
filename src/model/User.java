package model;

public class User {
	private String username,email,password,safepass;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String username, String email, String password, String safepass) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.safepass = safepass;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setSafepass(String safepass) {
		this.safepass = safepass;
	}


	public String getSafepass() {
		return safepass;
	}


	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public int isValid() {
		if(username.isBlank()||email.isBlank()||password.isBlank()) {
			return 0;
		}else if(!email.contains("@") && !email.endsWith(".com")) {
			return 1;
		}
		else {
			return 2;
		}
	}

}
