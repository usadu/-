package com.etc.frame;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.etc.entity.Music;
import com.thread.PlayerThread;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
/**
 * 播放界面
 * @author msi
 *
 */
public class PlayerFrame extends JFrame {
	private JTable table;
	private JButton playButton;
	private JButton stopButton;
	private JButton previousButton;
	private JButton nextButton;
	private JMenuItem openMenuItem;
	ArrayList<Music> list = null;
	private JSlider Volumeslider;
	private JProgressBar progress;
	private int obj;
	private JPanel panel;
	EmbeddedMediaPlayerComponent playerComponent;
	private JMenu systemMenu;
	private JMenuItem closeMenuItem;
	private Music m;
	private String s;
	private JLabel label;
	private ImageIcon bgImg = new ImageIcon(this.getClass().getResource("/image/PlayerFrame.png"));
	private JLabel imgLabel = new JLabel(bgImg);
	private String username;
	private int userid;
	/**
	 * @wbp.nonvisual location=-23,437
	 */
	private final JTable table_2 = new JTable();
	
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQu eue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// Player window = new Player();
	// window.this.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	public JTable getTable() {
		return table;
	}

	

	public JLabel getLabel() {
		return label;
	}



	public void setLabel(JLabel label) {
		this.label = label;
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



	public JButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	public int getObj() {
		return obj;
	}

	public void setObj(int obj) {
		this.obj = obj;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Music getM() {
		return m;
	}

	public void setM(Music m) {
		this.m = m;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * Create the application.
	 */
	public PlayerFrame(ArrayList<Music> list) {
		this.list = list;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 1150,700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("播放界面");
		this.setResizable(false);
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(30, 76, 417, 302);
//		this.getContentPane().add(scrollPane);
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
		
//		table = new JTable();
//		Object[][] date = new Object[list.size()][3];
//		for (int i = 0; i < list.size(); i++) {
//			date[i][0] = i + 1;
//			date[i][1] = list.get(i).getMusic_name();
//			date[i][2] = list.get(i).getMusic_singer();
//		}
//		table.setModel(new DefaultTableModel(date, new String[] { "New column", "\u6B4C\u540D", "\u6B4C\u624B" }));
//		table.getColumnModel().getColumn(0).setPreferredWidth(15);
//		scrollPane.setRowHeaderView(table);//
		table = new JTable();
		Object[][] data=new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = i+1;
			data[i][1] = list.get(i).getMusic_name();
			data[i][2] = list.get(i).getMusic_singer();
		}
		
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"", "\u6B4C\u540D", "\u6B4C\u624B"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		JMenuItem menuItem = new JMenuItem("\u6DFB\u52A0\u6536\u85CF");
		popupMenu.add(menuItem);
		/**
		 * 播放按钮table.addRowSelectionInterval(0,0);
		 */
		playButton = new JButton("播放");
		playButton.setBounds(714, 534, 73, 36);
		playButton.setBackground(Color.LIGHT_GRAY);
		playButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					 obj = (int) table.getValueAt(table.getSelectedRow(), 0);
//					 				} catch (Exception e2) {
//					JOptionPane.showMessageDialog(null, "未选择歌曲");
//				}
//				m = list.get(obj- 1);
//				
				if (playButton.getText().equals("播放")) {
					try {
						obj = (int) table.getValueAt(table.getSelectedRow(), 0);
					
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "未选择歌曲");
					}
					m = list.get(obj- 1);
					PlayerThread.playfile(m.getMusic_path());
					label.setText("正在播放："+m.getMusic_name());
					playButton.setText("暂停");
				} 
				else if(playButton.getText().equals("暂停")){
					PlayerThread.pause();
					playButton.setText("继续");
				}else{
					PlayerThread.play();
					playButton.setText("暂停");
				}
			}
		});
		getContentPane().setLayout(null);
		
		
		this.getContentPane().add(playButton);
		// playButton.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// obj = table.getValueAt(table.getSelectedRow(), 0);
		// Music m = list.get(((Integer) obj) - 1);
		// PlayerThread pt = new PlayerThread();
		// pt.setFile(m.getMusicpath());
		// pt.start();
		// }
		// });

		/**
		 * 停止按钮
		 */
		stopButton = new JButton("停止");
		stopButton.setBounds(880, 534, 73, 36);
		stopButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		stopButton.setBackground(Color.LIGHT_GRAY);
		stopButton.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e) {  
	                // TODO Auto-generated method stub  
	            	PlayerThread.stop();   
	            	label.setText("结束播放");
	            	playButton.setText("播放");
	            }  
	        });  
		this.getContentPane().add(stopButton);
		/**
		 * 上一首
		 */
		previousButton = new JButton("\u4E0A\u4E00\u9996");
		previousButton.setBounds(631, 534, 73, 36);
		previousButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		previousButton.setBackground(Color.LIGHT_GRAY);
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(obj==1){
					JOptionPane.showMessageDialog(null, "没有上一首啦！！");
				}else{
					
					obj--;
					m = list.get(obj-1);	
				
					PlayerThread.playfile(m.getMusic_path());
					label.setText("正在播放："+m.getMusic_name());
					
				
				}
			}
		});
		this.getContentPane().add(previousButton);
		/**
		 * 下一首
		 */
		nextButton = new JButton("\u4E0B\u4E00\u9996");
		nextButton.setBounds(797, 534, 73, 36);
		nextButton.setBackground(Color.LIGHT_GRAY);
		nextButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//m = list.get(obj- 1);
				if(obj==list.size()){
					JOptionPane.showMessageDialog(null, "没有下一首啦！！");
				}else{
					m = list.get(obj);
		
					PlayerThread.playfile(m.getMusic_path());
					label.setText("正在播放："+m.getMusic_name());
//					new SwingWorker<String, Integer>(){
//
//						@Override
//						protected String doInBackground() throws Exception {
//							// TODO Auto-generated method stub
//							while (true) { // 获取视频播放进度并且按百分比显示
//								long total =getMediaPlayer().getLength();
//								long curr = getMediaPlayer().getTime();
//								float percent = (float) curr / total;
//								publish((int) (percent * 100));
//								if(total==-1) {
//									break;
//								}
//								}
//							return null;
//						}
//					};
					obj++;
				}
			}
			
		});
		this.getContentPane().add(nextButton);
		
		
		String[] v = { "单曲循环" , "随机播放", "顺序播放"};
//		JComboBox<String> comboBox = new JComboBox<String>(v);
		JComboBox comboBox = new JComboBox(v);
		comboBox.setBounds(548, 534, 80, 36);
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 s=(String)comboBox.getSelectedItem();
				 System.out.println(s);
			}
		});
		this.getContentPane().add(comboBox);
		/**
		 * 音量控制
		 */
		Volumeslider = new JSlider();
		Volumeslider.setBounds(973, 632, 63, 23);
		Volumeslider.setBackground(Color.LIGHT_GRAY);
		Volumeslider.setValue(80);
		Volumeslider.setMaximum(100);  
		Volumeslider.addChangeListener(new ChangeListener() {  
            
            @Override  
            public void stateChanged(ChangeEvent e) {  
                // TODO Auto-generated method stub  
            	PlayerThread.setVol(Volumeslider.getValue());  
            }  
        });  
		this.getContentPane().add(Volumeslider);

		/**
		 * 进度条
		 */
		progress = new JProgressBar();
		progress.setBounds(549, 636, 404, 15);
		progress.setBackground(Color.ORANGE);
		progress.addMouseListener(new MouseAdapter() {  
	            @Override  
	            public void mouseClicked(MouseEvent e){     //点击进度条调整视屏播放进度  
	                int x=e.getX();  
	                PlayerThread.jumpTo((float)x/progress.getWidth());  
	            }  
	        });  
	    progress.setStringPainted(true);  
		this.getContentPane().add(progress);
/**
 * 视频播放界面部分
 */
		panel = new JPanel();
		panel.setBounds(469, 68, 650, 445);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));  
		panel.setLayout(new BorderLayout(0,0));  
		
		JPanel videoPane = new JPanel();
		panel.add(videoPane, BorderLayout.CENTER);
		videoPane.setLayout(new BorderLayout(0, 0));
		playerComponent = new EmbeddedMediaPlayerComponent();
		videoPane.add(playerComponent);
        this.getContentPane().add(panel);
        
        JButton btnNewButton = new JButton("MV");
        btnNewButton.setBounds(963, 534, 73, 36);
        btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					playButton.setText("暂停");
					getMediaPlayer().playMedia(m.getMusic_photo());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "还没有这首歌的mv");
					PlayerThread.stop();
					label.setText("当前没有文件在播放");
	            	playButton.setText("播放");
				}
        	}
        });
        this.getContentPane().add(btnNewButton);
        
        label = new JLabel();
        label.setText("未选择播放播放");
        label.setBounds(656, 595, 280, 23);
       
        this.getContentPane().add(label);
		  
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(1005, 15, 100, 25);
		menuBar.setBorderPainted(false);
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
		

		JMenuItem openMenuItem = new JMenuItem("打开");
		openMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				PlayerThread.openVideo();

			}
		});
		fileMenu.add(openMenuItem);

		JMenuItem returnMenuItem = new JMenuItem("返回");
		returnMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// PlayerThread.stop();

				TestmenuFrame tf = new TestmenuFrame();
				tf.setUsername(username);
				tf.setUserid(userid);
				PlayerThread.stop();   
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
		closeButton.setBounds(1094, 15, 25, 25);
		getContentPane().add(closeButton);
		//插入退出图片
		ImageIcon exit = new ImageIcon("exit.png");
		//设置图片大小
		exit = new ImageIcon(exit.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		closeButton.setIcon(exit);//重置按钮
		closeButton.setContentAreaFilled(false);//设置按钮透明
		closeButton.setToolTipText("退出");//按钮悬停提示信息
		closeButton.setBorderPainted(false);//去除边框
		closeButton.setFocusPainted(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 65, 420, 285);
		getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerThread.stop();
				dispose();
			}
		});

		this.setVisible(true);
	}

	public void VisibleSet() {
		this.setVisible(true);
	}

	public void Close1(){
		this.dispose();
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

	 /**
	  * 获取播放媒体实例（某个视频）  
	  * 
	  */
    public EmbeddedMediaPlayer getMediaPlayer() {  
        return playerComponent.getMediaPlayer();  
    }  
    /**
	  * 获取进度条实例  
	  * 
	  */
    public JProgressBar getProgressBar() {  
        return progress;  
    }  
}
