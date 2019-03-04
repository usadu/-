package com.etc.frame;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.etc.dao.CollectionDao;
import com.etc.dao.MusicDao;
import com.etc.dao.UserDao;
import com.etc.entity.Collection;
import com.etc.entity.HeaderCellRenderer;
import com.etc.entity.Music;
import com.thread.PlayerThread;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Font;
/**
 * 用户主界面
 * @author msi
 *
 */
public class TestmenuFrame extends JFrame implements ActionListener {

	private JTextField likename;
	private JTable table;
	private JButton queryallmusic;
	private JComboBox<String> comboBox;
	private String username;
	private int userid;
	ArrayList<Music> list;
	JPopupMenu popupMenu;
	JMenuItem dele_btn ;
	// 定义图片
	private ImageIcon bgImg = new ImageIcon(this.getClass().getResource("/image/main.png"));
	private JLabel imgLabel = new JLabel(bgImg);
	/**
	 * Launch the application.
	 */

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// Testmenuthis window = new Testmenuthis();
	// window.this.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Create the application.
	 */
	public TestmenuFrame() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(10, 10, 800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		//取消窗口的边框
		this.setUndecorated(true);
		//上面的系统条隐藏
		this.getRootPane().setWindowDecorationStyle(0);//关闭的样式没有,所以不能关闭
		// 将JFrame上自带的面板设置为透明，否则背景图片不会显示出来
		((JPanel) getContentPane()).setOpaque(false);
		JLabel imgLabel = new JLabel(bgImg);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, bgImg.getIconWidth(), bgImg.getIconHeight());
		this.setLocationRelativeTo(null);
	
		likename = new JTextField();
		likename.setBackground(SystemColor.controlHighlight);
		likename.setBounds(350, 80, 220, 32);
		this.getContentPane().add(likename);
		likename.setColumns(10);
		// 查询按钮功能
		JButton searchButton = new JButton("");
		searchButton.setBounds(580, 83, 25, 25);
		
		//插入搜索图片
		ImageIcon search = new ImageIcon("search2.png");
		//设置图片大小
		search = new ImageIcon(search.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		searchButton.setIcon(search);//重置按钮
		searchButton.setContentAreaFilled(false);//设置按钮透明
		searchButton.setToolTipText("搜索音乐");//按钮悬停提示信息
		searchButton.setBorderPainted(false);//去除边框
		searchButton.setFocusPainted(true);//获取焦点
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.remove(dele_btn);
				String name = likename.getText();
				if (name.trim().equals("") && name.equals("")) {
					System.out.println("");
				} else {
					list = new CollectionDao().querylike(name);
					String[] col = { "音乐编号", "歌名", "歌手", "类型", "时间", "状态" };
					Object[][] data = new Object[list.size()][6];
					for (int i = 0; i < list.size(); i++) {
						Music music = list.get(i);
						data[i][0] = i + 1;
						data[i][1] = music.getMusic_name();
						data[i][2] = music.getMusic_singer();
						data[i][3] = music.getType_name();
						data[i][4] = music.getMusic_time();
						data[i][5] = music.getMusic_status();
					}
					table.setModel(new DefaultTableModel(data, col));
				}
			}
		});
		this.getContentPane().add(searchButton);
		// 用户登录显示音乐库音乐在列表

		table = new JTable();
		table.setOpaque(false);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		render.setOpaque(false); // 将渲染器设置为透明

		// 将这个渲染器设置到fileTable里。

		// 这个设置在没有另外专门对column设置的情况下有效

		// 若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器

		// 因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明

		table.setDefaultRenderer(Object.class, render);

		// 设置显示范围

		Dimension viewSize = new Dimension();

		viewSize.width = table.getColumnModel().getTotalColumnWidth();
		;

		viewSize.height = 10 * table.getRowHeight();

		table.setPreferredScrollableViewportSize(viewSize);

		// 设置头部透明

		// 头部实际上也是一个JTABLE，只有一行而已。

		JTableHeader header = table.getTableHeader();// 获取头部

		header.setPreferredSize(new Dimension(30, 26));

		header.setOpaque(false);// 设置头部为透明

		header.getTable().setOpaque(false);// 设置头部里面的表格透明

		/*
		 * 头部的表格也像前面的表格设置一样，还需要将里面的单元项设置为透明 因此同样需要对头部单元项进行透明度设置，这里还是用渲染器。
		 * 但这里有个问题就是，若将头部渲染器直接像上文一样设置，则它的下面没有横线 因此，我们需要一个专用的头部渲染器来手动画横线
		 */

		header.setDefaultRenderer(new HeaderCellRenderer());

		TableCellRenderer headerRenderer = header.getDefaultRenderer();

		if (headerRenderer instanceof JLabel)

		{

			((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER);

			((JLabel) headerRenderer).setOpaque(false);

		}
		queryAllmusic();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 144, 489, 233);
		scrollPane.getViewport().setOpaque(false);// 将JScrollPane设置为透明
		scrollPane.setOpaque(false);// 将中间的viewport设置为透明
		scrollPane.setViewportView(table);// 装载表格
		scrollPane.setColumnHeaderView(table.getTableHeader());// 设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);// 再取出头部，并设置为透明
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		Toolkit tk = this.getToolkit();
		Dimension dm = tk.getScreenSize();

		this.getContentPane().add(scrollPane);

		 popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		// 添加音乐到收藏库
		JMenuItem add_btn = new JMenuItem("添加");
		add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "是否要添加收藏", "添加警告", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					Object mname = table.getValueAt(table.getSelectedRow(), 1);// 获取歌名
					Object msinger = table.getValueAt(table.getSelectedRow(), 2);// 获取歌手
					int musicid = MusicDao.queryMusicid((String) mname, (String) msinger);

					ArrayList<Collection> list = new CollectionDao().queryAllCollection();
					boolean flag = false;
					Collection collection = null;
					// 判断收藏集合里面是否已存在这首歌
					for (int i = 0; i < list.size(); i++) {
						collection = list.get(i);

						if (collection.getMusic_id() == musicid && collection.getUser_id() == userid) {
							flag = true;
						}
					}
					if (flag) {
						JOptionPane.showMessageDialog(null, "已存在");
					} else {
						Collection collection1 =new Collection(musicid,userid);
						new CollectionDao().addcollection(collection1);
						JOptionPane.showMessageDialog(null, "添加成功");
					}
					/*
					 * if(flag) { Collection c=new Collection(name,
					 * collection_singer, collection_type,collection_path); new
					 * CollectionDao().addcollection(c);
					 * JOptionPane.showMessageDialog(null, "添加成功"); }else {
					 * JOptionPane.showMessageDialog(null, "已存在"); }
					 */
					/*
					 * boolean flag=list.contains(c); System.out.println(flag);
					 * if(!flag) { new CollectionDao().addcollection(c);
					 * JOptionPane.showMessageDialog(null, "添加成功"); }else {
					 * JOptionPane.showMessageDialog(null, "已存在");
					 * 
					 * }
					 */
				}

			}
		});
		popupMenu.add(add_btn);
		// 删除功能
		 dele_btn = new JMenuItem("\u5220\u9664");
		dele_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 提醒是否要删除
				int n = JOptionPane.showConfirmDialog(null, "是否要删除", "删除警告", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					Object mname = table.getValueAt(table.getSelectedRow(), 1);// 获取歌名
					Object msinger = table.getValueAt(table.getSelectedRow(), 2);// 获取歌手
					int musicid = MusicDao.queryMusicid((String) mname, (String) msinger);
					boolean flag = new CollectionDao().delecollection(musicid,userid);
					if (flag) {
						JOptionPane.showMessageDialog(null, "删除成功");
						queryAllcollection();
					}
				}
			}
		});
//		popupMenu.add(dele_btn);
		// 下载功能
		JMenuItem download_btn = new JMenuItem("\u4E0B\u8F7D");
		download_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "是否要下载", "下载警告", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					Object mid = table.getValueAt(table.getSelectedRow(), 0);
					int id = (Integer) mid;
					List<Music> list = new CollectionDao().queryAllmusic();
					for (int i = 0; i < list.size(); i++) {
						Music music = list.get(i);
						if (id == list.get(i).getMusic_id()) {
							String path = list.get(i).getMusic_path();
							DownloadFrame downloadthis = new DownloadFrame();
							downloadthis.saveFile(path);
						}
					}
					// Downloadthis downloadthis=new Downloadthis(path);
					setVisible(false);
				}
			}
		});
	    popupMenu.add(download_btn);
		// 库存音乐按钮功能
		queryallmusic = new JButton("\u5E93\u5B58\u97F3\u4E50");
		queryallmusic.setFont(new Font("造字工房妙妙（非商用）常规体", Font.PLAIN, 18));
		queryallmusic.setBackground(Color.LIGHT_GRAY);
		queryallmusic.setBounds(82, 176, 120, 36);
		searchButton.setContentAreaFilled(false);
		queryallmusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.remove(dele_btn);
				queryAllmusic();
			}
		});
		this.getContentPane().add(queryallmusic);
		// 收藏按钮功能显示全部是收藏音乐
		JButton show_btn = new JButton("\u6536\u85CF");
		show_btn.setFont(new Font("造字工房妙妙（非商用）常规体", Font.PLAIN, 18));
		show_btn.setBackground(Color.LIGHT_GRAY);
		show_btn.setBounds(82, 232, 120, 36);
		show_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryAllcollection();
//				popupMenu.add(dele_btn);
//				popupMenu.add(download_btn);
//				list = new CollectionDao().queryAll(userid);
//				String[] col = { "音乐编号", "歌名", "歌手", "类型", "时间", "状态" };
//				Object[][] data = new Object[list.size()][6];
//				for (int i = 0; i < list.size(); i++) {
//					Music music = list.get(i);
//					data[i][0] = i + 1;
//					data[i][1] = music.getMusic_name();
//					data[i][2] = music.getMusic_singer();
//					data[i][3] = music.getType_name();
//					data[i][4] = music.getMusic_time();
//					data[i][5] = music.getMusic_status();
//				}
//				table.setModel(new DefaultTableModel(data, col));
			}
		});
		this.getContentPane().add(show_btn);

		JList list1 = new JList();
		list1.setBounds(74, 232, 1, 1);
		this.getContentPane().add(list1);

		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("造字工房妙妙（非商用）常规体", Font.PLAIN, 18));
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.addItem("流行");
		comboBox.addItem("民谣");
		comboBox.addItem("英语");
		comboBox.addActionListener(this);
		comboBox.setBounds(82, 296, 100, 36);
		String str = (String) comboBox.getSelectedItem();

		this.getContentPane().add(comboBox);

		JButton btnNewButton = new JButton("\u5F00\u59CB\u64AD\u653E");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerThread pf = new PlayerThread();
				
				pf.setUsername(username);
				pf.setUserid(userid);
				PlayerThread.setList(list);
					
					


			}
		});
		btnNewButton.setBounds(395, 404, 120, 36);
		this.getContentPane().add(btnNewButton);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBounds(670, 15, 100, 25);
		menuBar.setOpaque(false);
		getContentPane().add(menuBar);

		JMenu fileMenu = new JMenu("");
		
		menuBar.add(fileMenu);
		
		//插入文件图片
		ImageIcon file = new ImageIcon("file.png");
		//设置图片大小
		file = new ImageIcon(file.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		fileMenu.setIcon(file);//重置按钮
		fileMenu.setContentAreaFilled(false);//设置按钮透明
		fileMenu.setToolTipText("文件操作");//按钮悬停提示信息
		fileMenu.setBorderPainted(false);//去除边框
		fileMenu.setFocusPainted(true);//获取焦点
		

		

		JMenuItem returnMenuItem = new JMenuItem("返回");
		returnMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// PlayerThread.stop();

				new FirstFrame();
				
				dispose();

			}
		});
		fileMenu.add(returnMenuItem);

		JMenuItem quitMenuItem = new JMenuItem("退出");
		quitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(quitMenuItem);

		JMenu systemMenu = new JMenu("");
		systemMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(systemMenu);
		
		//插入设置图片
		ImageIcon setting = new ImageIcon("setting2.png");
		//设置图片大小
		setting = new ImageIcon(setting.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		systemMenu.setIcon(setting);//重置按钮
		systemMenu.setContentAreaFilled(false);//设置按钮透明
		systemMenu.setToolTipText("设置");//按钮悬停提示信息
		systemMenu.setBorderPainted(false);//去除边框
		systemMenu.setFocusPainted(true);//获取焦点
		

		JMenuItem closeMenuItem = new JMenuItem("自动关机");
		closeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColseCPFrame close=new ColseCPFrame();
				close.setUsername(username);
				close.setUserid(userid);
				
			}
		});
		systemMenu.add(closeMenuItem);
		
		JButton closeButton = new JButton("");
		getContentPane().add(closeButton);
		closeButton.setBounds(750, 15, 25, 25);
		//插入退出图片
		ImageIcon exit = new ImageIcon("exit.png");
		//设置图片大小
		exit = new ImageIcon(exit.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		closeButton.setIcon(exit);//重置按钮
		closeButton.setContentAreaFilled(false);//设置按钮透明
		closeButton.setToolTipText("退出");//按钮悬停提示信息
		closeButton.setBorderPainted(false);//去除边框
		closeButton.setFocusPainted(true);//获取焦点
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		this.setVisible(true);
	}

	public void VisibleSet() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		popupMenu.remove(dele_btn);
		int i = comboBox.getSelectedIndex() + 1;
		String s = (String) comboBox.getSelectedItem();
		list = new CollectionDao().queryType(i);
		String[] col = { "音乐编号", "歌名", "歌手", "类型", "时间", "状态" };
		Object[][] data = new Object[list.size()][6];
		for (int j = 0; j < list.size(); j++) {
			Music music = list.get(j);
			data[j][0] = j + 1;
			data[j][1] = music.getMusic_name();
			data[j][2] = music.getMusic_singer();
			data[j][3] = music.getType_name();
			data[j][4] = music.getMusic_time();
			data[j][5] = music.getMusic_status();
		}
		table.setModel(new DefaultTableModel(data, col));
		// System.out.println("你选中的是第"+i+"项"+",内容是:"+s);
		// 把i插入你数据库中对应的属性字段

	}
//查所有收藏
	public void queryAllcollection(){
		popupMenu.add(dele_btn);
		list = new CollectionDao().queryAll(userid);
		String[] col = { "音乐编号", "歌名", "歌手", "类型", "时间", "状态" };
		Object[][] data = new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			Music music = list.get(i);
			data[i][0] = i + 1;
			data[i][1] = music.getMusic_name();
			data[i][2] = music.getMusic_singer();
			data[i][3] = music.getType_name();
			data[i][4] = music.getMusic_time();
			data[i][5] = music.getMusic_status();
		}
		table.setModel(new DefaultTableModel(data, col));
	}
	// 用来调用:查找音乐库所有音乐的方法
	public void queryAllmusic() {
		list = new CollectionDao().queryAllmusic();
		String[] col = { "音乐编号", "歌名", "歌手", "类型", "时间", "状态" };
		Object[][] data = new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			Music music = list.get(i);
			data[i][0] = music.getMusic_id();
			data[i][1] = music.getMusic_name();
			data[i][2] = music.getMusic_singer();
			data[i][3] = music.getType_name();
			data[i][4] = music.getMusic_time();
			data[i][5] = music.getMusic_status();
		}
		table.setModel(new DefaultTableModel(data, col));
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
