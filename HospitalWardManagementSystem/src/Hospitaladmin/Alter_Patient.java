package Hospitaladmin;

import java.awt.Font;
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

import linkdatabase.linkdatabase;

public class Alter_Patient implements ActionListener{

	private JFrame frame;
	private JTextField textField;
	private JComboBox comboBox;
	private JButton button1 = new JButton("修改");
	private JButton button2 = new JButton("取消");
	private String Pno;
	
	public Alter_Patient(String Pno) {
		// TODO Auto-generated constructor stub
		frame = new JFrame("修改病人信息");
		frame.setBounds(380,100,550,400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("请选择需要修改的项");
		label1.setFont(new Font("宋体",Font.PLAIN, 25));
		label1.setBounds(50,60,250,40);
		frame.getContentPane().add(label1);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体",Font.PLAIN, 25));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"姓名","性别","诊断结果","病房号","病床号","主治医生工作证号","联系电话","入院日期","出院日期" }));
		comboBox.setBounds(300,60,160,40);
		frame.getContentPane().add(comboBox);
		
		JLabel label2 = new JLabel("请输入修改后的内容");
		label2.setFont(new Font("宋体",Font.PLAIN, 25));
		label2.setBounds(50,130,250,40);
		frame.getContentPane().add(label2);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体",Font.PLAIN, 25));
		textField.setBounds(300,130,160,40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		button1.setBounds(100, 220, 110, 40);
		frame.getContentPane().add(button1);
		button1.addActionListener(this);
		
		button2.setBounds(330, 220, 110, 40);
		frame.getContentPane().add(button2);
		button2.addActionListener(this);
		
		this.Pno = Pno;
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == button1) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String select = (String) comboBox.getSelectedItem();
			String alter = textField.getText().trim();
			
			try {
				con = linkdatabase.getConnection();
				int a =JOptionPane.showConfirmDialog(null, "确认修改病人信息？","系统提示",JOptionPane.YES_NO_OPTION);
				if(a == JOptionPane.YES_OPTION) {
					if(select.equals("姓名")) {
						String sql = "UPDATE Patient SET Pname = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
					if(select.equals("性别")) {
						String sql = "UPDATE Patient SET Psex = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
					if(select.equals("诊断结果")) {
						String sql = "UPDATE Patient SET Pdiagnose = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
					if(select.equals("病房号")) {
						String sql = "UPDATE Patient SET Wno = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
					if(select.equals("病床号")) {
						String sql = "UPDATE Patient SET Bno = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
					if(select.equals("主治医生工作证号")) {
						String sql = "UPDATE Patient SET Dno = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
					if(select.equals("联系电话")) {
						String sql = "UPDATE Patient SET Ptel = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
					if(select.equals("入院日期")) {
						String sql = "UPDATE Patient SET Pindate = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
					if(select.equals("出院日期")) {
						String sql = "UPDATE Patient SET Poutdate = ? where Pno = ?";
						ps = con.prepareStatement(sql);
						ps.setObject(1, alter);
						ps.setObject(2, Pno);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.ERROR_MESSAGE);
						frame.dispose();
					}
				}
			}catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "输入有误，请核实后重新输入!","系统提示",JOptionPane.ERROR_MESSAGE);
			}finally {
				linkdatabase.closeAll(rs, ps, con);
			}
		}
		if(source == button2) {
			frame.dispose();
		}
	}
}
