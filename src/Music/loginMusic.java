package Music;

import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet; 
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class loginMusic implements Runnable { 
    public String name = "�����˵�ʱ��.wav";//��������ѡ������Ҫ���ŵ�����
     File f ; 
     URL url; 
     URI uri;
     AudioClip clip;
     public loginMusic(String name) {
    	 f= new File("C:\\Users\\������\\Desktop\\Java\\�γ�\\TankWar2\\src\\Music/"+name);
    	 this.name = name;
    	 try
         {  
            uri=f.toURI();
            System.out.println(uri);
            url = uri.toURL();
            System.out.println(url);
            clip = Applet.newAudioClip(url); 
            clip.play();//����
            clip.loop();//ѭ������
            //clip.stop();//ֹͣ����
            System.out.println("�����ļ��Ѿ���");
        }catch (MalformedURLException e) { 
                e.printStackTrace(); 
                System.out.println("���Ŵ���");
           }
     }
    public void setMusic(String name)//�޸Ĳ��ŵ������ļ�
    {
        this.name=name;
    }
   public void stopMusic()//ֹͣ����
   {
       clip.stop();
   }
   public void playMusic()//����
   {
       clip.play();
   }
   public void loopMusic()//ѭ������
   {
       clip.loop();
   }
@Override
public void run() {
	 
}
}