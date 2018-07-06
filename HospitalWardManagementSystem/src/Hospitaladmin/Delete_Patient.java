package Hospitaladmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import linkdatabase.linkdatabase;

public class Delete_Patient {

	private String sql;
	
	public Delete_Patient(String Pno) throws SQLException {
		// TODO Auto-generated constructor stub
		Connection con = null;
		con = linkdatabase.getConnection();
		sql = "delete from Patient where Pno=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, Pno);
		ps.executeUpdate();
	}
}
