package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Backend {
	Connection cn;
	PreparedStatement pst;

	public Backend() {
		// TODO Auto-generated constructor stub
	
	}

	public boolean Login(String username, String password) throws SQLException {
		
		pst = cn.prepareStatement("select * from users where username = ? and hashed=?");
		pst.setString(1, username);
		pst.setString(2, password);
	    ResultSet rs = pst.executeQuery();
	    
	    if(rs.next()) {
	    	//user exists
	    	return true;
	    }else {
	    	return false;
	    }
	}
	

	public boolean Register(String name, String email, String password,String safepass) throws SQLException {
		pst = cn.prepareStatement("insert into users values(?,?,?,?,?)");
		pst.setString(1, null);
		pst.setString(2, name);
		pst.setString(3, email);
		pst.setString(4, password);
		pst.setString(5, safepass);
	     int rows = pst.executeUpdate();
	    
	    if(rows>0) {
	    	//user registered
	    	return true;
	    }else {
	    	return false;
	    }
	}
	
	public void Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn = DriverManager.getConnection("jdbc:mysql://localhost/Hashing","root","");
	}
}
