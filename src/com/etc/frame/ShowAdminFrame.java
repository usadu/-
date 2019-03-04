package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.etc.entity.Admin;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ShowAdminFrame extends JFrame {
/**
 * 把管理员登录成功后的一个admin传进来
 */
	// 定义图片
		private ImageIcon bgImg = new ImageIcon(this.getClass().getResource("/image/Login.png"));
		private JLabel imgLabel = new JLabel(bgImg);
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAdminthis window = new ShowAdminthis();
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
	public ShowAdminFrame(Admin admin) {
		initialize(admin);
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize(Admin admin) {
		this.getContentPane().setBackground(Color.WHITE);
		this.getContentPane().setForeground(Color.BLACK);
		this.setBounds(100, 100, 366, 640);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
				
				this.getRootPane().setWindowDecorationStyle(0);//关闭的样式没有,所以不能关闭
				// 将Jthis上自带的面板设置为透明，否则背景图片不会显示出来
				((JPanel) getContentPane()).setOpaque(false);
				JLabel imgLabel = new JLabel(bgImg);
				this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
				imgLabel.setBounds(0, 0, bgImg.getIconWidth(), bgImg.getIconHeight());
				this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JLabel label = new JLabel(admin.getAdmin_name());
		label.setBounds(137, 80, 97, 50);
		label.setFont(new Font("宋体", Font.BOLD, 16));
		this.getContentPane().add(label);
		/**
		 * 进入管理歌曲界面
		 */
		JButton button = new JButton("\u8FDB\u5165\u6B4C\u66F2\u7BA1\u7406\u754C\u9762");
		button.setBounds(88, 161, 176, 70);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowMusic showMusic=new ShowMusic();
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 13));
		button.setBackground(Color.WHITE);
		this.getContentPane().add(button);
		/**
		 * 进入用户管理界面
		 */
		JButton btnNewButton = new JButton("\u8FDB\u5165\u7528\u6237\u7BA1\u7406\u754C\u9762");
		btnNewButton.setBounds(88, 293, 176, 70);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowUserFrame showUserthis=new ShowUserFrame();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 13));
		this.getContentPane().add(btnNewButton);
		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\Login.png"));
//		lblNewLabel.setBounds(0, 0, 350, 597);
//		this.getContentPane().add(lblNewLabel);
		this.setVisible(true);
	}
}
