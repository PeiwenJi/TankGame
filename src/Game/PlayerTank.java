package Game;

import java.awt.Graphics;
import java.util.Random;

public class PlayerTank extends Tank{
	private int life = 3;
	public PlayerTank(){
		super(100,100,34,34);	
		setDirection(1);
	}
	public void draw(Graphics g){
	
		ImgSource.getInstance().drawPlayerTank(g,state,getX(),getY(),getDirection());
	}
	public void drawGameOver(Graphics g){		
		ImgSource.getInstance().drawGameOver(g);
	}
	public void drawLife(Graphics g,int life) {
		ImgSource.getInstance().drawLife(g,life);
	}
	public void go(){
		super.go();
		goForward();
	}
	public Bullet fire() {
		Bullet bullet = null;
		if(fireCoolTime >= FIRCOOLTIME) {
			bullet = new Bullet();
			bullet.setX(getX());
			bullet.setY(getY());
			bullet.setDirection(getDirection());
			fireCoolTime = 0;			
		}
		return bullet;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
}
