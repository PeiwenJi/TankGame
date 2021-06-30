package FrameGame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import Game.AutoTank;
import Game.Bullet;
import Game.Explode;
import Game.IntllgntFactory;
import Game.Map;
import Game.PlayerTank;
import Game.Tank;
import Game.ToolBox;
import Music.Music1;
import Music.Music2;
import Music.Music3;
import Music.SystemUtils;
import Music.loginMusic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import MapElement.GamePanel;
import MapElement.MapElement;

public class FrameGame extends JFrame {
	private PlayerTank tank;
	private ArrayList playerBullets =new ArrayList();
	private ArrayList autoBullets = new ArrayList();
	//private ArrayList<AutoTank> autoTank=new ArrayList<AutoTank>();
	private ArrayList autoTanks=new ArrayList();
	private ArrayList explodes=new ArrayList();
	private ToolBox toolBox = new ToolBox();
	private Map map = new Map();
	private int tankCoolTime = 0;
	//有关道具变量
	private int toolCoolTime = 0;
	private boolean tool = false;
	private int toolNum=-1;
	private int toolTime=0;	
	private int speedTime=0;
	private boolean speed = false;
	private  loginMusic loginmusic;
	private SystemUtils systemUtils = new SystemUtils();
	//地图容器里
	private File f;
	private GamePanel gamePanel = new GamePanel();
	private ArrayList elementList = new ArrayList();
	//窗口元素
	JMenuBar menuBar = new JMenuBar();
	//构造函数
	public FrameGame(File f) throws IOException{		
		super("TankWar");
		this.f=f;
		loginmusic = new loginMusic("易水两岸.wav");
		new Thread(loginmusic).start();
		System.out.println(1+loginmusic.name);
		setSize(800,600);
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tank = new PlayerTank();
		tank.setX(map.getMainPoint().x);
		tank.setY(map.getMainPoint().y);
		Thread t = new FreshThread();
		t.start();		
		//读取地图
		gamePanel.setBorder(new LineBorder(Color.BLUE));
		gamePanel.setPreferredSize(new Dimension(800,600));					
		this.add(gamePanel);
		try {
			gamePanel.openMap(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		elementList = gamePanel.getElementList();
		repaint();
		//添加窗体元素
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		//菜单
//		JMenu beginMenu = new JMenu("开始");
		JMenu musicMenu = new JMenu("音乐");
		this.setJMenuBar(menuBar);
//		this.add(beginMenu);
//		menuBar.add(beginMenu);
		this.add(musicMenu);
		menuBar.add(musicMenu);
		//菜单下按钮
//		JMenuItem beginItem = new JMenuItem("开始");
//		JMenuItem stopItem = new JMenuItem("暂停");
//		JMenuItem reBeginItem = new JMenuItem("重新开始");
//		beginMenu.add(beginItem);
//		beginMenu.add(stopItem);
//		beginMenu.add(reBeginItem);
		//音乐按钮
		JMenuItem closeMusicItem = new JMenuItem("关闭音乐");
		closeMusicItem.addActionListener(new closeListener());
		JMenuItem openMusicItem = new JMenuItem("开启音乐");
		openMusicItem.addActionListener(new openListener());
		JMenuItem upItem = new JMenuItem("增加音量");
		upItem.addActionListener(new upListener());		
		JMenuItem downItem = new JMenuItem("减少音量");
		downItem.addActionListener(new downListener());
		JMenuItem noMusicItem = new JMenuItem("静音");
		noMusicItem.addActionListener(new noListener());
		JMenu changeMenu = new JMenu("切换音乐");		
		musicMenu.add(closeMusicItem);
		musicMenu.add(openMusicItem);
		musicMenu.add(upItem);
		musicMenu.add(downItem);
		musicMenu.add(noMusicItem);
		musicMenu.add(changeMenu);		
		//切换音乐
		JMenuItem music1Item = new JMenuItem("At Nightfall");
		changeMenu.add(music1Item);
		music1Item.addActionListener(new Music1Listener());
		JMenuItem music2Item = new JMenuItem("三个人的时光");
		changeMenu.add(music2Item);
		music2Item.addActionListener(new Music2Listener());
		JMenuItem music3Item = new JMenuItem("易水两岸");
		changeMenu.add(music3Item);
		music3Item.addActionListener(new Music3Listener());
	}
	private Image offScreenImage = null;
	private Graphics gOffScreen = null;
	public void paint(Graphics g){
		if(offScreenImage==null){
			offScreenImage = this.createImage(800,600);
			gOffScreen = offScreenImage.getGraphics();
		}
		super.paint(gOffScreen);
		gamePanel.paint(gOffScreen);
		tank.draw(gOffScreen);
		tank.drawLife(gOffScreen,tank.getLife());
		for(int i=0;i<playerBullets.size();i++){
			Bullet b = (Bullet)playerBullets.get(i);
			b.draw(gOffScreen);
		}
	
		for(int i=0;i<autoTanks.size();i++){
			AutoTank a = (AutoTank)autoTanks.get(i);
			a.draw(gOffScreen);
		}
		for(int i=0;i<autoBullets.size();i++){
			Bullet b = (Bullet)autoBullets.get(i);
			b.draw(gOffScreen);
		}
		
		for(int i=0;i<explodes.size();i++) {
			Explode e = (Explode)explodes.get(i);
			if(e.getAlive()) {
				e.draw(gOffScreen);
			}else {
				explodes.remove(e);
			}
		}
		if(tank.getLife() == 0) {
			tank.drawGameOver(gOffScreen);							
		}
		//随机生成道具
		if(tool){			
			toolBox.drawTool(gOffScreen,toolNum,toolBox.getX(),toolBox.getY());
			repaint();
		}
		g.drawImage(offScreenImage,0,0,null);
	}
	private boolean hitBorder(Tank obj) {
		Point point;
		point = obj.getNextPosition();
		if(point.x<17 || point.x>800-17 ||
				point.y<17 || point.y>600-17){
			return true;			
		}else {
			return false;
		}
	}
	private class FreshThread extends Thread{
		public void run(){
			sign1:
			while(true){				
				try{
					Thread.sleep(100);
					if(!hitBorder(tank) && tank.getLife()>0 ){								
									tank.go();
					}
					for(int i=0;i<playerBullets.size();i++){
						Bullet b = (Bullet)playerBullets.get(i);
						if(hitBorder(b)){
							explodes.add(new Explode(b.getX(),b.getY()));
							playerBullets.remove(b);
							continue;
						}
						//玩家子弹撞击地图元素
						for(int j=0;j<elementList.size();j++) {						
							if(b.hit(((MapElement) elementList.get(j)).getBounds()) && ((MapElement) elementList.get(j)).getBoom()) {
								explodes.add(new Explode(b.getX(),b.getY()));
								playerBullets.remove(b);
								if(((MapElement) elementList.get(j)).getDestroy()) {
									elementList.remove(j);
								}
								continue;
							}
						}
						for(int j=0;j<autoTanks.size();j++){
							AutoTank a = (AutoTank)autoTanks.get(j);							
							if(b.hit(a.getBounds())){
								explodes.add(new Explode(a.getX(),a.getY()));
								playerBullets.remove(b);
								autoTanks.remove(a);	
								continue;
							}
						}						
						b.go();						
					}
					for(int i=0;i<autoTanks.size();i++){
						AutoTank a = (AutoTank)autoTanks.get(i);
						if(!hitBorder(a)) {
							a.go();
						}else {
							autoTanks.remove(a);
						}
						Bullet b = a.fire();
						if(b != null) {
							autoBullets.add(b);
						}
					}
					//判断电脑坦克是否与地图元素碰撞，并改变方向
					for(int j=0;j<autoTanks.size();j++) {
						if(autoTanks.get(j)!=null) {
							AutoTank b = (AutoTank) autoTanks.get(j);
						for(int i=0;i<elementList.size();i++){
							if(!((MapElement) elementList.get(i)).getPass()) {
								if(b.hit(((MapElement) elementList.get(j)).getBounds())) {
									//b.setCrash(false);
									Random random = new Random(); 
									b.setDirection(random.nextInt(4)+1);
								}
							}
						 }
					  }
					}
					//判断玩家坦克是否与地图元素碰撞，并改变方向
					for(int i=0;i<elementList.size();i++){
						if(!((MapElement) elementList.get(i)).getPass()) {
							if(tank.hit(((MapElement) elementList.get(i)).getBounds())) {
								//b.setCrash(false);
								Random random = new Random(); 
								tank.setDirection(random.nextInt(4)+1);
							}
						}
					 }
					for(int i=0;i<autoBullets.size();i++){
						Bullet b = (Bullet)autoBullets.get(i);
						if(hitBorder(b)){
							explodes.add(new Explode(b.getX(),b.getY()));
							autoBullets.remove(b);
							continue;	
						}
						if(b.hit(tank.getBounds())){
							autoBullets.remove(b);
							tank.setLife(tank.getLife()-1);
							tank.drawLife(gOffScreen,tank.getLife());
							
							explodes.add(new Explode(tank.getX(),tank.getY()));
							if(tank.getLife() == 0) {
								break sign1;								
							}
						}
						b.go();	
					}
					
					repaint();
					//随机生成道具
					if(toolCoolTime==150){
						tool = true;
						Random random = new Random();
						toolNum = random.nextInt(3);
						toolBox.setX(random.nextInt(800));
						toolBox.setY(random.nextInt(600));
						toolCoolTime=0;						
					}else{
						toolCoolTime++;
						if(toolTime ==200) {
							tool=false;
							toolTime=0;
						}else {
							toolTime++;
						}
					}
					if(tank.hit(new Rectangle(toolBox.getX(),toolBox.getY(),34,34))){
						tool=false;
						if(toolNum==0) {
							tank.setLife(tank.getLife()+1);
							toolNum=-1;
						}
						if(toolNum==1) {
							tool=false;
							speed = true;	
						}						
					}
					if(speed) {
						tank.setVelocity(15);
						speedTime++;
						if(speedTime==50) {
							speed = false;
							toolNum=-1;
							tank.setVelocity(10);
							speedTime=0;
						}
					}
					if(tankCoolTime==50){
						AutoTank aTank = new AutoTank();
						Random random = new Random();
						int i = random.nextInt(3);//up
						aTank.setX(map.getPoints()[i].x);
						aTank.setY(map.getPoints()[i].y);
						aTank.setGoCategory(IntllgntFactory.getGoCategory(tank));
						autoTanks.add(aTank);
						tankCoolTime=0;
					}else{
						tankCoolTime++;
					}
				}catch(InterruptedException e){
					e.printStackTrace();				
				}
			}
		}
	}
	private class KeyMonitor extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			switch(key){
				case KeyEvent.VK_UP:
					tank.setDirection(1);break;
				case KeyEvent.VK_RIGHT:
					tank.setDirection(2);break;
				case KeyEvent.VK_DOWN:
					tank.setDirection(3);break;
				case KeyEvent.VK_LEFT:
					tank.setDirection(4);break;
				case KeyEvent.VK_SPACE:
					Bullet bullet = tank.fire();
					if(bullet != null) {
						playerBullets.add(bullet);
					}
			};	
			repaint();
		}
	}
	//音乐操作的监听器
	public class Music1Listener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {
	    	loginmusic.stopMusic();
	    	loginmusic = new Music1();
	    	loginmusic.playMusic();
	    	System.out.println(2+loginmusic.name);
	    }   
	}
	public class Music2Listener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {
	    	loginmusic.stopMusic();
	    	loginmusic = new Music2();
	    	loginmusic.playMusic();
	    	System.out.println(2+loginmusic.name);
	    }   
	}public class Music3Listener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {
	    	loginmusic.stopMusic();
	    	loginmusic = new Music3();
	    	loginmusic.playMusic();
	    	System.out.println(2+loginmusic.name);
	    }   
	}
	public class closeListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {	   	
	    	loginmusic.stopMusic();
	    }
	}	
	
	public class openListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {	
	    	loginmusic.playMusic();	    	
	    }
	}
	
	public class upListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {	   	
	    	systemUtils.controlSystemVolume("1");
	    }
	}
	
	public class downListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {	   	
	    	systemUtils.controlSystemVolume("2");
	    }
	}
	
	public class noListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {	   	
	    	systemUtils.controlSystemVolume("0");
	    }
	}
}


