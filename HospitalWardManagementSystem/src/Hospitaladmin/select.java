package Hospitaladmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import linkdatabase.linkdatabase;

public class select {

	public select() {
		// TODO Auto-generated constructor stub
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		
		try {
			
		
				String sql = "SELECT Dno,Dname,Dsex,Dtitle,Dage,Dtel,Deptname from Doctor";
				ps = con.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					String Dno = rs.getString(1);
					String Dname = rs.getString(2);
					String Dsex = rs.getString(3);
					String Dtitle = rs.getString(4);
					String Dage = rs.getString(5);
					String Dtel = rs.getString(6);
					String Deptname = rs.getString(7);
					//dm.addRow(new Object[]{Dno,Dname,Dsex,Dtitle,Dage,Dtel,Deptname});							
				
			}
			/*
			JTable table=new JTable(dm);
			table.setBounds(250, 15, 765, 475);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(250, 15, 765, 475);
			frame.getContentPane().add(scrollPane);
			*/
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}finally {
			linkdatabase.closeAll(rs, ps, con);
		}
		
	}

}
