package FrameGame;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.swing.border.LineBorder;

import MapElement.GamePanel;

public class FrameGame3 extends FrameGame {
	private static File f = new File("̹�˴�ս��ͼ/��ͼ��");
	private GamePanel gamePanel = new GamePanel();
	public FrameGame3() throws IOException {
		super(f);
	}
}