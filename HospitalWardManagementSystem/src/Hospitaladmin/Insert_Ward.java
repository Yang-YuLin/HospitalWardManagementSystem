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

public class Insert_Ward implements ActionListener {

	private JFrame frame;
	private JTextField text_Wno;
	private JTextField text_Dname;
	private JTextField text_Wcharge;
	private JButton button1 = new JButton("插入");
	private JButton button2 = new JButton("取消");
	private String sql;
	
	public Insert_Ward() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("插入病房信息");
		frame.setBounds(380,100,600,600);
		frame.getContentPane().setLayout(null);
		
		JLabel label_Wno = new JLabel("病 房 号");
		label_Wno.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Wno.setBounds(120,120,120,30);
		frame.getContentPane().add(label_Wno);
		text_Wno = new JTextField();
		text_Wno.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Wno.setBounds(240,120,130,30);
		text_Wno.setColumns(10);
		frame.getContentPane().add(text_Wno);
		
		JLabel label_Dname = new JLabel("所属科室");
		label_Dname.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Dname.setBounds(120,220,120,30);
		frame.getContentPane().add(label_Dname);
		text_Dname = new JTextField();
		text_Dname.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Dname.setBounds(240,220,130,30);
		text_Dname.setColumns(10);
		frame.getContentPane().add(text_Dname);
		
		JLabel label_Wcharge = new JLabel("收费标准");
		label_Wcharge.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Wcharge.setBounds(120,320,120,30);
		frame.getContentPane().add(label_Wcharge);
		text_Wcharge = new JTextField();
		text_Wcharge.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Wcharge.setBounds(240,320,130,30);
		text_Wcharge.setColumns(10);
		frame.getContentPane().add(text_Wcharge);
		
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
			
			String Wno = text_Wno.getText().trim();
			String Deptname = text_Dname.getText().trim();
			String Wcharge =text_Wcharge.getText().trim();
		
			try {
				if("".equals(Wno) || "".equals(Deptname) || "".equals(Wcharge)) {
					JOptionPane.showMessageDialog(null, "请输入完整信息！","系统提示",JOptionPane.ERROR_MESSAGE);
				}else {
					con =linkdatabase.getConnection();
					sql = "select Wno from Ward where Wno=?";
					try {
						ps = con.prepareStatement(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						ps.setObject(1, Wno);
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
							int a =JOptionPane.showConfirmDialog(null, "确认插入病房信息？","系统提示",JOptionPane.YES_NO_OPTION);
							if(a == JOptionPane.YES_OPTION) {
								sql="INSERT INTO Ward(Wno,Deptname,Wcharge) VALUES(?,?,?)";
								ps = con.prepareStatement(sql);
								ps.setObject(1, Wno);
								ps.setObject(2, Deptname);
								ps.setObject(3, Wcharge);
								ps.executeUpdate();
								JOptionPane.showMessageDialog(null, "插入成功！", "系统提示",JOptionPane.PLAIN_MESSAGE); 
								frame.dispose();		 
							}
						}else {
							JOptionPane.showMessageDialog(null, "该病房已经存在，无法插入!","系统提示",JOptionPane.ERROR_MESSAGE);
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
