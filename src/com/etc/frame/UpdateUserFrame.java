package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.etc.dao.UserDao;
import com.etc.entity.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UpdateUserFrame {

	private JFrame frame;
	private JTextField text_userId;
	private JTextField test_userPwd;
	private JTextField test_userAge;
	private JTextField test_userName;
	private JTextField test_userCreatTime;
	private JTextField test_userSex;
	private JTextField test_userTell;

	/**管理员进行用户修改界面
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUserFrame window = new UpdateUserFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public UpdateUserFrame(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {//把要修改的用户传进来
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 457, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u53F7\u7F16\u53F7:");
		label.setBounds(115, 79, 72, 15);
		frame.getContentPane().add(label);
		
		text_userId = new JTextField();
		text_userId.setEnabled(false);
		text_userId.setEditable(false);
		text_userId.setBounds(198, 77, 117, 18);
		frame.getContentPane().add(text_userId);
		text_userId.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801:");
		label_1.setBounds(115, 108, 54, 15);
		frame.getContentPane().add(label_1);
		
		test_userPwd = new JTextField();
		test_userPwd.setColumns(10);
		test_userPwd.setBounds(198, 105, 117, 18);
		frame.getContentPane().add(test_userPwd);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u540D:");
		label_2.setBounds(115, 138, 54, 15);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u5E74\u9F84:");
		label_3.setBounds(115, 163, 54, 15);
		frame.getContentPane().add(label_3);
		
		test_userAge = new JTextField();
		test_userAge.setColumns(10);
		test_userAge.setBounds(198, 161, 117, 18);
		frame.getContentPane().add(test_userAge);
		
		test_userName = new JTextField();
		test_userName.setEnabled(false);
		test_userName.setEditable(false);
		test_userName.setColumns(10);
		test_userName.setBounds(198, 133, 117, 18);
		frame.getContentPane().add(test_userName);
		
		JLabel label_4 = new JLabel("\u7528\u6237\u521B\u5EFA\u65F6\u95F4:");
		label_4.setBounds(90, 191, 98, 15);
		frame.getContentPane().add(label_4);
		
		test_userCreatTime = new JTextField();
		test_userCreatTime.setEnabled(false);
		test_userCreatTime.setEditable(false);
		test_userCreatTime.setColumns(10);
		test_userCreatTime.setBounds(198, 189, 117, 18);
		frame.getContentPane().add(test_userCreatTime);
		
		JLabel label_5 = new JLabel("\u6027\u522B:");
		label_5.setBounds(115, 218, 54, 15);
		frame.getContentPane().add(label_5);
		
		test_userSex = new JTextField();
		test_userSex.setColumns(10);
		test_userSex.setBounds(198, 216, 117, 18);
		frame.getContentPane().add(test_userSex);
		
		JLabel label_6 = new JLabel("\u8054\u7CFB\u7535\u8BDD:");
		label_6.setBounds(116, 246, 72, 15);
		frame.getContentPane().add(label_6);
		
		test_userTell = new JTextField();
		test_userTell.setColumns(10);
		test_userTell.setBounds(198, 244, 117, 18);
		frame.getContentPane().add(test_userTell);
		
		JLabel label_7 = new JLabel("\u6B22\u8FCE\u6765\u5230\u4FEE\u6539\u7528\u6237\u754C\u9762...");
		label_7.setFont(new Font("楷体", Font.BOLD, 16));
		label_7.setBounds(23, 10, 197, 38);
		frame.getContentPane().add(label_7);
		/**
		 * 修改按钮时间
		 */
		JButton update_userButton = new JButton("\u4FEE\u6539");
		update_userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int user_id=Integer.parseInt(text_userId.getText());
				String user_pwd=test_userPwd.getText();
				String user_name=test_userName.getText();
				String user_age=test_userAge.getText();
				String user_creat_time=test_userCreatTime.getText();
				String user_sex=test_userSex.getText();
				int user_tell=Integer.parseInt(test_userTell.getText());
				User u=new User(user_id,user_pwd,user_name,user_age,user_creat_time,user_sex,user_tell);
				boolean flag=UserDao.updateUser(u);
				if(flag) {
			    	JOptionPane.showMessageDialog(null, "修改成功");
			    	//当前页面关闭
			    	frame.dispose();
			    	//主界面刷新
			    	ShowUserFrame.queryAll();
			    }else {
			    	JOptionPane.showMessageDialog(null, "修改失败");
			    }
			}
		});
		update_userButton.setBackground(Color.YELLOW);
		update_userButton.setBounds(115, 303, 93, 23);
		frame.getContentPane().add(update_userButton);
		//获取原来用户的信息显示出来
		text_userId.setText(String.valueOf(user.getUser_id()));
		test_userPwd.setText(user.getUser_pwd());
		test_userName.setText(user.getUser_name());
		test_userAge.setText(user.getUser_age());
		test_userCreatTime.setText(user.getUser_creat_time());
		test_userSex.setText(user.getUser_sex());
		test_userTell.setText(String.valueOf(user.getUser_tell()));
		
		JButton cancel_button = new JButton("\u53D6\u6D88");
		//取消按钮
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
			
		});
		cancel_button.setBackground(Color.YELLOW);
		cancel_button.setBounds(247, 303, 93, 23);
		frame.getContentPane().add(cancel_button);
		frame.setVisible(true);
	}
}
