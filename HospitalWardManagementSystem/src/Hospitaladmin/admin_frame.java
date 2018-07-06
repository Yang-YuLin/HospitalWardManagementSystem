package Hospitaladmin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import Jtable.Jtable;

public class admin_frame extends Jtable{

	JFrame frame;
	JMenuBar menuBar = new JMenuBar();//菜单栏组件
	JLabel l1 = new JLabel("欢迎进入");
	JLabel l2 = new JLabel("医院病房管理系统");
	JMenu menu1 = new JMenu("科室信息管理");
	JMenu menu2 = new JMenu("医生信息管理");
	JMenu menu3 = new JMenu("病房信息管理");
	JMenu menu4 = new JMenu("床位信息管理");
	JMenu menu5 = new JMenu("病人信息管理");
	JMenuItem insert_department,manage_department;
	JMenuItem insert_doctor,manage_doctor;
	JMenuItem insert_ward,manage_ward;
	JMenuItem insert_bed,manage_bed;
	JMenuItem insert_patient,manage_patient;
	
	JToolBar jToolBar;//工具栏组件
	JButton jButton1,jButton2,jButton3,jButton4,jButton5,jButton6,jButton7;
	
	private Jtable table = new Jtable(){
		   public boolean isCellEditable(int row, int column){
		       return false;
		   }
		};
	private DefaultTableModel dm = null;
	
	public admin_frame() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("医院内部信息管理");
		frame.setBounds(330,200,688,431);
		String path="lib/Main.jpeg";
		ImageIcon backgroundimage=new ImageIcon(path);//使标签有图片，用此创建背景图片
		JLabel jLabel = new JLabel(backgroundimage);//标签为用户提示信息
		jLabel.setBounds(0, 0,frame.getWidth(), frame.getHeight());//标签在窗口上的位置及大小
		JPanel jPanel1 = (JPanel) frame.getContentPane();//初始化一个内容面板，这样才可以把内容面板设置成透明的    jPanel1下面是我们要添加的图片，上面是我们的组件
		jPanel1.setOpaque(false);//使组件不会显示其中的某些像素，允许组件下面的像素显现出来，即设置透明
		jPanel1.setLayout(null);
		frame.getLayeredPane().add(jLabel,new Integer(Integer.MIN_VALUE));
		frame.setVisible(true);
		
		l1.setBounds(80,70,450,40);
		l1.setFont(new Font("微软雅黑",Font.BOLD,40));
		l2.setBounds(190,120,450,40);
		l2.setFont(new Font("微软雅黑",Font.BOLD,40));
		
		insert_department = new JMenuItem("插入科室");
		manage_department = new JMenuItem("管理科室");
		
		insert_doctor = new JMenuItem("插入医生");
		manage_doctor = new JMenuItem("管理医生");
		
		insert_ward = new JMenuItem("插入病房");
		manage_ward = new JMenuItem("管理病房");
	
		
		insert_bed = new JMenuItem("插入床位");
		manage_bed = new JMenuItem("管理床位");
		
		insert_patient = new JMenuItem("插入病人");
		manage_patient = new JMenuItem("管理病人");
		
		menu1.add(insert_department);
		menu1.add(manage_department);
		
		menu2.add(insert_doctor);
		menu2.add(manage_doctor);
		
		menu3.add(insert_ward);
		menu3.add(manage_ward);
		
		menu4.add(insert_bed);
		menu4.add(manage_bed);
		
		menu5.add(insert_patient);
		menu5.add(manage_patient);
		
		//将菜单加入菜单栏
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		menuBar.add(menu5);
		
		frame.setJMenuBar(menuBar);
		frame.add(l1);
		frame.add(l2);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		insert_department.addActionListener(listen);
		manage_department.addActionListener(listen);
		
		insert_doctor.addActionListener(listen);
		manage_doctor.addActionListener(listen);
		
		insert_ward.addActionListener(listen);
		manage_ward.addActionListener(listen);
		
		insert_bed.addActionListener(listen);
		manage_bed.addActionListener(listen);
		
		insert_patient.addActionListener(listen);
		manage_patient.addActionListener(listen);
		
		String[]names={"工作证号","姓名","性别","年龄","出生日期","职称","电话","所属科室"};
		DefaultTableModel dm = new DefaultTableModel(names, 0);
		
	}

	public static void main(String[] args) {
		new admin_frame();
	}
	
	ActionListener listen = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource()==insert_department) {
				new Insert_Department();
			}
			
			if(e.getSource()==manage_department) {
				new Manage_Department();
			}
			
			if(e.getSource()==insert_doctor) {
				new Insert_Doctor();
			}
			
			if(e.getSource()==manage_doctor) {
				new Manage_Doctor();
			}
			
			if(e.getSource()==insert_ward) {
				new Insert_Ward();
			}
			
			if(e.getSource()==manage_ward) {
				new Manage_Ward();
			}
			
			if(e.getSource()==insert_bed) {
				new Insert_Bed();
			}
			
			if(e.getSource()==manage_bed) {
				new Manage_Bed();
			}
			
			if(e.getSource()==insert_patient) {
				new Insert_Patient();
			}
			
			if(e.getSource()==manage_patient) {
				new Manage_Patient();
			}
		}
	};
}

