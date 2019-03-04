package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.etc.dao.MemberDao;
import com.etc.entity.Member;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
/**
 * 修改会员界面
 * @author Administrator
 *
 */
public class UpdateMemberFrame {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateMemberFrame window = new UpdateMemberFrame();
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
	public UpdateMemberFrame(Member member) {
		initialize(member);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Member member) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 403, 254);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4FEE\u6539\u4F1A\u5458\u72B6\u6001\u9875\u9762");
		label.setFont(new Font("楷体", Font.BOLD, 16));
		label.setBounds(124, 23, 191, 36);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u4F1A\u5458\u72B6\u6001:");
		label_1.setBounds(55, 103, 71, 15);
		frame.getContentPane().add(label_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("正常");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rdbtnNewRadioButton.setBounds(144, 99, 71, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("不正常");
		buttonGroup.add(radioButton);
		radioButton.setBounds(239, 99, 76, 23);
		frame.getContentPane().add(radioButton);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBackground(Color.YELLOW);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=member.getMember_id();
				int u=member.getUser_id();
				int s;
				if (rdbtnNewRadioButton.isSelected()) {
					s=0;
				}else {
					s=2;
				}
				Member m=new Member(i,u,s);
				boolean flag =MemberDao.updateMember(m);
				if(flag) {
			    	JOptionPane.showMessageDialog(null, "修改成功");
			    	//当前页面关闭
			    	frame.dispose();
			    	//主界面刷新
			    	ShowUserFrame.queryAll2();
			    }else {
			    	JOptionPane.showMessageDialog(null, "修改失败");
			    }
			}
		});
		button.setBounds(87, 156, 93, 23);
		frame.getContentPane().add(button);
		//取消按钮
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setBackground(Color.YELLOW);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setBounds(223, 156, 93, 23);
		frame.getContentPane().add(button_1);
		frame.setVisible(true);
		switch(member.getMember_status()) {
		case 0:
			rdbtnNewRadioButton.setSelected(true);
			break;
		case 1:
			radioButton.setSelected(true);
			break;
		
		default:
			
			break;
	}
	}
}
