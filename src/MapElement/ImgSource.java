package MapElement;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImgSource {
	private static ImgSource imgs=null;
	private static Image image = null;
	private ImgSource() {
		initImage();
	}
	public static ImgSource getInstance() {
		if(imgs == null) {
			imgs = new ImgSource();
		}
		return imgs; 
	}
	public void initImage() {
		File f = new File("insect_sprite.png");
		try{
			image = ImageIO.read(f);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void drawHouse(Graphics g) {
		g.drawImage(image, 400, 700, 400+34, 700+34,9*34,7*34, 10*34, 8*34, null);
		g.drawImage(image, 400-34, 700, 400, 700+34,9*34,7*34, 10*34, 8*34, null);
		g.drawImage(image, 400, 700-34, 400+34, 700,9*34,7*34, 10*34, 8*34, null);
		g.drawImage(image, 400-34, 700-34, 400, 700,9*34,7*34, 10*34, 8*34, null);
	}
	public void drawBullet(Graphics g,int state,int x,int y,int direction) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 5*34;
		imagey1 = 6*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;
		g.drawImage(image, x-17, y-17, x+17, y+17, imagex1,imagey1, imagex2, imagey2, null);
	}
	public void drawAutoBullet(Graphics g,int state,int x,int y,int direction) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 6*34;
		imagey1 = 6*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;
			g.drawImage(image, x-17, y-17, x+17, y+17, imagex1,imagey1, imagex2, imagey2, null);
	}
	public void drawPlayerTank(Graphics g,int state,int x,int y,int direction) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*34*2;
		imagey1 = 0;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		if(state ==0)
			g.drawImage(image, x-17, y-17, x+17, y+17, imagex1,imagey1, imagex2, imagey2, null);
		else
			g.drawImage(image, x-17, y-17, x+17, y+17, imagex1+34,imagey1, imagex2+34, imagey2, null);
		}
	public void drawAutoTank(Graphics g,int state,int x,int y,int direction) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*34*2;
		imagey1 = 34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		if(state ==0)
			g.drawImage(image, x-17, y-17, x+17, y+17, imagex1,imagey1, imagex2, imagey2, null);
		else
			g.drawImage(image, x-17, y-17, x+17, y+17, imagex1+34,imagey1, imagex2+34, imagey2, null);
		}
}
