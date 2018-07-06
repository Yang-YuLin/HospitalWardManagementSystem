package Doctorfunction;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Hospitaladmin.Delete_Patient;
import linkdatabase.linkdatabase;

public class leave_cost extends JTable implements ActionListener{

	String Pno,Wno;
	Date Poutdate;
	Date Pindate;
	int Wcharge;
	long diff;
	int days;
	int charge;
	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	private JFrame frame;
	private JLabel l1,l2,l3,l4,l5;
	private JTextField t1,t2;
	private JButton b1 = new JButton("查询");
	private JButton b2 = new JButton("结算");
	private JButton b3 = new JButton("删除该病人信息");
	private JButton b4 = new JButton("返回");
	private JTable table = new JTable();
	private DefaultTableModel dm = null;
	private JScrollPane scrollPane;//滚动面板来使得需要被显示的区域显示
	
	public leave_cost() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("出院结算");
		frame.setBounds(100, 100, 720, 550);
		frame.getContentPane().setLayout(null);
		
		l1 = new JLabel("请输入病历号:");
		l1.setFont(new Font("宋体", Font.PLAIN, 20));
		l1.setBounds(270,35,210,40);
		frame.getContentPane().add(l1);	
		
		t1 = new JTextField();
		t1.setFont(new Font("宋体",Font.PLAIN, 20));
		t1.setBounds(280,80,110,25);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
				
		b1.setBounds(300,140,60,25);
		frame.getContentPane().add(b1);
		b1.addActionListener(this);
		
		l2 = new JLabel("请输入出院日期:");
		l2.setFont(new Font("宋体", Font.PLAIN, 20));
		l2.setBounds(260,240,210,60);
		frame.getContentPane().add(l2);	
		
		t2 = new JTextField();
		t2.setFont(new Font("宋体",Font.PLAIN, 20));
		t2.setBounds(280,290,110,25);
		frame.getContentPane().add(t2);
		t2.setColumns(10);
		
		l3 = new JLabel("(格式为20XX-XX-XX)");
		l3.setFont(new Font("宋体", Font.PLAIN, 20));
		l3.setBounds(400,285,210,40);
		frame.getContentPane().add(l3);	
		
		l4 = new JLabel("应付费用为：");
		l4.setFont(new Font("宋体", Font.PLAIN, 20));
		l4.setBounds(250,370,210,40);
		frame.getContentPane().add(l4);	
		
		l5 = new JLabel("[money]");
		l5.setFont(new Font("宋体", Font.PLAIN, 20));
		l5.setBounds(370,370,210,40);
		frame.getContentPane().add(l5);	
		
		b2.setBounds(300,335,60,25);
		frame.getContentPane().add(b2);
		b2.addActionListener(this);
		
		b3.setBounds(500,420,150,25);
		frame.getContentPane().add(b3);
		b3.addActionListener(this);
		
		b4.setBounds(525,465,100,25);
		frame.getContentPane().add(b4);
		b4.addActionListener(this);
		
		String[] columnNames={"病历号", "姓名","性别","诊断","病房号","病床号","主医证号","联系电话","入院日期","出院日期"};
		dm = new DefaultTableModel(columnNames, 0);
		table.setModel(dm);
		scrollPane = new JScrollPane(table);
		table.setBounds(10, 190, 700, 41);
		scrollPane.setBounds(10, 190, 700, 41);
		frame.getContentPane().add(scrollPane);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String text = t1.getText().trim();
		
		try {
			if(source == b1) {
				con = linkdatabase.getConnection();
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
				while(rs.next()) {
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
			table = new JTable(dm);
			table.setBounds(10, 190, 700, 41);
			frame.getContentPane().add(scrollPane);
		}catch(SQLException e1){
			e1.printStackTrace();
		}finally {
			linkdatabase.closeAll(rs, ps, con);
		}
		
		try {
			if(source == b2) {
				con = linkdatabase.getConnection();
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
				while(rs.next()) {
					Pno = rs.getString(1);
					Wno = rs.getString(5);
					Pindate = rs.getDate(9);
					Poutdate = rs.getDate(10);
					days = differentDays(Pindate,Poutdate);
				}
				sql = "SELECT Wno,Deptname,Wcharge from Ward where Wno=?";
				ps = con.prepareStatement(sql);
				ps.setObject(1, Wno);
				rs=ps.executeQuery();
				while(rs.next()) {
					Wno = rs.getString(1);
					String Deptname = rs.getString(2);
					Wcharge = rs.getInt(3);
					charge = days*Wcharge;
					l5.setText(String.valueOf(charge));
				}
			}
		}catch(SQLException e1){
			e1.printStackTrace();
		}finally {
			linkdatabase.closeAll(rs, ps, con);
		}
		
		if(source == b3) {
			try {
				new Delete_Patient(Pno);
				JOptionPane.showMessageDialog(null, "删除成功","系统提示",JOptionPane.WARNING_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(1);
		}
		
		if(source == b4) {
			frame.dispose();
		}
	}

	public int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            return day2-day1;
        }
    }
}
