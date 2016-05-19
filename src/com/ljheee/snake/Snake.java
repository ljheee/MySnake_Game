package com.ljheee.snake;

import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JOptionPane;
/**
 * ��--����һ��Rectangle r
 * @author ljheee
 *
 */
public class Snake  {
	
	LinkedList<Rectangle> link = null;
	static Rectangle r = null;//r ������--�����ߵ�x,y
	
	public static int speed = 1;  //�ƶ��ٶ�---����ı���
	public static int oldX = 200; //������
	public static int oldY = 200;
	static int x,y;	//������
	
	public static int score = 0;
//	public static Thread t = null;
	public static Direction direction = Direction.STOP;//��ʼ��--Ϊ��ֹ
	public static boolean islive = true;
	

	public Snake(int x, int y,  Direction dir) {// Tank�Ĺ��캯��
		this.x = x;
		this.y = y;
		
		this.direction = dir;
	}
	
	
	public  void eat(){
		this.score += 10;
	}
	
	
	
	/**
	 * [ͨ�������¼�]���÷���
	 * @param direction
	 */
	public  void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	
	/**
	 * �ƶ������ݷ��򣬸ı�����
	 */
	public  void move(){
//		oldX = x;
//		oldY = y;
		switch (this.direction){
		case U :
			this.r.y-= speed;
			y -= speed;
			break;
		case D :
			this.r.y+= speed;
			y += speed;
			break;
		case L :
			this.r.x-= speed;
			x -= speed;
			break;
		case R :
			this.r.x+= speed;
			x += speed;
			break;
		case STOP:
			break;
		}
		if(x<0||y<0||x>484||y>460) {
			//ֹͣ�߳�
			this.islive = false;
//			this.t.interrupt();
//			JOptionPane.showMessageDialog(StartGame.jf, "GameOver");
			int option = JOptionPane.showConfirmDialog(StartGame.jf,"GameOver"+"\n"+"����һ��..." , "Tip", JOptionPane.YES_NO_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION ){
				this.x = this.y = 400;
				this.r.x = this.r.y = 400;
				this.direction = Direction.STOP;
				this.score = 0;
				StartGame.reStart();
				
				this.islive = true;
				return;
//				this.t = new Thread(new StartGame.);
			}
			
			if(option == JOptionPane.NO_OPTION ){
				StartGame.jf.dispose();
			}
			if(option == JOptionPane.CANCEL_OPTION ){
				return;
			}
			if(option == JOptionPane.CLOSED_OPTION ){
				return;
			}
			
		}
//		System.out.println(x+"  "+y);
		
	}

	

	public void setLink(LinkedList<Rectangle> link) {
		this.link = link;
	}

	

	public void setRectangle(Rectangle r) {
		this.r = r;
		this.x = r.x;
		this.y = r.y;
	}
	
	
	
	

}
