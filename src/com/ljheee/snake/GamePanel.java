package com.ljheee.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;

import com.ljheee.snake.GamePanel.PaintThread;
/**
 * 游戏面板类   extends JPanel
 * @author Administrator
 *
 */
public class GamePanel extends JPanel {
	boolean isRunning = false;
	Snake  snake = null;
	static LinkedList<Rectangle> link = null;
	static Rectangle r = null;
	//........................................
	Rectangle foodRectangle =null;  //食物
	Random random = null;
	Thread t = null;
	boolean isStart = false;
	
	public GamePanel() {
		snake = new Snake(400,400,Direction.STOP);
		link = new LinkedList<>() ;
		
		random = new Random();//随机数：生成“食物”坐标
		r = new Rectangle(400,400,8,8);
		foodRectangle = new Rectangle(random.nextInt(484), random.nextInt(460), 6, 6);
		link.add(r);
		snake.setLink(link);
		snake.setRectangle(r);
		t =new Thread(new PaintThread());//启动[重绘]线程
		
//        requestFocus(); //让Panel获得焦点
//        t =new Thread(new PaintThread());
//        t.start();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);  //在当前frame里将画出来的东西给清除掉
		g.setColor(Color.black);
		//绘制蛇
		for (Rectangle rectangle : link) {
			g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
		
		paintFood(g);//绘制食物
		
		
		g.setColor(Color.red);
		g.drawString("当前分数:"+snake.score, 410, 10);
		
	}
	
	
	public void paintFood(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillRect(foodRectangle.x, foodRectangle.y, foodRectangle.width, foodRectangle.height);
//		if(foodRectangle.x==r.x&&foodRectangle.y==r.y)   {
//			snake.eat();
//			foodRectangle.x=random.nextInt(484);
//			foodRectangle.y=random.nextInt(460);
//			foodRectangle.setLocation(foodRectangle.x,foodRectangle.y);;
//		}
		
		if(r.x>=foodRectangle.x&&r.x<=foodRectangle.x+foodRectangle.width&&r.y>=foodRectangle.y&&r.y<=foodRectangle.y+foodRectangle.height){
			snake.eat();
			foodRectangle.x=random.nextInt(484);
			foodRectangle.y=random.nextInt(460);
			foodRectangle.setLocation(foodRectangle.x,foodRectangle.y);
		}
	}
	
	/**
	 *键盘--监听 
	 *
	 */
	class MyKeyListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {//按下键盘，启动绘图线程
			isRunning = true;
			if(isStart==false){
				t.start();//start绘图线程
				isStart=true;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key){
			case KeyEvent.VK_UP:
				snake.setDirection(Direction.U);
				break;
			case KeyEvent.VK_DOWN:
				snake.setDirection(Direction.D);
				break;
			case KeyEvent.VK_LEFT:
				snake.setDirection(Direction.L);
				break;
			case KeyEvent.VK_RIGHT:
				snake.setDirection(Direction.R);
				break;	
			default:
				snake.setDirection(snake.direction);
				break;
			}
			snake.move();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	/**
	 * 内部类：专门每隔1s，重绘一次图
	 * @author ljheee
	 *
	 */
	 class PaintThread implements Runnable {
		 
		 @Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning&&snake.islive) {
				snake.move();
				repaint();
//				revalidate();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
			}
			
		
		/*	switch (Snake.score) {  //根据玩家的分数，动态改变游戏难度[速度]
			case 50:
				Snake.speed = 3;
				break;
			case 100:
				Snake.speed = 5;
				break;
			default:
				break;
			}
		*/
		}
		 
		 
		 
	}
	
	

}
