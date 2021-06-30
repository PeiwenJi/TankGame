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
		super("��ͼ�༭��");
	}
	
	public void initFrame() {
		//�½���ͼ���
		panelMain = new PanelMain();	
		panelMain.initPanel();
		panelMain.setBorder(new LineBorder(Color.BLUE));
		panelMain.setPreferredSize(new Dimension(800,600));
		//�½�һ��������壬��ͼ�������ŵ���������У���ֱ������������ʾ��ˮƽ������ֻ������Ҫʱ��ʾ
		JScrollPane panel = new JScrollPane(panelMain,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelIcon = new PanelIcon();	
		panelIcon.initPanel();
		panelMain.setPanelIcon(panelIcon);
		panelIcon.setPanelMain(panelMain);
		
		setLayout(new BorderLayout());
		add(panelIcon,BorderLayout.WEST);
		add(panel);//	������������ӵ�����
		
		//��Ӳ˵�
		JMenuBar menuBar = new JMenuBar();//�½��˵���
		this.setJMenuBar(menuBar);//���˵�����ӵ�������
		
		JMenu fileMenu = new JMenu("File");//�½��˵�"File"
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);
		
//		JMenu editMenu = new JMenu("Edit");//�½��˵�"Edit"
//		editMenu.setMnemonic('E');
//		menuBar.add(editMenu);
		
		JMenu helpMenu = new JMenu("Help");//�½��˵�"Help"
		helpMenu.setMnemonic('H');
		menuBar.add(helpMenu);
		
		//File�¹���
		JMenuItem newItem = new JMenuItem("new");//�½�"new"�˵���
		newItem.setAccelerator(KeyStroke.getKeyStroke('N'));//Ϊ�˵������"N"��ݼ�
		newItem.addActionListener(new actNew());
		fileMenu.add(newItem);//��"new"�˵�����ӵ�"File"�˵���
		
		JMenuItem openItem = new JMenuItem("open");//�½�"open"�˵���
		openItem.addActionListener(new actOpen());
		//Ϊ�˵������Ctrl+O��ݼ�
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		fileMenu.add(openItem);//��"open"�˵�����ӵ�"File"�˵���
		
		JMenuItem saveItem = new JMenuItem("save");//�½�"open"�˵���
		//Ϊ�˵������Ctrl+S��ݼ�
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		fileMenu.add(saveItem);//��"open"�˵�����ӵ�"File"�˵���
		saveItem.addActionListener(new actSave());
		
		//Edit�¹���
//		JMenuItem undoItem = new JMenuItem("undo");//�½�undo"�˵���
//		//Ϊ�˵������Ctrl+Z��ݼ�
//		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
//		editMenu.add(undoItem);//��"undo"�˵�����ӵ�"Edit"�˵���
//		undoItem.addActionListener(new actUndo());
//		
//		JMenuItem redoItem = new JMenuItem("redo");//�½�"redo"�˵���
//		//Ϊ�˵������Ctrl+Y��ݼ�
//		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_MASK));
//		editMenu.add(redoItem);//��"undo"�˵�����ӵ�"Edit"�˵���
		
		//Help�¹���
		JMenuItem aboutItem = new JMenuItem("about");//�½�"about"�˵���
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(new actabout());
		
		JMenuItem helpItem = new JMenuItem("help");//�½�"help"�˵���
		helpMenu.add(helpItem);
		helpItem.addActionListener(new actHelp());
		
		//Format�¹���
//		JMenuItem gridItem1 = new JMenuItem("gridline hide");//�½�"about"�˵���
//		formatMenu.add(gridItem1);
//		aboutItem.addActionListener(new actHide());
//		JMenuItem gridItem2 = new JMenuItem("gridline appear");//�½�"about"�˵���
//		formatMenu.add(gridItem2);
//		aboutItem.addActionListener(new actAppear());
		
		this.setSize(800,600);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);//���ô���ɼ�
	}
	
	
	class actSave implements ActionListener{

		JFileChooser fileDialog = new JFileChooser();
		public void actionPerformed(ActionEvent arg0) {
			
			int state = fileDialog.showSaveDialog(null);
	        if (state == JFileChooser.APPROVE_OPTION) {
	        	// ȡ���ļ�·����ʾ��tfDir�ı�����
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
	        	JOptionPane.showMessageDialog(null, "���ߣ�������\nѧУ���Ĵ���ѧ\n"
	        			+ "רҵ����������ѧ��2018����\n","��������Ϣ",JOptionPane.PLAIN_MESSAGE); 	        	
	        }
	    }
	 
	 class actHelp implements ActionListener {
	        public void actionPerformed(ActionEvent arg0) {	        	
	        	JOptionPane.showMessageDialog(null,"1. �˵������С�File����ť�����½����򿪣������ͼ\n"+
	        			"2. ѡ����Ԫ�ػ��Ƶ�ͼ\n"
	        			+"3. ѡ��������ʾ������","��ͼ�༭��ʹ��˵��",JOptionPane.PLAIN_MESSAGE); 	        	
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
