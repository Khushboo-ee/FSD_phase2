package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModelLogin {
	private String url = "jdbc:mysql://localhost:3305/p2_project1";
	private String user="root";
	private String pwd ="root";
	private Connection con;
	private ResultSet  res;
	
	String name;
	String password;
	String username;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	


	public ModelLogin() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}



	public boolean verify() {
		try {

			String sql = "select * from admin where password=? and username=?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, password);
			pstmt.setString(2, username);
			res = pstmt.executeQuery();


			while(res.next()==true) {
				password = res.getString("password");
				username = res.getString("username");
				return true;
			} 
			return false;
		}
		catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

}
