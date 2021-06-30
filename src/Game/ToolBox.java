package Game;

import java.awt.Graphics;
import java.util.Random;

public class ToolBox {
	private int toolX=-100;
	private int toolY=-100;
	public void drawTool(Graphics g,int toolNum,int toolX,int toolY){
		if(toolNum==0) {			
			ImgSource.getInstance().drawLifeTool(g,toolX,toolY);
		}
		if(toolNum==1) {			
			ImgSource.getInstance().drawSpeedTool(g,toolX,toolY);
		}
	}
	public void setX(int x){
		this.toolX = x;
	}
	public int getX(){
		return toolX;
	}
	public void setY(int y){
		this.toolY = y;
	}
	public int getY(){
		return toolY;
	} 
}
