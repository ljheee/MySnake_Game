package com.ljheee.snake;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.ljheee.snake.GamePanel.PaintThread;


public class StartGame {
	static JFrame jf= null;
	static GamePanel gp = null;
	JMenuItem reStart,pauseGame,continueGame;
	JMenuItem easyGame,difficultGame,about;
	SimpleDateFormat sdf = null;
	
	public StartGame(){
		Date d = new Date();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		jf = new JFrame("Snake1.20"+"        李建华20144619     "+sdf.format(d));
		gp = new GamePanel();
		
		jf.setLocation(300,150);
		jf.setSize(500, 520);   //470
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(gp, BorderLayout.CENTER);
		jf.addKeyListener(gp.new MyKeyListener());
		
		JMenuBar menuBar = new JMenuBar();
		jf.setJMenuBar(menuBar);
		
		JMenu gameOption = new JMenu("游戏选项"); //“游戏选项”菜单
		reStart = new JMenuItem("重新开始");
		pauseGame = new JMenuItem("暂停游戏");
		continueGame = new JMenuItem("继续游戏");
		
		JMenu conf = new JMenu("难度设置"); //
		easyGame = new JMenuItem("简单模式");
		difficultGame = new JMenuItem("困难模式");
		
		JMenu helpMe = new JMenu("帮助"); //
		about = new JMenuItem("关于帮助");
		
		menuBar.add(gameOption);
		gameOption.add(reStart);
		gameOption.add(pauseGame);
		gameOption.add(continueGame);
		
		menuBar.add(conf);
		conf.add(easyGame);
		conf.add(difficultGame);
		
		menuBar.add(helpMe);
		helpMe.add(about);
		ActionHandler actionHandler = new ActionHandler();
		reStart.addActionListener(actionHandler);
		pauseGame.addActionListener(actionHandler);
		continueGame.addActionListener(actionHandler);
		easyGame.addActionListener(actionHandler);
		difficultGame.addActionListener(actionHandler);
		about.addActionListener(actionHandler);
		
		
		jf.setVisible(true);
	}
	

	/**
	 * 内部类--事件监听
	 * @author ljheee
	 *
	 */
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==reStart){
				Snake.islive = false;
				Snake.x = Snake.y = 400;
				Snake.r.x = Snake.r.y = 400;
				Snake.direction = Direction.STOP;
				Snake.score = 0;
				reStart();
				 
				Snake.islive = true;
			}
			if(e.getSource()==pauseGame){
				pauseGame();
			}
			if(e.getSource()==continueGame){
				continueGame();
			}
			if(e.getSource()==easyGame){
				Snake.speed = 2;
			}
			if(e.getSource()==difficultGame){
				Snake.speed = 6;
			}
			if(e.getSource()==about){
				JOptionPane.showMessageDialog(StartGame.jf, "@author:ljheee");
			}
		}
		
	}
	//重新开始游戏
	public static void reStart() {
		jf.dispose();
		new StartGame();
//		new Thread(gp.new PaintThread()).start();
	}
	//暂停游戏
	public void pauseGame() {
		Snake.islive = false;
	}
	//继续游戏
	public void continueGame() {
		Snake.islive = true;
		new Thread(gp.new PaintThread()).start();;
	}
	
	public static void main(String[] args) {
		new StartGame();
	}

	

}
