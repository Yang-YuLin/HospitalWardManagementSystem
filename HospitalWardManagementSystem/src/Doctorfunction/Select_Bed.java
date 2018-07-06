package Doctorfunction;

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
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import linkdatabase.linkdatabase;

public class Select_Bed extends JTable implements ActionListener{

	private JFrame frame;
	private JLabel label;
	private JComboBox comboBox;
	private JTextField textField;
	private JButton button1 = new JButton("查询");
	private JButton button2 = new JButton("取消");
	private JTable table = new JTable();
	private DefaultTableModel dm = null;
	private JScrollPane scrollPane;//滚动面板来使得需要被显示的区域显示
	private JPopupMenu popupMenu;//弹出式菜单
	
	public Select_Bed() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("查询床位信息");
		frame.setBounds(100, 100, 1050, 550);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("选择查询条件");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(15,90,210,40);
		frame.getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体",Font.PLAIN, 25));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"病房号","病床号","目前状态","全部"}));
		comboBox.setBounds(15,150,210,40);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体",Font.PLAIN, 20));
		textField.setBounds(15,220,210,40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		button1.setBounds(15,290,85,30);
		frame.getContentPane().add(button1);
		button1.addActionListener(this);
		
		button2.setBounds(140,290,85,30);
		frame.getContentPane().add(button2);
		button2.addActionListener(this);
		
		String[] columnNames={"病房号","病床号","目前状态"};
		dm = new DefaultTableModel(columnNames, 0);
		table.setModel(dm);
		scrollPane = new JScrollPane(table);
		table.setBounds(250, 15, 765, 475);
		scrollPane.setBounds(250, 15, 765, 475);
		frame.getContentPane().add(scrollPane);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String select = (String) comboBox.getSelectedItem();
		String text = textField.getText().trim();
		
		try {
			if(source == button1) {
				con = linkdatabase.getConnection();
				if(select.equals("病房号")) {
					String sql = "SELECT Wno,Bno,Bstate from Bed where Wno=? ";
					ps = con.prepareStatement(sql);
					ps.setObject(1, text);
					rs=ps.executeQuery();
					if(!rs.next()){
						JOptionPane.showMessageDialog(null, "相关信息不存在，请重新输入!","系统提示", JOptionPane.ERROR_MESSAGE);
					}
					ps.close();
					rs.close();
					
					ps = con.prepareStatement(sql);
					ps.setObject(1, text);
					rs = ps.executeQuery();
					while(rs.next()){
						String Wno = rs.getString(1);
						String Bno = rs.getString(2);
						String Bstate = rs.getString(3);
						dm.addRow(new Object[]{Wno,Bno,Bstate});							
					}
				}
			if(select.equals("病床号")) {
				String sql = "SELECT Wno,Bno,Bstate from Bed where Bno=? ";
				ps = con.prepareStatement(sql);
				ps.setObject(1, text);
				rs=ps.executeQuery();
				if(!rs.next()){
					JOptionPane.showMessageDialog(null, "相关信息不存在，请重新输入!","系统提示", JOptionPane.ERROR_MESSAGE);
				}
				ps.close();
				rs.close();
				
				ps = con.prepareStatement(sql);
				ps.setObject(1, text);
				rs = ps.executeQuery();
				while(rs.next()){
					String Wno = rs.getString(1);
					String Bno = rs.getString(2);
					String Bstate = rs.getString(3);
					dm.addRow(new Object[]{Wno,Bno,Bstate});							
				}
			}
			if(select.equals("目前状态")) {
				String sql = "SELECT Wno,Bno,Bstate from Bed where Bstate=?";
				ps = con.prepareStatement(sql);
				ps.setObject(1, text);
				rs=ps.executeQuery();
				if(!rs.next()){
					JOptionPane.showMessageDialog(null, "相关信息不存在，请重新输入!","系统提示", JOptionPane.ERROR_MESSAGE);
				}
				ps.close();
				rs.close();
				
				ps = con.prepareStatement(sql);
				ps.setObject(1, text);
				rs = ps.executeQuery();
				while(rs.next()){
					String Wno = rs.getString(1);
					String Bno = rs.getString(2);
					String Bstate = rs.getString(3);
					dm.addRow(new Object[]{Wno,Bno,Bstate});							
				}
			}
			if(select.equals("全部")) {
				String sql = "SELECT Wno,Bno,Bstate from Bed";
				ps = con.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					String Wno = rs.getString(1);
					String Bno = rs.getString(2);
					String Bstate = rs.getString(3);
					dm.addRow(new Object[]{Wno,Bno,Bstate});	
				}
			}
			/*
			JTable table=new JTable(dm);
			table.setBounds(250, 15, 765, 475);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(250, 15, 765, 475);
			frame.getContentPane().add(scrollPane);
			*/
			table = new JTable(dm);
			table.setBounds(250, 15, 765, 475);
			frame.getContentPane().add(scrollPane);
			}
		}catch(SQLException e1){
			e1.printStackTrace();
		}finally {
			linkdatabase.closeAll(rs, ps, con);
		}
		if(source == button2) {
			frame.dispose();
		}
	}
}
