package com.ljheee.snake;

import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JOptionPane;
/**
 * 蛇--就是一个Rectangle r
 * @author ljheee
 *
 */
public class Snake  {
	
	LinkedList<Rectangle> link = null;
	static Rectangle r = null;//r 的坐标--就是蛇的x,y
	
	public static int speed = 1;  //移动速度---坐标改变跨度
	public static int oldX = 200; //旧坐标
	public static int oldY = 200;
	static int x,y;	//新坐标
	
	public static int score = 0;
//	public static Thread t = null;
	public static Direction direction = Direction.STOP;//初始化--为静止
	public static boolean islive = true;
	

	public Snake(int x, int y,  Direction dir) {// Tank的构造函数
		this.x = x;
		this.y = y;
		
		this.direction = dir;
	}
	
	
	public  void eat(){
		this.score += 10;
	}
	
	
	
	/**
	 * [通过键盘事件]设置方向
	 * @param direction
	 */
	public  void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	
	/**
	 * 移动：根据方向，改变坐标
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
			//停止线程
			this.islive = false;
//			this.t.interrupt();
//			JOptionPane.showMessageDialog(StartGame.jf, "GameOver");
			int option = JOptionPane.showConfirmDialog(StartGame.jf,"GameOver"+"\n"+"再来一局..." , "Tip", JOptionPane.YES_NO_CANCEL_OPTION);
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
