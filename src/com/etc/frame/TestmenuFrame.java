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
 * �û�������
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
	// ����ͼƬ
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
		
		//ȡ�����ڵı߿�
		this.setUndecorated(true);
		//�����ϵͳ������
		this.getRootPane().setWindowDecorationStyle(0);//�رյ���ʽû��,���Բ��ܹر�
		// ��JFrame���Դ����������Ϊ͸�������򱳾�ͼƬ������ʾ����
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
		// ��ѯ��ť����
		JButton searchButton = new JButton("");
		searchButton.setBounds(580, 83, 25, 25);
		
		//��������ͼƬ
		ImageIcon search = new ImageIcon("search2.png");
		//����ͼƬ��С
		search = new ImageIcon(search.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		searchButton.setIcon(search);//���ð�ť
		searchButton.setContentAreaFilled(false);//���ð�ť͸��
		searchButton.setToolTipText("��������");//��ť��ͣ��ʾ��Ϣ
		searchButton.setBorderPainted(false);//ȥ���߿�
		searchButton.setFocusPainted(true);//��ȡ����
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.remove(dele_btn);
				String name = likename.getText();
				if (name.trim().equals("") && name.equals("")) {
					System.out.println("");
				} else {
					list = new CollectionDao().querylike(name);
					String[] col = { "���ֱ��", "����", "����", "����", "ʱ��", "״̬" };
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
		// �û���¼��ʾ���ֿ��������б�

		table = new JTable();
		table.setOpaque(false);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		render.setOpaque(false); // ����Ⱦ������Ϊ͸��

		// �������Ⱦ�����õ�fileTable�

		// ���������û������ר�Ŷ�column���õ��������Ч

		// �����ĳ��column����ָ������Ⱦ������������column������������render��Ⱦ��

		// ���Ϊ�˱�֤͸����������column����ָ������Ⱦ������ô�ڶ������Ⱦ����ҲӦ������͸��

		table.setDefaultRenderer(Object.class, render);

		// ������ʾ��Χ

		Dimension viewSize = new Dimension();

		viewSize.width = table.getColumnModel().getTotalColumnWidth();
		;

		viewSize.height = 10 * table.getRowHeight();

		table.setPreferredScrollableViewportSize(viewSize);

		// ����ͷ��͸��

		// ͷ��ʵ����Ҳ��һ��JTABLE��ֻ��һ�ж��ѡ�

		JTableHeader header = table.getTableHeader();// ��ȡͷ��

		header.setPreferredSize(new Dimension(30, 26));

		header.setOpaque(false);// ����ͷ��Ϊ͸��

		header.getTable().setOpaque(false);// ����ͷ������ı��͸��

		/*
		 * ͷ���ı��Ҳ��ǰ��ı������һ��������Ҫ������ĵ�Ԫ������Ϊ͸�� ���ͬ����Ҫ��ͷ����Ԫ�����͸�������ã����ﻹ������Ⱦ����
		 * �������и�������ǣ�����ͷ����Ⱦ��ֱ��������һ�����ã�����������û�к��� ��ˣ�������Ҫһ��ר�õ�ͷ����Ⱦ�����ֶ�������
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
		scrollPane.getViewport().setOpaque(false);// ��JScrollPane����Ϊ͸��
		scrollPane.setOpaque(false);// ���м��viewport����Ϊ͸��
		scrollPane.setViewportView(table);// װ�ر��
		scrollPane.setColumnHeaderView(table.getTableHeader());// ����ͷ����HeaderView���֣�
		scrollPane.getColumnHeader().setOpaque(false);// ��ȡ��ͷ����������Ϊ͸��
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		Toolkit tk = this.getToolkit();
		Dimension dm = tk.getScreenSize();

		this.getContentPane().add(scrollPane);

		 popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		// ������ֵ��ղؿ�
		JMenuItem add_btn = new JMenuItem("���");
		add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ����ղ�", "��Ӿ���", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					Object mname = table.getValueAt(table.getSelectedRow(), 1);// ��ȡ����
					Object msinger = table.getValueAt(table.getSelectedRow(), 2);// ��ȡ����
					int musicid = MusicDao.queryMusicid((String) mname, (String) msinger);

					ArrayList<Collection> list = new CollectionDao().queryAllCollection();
					boolean flag = false;
					Collection collection = null;
					// �ж��ղؼ��������Ƿ��Ѵ������׸�
					for (int i = 0; i < list.size(); i++) {
						collection = list.get(i);

						if (collection.getMusic_id() == musicid && collection.getUser_id() == userid) {
							flag = true;
						}
					}
					if (flag) {
						JOptionPane.showMessageDialog(null, "�Ѵ���");
					} else {
						Collection collection1 =new Collection(musicid,userid);
						new CollectionDao().addcollection(collection1);
						JOptionPane.showMessageDialog(null, "��ӳɹ�");
					}
					/*
					 * if(flag) { Collection c=new Collection(name,
					 * collection_singer, collection_type,collection_path); new
					 * CollectionDao().addcollection(c);
					 * JOptionPane.showMessageDialog(null, "��ӳɹ�"); }else {
					 * JOptionPane.showMessageDialog(null, "�Ѵ���"); }
					 */
					/*
					 * boolean flag=list.contains(c); System.out.println(flag);
					 * if(!flag) { new CollectionDao().addcollection(c);
					 * JOptionPane.showMessageDialog(null, "��ӳɹ�"); }else {
					 * JOptionPane.showMessageDialog(null, "�Ѵ���");
					 * 
					 * }
					 */
				}

			}
		});
		popupMenu.add(add_btn);
		// ɾ������
		 dele_btn = new JMenuItem("\u5220\u9664");
		dele_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����Ƿ�Ҫɾ��
				int n = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫɾ��", "ɾ������", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					Object mname = table.getValueAt(table.getSelectedRow(), 1);// ��ȡ����
					Object msinger = table.getValueAt(table.getSelectedRow(), 2);// ��ȡ����
					int musicid = MusicDao.queryMusicid((String) mname, (String) msinger);
					boolean flag = new CollectionDao().delecollection(musicid,userid);
					if (flag) {
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						queryAllcollection();
					}
				}
			}
		});
//		popupMenu.add(dele_btn);
		// ���ع���
		JMenuItem download_btn = new JMenuItem("\u4E0B\u8F7D");
		download_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ����", "���ؾ���", JOptionPane.YES_NO_OPTION);
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
		// ������ְ�ť����
		queryallmusic = new JButton("\u5E93\u5B58\u97F3\u4E50");
		queryallmusic.setFont(new Font("���ֹ�����������ã�������", Font.PLAIN, 18));
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
		// �ղذ�ť������ʾȫ�����ղ�����
		JButton show_btn = new JButton("\u6536\u85CF");
		show_btn.setFont(new Font("���ֹ�����������ã�������", Font.PLAIN, 18));
		show_btn.setBackground(Color.LIGHT_GRAY);
		show_btn.setBounds(82, 232, 120, 36);
		show_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryAllcollection();
//				popupMenu.add(dele_btn);
//				popupMenu.add(download_btn);
//				list = new CollectionDao().queryAll(userid);
//				String[] col = { "���ֱ��", "����", "����", "����", "ʱ��", "״̬" };
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
		comboBox.setFont(new Font("���ֹ�����������ã�������", Font.PLAIN, 18));
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.addItem("����");
		comboBox.addItem("��ҥ");
		comboBox.addItem("Ӣ��");
		comboBox.addActionListener(this);
		comboBox.setBounds(82, 296, 100, 36);
		String str = (String) comboBox.getSelectedItem();

		this.getContentPane().add(comboBox);

		JButton btnNewButton = new JButton("\u5F00\u59CB\u64AD\u653E");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
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
		
		//�����ļ�ͼƬ
		ImageIcon file = new ImageIcon("file.png");
		//����ͼƬ��С
		file = new ImageIcon(file.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		fileMenu.setIcon(file);//���ð�ť
		fileMenu.setContentAreaFilled(false);//���ð�ť͸��
		fileMenu.setToolTipText("�ļ�����");//��ť��ͣ��ʾ��Ϣ
		fileMenu.setBorderPainted(false);//ȥ���߿�
		fileMenu.setFocusPainted(true);//��ȡ����
		

		

		JMenuItem returnMenuItem = new JMenuItem("����");
		returnMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// PlayerThread.stop();

				new FirstFrame();
				
				dispose();

			}
		});
		fileMenu.add(returnMenuItem);

		JMenuItem quitMenuItem = new JMenuItem("�˳�");
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
		
		//��������ͼƬ
		ImageIcon setting = new ImageIcon("setting2.png");
		//����ͼƬ��С
		setting = new ImageIcon(setting.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		systemMenu.setIcon(setting);//���ð�ť
		systemMenu.setContentAreaFilled(false);//���ð�ť͸��
		systemMenu.setToolTipText("����");//��ť��ͣ��ʾ��Ϣ
		systemMenu.setBorderPainted(false);//ȥ���߿�
		systemMenu.setFocusPainted(true);//��ȡ����
		

		JMenuItem closeMenuItem = new JMenuItem("�Զ��ػ�");
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
		//�����˳�ͼƬ
		ImageIcon exit = new ImageIcon("exit.png");
		//����ͼƬ��С
		exit = new ImageIcon(exit.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
		closeButton.setIcon(exit);//���ð�ť
		closeButton.setContentAreaFilled(false);//���ð�ť͸��
		closeButton.setToolTipText("�˳�");//��ť��ͣ��ʾ��Ϣ
		closeButton.setBorderPainted(false);//ȥ���߿�
		closeButton.setFocusPainted(true);//��ȡ����
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
		String[] col = { "���ֱ��", "����", "����", "����", "ʱ��", "״̬" };
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
		// System.out.println("��ѡ�е��ǵ�"+i+"��"+",������:"+s);
		// ��i���������ݿ��ж�Ӧ�������ֶ�

	}
//�������ղ�
	public void queryAllcollection(){
		popupMenu.add(dele_btn);
		list = new CollectionDao().queryAll(userid);
		String[] col = { "���ֱ��", "����", "����", "����", "ʱ��", "״̬" };
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
	// ��������:�������ֿ��������ֵķ���
	public void queryAllmusic() {
		list = new CollectionDao().queryAllmusic();
		String[] col = { "���ֱ��", "����", "����", "����", "ʱ��", "״̬" };
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
