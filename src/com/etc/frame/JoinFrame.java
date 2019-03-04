package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.etc.dao.UserDao;
import com.etc.entity.User;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class JoinFrame extends JFrame {

	private JTextField text_userName;
	private JPasswordField text_userPwd;
	private JTextField text_userAge;
	private JTextField text_userTell;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ImageIcon bgImg = new ImageIcon(this.getClass().getResource("/image/Login.png"));
	private JLabel imgLabel = new JLabel(bgImg);
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jointhis window = new Jointhis();
					window.this.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public JoinFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 364, 633);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		((JPanel) getContentPane()).setOpaque(false);
		JLabel imgLabel = new JLabel(bgImg);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, bgImg.getIconWidth(), bgImg.getIconHeight());
		
		JLabel label = new JLabel("\u8D26\u53F7:");
		label.setBounds(63, 93, 44, 21);
		this.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801:");
		label_1.setBounds(63, 135, 44, 21);
		this.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5E74\u9F84:");
		label_2.setBounds(63, 181, 44, 21);
		this.getContentPane().add(label_2);
		
		text_userName = new JTextField();
		text_userName.setBounds(131, 93, 144, 21);
		this.getContentPane().add(text_userName);
		text_userName.setColumns(10);
		
		text_userPwd = new JPasswordField();
		text_userPwd.setBounds(131, 135, 144, 21);
		this.getContentPane().add(text_userPwd);
		
		text_userAge = new JTextField();
		text_userAge.setBounds(131, 181, 144, 21);
		this.getContentPane().add(text_userAge);
		text_userAge.setColumns(10);
		
		JLabel label_3 = new JLabel("\u6027\u522B:");
		label_3.setBounds(63, 220, 37, 28);
		this.getContentPane().add(label_3);
		
		JRadioButton man = new JRadioButton("\u7537");
		man.setBounds(131, 223, 50, 23);
		buttonGroup.add(man);
		man.setSelected(true);
		this.getContentPane().add(man);
		
		JRadioButton woman = new JRadioButton("\u5973");
		woman.setBounds(225, 223, 50, 23);
		buttonGroup.add(woman);
		this.getContentPane().add(woman);
		
		JLabel label_4 = new JLabel("\u624B\u673A\u53F7:");
		label_4.setBounds(64, 258, 57, 28);
		this.getContentPane().add(label_4);
		
		text_userTell = new JTextField();
		text_userTell.setBounds(131, 262, 144, 21);
		text_userTell.setColumns(10);
		this.getContentPane().add(text_userTell);
		//注册按钮时间
		JButton add_button = new JButton("\u6CE8\u518C");
		add_button.setBounds(131, 321, 93, 41);
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pd=text_userPwd.getPassword();
				//怎么将一个字符数组转成字符串
				String pwd=new String(pd);
				String name=text_userName.getText();
				String age=text_userAge.getText();
				Calendar c=Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
				String time=sdf.format(c.getTime());
				String sex=man.isSelected()?man.getText():woman.getText();
				int tell=Integer.parseInt(text_userTell.getText());
				User user=new User(pwd,name,age,time,sex,tell);
				boolean flag=UserDao.addUser(user);
				if(flag) {
					JOptionPane.showMessageDialog(null, "注册成功");
					new FirstFrame();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "注册失败");
				}

			}
		});
		this.getContentPane().add(add_button);
		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\Login.png"));
//		lblNewLabel.setBounds(0, 0, 350, 600);
//		this.getContentPane().add(lblNewLabel);
	}
}
