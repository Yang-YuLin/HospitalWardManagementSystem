package Hospitaladmin;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import linkdatabase.linkdatabase;

public class Insert_Department implements ActionListener {
	
	private JFrame frame;
	private JTextField text_Name;
	private JTextField text_Address;
	private JTextField text_Tel;
	private JButton button1 = new JButton("插入");
	private JButton button2 = new JButton("取消");
	private String sql;

	public Insert_Department() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("插入科室信息");
		frame.setBounds(380,100,600,600);
		frame.getContentPane().setLayout(null);
		
		JLabel label_Name = new JLabel("科室名称");
		label_Name.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Name.setBounds(120,120,120,30);
		frame.getContentPane().add(label_Name);
		text_Name = new JTextField();
		text_Name.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Name.setBounds(240,120,130,30);
		text_Name.setColumns(10);
		frame.getContentPane().add(text_Name);
		
		JLabel label_Address = new JLabel("科室地址");
		label_Address.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Address.setBounds(120,220,120,30);
		frame.getContentPane().add(label_Address);
		text_Address = new JTextField();
		text_Address.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Address.setBounds(240,220,130,30);
		text_Address.setColumns(10);
		frame.getContentPane().add(text_Address);
		
		JLabel label_Tel = new JLabel("科室电话");
		label_Tel.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Tel.setBounds(120,320,120,30);
		frame.getContentPane().add(label_Tel);
		text_Tel = new JTextField();
		text_Tel.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Tel.setBounds(240,320,130,30);
		text_Tel.setColumns(10);
		frame.getContentPane().add(text_Tel);
		
		button1.setBounds(90, 470, 120, 40);
		frame.getContentPane().add(button1);
		button1.addActionListener(this);
		
		button2.setBounds(280, 470, 120, 40);
		frame.getContentPane().add(button2);
		button2.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == button1) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String name = text_Name.getText().trim();
			String address = text_Address.getText().trim();
			String tel =text_Tel.getText().trim();
		
			try {
				if("".equals(name) || "".equals(address) || "".equals(tel)) {
					JOptionPane.showMessageDialog(null, "请输入完整信息！","系统提示",JOptionPane.ERROR_MESSAGE);
				}else {
					con =linkdatabase.getConnection();
					sql = "select Deptname from Department where Deptname=?";
					try {
						ps = con.prepareStatement(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						ps.setObject(1, name);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						rs=ps.executeQuery();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						if(!rs.next()) {
							int a =JOptionPane.showConfirmDialog(null, "确认插入科室信息？","系统提示",JOptionPane.YES_NO_OPTION);
							if(a == JOptionPane.YES_OPTION) {
								sql="INSERT INTO Department(Deptname,Deptaddress,Depttel) VALUES(?,?,?)";
								ps = con.prepareStatement(sql);
								ps.setObject(1, name);
								ps.setObject(2, address);
								ps.setObject(3, tel);
								ps.executeUpdate();
								JOptionPane.showMessageDialog(null, "插入成功！", "系统提示",JOptionPane.PLAIN_MESSAGE); 
								frame.dispose();		 
							}
						}else {
							JOptionPane.showMessageDialog(null, "该科室已经存在，无法插入!","系统提示",JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "请输入完整信息!","系统提示",JOptionPane.ERROR_MESSAGE);
			} finally {
				linkdatabase.closeAll(rs, ps, con);
			}
		}
		if(source == button2) {
			frame.dispose();
		}
	}
}


