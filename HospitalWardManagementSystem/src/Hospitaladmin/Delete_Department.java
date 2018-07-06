package Hospitaladmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import linkdatabase.linkdatabase;

public class Delete_Department {

	private String sql;
	
	public Delete_Department(String Deptname) throws SQLException {
		// TODO Auto-generated constructor stub
		Connection con = null;
		con = linkdatabase.getConnection();
		sql = "delete from Department where Deptname=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, Deptname);
		ps.executeUpdate();
	}

}
