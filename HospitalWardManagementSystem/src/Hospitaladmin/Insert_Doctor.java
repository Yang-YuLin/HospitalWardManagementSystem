package Hospitaladmin;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import linkdatabase.linkdatabase;

public class Insert_Doctor implements ActionListener{

	private JFrame frame;
	private JTextField text_No;
	private JTextField text_Password;
	private JTextField text_Name;
	private JComboBox comboBox_Sex;
	private JComboBox comboBox_Title;
	private JTextField text_Age;
	private JTextField text_Tel;
	private JComboBox comboBox_Dept;
	private JButton button1 = new JButton("插入");
	private JButton button2 = new JButton("取消");
	private String sql;
	
	public Insert_Doctor() {
		// TODO Auto-generated constructor stub
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new JFrame("插入医生信息");
		frame.setBounds(380,100,600,600);
		frame.getContentPane().setLayout(null);
		
		JLabel label_No = new JLabel("工作证号");
		label_No.setFont(new Font("宋体", Font.PLAIN, 25));
		label_No.setBounds(120, 50, 100, 30);
		frame.getContentPane().add(label_No);
		text_No = new JTextField();
		text_No.setFont(new Font("宋体", Font.PLAIN, 20));
		text_No.setBounds(240, 50, 130, 30);
		frame.getContentPane().add(text_No);;
		text_No.setColumns(10);
		
		JLabel label_Password = new JLabel("密    码");
		label_Password.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Password.setBounds(120,100,120,30);
		frame.getContentPane().add(label_Password);
		text_Password = new JTextField();
		text_Password.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Password.setBounds(240,100,130,30);
		frame.getContentPane().add(text_Password);
		text_Password.setColumns(10);
		
		JLabel label_Name = new JLabel("姓    名");
		label_Name.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Name.setBounds(120,150,120,30);
		frame.getContentPane().add(label_Name);
		text_Name = new JTextField();
		text_Name.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Name.setBounds(240,150,130,30);
		frame.getContentPane().add(text_Name);
		text_Name.setColumns(10);
		
		JLabel label_Sex = new JLabel("性    别");
		label_Sex.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Sex.setBounds(120,200,120,30);
		frame.getContentPane().add(label_Sex);
		comboBox_Sex = new JComboBox();
		comboBox_Sex.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_Sex.setModel(new DefaultComboBoxModel(new String[] {"男","女"}));
		comboBox_Sex.setToolTipText("男");
		comboBox_Sex.setBounds(240,200,130,30);
		frame.getContentPane().add(comboBox_Sex);
		
		JLabel label_Title = new JLabel("职    称");
		label_Title.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Title.setBounds(120,250,120,30);
		frame.getContentPane().add(label_Title);
		comboBox_Title = new JComboBox();
		comboBox_Title.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_Title.setModel(new DefaultComboBoxModel(new String[] {"住院医师","主治医师","副主任医师","主任医师"}));
		comboBox_Title.setToolTipText("住院医师");
		comboBox_Title.setBounds(240,250,130,30);
		frame.getContentPane().add(comboBox_Title);
		
		JLabel label_Age = new JLabel("年    龄");
		label_Age.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Age.setBounds(120,300,120,30);
		frame.getContentPane().add(label_Age);
		text_Age = new JTextField();
		text_Age.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Age.setBounds(240,300,130,30);
		frame.getContentPane().add(text_Age);
		text_Age.setColumns(10);
		
		JLabel label_Tel = new JLabel("联系电话");
		label_Tel.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Tel.setBounds(120,350,120,30);
		frame.getContentPane().add(label_Tel);
		text_Tel = new JTextField();
		text_Tel.setFont(new Font("宋体", Font.PLAIN, 20));
		text_Tel.setBounds(240,350,130,30);
		frame.getContentPane().add(text_Tel);
		text_Tel.setColumns(10);
		
		JLabel label_Dept = new JLabel("所属科室");
		label_Dept.setFont(new Font("宋体", Font.PLAIN, 25));
		label_Dept.setBounds(120,400,120,30);
		frame.getContentPane().add(label_Dept);
		comboBox_Dept = new JComboBox();
		comboBox_Dept.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_Dept.setModel(new DefaultComboBoxModel(new String[] {"普通外科","内科","儿科","妇科","精神科"}));
		comboBox_Dept.setToolTipText("普通外科");
		comboBox_Dept.setBounds(240,400,130,30);
		frame.getContentPane().add(comboBox_Dept);
		
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
			
			String no = text_No.getText().trim();
			String password = text_Password.getText().trim();
			String name = text_Name.getText().trim();
			String age = text_Age.getText().trim();
			String tel =text_Tel.getText().trim();
			String sex = (String) comboBox_Sex.getSelectedItem();
			String title =(String) comboBox_Title.getSelectedItem();
			String deptname =(String) comboBox_Dept.getSelectedItem();
			
			try {
				if("".equals(no) || "".equals(password) || "".equals(name) || "".equals(age) || "".equals(tel) || "".equals(sex.trim())|| "".equals(title.trim()) || "".equals(deptname.trim())) {
					JOptionPane.showMessageDialog(null, "请输入完整信息！","系统提示",JOptionPane.ERROR_MESSAGE);
				}else {
					con =linkdatabase.getConnection();
					sql = "select Dno from Doctor where Dno=?";
					try {
						ps = con.prepareStatement(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						ps.setObject(1, no);
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
							int a =JOptionPane.showConfirmDialog(null, "确认插入医生信息？","系统提示",JOptionPane.YES_NO_OPTION);
							if(a == JOptionPane.YES_OPTION) {
								sql="INSERT INTO Doctor(Dno,Dpassword,Dname,Dsex,Dtitle,Dage,Dtel,Deptname) VALUES(?,?,?,?,?,?,?,?)";
								ps = con.prepareStatement(sql);
								ps.setObject(1, no);
								ps.setObject(2, password);
								ps.setObject(3, name);
								ps.setObject(4, sex);
								ps.setObject(5, title);
								ps.setObject(6, age);
								ps.setObject(7, tel);
								ps.setObject(8, deptname);
								ps.executeUpdate();
								JOptionPane.showMessageDialog(null, "插入成功！", "系统提示",JOptionPane.PLAIN_MESSAGE); 
								frame.dispose();		 
							}
						}else {
							JOptionPane.showMessageDialog(null, "该医生已经存在，无法插入!","系统提示",JOptionPane.ERROR_MESSAGE);
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
