package Music;

import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet; 
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class loginMusic implements Runnable { 
    public String name = "三个人的时光.wav";//可以用来选择你想要播放的音乐
     File f ; 
     URL url; 
     URI uri;
     AudioClip clip;
     public loginMusic(String name) {
    	 f= new File("C:\\Users\\吉佩雯\\Desktop\\Java\\课程\\TankWar2\\src\\Music/"+name);
    	 this.name = name;
    	 try
         {  
            uri=f.toURI();
            System.out.println(uri);
            url = uri.toURL();
            System.out.println(url);
            clip = Applet.newAudioClip(url); 
            clip.play();//播放
            clip.loop();//循环播放
            //clip.stop();//停止播放
            System.out.println("音乐文件已经打开");
        }catch (MalformedURLException e) { 
                e.printStackTrace(); 
                System.out.println("播放错误！");
           }
     }
    public void setMusic(String name)//修改播放的音乐文件
    {
        this.name=name;
    }
   public void stopMusic()//停止播放
   {
       clip.stop();
   }
   public void playMusic()//播放
   {
       clip.play();
   }
   public void loopMusic()//循环播放
   {
       clip.loop();
   }
@Override
public void run() {
	 
}
}