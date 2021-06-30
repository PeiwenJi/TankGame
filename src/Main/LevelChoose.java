package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import FrameGame.FrameGame1;
import FrameGame.FrameGame2;
import FrameGame.FrameGame3;

public class LevelChoose extends JFrame {
	private JButton jb1;
	private JButton jb2;
	private JButton jb3;
	private JPanel mp;
	
	public LevelChoose() throws IOException{
		super("��ӭѡ��ؿ�");
		setSize(400,400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		mp = new JPanel();
		jb1 = new JButton("�ؿ�һ(���Ѷ�)");
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ؿ�ѡ�����,�������滭��Ͱ�ť���أ�������Ϸ���
				mp.setVisible(false);
				jb1.setVisible(false);
				jb2.setVisible(false);
				jb3.setVisible(false);
				setVisible(false);
				FrameGame1 frame = null;
				try {
					frame = new FrameGame1();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);		
		    }
		});
		jb2 = new JButton("�ؿ���(���Ѷ�)");
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ؿ�ѡ�����,�������滭��Ͱ�ť���أ�������Ϸ���
				mp.setVisible(false);
				jb1.setVisible(false);
				jb2.setVisible(false);
				jb3.setVisible(false);
				setVisible(false);
				FrameGame2 frame = null;
				try {
					frame = new FrameGame2();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);		
		    }
		});
		jb3 = new JButton("�ؿ���(���Ѷ�)");
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ؿ�ѡ�����,�������滭��Ͱ�ť���أ�������Ϸ���
				mp.setVisible(false);
				jb1.setVisible(false);
				jb2.setVisible(false);
				jb3.setVisible(false);
				setVisible(false);
				FrameGame3 frame = null;
				try {
					frame = new FrameGame3();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);		
		    }
		});
		//jb3.addActionListener(new jb3Listener());
		this.add(mp);
		mp.setLayout(new GridLayout(3, 1));
		mp.setBounds(0, 0, 400, 400);
		mp.add(jb1);
		mp.add(jb2);
		mp.add(jb3);
	}
	
}
