package MapEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import Game.ImgSource;


public class FrameMain extends JFrame{
	private PanelMain panelMain;
	private PanelIcon panelIcon;
	private ArrayList<MapElement> MapElements;	
	public FrameMain() {
		super("地图编辑器");
	}
	
	public void initFrame() {
		//新建地图面板
		panelMain = new PanelMain();	
		panelMain.initPanel();
		panelMain.setBorder(new LineBorder(Color.BLUE));
		panelMain.setPreferredSize(new Dimension(800,600));
		//新建一个滚动面板，地图面板区域放到滚动面板中，垂直滚动条总是显示，水平滚动条只有在需要时显示
		JScrollPane panel = new JScrollPane(panelMain,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelIcon = new PanelIcon();	
		panelIcon.initPanel();
		panelMain.setPanelIcon(panelIcon);
		panelIcon.setPanelMain(panelMain);
		
		setLayout(new BorderLayout());
		add(panelIcon,BorderLayout.WEST);
		add(panel);//	将滚动区域添加到窗体
		
		//添加菜单
		JMenuBar menuBar = new JMenuBar();//新建菜单条
		this.setJMenuBar(menuBar);//将菜单条添加到窗体上
		
		JMenu fileMenu = new JMenu("File");//新建菜单"File"
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);
		
//		JMenu editMenu = new JMenu("Edit");//新建菜单"Edit"
//		editMenu.setMnemonic('E');
//		menuBar.add(editMenu);
		
		JMenu helpMenu = new JMenu("Help");//新建菜单"Help"
		helpMenu.setMnemonic('H');
		menuBar.add(helpMenu);
		
		//File下功能
		JMenuItem newItem = new JMenuItem("new");//新建"new"菜单项
		newItem.setAccelerator(KeyStroke.getKeyStroke('N'));//为菜单项添加"N"快捷键
		newItem.addActionListener(new actNew());
		fileMenu.add(newItem);//将"new"菜单项添加到"File"菜单中
		
		JMenuItem openItem = new JMenuItem("open");//新建"open"菜单项
		openItem.addActionListener(new actOpen());
		//为菜单项添加Ctrl+O快捷键
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		fileMenu.add(openItem);//将"open"菜单项添加到"File"菜单中
		
		JMenuItem saveItem = new JMenuItem("save");//新建"open"菜单项
		//为菜单项添加Ctrl+S快捷键
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		fileMenu.add(saveItem);//将"open"菜单项添加到"File"菜单中
		saveItem.addActionListener(new actSave());
		
		//Edit下功能
//		JMenuItem undoItem = new JMenuItem("undo");//新建undo"菜单项
//		//为菜单项添加Ctrl+Z快捷键
//		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
//		editMenu.add(undoItem);//将"undo"菜单项添加到"Edit"菜单中
//		undoItem.addActionListener(new actUndo());
//		
//		JMenuItem redoItem = new JMenuItem("redo");//新建"redo"菜单项
//		//为菜单项添加Ctrl+Y快捷键
//		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_MASK));
//		editMenu.add(redoItem);//将"undo"菜单项添加到"Edit"菜单中
		
		//Help下功能
		JMenuItem aboutItem = new JMenuItem("about");//新建"about"菜单项
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(new actabout());
		
		JMenuItem helpItem = new JMenuItem("help");//新建"help"菜单项
		helpMenu.add(helpItem);
		helpItem.addActionListener(new actHelp());
		
		//Format下功能
//		JMenuItem gridItem1 = new JMenuItem("gridline hide");//新建"about"菜单项
//		formatMenu.add(gridItem1);
//		aboutItem.addActionListener(new actHide());
//		JMenuItem gridItem2 = new JMenuItem("gridline appear");//新建"about"菜单项
//		formatMenu.add(gridItem2);
//		aboutItem.addActionListener(new actAppear());
		
		this.setSize(800,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);//设置窗体可见
	}
	
	
	class actSave implements ActionListener{

		JFileChooser fileDialog = new JFileChooser();
		public void actionPerformed(ActionEvent arg0) {
			
			int state = fileDialog.showSaveDialog(null);
	        if (state == JFileChooser.APPROVE_OPTION) {
	        	// 取得文件路径显示在tfDir文本框中
	        	try {
					panelMain.saveMap(fileDialog.getSelectedFile().getAbsoluteFile());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	System.out.println(fileDialog.getSelectedFile().getAbsoluteFile());

	        }
			
		}
		
	}
	
	
//	class actUndo implements ActionListener{
//		public void actionPerformed(ActionEvent arg0) {
//			
//		}	
//	}
	
	 class actabout implements ActionListener {
	        public void actionPerformed(ActionEvent arg0) {	        	
	        	JOptionPane.showMessageDialog(null, "作者：吉佩雯\n学校：四川大学\n"
	        			+ "专业：计算生物学（2018级）\n","创作者信息",JOptionPane.PLAIN_MESSAGE); 	        	
	        }
	    }
	 
	 class actHelp implements ActionListener {
	        public void actionPerformed(ActionEvent arg0) {	        	
	        	JOptionPane.showMessageDialog(null,"1. 菜单栏上有“File”按钮，可新建，打开，保存地图\n"+
	        			"2. 选择左方元素绘制地图\n"
	        			+"3. 选择网格显示或隐藏","地图编辑器使用说明",JOptionPane.PLAIN_MESSAGE); 	        	
	        }
	    }
	 
	 class actNew implements ActionListener {
	        public void actionPerformed(ActionEvent arg0) {	        	
	        	panelMain.newMap();
	        }
	    }
	 class actOpen implements ActionListener {		 
		 JFileChooser fileDialog = new JFileChooser();
		 File f;
	       public void actionPerformed(ActionEvent arg0) {	        	
	        	int state = fileDialog.showOpenDialog(null);
		        if (state == JFileChooser.APPROVE_OPTION) {
		        	f=fileDialog.getSelectedFile();
	             try {
					panelMain.openMap(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    }
	 } 
	 
}
