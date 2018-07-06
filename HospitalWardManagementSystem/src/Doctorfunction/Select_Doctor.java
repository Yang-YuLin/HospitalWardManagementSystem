package Doctorfunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Hospitaladmin.Alter_Doctor;
import geng.model.Login;
import linkdatabase.linkdatabase;

public class Select_Doctor implements ActionListener{

	Login login;
	private JFrame frame;
	private JLabel label;
	private JComboBox comboBox;
	private JTextField textField;
	private JButton button1 = new JButton("查询");
	private JButton button2 = new JButton("取消");
	private JTable table;
	private DefaultTableModel dm = null;
	private JScrollPane scrollPane;//滚动面板来使得需要被显示的区域显示
	private JPopupMenu popupMenu;//弹出式菜单
	
	public Select_Doctor(Login login) {
		// TODO Auto-generated constructor stub
		this.login=login;
		frame = new JFrame("查询个人信息");
		frame.setBounds(100, 100, 1050, 550);
		frame.getContentPane().setLayout(null);
	
		button1.setBounds(15,290,85,30);
		frame.getContentPane().add(button1);
		button1.addActionListener(this);
		
		button2.setBounds(140,290,85,30);
		frame.getContentPane().add(button2);
		button2.addActionListener(this);
		
		table = new JTable(){
			   public boolean isCellEditable(int row, int column){
			       return false;
			   }
			};
			
		String[]names={"工作证号","姓名","性别","职称","年龄","联系电话","所属科室"};
		dm = new DefaultTableModel(names, 0);
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
        menItem_Alter.setText("修改");
        menItem_Alter.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	//获取ID号
            	String Dno = (String) table.getValueAt(index, 0);//返回 row 和 column 位置的单元格值
            	new Alter_Doctor(Dno);
            }
        });
        
       popupMenu.add(menItem_Alter);
       
    }  
	
	@Override
	public void actionPerformed(ActionEvent e) {
		get(login,e);
	}
	
	public Login get(Login login,ActionEvent e) {
		Object source = e.getSource();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			if(source == button1) {
				con = linkdatabase.getConnection();
				String sql = "SELECT Dno,Dname,Dsex,Dtitle,Dage,Dtel,Deptname from Doctor where Dno = ?";
				ps = con.prepareStatement(sql);
				String DD=login.getDno();
				System.out.println(DD);
				ps.setObject(1, DD);
				rs=ps.executeQuery();
				
				while(rs.next()) {
					String Dno = rs.getString(1);
				String Dname = rs.getString(2);
				String Dsex = rs.getString(3);
				String Dtitle = rs.getString(4);
				String Dage = rs.getString(5);
				String Dtel = rs.getString(6);
				String Deptname = rs.getString(7);
				dm.addRow(new Object[]{Dno,Dname,Dsex,Dtitle,Dage,Dtel,Deptname});	
				}
				
			}
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
		return login ;
	}
}
