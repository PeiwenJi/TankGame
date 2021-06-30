package MapElement;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;

import Game.AutoTank;
import Game.ImgSource;

public class MapElement {
	private int positionX;
	private int positionY;
	protected int imgX;
	protected int imgY;
	protected int width;
	protected String type;
	protected boolean pass = true;
	protected boolean destroy = true;
	protected boolean boom = true;
	
	public MapElement(int x,int y) {
		positionX = x;
		positionY = y;	
	}

	public void draw(Graphics g) {				
		ImgSource.getInstance().drawElement(g,positionX,positionY,imgX,imgY,width);	
		
	}

	public MapElement CloneElement(int x,int y) {
		return null;
	}
	public boolean pointSelect(int x,int y) {
		if(x>positionX && x<positionX+width &&
				y>positionY && y<positionY+width ) {
			return true;
		}else {
			return false;
		}
	}
	public Image getCursor() {
	
		//�����ڴ�ͼ�����
		BufferedImage bufferedImage = new BufferedImage(width, width,BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();
		//���ñ���͸��
		bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(width, width, Transparency.TRANSLUCENT);

		g2d.dispose();
		g2d = bufferedImage.createGraphics();

	
		ImgSource.getInstance().drawElement(g2d,0,0,imgX,imgY,width);
		
		return bufferedImage;
	}
	public Rectangle getBounds() {
		// ���� һ�������ڣ�x,y��λ�ã����Ϊ(width,height)�ľ��α߽���󲢷���
		return new Rectangle(positionX,positionY,width,width);
	}
	public boolean hit(Rectangle r) {
		if(pass) {
			if(r == null) {//���Ŀ��Ϊ��
				return false; //����false,��������ײ
			}
		}
		return getBounds().intersects(r); //�������ߵı߽�����Ƿ��ཻ
	}
	public void Setx(int x) {
		this.positionX  = x;
	}
	public int getx() {
		return positionX;
	}
	public void Sety(int y) {
		this.positionY = y;
	}
	public int gety() {
	    return positionY;
	}
	public String getType() {
		return type;
	}
	public boolean getDestroy() {
		return destroy;
	}
	public boolean getBoom() {
		return boom;
	}
	public boolean getPass() {
		return this.pass;
	}
	public int getX() {
		return positionX;
	}
	public int getY() {
		return positionY;
	}
	public String toString() {
		return positionX + ","+positionY+";";
	}

	public MapElement setType(String Type, int x, int y) {
		positionX = x;
		positionY = y;
		MapElement a ;
			if(Type.equals("BRICK") ) {
				a = new MapElementBrick(x,y);
			}else if(Type.equals("BASE")) {
				a = new MapElementBase(x,y);
			}else if(Type.equals("GRASS")) {
				a = new MapElementGrass(x,y);
			}else if(Type.equals("IRON")) {
				a = new MapElementIron(x,y);
			}else if(Type.equals("WATER")) {
				a = new MapElementWater(x,y);
			}else {
				a = null ;
			}
			return a;
	}	
}
