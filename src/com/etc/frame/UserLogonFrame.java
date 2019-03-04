package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.etc.dao.UserDao;
import com.etc.entity.User;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class UserLogonFrame extends JFrame{

	private JTextField adminField;
	private JPasswordField pwdField;
	private JLabel label;
	private JLabel label_1;
	private JButton btnNewButton_1;
	private JTextField textField;
	private JLabel lblNewLabel;
	private ImageIcon bgImg = new ImageIcon(this.getClass().getResource("/image/Login.png"));
	private JLabel imgLabel = new JLabel(bgImg);
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					登陆密码页面 window = new 登陆密码页面();
//					window.this.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public UserLogonFrame() {
		initialize();
	}
	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 361, 637);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		((JPanel) getContentPane()).setOpaque(false);
		JLabel imgLabel = new JLabel(bgImg);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, bgImg.getIconWidth(), bgImg.getIconHeight());
		
		
		adminField = new JTextField();
		adminField.setBounds(129, 147, 178, 24);
		this.getContentPane().add(adminField);
		adminField.setColumns(10);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(129, 189, 178, 24);
		this.getContentPane().add(pwdField);
		
		
		ValidCode vc = new ValidCode();
		vc.setBounds(129, 286, 145, 38);
		this.getContentPane().add(vc);
		/**
		 * 登录按钮事件
		 */
		JButton btnNewButton = new JButton("\u767B\u9646");
		btnNewButton.setBounds(129, 347, 136, 43);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st=adminField.getText();
				char[] pw=pwdField.getPassword();
				String pwd=new String(pw);
				String valid=textField.getText();
				if(vc.code.trim().replaceAll(" ", "").equalsIgnoreCase(valid)) {
					//判断是否为空
					if(!st.equals("")&& !pwd.equals("")) {
						User user=UserDao.login(pwd, st);	
						
						if(user!=null) {
							//a.界面提示
								JOptionPane.showMessageDialog(null, "登录成功");
							//b.跳转到主页面
							
								String name = st;
								int userid = UserDao.queryIDByUserName(name);
								TestmenuFrame tf = new TestmenuFrame();
								tf.setUsername(name);
								tf.setUserid(userid);
								dispose();

							//c.隐藏当前的页面
							setVisible(false);
							}else {
								//登录失败
								JOptionPane.showMessageDialog(null, "登录失败");
							}
							
						}else {
							//提示不可以为空
							JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
						}
				}else {
					JOptionPane.showMessageDialog(null, "验证码错误");
				}

				
				}
				
				
		});
	
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 19));
		this.getContentPane().add(btnNewButton);
		
		label = new JLabel("\u8D26\u53F7");
		label.setBounds(36, 150, 54, 18);
		label.setFont(new Font("宋体", Font.PLAIN, 19));
		this.getContentPane().add(label);
		
		label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(36, 192, 54, 18);
		label_1.setFont(new Font("宋体", Font.PLAIN, 19));
		this.getContentPane().add(label_1);
		
		btnNewButton_1 = new JButton("\u2190");
		btnNewButton_1.setBounds(281, 10, 54, 43);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new FirstFrame();
			}
		});
		this.getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(130, 235, 173, 29);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u8BF7\u8F93\u5165\u9A8C\u8BC1\u7801");
		label_2.setBounds(36, 236, 78, 26);
		this.getContentPane().add(label_2);
		
//		lblNewLabel = new JLabel("New label");
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\Login.png"));
//		lblNewLabel.setBounds(0, 0, 350, 600);
//		this.getContentPane().add(lblNewLabel);
//	

	}
}
