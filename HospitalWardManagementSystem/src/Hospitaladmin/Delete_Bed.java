package Hospitaladmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import linkdatabase.linkdatabase;

public class Delete_Bed {

	private String sql;
	
	public Delete_Bed(String Wno,String Bno) throws SQLException {
		// TODO Auto-generated constructor stub
		Connection con = null;
		con = linkdatabase.getConnection();
		sql = "delete from Bed where Wno=? and Bno=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, Wno);
		ps.setObject(2, Bno);
		ps.executeUpdate();
	}

}
