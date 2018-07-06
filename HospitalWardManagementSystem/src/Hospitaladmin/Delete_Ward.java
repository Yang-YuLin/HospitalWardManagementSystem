package Hospitaladmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import linkdatabase.linkdatabase;

public class Delete_Ward {

	private String sql;
	
	public Delete_Ward(String Wno) throws SQLException {
		// TODO Auto-generated constructor stub
		Connection con = null;
		con = linkdatabase.getConnection();
		sql = "delete from Ward where Wno=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, Wno);
		ps.executeUpdate();
	}

}
