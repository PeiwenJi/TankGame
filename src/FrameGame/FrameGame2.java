package FrameGame;
import javax.swing.border.LineBorder;

import MapElement.GamePanel;

import java.io.File;
import java.io.IOException;
import java.awt.*;

public class FrameGame2 extends FrameGame {
	private static File f = new File("̹�˴�ս��ͼ/��ͼ��");
	private GamePanel gamePanel = new GamePanel();
	public FrameGame2() throws IOException {
		super(f);
	}
	
}