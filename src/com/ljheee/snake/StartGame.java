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
		jf = new JFrame("Snake1.20"+"        ���20144619     "+sdf.format(d));
		gp = new GamePanel();
		
		jf.setLocation(300,150);
		jf.setSize(500, 520);   //470
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(gp, BorderLayout.CENTER);
		jf.addKeyListener(gp.new MyKeyListener());
		
		JMenuBar menuBar = new JMenuBar();
		jf.setJMenuBar(menuBar);
		
		JMenu gameOption = new JMenu("��Ϸѡ��"); //����Ϸѡ��˵�
		reStart = new JMenuItem("���¿�ʼ");
		pauseGame = new JMenuItem("��ͣ��Ϸ");
		continueGame = new JMenuItem("������Ϸ");
		
		JMenu conf = new JMenu("�Ѷ�����"); //
		easyGame = new JMenuItem("��ģʽ");
		difficultGame = new JMenuItem("����ģʽ");
		
		JMenu helpMe = new JMenu("����"); //
		about = new JMenuItem("���ڰ���");
		
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
	 * �ڲ���--�¼�����
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
	//���¿�ʼ��Ϸ
	public static void reStart() {
		jf.dispose();
		new StartGame();
//		new Thread(gp.new PaintThread()).start();
	}
	//��ͣ��Ϸ
	public void pauseGame() {
		Snake.islive = false;
	}
	//������Ϸ
	public void continueGame() {
		Snake.islive = true;
		new Thread(gp.new PaintThread()).start();;
	}
	
	public static void main(String[] args) {
		new StartGame();
	}

	

}
