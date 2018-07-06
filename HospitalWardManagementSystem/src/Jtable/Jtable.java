package Jtable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import linkdatabase.linkdatabase;

public class Jtable {

	//获取行和列向量中存储的信息
	public Vector<String> getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
		Vector<String> currentRow = new Vector<String>();//定义一个向量，用于存放记录
		for(int i = 1;i <= rsmd.getColumnCount();i++) {
			currentRow.addElement(rs.getString(i));//获取记录
		}
		return currentRow;
	}

	//通过两个向量存储数据库信息
	public DefaultTableModel save_database_table(ResultSet rs) {
		Vector<Vector<?>> rows = new Vector<Vector<?>>();
		Vector<String> columnHeads = new Vector<String>();
		
		ResultSetMetaData rsmd = null;
		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for(int i = 1;i <= rsmd.getColumnCount();i++) {
				try {
					columnHeads.addElement(rsmd.getColumnName(i));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				try {
					rows.addElement(getNextRow(rs, rsmd));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(rows, columnHeads) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column) {
//				if(column == 0) 
//					return false;
//				else 
//					return true;
				return false;
			}
		};
		return defaultTableModel;
	}
	
	//病房数据表回显
	public DefaultTableModel get_Ward_table() {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = linkdatabase.getConnection().createStatement();
			String sql ="select * from Ward";
			rs = st.executeQuery(sql);
			DefaultTableModel model = save_database_table(rs);
			return model; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//床位数据表回显
	public DefaultTableModel get_Bed_table() {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = linkdatabase.getConnection().createStatement();
			String sql ="select * from Bed";
			rs = st.executeQuery(sql);
			DefaultTableModel model = save_database_table(rs);
			return model; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//科室数据表回显
	public DefaultTableModel get_Department_table() {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = linkdatabase.getConnection().createStatement();
			String sql ="select * from Department";
			rs = st.executeQuery(sql);
			DefaultTableModel model = save_database_table(rs);
			return model; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//医生数据表回显
	public DefaultTableModel get_Doctor_table() {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = linkdatabase.getConnection().createStatement();
			String sql ="select * from Doctor";
			rs = st.executeQuery(sql);
			System.out.println(st);
			System.out.println(rs);
			DefaultTableModel model = save_database_table(rs);
			System.out.println(333);
			return model; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(555);
		return null;
	}
	
	//病人数据表回显
	public DefaultTableModel get_Patient_table() {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = linkdatabase.getConnection().createStatement();
			String sql ="select * from Patient";
			rs = st.executeQuery(sql);
			DefaultTableModel model = save_database_table(rs);
			return model; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
