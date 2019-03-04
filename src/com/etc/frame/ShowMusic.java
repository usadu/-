package com.etc.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.etc.dao.MemberDao;
import com.etc.dao.MusicDao;
import com.etc.entity.Music;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
	/**
	 * 管理员管理歌曲界面
	 * @author Administrator
	 *
	 */
public class ShowMusic {

	private JFrame frame;
	private static JTable table;
	private JTextField text_name;
	private static JTextField text_musicNo;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowMusic window = new ShowMusic();
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
	public ShowMusic() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 756, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u6765\u5230\u6B4C\u66F2\u7BA1\u7406\u754C\u9762");
		label.setFont(new Font("楷体", Font.BOLD, 16));
		label.setBounds(20, 0, 204, 47);
		frame.getContentPane().add(label);
		//添加歌曲按钮
		JButton addMusic_Button = new JButton("\u6DFB\u52A0\u6B4C\u66F2");
		addMusic_Button.setBackground(Color.YELLOW);
		addMusic_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMusicFrame addMusicFrame=new AddMusicFrame();
			}
		});
		addMusic_Button.setBounds(176, 327, 93, 23);
		frame.getContentPane().add(addMusic_Button);
		
		JScrollPane music_list = new JScrollPane();
		music_list.setBounds(20, 58, 671, 240);
		frame.getContentPane().add(music_list);
		/**
		 * 显示歌曲列表
		 */
		table = new JTable();
		List<Music> list=new MusicDao().queryAll();
		query(list);
		music_list.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		//修改歌曲按钮
		JMenuItem update_button = new JMenuItem("\u4FEE\u6539");
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj=table.getValueAt(table.getSelectedRow(), 0);
				if(null!=obj) {
					Music music=MusicDao.queryByMusic_id((Integer)obj);
					//这里跳到修改音乐界面
					UpdateFrame updateFtame=new UpdateFrame(music);
			    }else {
			    	JOptionPane.showMessageDialog(null, "请左键选择要修改的对象后修改");
			    }
				
			}
		});
		popupMenu.add(update_button);
		//删除按钮
		JMenuItem delete_button = new JMenuItem("\u5220\u9664");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null,"是否确认删除", "删除提示框", JOptionPane.YES_NO_OPTION);
				if (n==0) {
					Object obj=table.getValueAt(table.getSelectedRow(), 0);
					boolean flag=MusicDao.deleteMusic((Integer)obj);
					if(flag) {
						JOptionPane.showMessageDialog(null, "删除成功");
						List<Music> list=new MusicDao().queryAll();
						query(list);//删除成功刷新删除后的页面
						showNo();//显示歌曲数量
					}else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}
		});
		popupMenu.add(delete_button);
		//搜索输入框
		text_name = new JTextField();
		text_name.setBounds(443, 19, 147, 28);
		frame.getContentPane().add(text_name);
		text_name.setColumns(10);
		
		JLabel label_1 = new JLabel("\u641C\u7D22\u6B4C\u66F2:");
		label_1.setBounds(355, 25, 78, 15);
		frame.getContentPane().add(label_1);
		//查询歌曲按钮
		JButton searche = new JButton("\u641C\u7D22");
		searche.setBackground(Color.YELLOW);
		searche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=text_name.getText();
				if(name.trim().equals("") || name.equals("")) {
					//查询全部
				List<Music> list=new MusicDao().queryAll();
				query(list);
				}else {
					List<Music> list=new MusicDao().queryByMusic_name(name);
					query(list);
					text_musicNo.setText(String.valueOf(list.size()));
				}
			}
		});
		searche.setBounds(598, 21, 93, 23);
		frame.getContentPane().add(searche);
		//刷新歌曲列表按钮
		JButton refresh_button = new JButton("\u5237\u65B0\u9875\u9762");
		refresh_button.setBackground(Color.YELLOW);
		refresh_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryShowMusic();
				text_name.setText("");
				showNo();
			}
		});
		refresh_button.setBounds(355, 327, 93, 23);
		frame.getContentPane().add(refresh_button);
		//退出管理歌曲按钮
		JButton exit_button = new JButton("\u9000\u51FA");
		exit_button.setBackground(Color.YELLOW);
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		exit_button.setBounds(551, 327, 93, 23);
		frame.getContentPane().add(exit_button);
		
		JLabel label_2 = new JLabel("\u6B4C\u66F2\u6570\u91CF:");
		label_2.setBounds(20, 308, 54, 15);
		frame.getContentPane().add(label_2);
		
		text_musicNo = new JTextField();
		text_musicNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		text_musicNo.setBounds(84, 308, 42, 15);
		frame.getContentPane().add(text_musicNo);
		text_musicNo.setColumns(10);
		text_musicNo.setText(String.valueOf(new MusicDao().queryAll().size()));
	}
	//刷新主页面
	public static void queryShowMusic() {
		List<Music> list=new MusicDao().queryAll();
		
		String[]str=new String[] {
				"音乐编号", "歌名", "歌手", "类型(编号)", "上传时间", "音乐路径", "图片路径", "是否可以下载"
			};
		
		Object[][] data=new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			Music music=list.get(i);
			data[i][0]=music.getMusic_id();
			data[i][1]=music.getMusic_name();
			data[i][2]=music.getMusic_singer();
			data[i][3]=music.getType_id();
			data[i][4]=music.getMusic_time();
			data[i][5]=music.getMusic_path();
			data[i][6]=music.getMusic_photo();
			data[i][7]=music.getMusic_status();
		}
		table.setModel(new DefaultTableModel(
			data,
			str
			
		));
	}
	
	//歌曲列表显示方法
	public void query(List<Music> list) {
		String[]str=new String[] {
				"音乐编号", "歌名", "歌手", "类型(编号)", "上传时间", "音乐路径", "图片路径", "是否可以下载"
			};
		
		Object[][] data=new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			Music music=list.get(i);
			data[i][0]=music.getMusic_id();
			data[i][1]=music.getMusic_name();
			data[i][2]=music.getMusic_singer();
			data[i][3]=music.getType_id();
			data[i][4]=music.getMusic_time();
			data[i][5]=music.getMusic_path();
			data[i][6]=music.getMusic_photo();
			data[i][7]=music.getMusic_status();
		}
		table.setModel(new DefaultTableModel(
			data,
			str
			
		));
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
		//显示歌曲数量方法
	public static void showNo() {
		text_musicNo.setText(String.valueOf(new MusicDao().queryAll().size()));
	}
}
