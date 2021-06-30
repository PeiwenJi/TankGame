package Main;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.sun.org.apache.bcel.internal.generic.Select;
import Music.Music1;
import Music.Music3;
import Music.loginMusic;
/**
 * 
 * @author Administrator
 *点击开始进入游戏界面
 */
public class Main extends JFrame{
    private static Main main = new Main();
	private JButton jb;
	private MyPanel mp;
	JMenuBar menuBar = new JMenuBar();
	public static void main(String[] args) {
		Main.getInstance();
	}
	private Main(){
		super("欢迎进入游戏");
		jb = new JButton("开始游戏");
		mp = new MyPanel();
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("about");
		JMenuItem helpItem = new JMenuItem("help");
		//启动音乐线程
		new Thread(new Music3()).start();		
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//进入关卡选择界面,将本界面画板和按钮隐藏，进入游戏面板
				mp.setVisible(false);
				jb.setVisible(false);
				main.setVisible(false);
				LevelChoose levelchoose;
				try {
					levelchoose = new LevelChoose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				FrameGame frame = null;
//				try {
//					
//					frame = new FrameGame();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				frame.setVisible(true);
//			
			}
		});
		//添加窗口元素
		this.setLayout(new BorderLayout());
		this.add(jb);
		this.add(mp);
		//将菜单条添加到窗体上
		this.setJMenuBar(menuBar);
		//在菜单条增加选项
		this.add(helpMenu);
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);		
		aboutItem.addActionListener(new actabout());
		helpMenu.add(helpItem);
		helpItem.addActionListener(new actHelp());
		jb.setBounds(200, 470, 100, 80);
		mp.setBounds(0, 0, 500, 600);
		//界面设置
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public static Main getInstance() {
        return main;
    }
}
//登入面板
class MyPanel extends JPanel{
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(new ImageIcon("src/Main/loginPic.jpg").getImage(),0,0, 500,600, null);
	}
}

class actabout implements ActionListener {
    public void actionPerformed(ActionEvent arg0) {	        	
    	JOptionPane.showMessageDialog(null, "作者：吉佩雯\n学校：四川大学\n专业："
    			+ "计算生物学（2018级）","创作者信息",JOptionPane.PLAIN_MESSAGE); 	        	
    }
}
class actHelp implements ActionListener {
    public void actionPerformed(ActionEvent arg0) {	        	
    	JOptionPane.showMessageDialog(null, "游戏说明：\n鼠标单击 “开始游戏”进入坦克大战\n"
    			,"游戏说明",JOptionPane.PLAIN_MESSAGE); 	        	
    }
}
