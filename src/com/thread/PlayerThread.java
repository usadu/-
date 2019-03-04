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
 * 播放线程
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

		// 环境配置，将vlc sdk导入到eclipse

		// if(RuntimeUtil.isWindows()){ }
		// NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
		// "D:\\vlc\\vlc-2.2.6\\sdk\\lib"); //导入的路径是vlc的安装路径
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "vlcj//lib"); // 导入的路径是vlc的安装路径
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		// System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		// 创建主程序界面运行窗体
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {

					frame = new PlayerFrame(list);
					frame.setUsername(username);
					frame.setUserid(userid);
					frame.VisibleSet();
					// frame.getMediaPlayer().playMedia("src/images/IMG_0876.MP4");
					// // 直接播放视屏，参数是视屏文件的绝对路径
					// frame.getMediaPlayer().prepareMedia(list.get(1).getMusicpath());
					// //控制即将播放的视频
					new SwingWorker<String, Integer>() {

						@Override
						protected synchronized String doInBackground() throws Exception {
							// TODO Auto-generated method stub
							while (flag) { // 获取视频播放进度并且按百分比显示
								long total = frame.getMediaPlayer().getLength();
								long curr = frame.getMediaPlayer().getTime();
								float percent = (float) curr / total;
								publish((int) (percent * 100));

								if (total == -1 && !(frame.getPlayButton().getText().equals("播放"))) {
									// 如果视频播放完，循环播放视频
									if (frame.getS().equals("单曲循环")) {
										play();
									} else if (frame.getS().equals("随机播放")) {
										frame.setM(list.get((int) (Math.random() * list.size())));
										playfile(frame.getM().getMusic_path());
										frame.getLabel().setText("正在播放："+frame.getM().getMusic_name());
									} else if (frame.getS().equals("顺序播放")) {
										if (frame.getObj() == list.size()) {
											frame.setM(list.get(0));
											playfile(frame.getM().getMusic_path());
											frame.getLabel().setText("正在播放："+frame.getM().getMusic_name());
										} else {
											frame.setObj(frame.getObj() + 1);
											frame.setM(list.get(((Integer) (frame.getObj()) - 1)));
											playfile(frame.getM().getMusic_path());
											frame.getLabel().setText("正在播放："+frame.getM().getMusic_name());
										}
									}
								}
								Thread.sleep(500);
							}
							return null;
						}

						/**
						 * 根据播放模式选择下一首
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

	// 关闭播放器释放资源
	public static void dispose() {
		flag = false;
		frame.getMediaPlayer().stop();
		frame.Close1();
	}

	// 打开文件
	public static void openVideo() {
		JFileChooser chooser = new JFileChooser();
		int v = chooser.showOpenDialog(null);
		if (v == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			frame.getMediaPlayer().playMedia(file.getAbsolutePath());
		}
	}

	// 选择播放文件
	public static void playfile(String file) {
		frame.getMediaPlayer().playMedia(file);
	}

	// 退出播放
	public static void exit() {
		frame.getMediaPlayer().release();
		System.exit(0);
	}

	// 实现播放按钮的方法
	public static void play() {
		frame.getMediaPlayer().play();
	}

	// 实现暂停按钮的方法
	public static void pause() {
		frame.getMediaPlayer().pause();
	}

	// 实现停止按钮的方法
	public static void stop() {
		frame.getMediaPlayer().stop();
	}

	// 实现点击进度条跳转的方法
	public static void jumpTo(float to) {
		frame.getMediaPlayer().setTime((long) (to * frame.getMediaPlayer().getLength()));
	}

	// 实现控制声音的方法
	public static void setVol(int v) {
		frame.getMediaPlayer().setVolume(v);
	}

}