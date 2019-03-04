package com.etc.frame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.etc.dao.CollectionDao;
import com.etc.entity.Music;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownloadFrame {
	private JFrame frame;
	private JComboBox<String> comboBox;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DownloadFrame window = new DownloadFrame();
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
	public DownloadFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 659, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("����");
		
		
		/*comboBox = new JComboBox<>();
		comboBox.addItem("E:\\����\\music.mp3");
		comboBox.addItem("E:\\Music\\music.mp3");
		comboBox.addItem("E:\\����\\music.mp3");
		comboBox.addActionListener(this);
		comboBox.setBounds(145, 60, 281, 45);
		String str = (String) comboBox.getSelectedItem();
		frame.getContentPane().add(comboBox);*/

		
		
		
		/*open=new JButton("����");
		frame.getContentPane().add(open);
		open.setBounds(145, 60, 281, 45);
		//open.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		open.addActionListener(this);*/
		
		JButton download_btn = new JButton("\u4E0B\u8F7D");
		download_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				/*FileInputStream fis=null;
				FileOutputStream fos=null;
			 try {
				 //������·��
				fis=new FileInputStream(path);
				//��ȡ�û������·��
				//String Dpath=download_path.getText();
				File file=new File("E:\\����\\music.mp3");
				//�ж��û�����·��,������
				if(!(file.exists())) {
					File f=file.getParentFile();
					if(!(f.exists())) {
						f.mkdirs();
					}
					file.createNewFile();
				}
				fos=new FileOutputStream(file);
				 byte[] b=new byte[5286960];//1024����
				 int len=0;
				 while((len=fis.read(b))!=-1) {
					//д��Ŀ��λ��
						fos.write(b, 0, len); 
						JOptionPane.showMessageDialog(null, "���سɹ�");
				 }
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				
					try {
						if(null!=fos)
						fos.close();
						if(null!=fis)
						fis.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}*/


			}
		});
		download_btn.setBounds(26, 60, 105, 45);
		frame.getContentPane().add(download_btn);
		frame.setVisible(false);
	}
	/*public FileChooser(){
		open=new JButton("open");
		getContentPane().add(open);
		this.setBounds(400, 200, 689, 480);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		open.addActionListener(this);
	}*/


	public void saveFile(String path) {
		
		//�����ļ�ѡ���
		JFileChooser chooser = new JFileChooser();
		
		//����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��
		int option = chooser.showSaveDialog(null);
		if(option==JFileChooser.APPROVE_OPTION){	//�����û�ѡ���˱���
			
			File file = chooser.getSelectedFile();
			FileInputStream fis=null;
			FileOutputStream fos =null;
			try {
				fis=new FileInputStream(path);
				fos = new FileOutputStream(file);
				byte[] b=new byte[5286960];//1024����
				 int len=0;
				 while((len=fis.read(b))!=-1) {
					//д��Ŀ��λ��
						fos.write(b, 0, len); 
						JOptionPane.showMessageDialog(null, "���سɹ�");
				 }
				 } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
				try {
					if(null!=fos)
					fos.close();
					if(null!=fis)
					fis.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
