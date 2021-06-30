package FrameGame;
import javax.swing.border.LineBorder;

import MapElement.GamePanel;

import java.io.File;
import java.io.IOException;
import java.awt.*;

public class FrameGame2 extends FrameGame {
	private static File f = new File("坦克大战地图/地图二");
	private GamePanel gamePanel = new GamePanel();
	public FrameGame2() throws IOException {
		super(f);
	}
	
}