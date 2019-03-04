package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
/**
 * 自动关机
 * @author msi
 *
 */
public class ColseCPFrame {

	private JFrame frame;
	private String username;
	private int userid;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ColseCPFrame window = new ColseCPFrame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ColseCPFrame() {
		initialize();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("设置自动关机");
		JPanel panel = new JPanel();
		panel.setBounds(52, 58, 333, 42);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("\u8BBE\u7F6E\u7535\u8111\u5728");
		panel.add(label);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u79D2\u540E\u5173\u95ED");
		panel.add(label_1);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String time=textField.getText();
				int n=Integer.parseInt(time);
				 try {
					Runtime.getRuntime().exec("shutdown -s -t "+n);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88\u81EA\u52A8\u5173\u673A");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("shutdown -a");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(138, 164, 114, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestmenuFrame tf = new TestmenuFrame();
				tf.setUsername(username);
				tf.setUserid(userid);
				frame.dispose();
			}
		});
		button.setBounds(10, 10, 53, 27);
		frame.getContentPane().add(button);
		frame.setVisible(true);
	}
}
