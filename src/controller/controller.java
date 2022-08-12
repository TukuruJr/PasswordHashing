package controller;

import java.sql.SQLException;

import database.Backend;
import model.User;
import view.Iview;

public class controller implements Icontroller {
	
	private Iview.Login lview;
	private Iview.Register rview;
	private Backend bk;
	

	public void setLview(Iview.Login lview) {
		this.lview = lview;
	}

	public void setRview(Iview.Register rview) {
		this.rview = rview;
	}

	public controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onLogin(String username, String password) {
		// TODO Auto-generated method stub
		bk = new Backend();
		try {
			bk.Connect();
			User user = new User();
			
			//update model
			user.setUsername(username);
			user.setPassword(password);
				if(bk.Login(user.getUsername(), user.getPassword())) {
					lview.ShowHash("Your Input Password Hash -> "+user.getPassword());
					lview.LoginSuccess("Hash matches"+"\n"+"Login Success!!");
				}else {
					lview.ShowHash("Login Attempt Input Password Hash -> "+user.getPassword());
					lview.LoginFailed("Hash didn't match "+"\n"+"Login Failed");
				}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onRegister(String username, String email, String password, String safepass) {
		// TODO Auto-generated method stub
		bk = new Backend();
		try {
			//connect to the db
			bk.Connect();
			User user = new User(username,email, password, safepass); //update our model class
			
			switch(user.isValid()) {
			case 0:
				rview.FieldsError("Fill in all Fields!!");
				break;
			case 1:
				rview.FieldsError("Invalid Email!!");
				break;
			case 2:
				//do register
				if(bk.Register(user.getUsername(),user.getEmail(),user.getPassword(),user.getSafepass())) {
					rview.RegistrationSuccess("Registration Successful"); //show JOPtionPane message to the user
					rview.ShowHash("Password Input -> "+user.getPassword()+"\n"+"Your Hashed  Password -> "+user.getSafepass());
				}else {
					rview.RegistrationFailed("Failed to Register!!");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
