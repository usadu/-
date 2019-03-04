package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.etc.dao.MusicDao;
import com.etc.entity.Music;


import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
/**
 * 修改歌曲界面
 * @author Administrator
 *
 */
public class UpdateFrame {

	private JFrame frame;
	private JTextField test_music_id;
	private JTextField test_music_name;
	private JTextField test_music_singer;
	private JTextField test_music_type;
	private JTextField test_music_time;
	private JTextField test_music_path;
	private JTextField test_music_photo;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFrame window = new UpdateFrame();
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
	public UpdateFrame(Music music) {
		initialize(music);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Music music) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u4FEE\u6539\u6B4C\u66F2\u4FE1\u606F");
		label.setFont(new Font("宋体", Font.BOLD, 13));
		label.setBounds(20, 10, 111, 23);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u97F3\u4E50\u7F16\u53F7:");
		label_1.setBounds(69, 35, 62, 15);
		frame.getContentPane().add(label_1);
		
		test_music_id = new JTextField();
		test_music_id.setEnabled(false);
		test_music_id.setEditable(false);
		test_music_id.setBounds(151, 35, 149, 15);
		frame.getContentPane().add(test_music_id);
		test_music_id.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6B4C\u540D:");
		label_2.setBounds(69, 62, 54, 15);
		frame.getContentPane().add(label_2);
		
		test_music_name = new JTextField();
		test_music_name.setColumns(10);
		test_music_name.setBounds(151, 62, 149, 15);
		frame.getContentPane().add(test_music_name);
		
		JLabel label_3 = new JLabel("\u6B4C\u624B:");
		label_3.setBounds(69, 85, 54, 15);
		frame.getContentPane().add(label_3);
		
		test_music_singer = new JTextField();
		test_music_singer.setColumns(10);
		test_music_singer.setBounds(151, 85, 149, 15);
		frame.getContentPane().add(test_music_singer);
		
		JLabel label_4 = new JLabel("\u7C7B\u578B(\u7F16\u53F7):");
		label_4.setBounds(69, 109, 72, 15);
		frame.getContentPane().add(label_4);
		
		test_music_type = new JTextField();
		test_music_type.setColumns(10);
		test_music_type.setBounds(151, 109, 149, 15);
		frame.getContentPane().add(test_music_type);
		
		test_music_time = new JTextField();
		test_music_time.setEnabled(false);
		test_music_time.setEditable(false);
		test_music_time.setColumns(10);
		test_music_time.setBounds(151, 134, 149, 15);
		frame.getContentPane().add(test_music_time);
		
		test_music_path = new JTextField();
		test_music_path.setColumns(10);
		test_music_path.setBounds(151, 161, 149, 15);
		frame.getContentPane().add(test_music_path);
		
		test_music_photo = new JTextField();
		test_music_photo.setColumns(10);
		test_music_photo.setBounds(151, 184, 149, 15);
		frame.getContentPane().add(test_music_photo);
		
		JLabel label_6 = new JLabel("\u4E0A\u4F20\u65F6\u95F4:");
		label_6.setBounds(69, 134, 62, 15);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u97F3\u4E50\u8DEF\u5F84:");
		label_7.setBounds(59, 161, 72, 15);
		frame.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("\u56FE\u7247\u8DEF\u5F84:");
		label_8.setBounds(59, 184, 72, 15);
		frame.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("\u662F\u5426\u53EF\u4EE5\u4E0B\u8F7D:");
		label_9.setBounds(41, 208, 100, 15);
		frame.getContentPane().add(label_9);
		
		JRadioButton status_Button1 = new JRadioButton("\u6B63\u5E38");
		buttonGroup.add(status_Button1);
		status_Button1.setSelected(true);
		status_Button1.setBounds(146, 204, 54, 23);
		frame.getContentPane().add(status_Button1);
		
		JRadioButton status_Button_2 = new JRadioButton("\u7981\u6B62");
		buttonGroup.add(status_Button_2);
		status_Button_2.setBounds(212, 205, 54, 23);
		frame.getContentPane().add(status_Button_2);
		
		JRadioButton status_Button_3 = new JRadioButton("\u6761\u4EF6\u4E0B\u8F7D");
		buttonGroup.add(status_Button_3);
		status_Button_3.setBounds(273, 205, 78, 23);
		frame.getContentPane().add(status_Button_3);
		frame.setVisible(true);
		//获取原来歌曲信息
		test_music_id.setText(String.valueOf(music.getMusic_id()));
		test_music_name.setText(music.getMusic_name());
		test_music_singer.setText(music.getMusic_singer());
		test_music_type.setText(String.valueOf(music.getType_id()) );
		test_music_time.setText(music.getMusic_time());
		test_music_path.setText(music.getMusic_path());
		test_music_photo.setText(music.getMusic_photo());
		//修改按钮
		JButton update_button = new JButton("\u4FEE\u6539");
		update_button.setBackground(Color.YELLOW);
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取管理员修改后的歌曲信息
				int test_id=Integer.parseInt(test_music_id.getText());
				String test_name=test_music_name.getText();
				String test_singer=test_music_singer.getText();
				int test_type=Integer.parseInt(test_music_type.getText());
				String test_time=test_music_time.getText();
				String test_path=test_music_path.getText();
				String test_photo=test_music_photo.getText();
				int test_status;
				if (status_Button1.isSelected()) {
					test_status=1;
				}else if (status_Button_2.isSelected()) {
					test_status=2;
				}else {
					test_status=3;
				}
				Music music=new Music(test_id,test_name,test_singer,test_type,test_time,test_path,test_photo,test_status);
				boolean flag=new MusicDao().updateMusic(music);
				if(flag) {
			    	JOptionPane.showMessageDialog(null, "修改成功");
			    	//当前页面关闭
			    	frame.dispose();
			    	//主界面刷新
			    	ShowMusic.queryShowMusic();
			    }else {
			    	JOptionPane.showMessageDialog(null, "修改失败");
			    }
			}
		});
		update_button.setBounds(90, 233, 93, 23);
		frame.getContentPane().add(update_button);
		//取消按钮事件
		JButton cancel_button = new JButton("\u53D6\u6D88");
		cancel_button.setBackground(Color.YELLOW);
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancel_button.setBounds(241, 233, 93, 23);
		frame.getContentPane().add(cancel_button);
		//获取原来歌曲是否可以下载
		switch(music.getMusic_status()) {
			case 1:
				status_Button1.setSelected(true);
				break;
			case 2:
				status_Button_2.setSelected(true);
				break;
			case 3:
				status_Button_3.setSelected(true);
			default:
				
				break;
		}
		
	}
}
