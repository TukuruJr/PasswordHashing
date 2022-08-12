package controller;

public interface Icontroller {
	
	void onLogin(String username, String password);
	void onRegister(String username, String email, String password, String safepass);

}
