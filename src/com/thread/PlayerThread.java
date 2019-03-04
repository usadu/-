package com.thread;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.SwingWorker;

import com.etc.entity.Music;
import com.etc.frame.PlayerFrame;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
/**
 * �����߳�
 * @author msi
 *
 */
public class PlayerThread {
	static PlayerFrame frame;
	private static boolean flag = true;
	private String username;
	private int userid;
	
	
	
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

	public static ArrayList<Music> getList() {
		return list;
	}

	public static void setList(ArrayList<Music> list) {
		PlayerThread.list = list;
	}

	private static ArrayList<Music> list = null;
	// private static final String NATIVE_LIBRARY_SEARCH_PATH = "D:\\Program
	// Files\\VideoLAN\\VLC\\sdk\\lib";

	public PlayerThread() {

		// �������ã���vlc sdk���뵽eclipse

		// if(RuntimeUtil.isWindows()){ }
		// NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
		// "D:\\vlc\\vlc-2.2.6\\sdk\\lib"); //�����·����vlc�İ�װ·��
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "vlcj//lib"); // �����·����vlc�İ�װ·��
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		// System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		// ����������������д���
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {

					frame = new PlayerFrame(list);
					frame.setUsername(username);
					frame.setUserid(userid);
					frame.VisibleSet();
					// frame.getMediaPlayer().playMedia("src/images/IMG_0876.MP4");
					// // ֱ�Ӳ��������������������ļ��ľ���·��
					// frame.getMediaPlayer().prepareMedia(list.get(1).getMusicpath());
					// //���Ƽ������ŵ���Ƶ
					new SwingWorker<String, Integer>() {

						@Override
						protected synchronized String doInBackground() throws Exception {
							// TODO Auto-generated method stub
							while (flag) { // ��ȡ��Ƶ���Ž��Ȳ��Ұ��ٷֱ���ʾ
								long total = frame.getMediaPlayer().getLength();
								long curr = frame.getMediaPlayer().getTime();
								float percent = (float) curr / total;
								publish((int) (percent * 100));

								if (total == -1 && !(frame.getPlayButton().getText().equals("����"))) {
									// �����Ƶ�����꣬ѭ��������Ƶ
									if (frame.getS().equals("����ѭ��")) {
										play();
									} else if (frame.getS().equals("�������")) {
										frame.setM(list.get((int) (Math.random() * list.size())));
										playfile(frame.getM().getMusic_path());
										frame.getLabel().setText("���ڲ��ţ�"+frame.getM().getMusic_name());
									} else if (frame.getS().equals("˳�򲥷�")) {
										if (frame.getObj() == list.size()) {
											frame.setM(list.get(0));
											playfile(frame.getM().getMusic_path());
											frame.getLabel().setText("���ڲ��ţ�"+frame.getM().getMusic_name());
										} else {
											frame.setObj(frame.getObj() + 1);
											frame.setM(list.get(((Integer) (frame.getObj()) - 1)));
											playfile(frame.getM().getMusic_path());
											frame.getLabel().setText("���ڲ��ţ�"+frame.getM().getMusic_name());
										}
									}
								}
								Thread.sleep(500);
							}
							return null;
						}

						/**
						 * ���ݲ���ģʽѡ����һ��
						 */
						// private void nextMusic() {
						// // TODO Auto-generated method stub
						//
						// }

						protected void process(java.util.List<Integer> chunks) {
							for (int v : chunks) {
								frame.getProgressBar().setValue(v);
							}
						}
					}.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	// �رղ������ͷ���Դ
	public static void dispose() {
		flag = false;
		frame.getMediaPlayer().stop();
		frame.Close1();
	}

	// ���ļ�
	public static void openVideo() {
		JFileChooser chooser = new JFileChooser();
		int v = chooser.showOpenDialog(null);
		if (v == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			frame.getMediaPlayer().playMedia(file.getAbsolutePath());
		}
	}

	// ѡ�񲥷��ļ�
	public static void playfile(String file) {
		frame.getMediaPlayer().playMedia(file);
	}

	// �˳�����
	public static void exit() {
		frame.getMediaPlayer().release();
		System.exit(0);
	}

	// ʵ�ֲ��Ű�ť�ķ���
	public static void play() {
		frame.getMediaPlayer().play();
	}

	// ʵ����ͣ��ť�ķ���
	public static void pause() {
		frame.getMediaPlayer().pause();
	}

	// ʵ��ֹͣ��ť�ķ���
	public static void stop() {
		frame.getMediaPlayer().stop();
	}

	// ʵ�ֵ����������ת�ķ���
	public static void jumpTo(float to) {
		frame.getMediaPlayer().setTime((long) (to * frame.getMediaPlayer().getLength()));
	}

	// ʵ�ֿ��������ķ���
	public static void setVol(int v) {
		frame.getMediaPlayer().setVolume(v);
	}

}