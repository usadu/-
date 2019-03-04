package com.etc.frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.etc.dao.MemberDao;
import com.etc.dao.MusicDao;
import com.etc.dao.UserDao;
import com.etc.entity.Member;
import com.etc.entity.Music;
import com.etc.entity.User;
import com.etc.util.DBUtil;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Font;
/**
 * 显示用户管理界面********************************
 * @author Administrator
 *
 */
public class ShowUserFrame {

	private JFrame frame;
	private static JTable table;
	private static JTable table_1;
	private JTextField text_users_size;
	private JTextField test_showMemberNo;
	private JTextField text_select;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowUserFrame window = new ShowUserFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public ShowUserFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 584, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u8FDB\u5165\u7528\u6237\u7BA1\u7406\u754C\u9762");
		label.setFont(new Font("楷体", Font.BOLD, 16));
		label.setBackground(Color.YELLOW);
		label.setBounds(10, 10, 188, 31);
		frame.getContentPane().add(label);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(57, 55, 475, 206);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("用户列表", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 470, 177);
		panel.add(scrollPane);
		/**
		 * 显示用户列表
		 */
		table = new JTable();
		queryAll();
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		//对用户表进行修改
		JMenuItem update_user_menu = new JMenuItem("\u4FEE\u6539");
		update_user_menu.setBackground(Color.YELLOW);
		update_user_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj=table.getValueAt(table.getSelectedRow(), 0);
				User user=UserDao.queryByUserId((Integer)obj);
				//把要修改的对象传入到一个新的修改用户界面中*******
				UpdateUserFrame updateUserFrame=new UpdateUserFrame(user);
			}
		});
		popupMenu.add(update_user_menu);
		//删除用户按钮
		JMenuItem deleteUserButton = new JMenuItem("\u5220\u9664");
		deleteUserButton.setBackground(Color.RED);
		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"是否确认删除(如果是会员将同时删除会员)", "删除提示框", JOptionPane.YES_NO_OPTION);
				if (n==0) {
					Object obj=table.getValueAt(table.getSelectedRow(), 0);
					boolean flag=UserDao.deleteUser((Integer)obj);
					MemberDao.deleteMemberByUser_id((Integer)obj);
					queryAll2();
					if(flag) {
						JOptionPane.showMessageDialog(null, "删除成功");
						queryAll();//删除成功刷新删除后的页面
						test_showMemberNo.setText(String.valueOf(new MemberDao().queryAll().size()));//删除后重新显示个数
						text_users_size.setText(String.valueOf(new UserDao().queryAll().size()));//显示用户个数
					}else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}
		});
		popupMenu.add(deleteUserButton);
		/**
		 * 添加成会员按钮******************************
		 */
		JMenuItem addMemberButton = new JMenuItem("\u6DFB\u52A0\u6210\u4F1A\u5458");
		addMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"是否确认添加成会员", "添加会员提示框", JOptionPane.YES_NO_OPTION);
				if (n==0) {
					Object obj=table.getValueAt(table.getSelectedRow(), 0);
					int member_userid=(Integer)obj;
					int member_status=0;
					Member m=MemberDao.queryByUser_id(member_userid);
					if(null==m) {//判断该用户是否在会员列表中存在
						Member member= new Member(member_userid,member_status);
						boolean flag=MemberDao.addMember(member);
						if(flag) {
							JOptionPane.showMessageDialog(null, "添加成功");
							queryAll2();
							test_showMemberNo.setText(String.valueOf(new MemberDao().queryAll().size()));
						}else {
							JOptionPane.showMessageDialog(null, "添加失败");
						}
					}else {
						JOptionPane.showMessageDialog(null, "添加失败,该用户已经是会员");
					}
				}
			}
		});
		addMemberButton.setBackground(Color.GREEN);
		popupMenu.add(addMemberButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("会员列表", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 470, 167);
		panel_1.add(scrollPane_1);
		/**
		 * 会员显示列表
		 */
		table_1 = new JTable();
		queryAll2();
		scrollPane_1.setViewportView(table_1);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(table_1, popupMenu_1);
		/**
		 * 删除会员菜单
		 */
		JMenuItem delete_memberMenu = new JMenuItem("\u5220\u9664");
		delete_memberMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"是否确认删除", "删除提示框", JOptionPane.YES_NO_OPTION);
				if (n==0) {
					Object obj=table_1.getValueAt(table_1.getSelectedRow(), 0);
					boolean flag=MemberDao.deleteMember((Integer)obj);
					if(flag) {
						JOptionPane.showMessageDialog(null, "删除成功");
						queryAll2();//删除成功刷新删除后的页面
						test_showMemberNo.setText(String.valueOf(new MemberDao().queryAll().size()));
					}else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}
		});
		popupMenu_1.add(delete_memberMenu);
		/**
		 * 修改会员按钮***************
		 */
		JMenuItem update_memberButton = new JMenuItem("\u4FEE\u6539\u4F1A\u5458\u72B6\u6001");
		update_memberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj=table_1.getValueAt(table_1.getSelectedRow(), 0);
				Member member=MemberDao.queryByMember_id((Integer)obj);
				UpdateMemberFrame updateMemberFrame=new UpdateMemberFrame(member);//修改会员页面
			}
		});
		popupMenu_1.add(update_memberButton);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u4E2A\u6570:");
		label_1.setBounds(63, 271, 67, 15);
		frame.getContentPane().add(label_1);
		
		text_users_size = new JTextField();
		text_users_size.setForeground(Color.RED);
		text_users_size.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		text_users_size.setEnabled(false);
		text_users_size.setEditable(false);
		text_users_size.setBounds(136, 272, 34, 15);
		frame.getContentPane().add(text_users_size);
		text_users_size.setColumns(10);	 
		text_users_size.setText(String.valueOf(new UserDao().queryAll().size()));//显示用户个数
		JLabel label_2 = new JLabel("\u4F1A\u5458\u4E2A\u6570:");
		label_2.setBounds(180, 271, 67, 15);
		frame.getContentPane().add(label_2);
		
		test_showMemberNo = new JTextField();
		test_showMemberNo.setForeground(Color.RED);
		test_showMemberNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		test_showMemberNo.setEnabled(false);
		test_showMemberNo.setEditable(false);
		test_showMemberNo.setColumns(10);
		test_showMemberNo.setBounds(253, 271, 34, 15);
		frame.getContentPane().add(test_showMemberNo);
		test_showMemberNo.setText(String.valueOf(new MemberDao().queryAll().size()));//显示会员个数
		/**
		 * 刷新按钮实现
		 */
		JButton button = new JButton("\u5237\u65B0\u5217\u8868");
		button.setBackground(Color.YELLOW);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_select.setText("");
				queryAll();
				queryAll2();
				text_users_size.setText(String.valueOf(new UserDao().queryAll().size()));
				test_showMemberNo.setText(String.valueOf(new MemberDao().queryAll().size()));
			}
		});
		button.setBounds(168, 314, 93, 23);
		frame.getContentPane().add(button);
		
		JLabel label_3 = new JLabel("\u67E5\u8BE2\u7528\u6237:");
		label_3.setBounds(278, 26, 67, 15);
		frame.getContentPane().add(label_3);
		
		text_select = new JTextField();
		text_select.setBounds(362, 26, 93, 15);
		frame.getContentPane().add(text_select);
		text_select.setColumns(10);
		/**
		 * 搜索按键实现***根据用户账号
		 */
		JButton select_button = new JButton("\u641C\u7D22");
		select_button.setBackground(Color.YELLOW);
		select_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user=text_select.getText();
				if(user.trim().equals("") || user.equals("")) {
					//查询全部
					queryAll();
					//queryAll2();
				}else {
					List<User> list=UserDao.queryByUserName(user);			
					query(list);
					text_users_size.setText(String.valueOf(list.size()));
					List<Member>members=new ArrayList<>();
					for (int i = 0; i < list.size(); i++) {
						User u=list.get(i);
						int b=u.getUser_id();
						Member member=MemberDao.queryByUser_id(b);
						if(null!=member) {
							members.add(member);
						}
					}
				if(null!=members) {//判断所查的的用户是不是会员,如果是,在会员列表显示
					query2(members);
					test_showMemberNo.setText(String.valueOf(members.size()));
						}else {
							queryAll2();
					}
				}
			}
		});
		select_button.setBounds(465, 22, 67, 23);
		frame.getContentPane().add(select_button);
		
		JButton exit_exitbutton = new JButton("\u9000\u51FA");
		exit_exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		exit_exitbutton.setBackground(Color.YELLOW);
		exit_exitbutton.setBounds(362, 314, 93, 23);
		frame.getContentPane().add(exit_exitbutton);
			
	}
	//查询所有用户显示在列表中方法
	public static void queryAll(){
		String str[]=new String[] {
				"账号编号", "密码", "用户名", "年龄", "用户创建时间", "性别", "联系电话"
			};
		List<User>list=new UserDao().queryAll();
		Object[][] data=new Object[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			User user=list.get(i);
			data[i][0]=user.getUser_id();
			data[i][1]=user.getUser_pwd();
			data[i][2]=user.getUser_name();
			data[i][3]=user.getUser_age();
			data[i][4]=user.getUser_creat_time();
			data[i][5]=user.getUser_sex();
			data[i][6]=user.getUser_tell();

		}
		table.setModel(new DefaultTableModel(
			data,
			str
		));
	}
	//显示方法
	public  void query(List<User> list){
		String str[]=new String[] {
				"账号编号", "密码", "用户名", "年龄", "用户创建时间", "性别", "联系电话"
			};	
		Object[][] data=new Object[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			User user=list.get(i);
			data[i][0]=user.getUser_id();
			data[i][1]=user.getUser_pwd();
			data[i][2]=user.getUser_name();
			data[i][3]=user.getUser_age();
			data[i][4]=user.getUser_creat_time();
			data[i][5]=user.getUser_sex();
			data[i][6]=user.getUser_tell();

		}
		table.setModel(new DefaultTableModel(data,str));
	}
	//显示所有会员方法
	public static void queryAll2() {
		String str[]=new String[] {"会员编号","用户编号","会员状态"};
		List<Member>list=MemberDao.queryAll();
		Object[][] obj=new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			Member member=list.get(i);
			obj[i][0]=member.getMember_id();
			obj[i][1]=member.getUser_id();
			obj[i][2]=member.getMember_status();
			
		}
		table_1.setModel(new DefaultTableModel(obj,str));
	}
	//显示会员方法
		public  void query2(List<Member> list){
			String str[]=new String[] {
					"会员编号","用户编号","会员状态"};	
			Object[][] obj=new Object[list.size()][3];
			for (int i = 0; i < list.size(); i++) {
				Member member=list.get(i);
				obj[i][0]=member.getMember_id();
				obj[i][1]=member.getUser_id();
				obj[i][2]=member.getMember_status();

			}
			table_1.setModel(new DefaultTableModel(obj,str));
		}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
