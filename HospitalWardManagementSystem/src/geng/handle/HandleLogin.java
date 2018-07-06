package geng.handle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import geng.model.Login;

public class HandleLogin {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public HandleLogin() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(Exception e) {}
		
		String url="jdbc:sqlserver://localhost:1433;DatabaseName=HWMS";
		try {
			con = (Connection) DriverManager.getConnection(url,"sa","yyl13593519418");
		}catch(SQLException e) {}
	}

	public Login queryVerify(Login loginModel) {
		String Dno = loginModel.getDno();
		String Dpassword = loginModel.getDpassword();
		String sql = "select Dno,Dpassword from Doctor where Dno = ? and Dpassword = ?";
		
		try {
			System.out.println(con);
			ps = con.prepareStatement(sql);
			ps.setString(1, Dno);
			ps.setString(2, Dpassword);
			rs = ps.executeQuery();
			if(rs.next()==true) {
				loginModel.setLoginSuccess(true);
				//JOptionPane.showMessageDialog(null, "µÇÂ¼³É¹¦","¹§Ï²",JOptionPane.WARNING_MESSAGE);
			}else {
				loginModel.setLoginSuccess(false);
				//JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü","µÇÂ¼Ê§°Ü£¬ÖØÐÂµÇÂ¼",JOptionPane.WARNING_MESSAGE);
			}
			//linkdatabase.closeAll(rs, ps, con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return loginModel;
	}
	
}
