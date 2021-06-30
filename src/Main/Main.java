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
 *�����ʼ������Ϸ����
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
		super("��ӭ������Ϸ");
		jb = new JButton("��ʼ��Ϸ");
		mp = new MyPanel();
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("about");
		JMenuItem helpItem = new JMenuItem("help");
		//���������߳�
		new Thread(new Music3()).start();		
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ؿ�ѡ�����,�������滭��Ͱ�ť���أ�������Ϸ���
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
		//��Ӵ���Ԫ��
		this.setLayout(new BorderLayout());
		this.add(jb);
		this.add(mp);
		//���˵�����ӵ�������
		this.setJMenuBar(menuBar);
		//�ڲ˵�������ѡ��
		this.add(helpMenu);
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);		
		aboutItem.addActionListener(new actabout());
		helpMenu.add(helpItem);
		helpItem.addActionListener(new actHelp());
		jb.setBounds(200, 470, 100, 80);
		mp.setBounds(0, 0, 500, 600);
		//��������
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public static Main getInstance() {
        return main;
    }
}
//�������
class MyPanel extends JPanel{
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(new ImageIcon("src/Main/loginPic.jpg").getImage(),0,0, 500,600, null);
	}
}

class actabout implements ActionListener {
    public void actionPerformed(ActionEvent arg0) {	        	
    	JOptionPane.showMessageDialog(null, "���ߣ�������\nѧУ���Ĵ���ѧ\nרҵ��"
    			+ "��������ѧ��2018����","��������Ϣ",JOptionPane.PLAIN_MESSAGE); 	        	
    }
}
class actHelp implements ActionListener {
    public void actionPerformed(ActionEvent arg0) {	        	
    	JOptionPane.showMessageDialog(null, "��Ϸ˵����\n��굥�� ����ʼ��Ϸ������̹�˴�ս\n"
    			,"��Ϸ˵��",JOptionPane.PLAIN_MESSAGE); 	        	
    }
}
