package Hospitaladmin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import linkdatabase.linkdatabase;


public class Manage_Patient extends JTable implements ActionListener{

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
	
	public Manage_Patient() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("查询病人信息");
		frame.setBounds(100, 100, 1050, 550);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("选择查询条件");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(15,90,210,40);
		frame.getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体",Font.PLAIN, 25));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"病历号", "姓名","性别","诊断","病房号","病床号","主治医生工作证号","联系电话","入院日期","出院日期","全部"}));
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
		
		String[] columnNames={"病历号", "姓名","性别","诊断","病房号","病床号","主医证号","联系电话","入院日期","出院日期"};
		dm = new DefaultTableModel(columnNames, 0);
		table.setModel(dm);
		scrollPane = new JScrollPane(table);
		table.setBounds(250, 15, 765, 475);
		scrollPane.setBounds(250, 15, 765, 475);
		frame.getContentPane().add(scrollPane);
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseRightButtonClick(e); 
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	private void mouseRightButtonClick(MouseEvent e) {
		//判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键  
		if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
			//返回point所在的行索引;如果结果不在 [0, getRowCount()-1] 范围内，则返回 -1。  
			int focusedRowIndex = table.rowAtPoint(e.getPoint());
			if (focusedRowIndex == -1) {
				return;  
				}
			//将表格所选项设为当前右键点击的行
			table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
			table.setComponentPopupMenu(popupMenu);//设置一个弹出菜单
			createPopupMenu(focusedRowIndex);
			popupMenu.show(e.getComponent(), e.getX(), e.getY());//显示一个弹出菜单
			}  
	  }
	
	private void createPopupMenu(int index) {
		popupMenu = new JPopupMenu();  
        JMenuItem menItem_Alter = new JMenuItem();
        JMenuItem menItem_Delete = new JMenuItem();
        menItem_Alter.setText("修改");
        menItem_Delete.setText("删除");
        menItem_Alter.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	//获取ID号
            	String Pno = (String) table.getValueAt(index, 0);//返回 row 和 column 位置的单元格值
            	new Alter_Patient(Pno);
            	dm.getDataVector().clear();
            	scrollPane.validate();
            }
        });
        menItem_Delete.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent evt) {
            	String Pno = (String) table.getValueAt(index, 0);//返回 row 和 column 位置的单元格值
            	try {
            		int a = JOptionPane.showConfirmDialog(null, "确认删除该病人信息？","系统提示", JOptionPane.YES_NO_OPTION);
				if(a== JOptionPane.YES_OPTION){
					new Delete_Patient(Pno);
					dm.removeRow(index);
					JOptionPane.showMessageDialog(null, "删除成功！", "系统提示",JOptionPane.PLAIN_MESSAGE); 
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }  
        });
        popupMenu.add(menItem_Alter);
        popupMenu.add(menItem_Delete);
    }  
	
	
	@Override
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
				if(select.equals("病历号")) {
					dm.getDataVector().clear();
	            	scrollPane.validate();
					String sql = "SELECT Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate from Patient where Pno=?";
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
						String Pno = rs.getString(1);
						String Pname = rs.getString(2);
						String Psex = rs.getString(3);
						String Pdiagnose = rs.getString(4);
						String Wno = rs.getString(5);
						String Bno = rs.getString(6);
						String Dno = rs.getString(7);
						String Ptel = rs.getString(8);
						String Pindate = rs.getString(9);
						String Poutdate = rs.getString(10);
						dm.addRow(new Object[]{Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate});							
					}
				}
			}
			if(select.equals("姓名")) {
				dm.getDataVector().clear();
            	scrollPane.validate();
				String sql = "SELECT Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate from Patient where Pname=?";
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
					String Pno = rs.getString(1);
					String Pname = rs.getString(2);
					String Psex = rs.getString(3);
					String Pdiagnose = rs.getString(4);
					String Wno = rs.getString(5);
					String Bno = rs.getString(6);
					String Dno = rs.getString(7);
					String Ptel = rs.getString(8);
					String Pindate = rs.getString(9);
					String Poutdate = rs.getString(10);
					dm.addRow(new Object[]{Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate});							
				}
			}
			if(select.equals("性别")) {
				dm.getDataVector().clear();
            	scrollPane.validate();
				String sql = "SELECT Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate from Patient where Psex=?";
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
					String Pno = rs.getString(1);
					String Pname = rs.getString(2);
					String Psex = rs.getString(3);
					String Pdiagnose = rs.getString(4);
					String Wno = rs.getString(5);
					String Bno = rs.getString(6);
					String Dno = rs.getString(7);
					String Ptel = rs.getString(8);
					String Pindate = rs.getString(9);
					String Poutdate = rs.getString(10);
					dm.addRow(new Object[]{Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate});							
				}
			}
			if(select.equals("诊断结果")) {
				dm.getDataVector().clear();
            	scrollPane.validate();
				String sql = "SELECT Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate from Patient where Pdiagnose=?";
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
					String Pno = rs.getString(1);
					String Pname = rs.getString(2);
					String Psex = rs.getString(3);
					String Pdiagnose = rs.getString(4);
					String Wno = rs.getString(5);
					String Bno = rs.getString(6);
					String Dno = rs.getString(7);
					String Ptel = rs.getString(8);
					String Pindate = rs.getString(9);
					String Poutdate = rs.getString(10);
					dm.addRow(new Object[]{Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate});							
				}
			}
			if(select.equals("病房号")) {
				dm.getDataVector().clear();
            	scrollPane.validate();
				String sql = "SELECT Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate from Patient where Wno=?";
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
					String Pno = rs.getString(1);
					String Pname = rs.getString(2);
					String Psex = rs.getString(3);
					String Pdiagnose = rs.getString(4);
					String Wno = rs.getString(5);
					String Bno = rs.getString(6);
					String Dno = rs.getString(7);
					String Ptel = rs.getString(8);
					String Pindate = rs.getString(9);
					String Poutdate = rs.getString(10);
					dm.addRow(new Object[]{Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate});							
				}
			}
			if(select.equals("病床号")) {
				dm.getDataVector().clear();
            	scrollPane.validate();
				String sql = "SELECT Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate from Patient where Bno=?";
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
					String Pno = rs.getString(1);
					String Pname = rs.getString(2);
					String Psex = rs.getString(3);
					String Pdiagnose = rs.getString(4);
					String Wno = rs.getString(5);
					String Bno = rs.getString(6);
					String Dno = rs.getString(7);
					String Ptel = rs.getString(8);
					String Pindate = rs.getString(9);
					String Poutdate = rs.getString(10);
					dm.addRow(new Object[]{Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate});							
				}
			}
			if(select.equals("主治医生工作证号")) {
				dm.getDataVector().clear();
            	scrollPane.validate();
				String sql = "SELECT Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate from Patient where Dno=?";
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
					String Pno = rs.getString(1);
					String Pname = rs.getString(2);
					String Psex = rs.getString(3);
					String Pdiagnose = rs.getString(4);
					String Wno = rs.getString(5);
					String Bno = rs.getString(6);
					String Dno = rs.getString(7);
					String Ptel = rs.getString(8);
					String Pindate = rs.getString(9);
					String Poutdate = rs.getString(10);
					dm.addRow(new Object[]{Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate});							
				}
			}
			if(select.equals("全部")) {
				dm.getDataVector().clear();
            	scrollPane.validate();
				String sql = "SELECT Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate from Patient";
				ps = con.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					String Pno = rs.getString(1);
					String Pname = rs.getString(2);
					String Psex = rs.getString(3);
					String Pdiagnose = rs.getString(4);
					String Wno = rs.getString(5);
					String Bno = rs.getString(6);
					String Dno = rs.getString(7);
					String Ptel = rs.getString(8);
					String Pindate = rs.getString(9);
					String Poutdate = rs.getString(10);
					dm.addRow(new Object[]{Pno,Pname,Psex,Pdiagnose,Wno,Bno,Dno,Ptel,Pindate,Poutdate});								
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
