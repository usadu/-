package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.etc.dao.MusicDao;
import com.etc.entity.Music;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.Font;
/**
 * 添加歌曲界面*******************************
 * @author Administrator
 *
 */
public class AddMusicFrame {

	private JFrame frame;
	private JTextField text_music_name;
	private JTextField test_music_singer;
	private JTextField test_type_id;
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
					AddMusicFrame window = new AddMusicFrame();
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
	public AddMusicFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("\u6B4C\u540D:");
		label_1.setBounds(72, 49, 54, 15);
		frame.getContentPane().add(label_1);
		
		text_music_name = new JTextField();
		text_music_name.setText((String) null);
		text_music_name.setColumns(10);
		text_music_name.setBounds(154, 49, 149, 15);
		frame.getContentPane().add(text_music_name);
		
		JLabel label_2 = new JLabel("\u6B4C\u624B:");
		label_2.setBounds(72, 72, 54, 15);
		frame.getContentPane().add(label_2);
		
		test_music_singer = new JTextField();
		test_music_singer.setText((String) null);
		test_music_singer.setColumns(10);
		test_music_singer.setBounds(154, 72, 149, 15);
		frame.getContentPane().add(test_music_singer);
		
		JLabel label_3 = new JLabel("\u7C7B\u578B(\u7F16\u53F7):");
		label_3.setBounds(72, 96, 72, 15);
		frame.getContentPane().add(label_3);
		
		test_type_id = new JTextField();
		test_type_id.setColumns(10);
		test_type_id.setBounds(154, 96, 149, 15);
		frame.getContentPane().add(test_type_id);
		
		JLabel label_4 = new JLabel("\u4E0A\u4F20\u65F6\u95F4:");
		label_4.setBounds(72, 121, 62, 15);
		frame.getContentPane().add(label_4);
		
		test_music_time = new JTextField();
		test_music_time.setEnabled(false);
		test_music_time.setEditable(false);
		test_music_time.setText((String) null);
		test_music_time.setColumns(10);
		test_music_time.setBounds(154, 121, 149, 15);
		frame.getContentPane().add(test_music_time);
		
		JLabel label_5 = new JLabel("\u97F3\u4E50\u8DEF\u5F84:");
		label_5.setBounds(54, 148, 72, 15);
		frame.getContentPane().add(label_5);
		
		test_music_path = new JTextField();
		test_music_path.setEnabled(false);
		test_music_path.setEditable(false);
		test_music_path.setText((String) null);
		test_music_path.setColumns(10);
		test_music_path.setBounds(154, 148, 149, 15);
		frame.getContentPane().add(test_music_path);
		
		JLabel label_6 = new JLabel("\u56FE\u7247\u8DEF\u5F84:");
		label_6.setBounds(54, 181, 72, 15);
		frame.getContentPane().add(label_6);
		
		test_music_photo = new JTextField();
		test_music_photo.setEnabled(false);
		test_music_photo.setEditable(false);
		test_music_photo.setText((String) null);
		test_music_photo.setColumns(10);
		test_music_photo.setBounds(154, 181, 149, 15);
		frame.getContentPane().add(test_music_photo);
		
		JLabel label_7 = new JLabel("\u662F\u5426\u53EF\u4EE5\u4E0B\u8F7D:");
		label_7.setBounds(74, 210, 78, 15);
		frame.getContentPane().add(label_7);
		
		JRadioButton radioButton = new JRadioButton("\u6B63\u5E38");
		buttonGroup.add(radioButton);
		radioButton.setSelected(true);
		radioButton.setBounds(157, 206, 54, 23);
		frame.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u7981\u6B62");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(223, 207, 54, 23);
		frame.getContentPane().add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("\u6761\u4EF6\u4E0B\u8F7D");
		buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(284, 207, 78, 23);
		frame.getContentPane().add(radioButton_2);
		
		JLabel label = new JLabel("\u8BF7\u586B\u5199\u8981\u6DFB\u52A0\u6B4C\u66F2\u7684\u4FE1\u606F");
		label.setFont(new Font("宋体", Font.BOLD, 16));
		label.setBounds(125, 10, 216, 29);
		frame.getContentPane().add(label);
		//选择路径按钮
		JButton chooser_music_button = new JButton("\u8BF7\u9009\u62E9\u8DEF\u5F84");
		chooser_music_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();             //设置选择器
				 chooser.setMultiSelectionEnabled(true);             //设为多选
				int returnVal = chooser.showOpenDialog(chooser_music_button);        //是否打开文件选择框
				//System.out.println("returnVal="+returnVal);
				 
				if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型
				 
				String filepath = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径
				test_music_path.setText(filepath);
				//System.out.println(filepath);
				 
				 
				//System.out.println("You chose to open this file: "+ chooser.getSelectedFile().getName());  //输出相对路径
				 
				}
				
			}
		});
		chooser_music_button.setBounds(316, 144, 108, 23);
		frame.getContentPane().add(chooser_music_button);
		//选择图片路径按钮
		JButton chooser_photo_button = new JButton("\u8BF7\u9009\u62E9\u8DEF\u5F84");
		chooser_photo_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();             //设置选择器
				 chooser.setMultiSelectionEnabled(true);             //设为多选
				int returnVal = chooser.showOpenDialog(chooser_photo_button);        //是否打开文件选择框
				//System.out.println("returnVal="+returnVal);
				 
				if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型
				 
				String filepath = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径
				test_music_photo.setText(filepath);
				//System.out.println(filepath);
				 
				 
				//System.out.println("You chose to open this file: "+ chooser.getSelectedFile().getName());  //输出相对路径
				 
				}
			}
		});
		chooser_photo_button.setBounds(316, 177, 108, 23);
		frame.getContentPane().add(chooser_photo_button);
		//添加按钮事件
		JButton add_music_button = new JButton("\u6DFB\u52A0");
		add_music_button.setBackground(Color.YELLOW);
		add_music_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test_name=text_music_name.getText();
				String test_singer=test_music_singer.getText();
				int test_type=Integer.parseInt(test_type_id.getText());
				//获取当前时间
				Calendar c=Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
				String test_time=sdf.format(c.getTime());
				String test_path=test_music_path.getText();
				String test_photo=test_music_photo.getText();
				int test_status;
				if (radioButton.isSelected()) {
					test_status=1;
				 	}else if (radioButton_1.isSelected()) {
				 		test_status=2;
				 			}else {
				 					test_status=3;
				 			}
				Music music=new Music(test_name,test_singer,test_type,test_time,test_path,test_photo,test_status);
				boolean flag=new MusicDao().addMusic(music);
				if(flag) {
					int n=JOptionPane.showConfirmDialog(null,"是否继续添加", "添加成功", JOptionPane.YES_NO_OPTION);
					if(n==0) {
						
						text_music_name.setText("");
						test_music_singer.setText("");
						test_type_id.setText("");
						test_music_time.setText("");
						test_music_path.setText("");
						test_music_photo.setText("");
						ShowMusic.queryShowMusic();
					}else {
						frame.dispose();
				    	//主界面刷新
				    	ShowMusic.queryShowMusic();
				    	ShowMusic.showNo();
						
					}
			    }else {
			    	JOptionPane.showMessageDialog(null, "增加失败");
			    }
			}
		});
		add_music_button.setBounds(116, 235, 93, 23);
		frame.getContentPane().add(add_music_button);
		//取消按钮
		JButton up_button = new JButton("\u53D6\u6D88");
		up_button.setBackground(Color.YELLOW);
		up_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ShowMusic.queryShowMusic();
			}
		});
		up_button.setBounds(248, 235, 93, 23);
		frame.getContentPane().add(up_button);
		frame.setVisible(true);
	}
	

}
