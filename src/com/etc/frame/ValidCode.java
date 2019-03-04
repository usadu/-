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
        *1.��дPanel����е�paint()������
        *  ע��
        *   ��Ϊ���������һ����ʾʱ��AWT�̶߳����Զ�ȥ���������paint(Graphics g)������
        *�÷�������һ��Graphics���͵Ķ������ڻ���ͼ�Ρ�GraphicsΪ�����ࡣ
     *   ʹ�ã�Graohics�ࡪ����ͼ�࣬�����Ϊ����Ļ��ʡ�
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
		int width = 160;//������֤��ͼƬ�Ŀ��
        int height = 40;//������֤��ĸ߶�
        
        g.setColor(Color.LIGHT_GRAY);//������������ɫ
        g.fillRect(0,0, width, height);//�����֤�뱳��
        g.setColor(Color.BLACK);//������������ɫ
        g.drawRect(0,0, width -1, height -1);//���Ʊ߿�
        
    /**
     * 2.���Ƹ��ŵ㡣
     * ʹ�ã�Random���еķ�����
     */
//        Random r =new Random();
        for (int i = 0; i < 100; i++) {
            int x= random.nextInt(width)-2;//nextInt(width);�˷�������0-width֮��������������Ϊ����Բ��Բ��width=2������x����Ҫ��ȥ2.
            int y= random.nextInt(height)-2;
            g.drawOval(x, y, 2, 2);//����һ����Բ��Բ���պ��ܹ�����x,y,width,height����ָ���ľ����С�x,yΪ��Բ��Բ�����꣬width,heightΪ��Բ��Բ�Ŀ�ߡ�
        }
        
    /**
     * 3.������������ַ�    .
     * ʹ�ã�Random�༰StringBuilder���еķ�����
     */
        g.setFont(new Font("����",Font.BOLD,30));//������֤������
        g.setColor(Color.BLACK);//������֤����ɫ   
        g.drawString(code, 20, 30);//toString ���������ش����������ݵ��ַ�����ʾ��ʽ��������ַ����½�λ�ڣ�20,30)���ꡣ        
	}

	public void paint(Graphics g){
         
         drawcode(g);
       
     }

	


     public  String randomCode() {
    	 //���������֤��
         //����һ��Char���飬toCharArray()�����ǣ��������ַ���ת��Ϊһ���µ��ַ����顣
    	 
         char[] chars = ("0123456789abcdefghijkmnopqrstuvwxyzABCDEFG"+"HIJKHMNOPQRSTUVWXYZ").toCharArray();
         
        StringBuilder sb = new StringBuilder();//��Ч�ؽ�����������ת�����ַ�����Ȼ�󽫸��ַ������ַ�׷�ӻ���뵽�ַ����������С�
         for (int i = 0; i < 4; i++) {
             int pos = random.nextInt(chars.length);//�����ȡChar�����е��±�����
             char c= chars[pos];//ͨ�������±��ȡ����Ӧ���ַ���
             sb.append(c+" ");//append ������ʼ�ս���Щ�ַ���ӵ���������ĩ�ˡ�
         }
         
          return sb.toString();
	}
     
 	@Override
 	public String toString() {
 		return "d [str=" + code + "]";
 	}

 }