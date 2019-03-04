package com.etc.frame;

import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.util.Random;
 import java.awt.event.MouseAdapter; 
 import java.awt.event.MouseEvent; 

import javax.swing.JPanel;

 @SuppressWarnings("serial")
public class ValidCode extends JPanel{
     
	/**
        *1.重写Panel组件中的paint()方法。
        *  注：
        *   因为，在组件第一次显示时，AWT线程都会自动去调用组件的paint(Graphics g)方法。
        *该方法传入一个Graphics类型的对象用于绘制图形。Graphics为抽象类。
     *   使用：Graohics类——绘图类，其对象为抽象的画笔。
     */
	public  String code;
	private static Random random=new Random();
	
	public ValidCode() {
		// TODO Auto-generated constructor stub
		this.code=randomCode();
		addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) { 
			code=randomCode();
			repaint(); 
			} 
			});
	}
	
	public void drawcode(Graphics g) {
		int width = 160;//定义验证码图片的宽度
        int height = 40;//定义验证码的高度
        
        g.setColor(Color.LIGHT_GRAY);//设置上下文颜色
        g.fillRect(0,0, width, height);//填充验证码背景
        g.setColor(Color.BLACK);//设置上下文颜色
        g.drawRect(0,0, width -1, height -1);//绘制边框
        
    /**
     * 2.绘制干扰点。
     * 使用：Random类中的方法。
     */
//        Random r =new Random();
        for (int i = 0; i < 100; i++) {
            int x= random.nextInt(width)-2;//nextInt(width);此方法返回0-width之间的随机整数。因为，椭圆或圆的width=2，所以x轴需要减去2.
            int y= random.nextInt(height)-2;
            g.drawOval(x, y, 2, 2);//绘制一个椭圆或圆，刚好能够放入x,y,width,height参数指定的矩形中。x,y为椭圆或圆的坐标，width,height为椭圆或圆的宽高。
        }
        
    /**
     * 3.产生随机数或字符    .
     * 使用：Random类及StringBuilder类中的方法。
     */
        g.setFont(new Font("黑体",Font.BOLD,30));//设置验证码字体
        g.setColor(Color.BLACK);//设置验证码颜色   
        g.drawString(code, 20, 30);//toString 方法：返回此序列中数据的字符串表示形式。最左侧字符左下角位于（20,30)坐标。        
	}

	public void paint(Graphics g){
         
         drawcode(g);
       
     }

	


     public  String randomCode() {
    	 //产生随机验证码
         //创建一个Char数组，toCharArray()方法是：将将此字符串转换为一个新的字符数组。
    	 
         char[] chars = ("0123456789abcdefghijkmnopqrstuvwxyzABCDEFG"+"HIJKHMNOPQRSTUVWXYZ").toCharArray();
         
        StringBuilder sb = new StringBuilder();//有效地将给定的数据转换成字符串，然后将该字符串的字符追加或插入到字符串缓冲区中。
         for (int i = 0; i < 4; i++) {
             int pos = random.nextInt(chars.length);//随机获取Char数组中的下标数。
             char c= chars[pos];//通过数组下标获取，对应的字符。
             sb.append(c+" ");//append 方法：始终将这些字符添加到缓冲区的末端。
         }
         
          return sb.toString();
	}
     
 	@Override
 	public String toString() {
 		return "d [str=" + code + "]";
 	}

 }