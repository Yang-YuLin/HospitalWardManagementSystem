package Hospitaladmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import linkdatabase.linkdatabase;

public class Delete_Doctor {

	private String sql;
	
	public Delete_Doctor(String Dno) throws SQLException {
		// TODO Auto-generated constructor stub
		Connection con = null;
		con = linkdatabase.getConnection();
		sql = "delete from Doctor where Dno=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, Dno);
		ps.executeUpdate();
	}

}
