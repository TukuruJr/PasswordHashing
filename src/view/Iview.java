package view;

public interface Iview {
	
	//show login status
	interface Login{
		void LoginSuccess(String message);
		void ShowHash(String message);
		void LoginFailed(String error);
	}

	//show registration status
	interface Register{
		void FieldsError(String error);
		void RegistrationSuccess(String message);
		void RegistrationFailed(String error);
		void ShowHash(String message);
	}
}
