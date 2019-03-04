package com.etc.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import com.etc.dao.AdminDao;
import com.etc.entity.Admin;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class FirstFrame extends JFrame{

	private JTextField textField;
	private JPasswordField passwordField;
	// ����ͼƬ
	private ImageIcon bgImg = new ImageIcon(this.getClass().getResource("/image/Login.png"));
	private JLabel imgLabel = new JLabel(bgImg);

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstFrame window = new FirstFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	//��¼ע�ᰴť
	private void initialize() {
		this.setBounds(100, 100, 358, 637);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.getRootPane().setWindowDecorationStyle(0);//�رյ���ʽû��,���Բ��ܹر�
		// ��Jthis���Դ����������Ϊ͸�������򱳾�ͼƬ������ʾ����
		((JPanel) getContentPane()).setOpaque(false);
		JLabel imgLabel = new JLabel(bgImg);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, bgImg.getIconWidth(), bgImg.getIconHeight());
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 105, 323, 299);
		this.getContentPane().add(tabbedPane_1);
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("�û���¼", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("\u8D26\u53F7\u767B\u9646");
		btnNewButton.setBounds(66, 71, 198, 41);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new UserLogonFrame();
				
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 19));
		
		JButton btnNewButton_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1.setBounds(66, 155, 198, 34);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new JoinFrame();
				
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 19));
		
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("����Ա", null, panel_1, null);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(134, 73, 137, 24);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458\u8D26\u53F7");
		lblNewLabel.setBounds(41, 79, 74, 18);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7BA1\u7406\u5458\u5BC6\u7801");
		lblNewLabel_1.setBounds(41, 128, 74, 18);
		panel_1.add(lblNewLabel_1);
		/**
		 * ��¼��ť�¼�
		 */
		JButton goin_button = new JButton("\u767B\u5F55");
		goin_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
										//1.��ȡ�û�������û���������
//						�û���
						String tf=textField.getText();
						//����
						char[] pf=passwordField.getPassword();
						//��ô��һ���ַ�����ת���ַ���
						String pwd=new String(pf);
						/*AdminDao ad=new AdminDao();
						Admin admin=ad.queryAll(tf, pwd);*/
						//2.�ж��û����������Ƿ�Ϊ��
						if(!tf.equals("") && !pwd.equals("")) {//��Ϊ��
							//Object AdminDao;
							//��¼����
							Admin admin=new AdminDao().queryAll(tf, pwd);
							if(admin!=null) {
							//a.������ʾ
								JOptionPane.showMessageDialog(null, "��¼�ɹ�");
							//b.��ת����ҳ��
								ShowAdminFrame showAdminthis=new ShowAdminFrame(admin);
							//c.���ص�ǰ��ҳ��
							setVisible(false);
							}else {
								//��¼ʧ��
								JOptionPane.showMessageDialog(null, "��¼ʧ��");
							}
							
						}else {
							//��ʾ������Ϊ��
							JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ��");
						}
					
					}
				
			
		});
				
		goin_button.setBounds(134, 173, 107, 38);
		panel_1.add(goin_button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(134, 122, 137, 24);
		panel_1.add(passwordField);
		
//		JLabel lblNewLabel_2 = new JLabel("New label");
//		lblNewLabel_2.setIcon(new ImageIcon("/image/Login.png"));
//		lblNewLabel_2.setBounds(0, 0, 350, 600);
//		this.getContentPane().add(lblNewLabel_2);
	}
}

