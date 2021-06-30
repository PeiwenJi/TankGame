package MapElement;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



public class GamePanel extends JPanel{
	private ArrayList elementList = new ArrayList();
	private ArrayList<MapElementBrick> bricksList;
	
	public void paint(Graphics g) {
		for(int i=0;i<elementList.size();i++) {
			((MapElement) elementList.get(i)).draw(g);
		}
	}
	
	public void newMap() {
		elementList.clear();
		repaint();
	}
	public ArrayList getElementList() {
		return elementList;
	}
	public void openMap(File file) throws IOException {
		String lines;
		List<String> list = new ArrayList<String>();
		ArrayList<MapElement> load = new ArrayList<MapElement>(); 
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while((lines = bufferedReader.readLine()) != null) {
			list.add(lines);
		}
		bufferedReader.close();
		for(int i = 0;i<list.size();i++) {
			String singleList = list.get(i);
			if(singleList != "") {
				String[] item = new String[1];
			    item = singleList.split("=|,|;");
			    int j ;
			    for( j = 0 ; j < item.length ; j++) {
			       if(j%2 == 1) {
			    		int a = Integer.parseInt(item[j]);
			    		int b = Integer.parseInt(item[j+1]);
			    		MapElement e = new MapElement(a,b);
			    		e = e.setType(item[0],a,b);
			    		load.add(e);
			    	}
			    }
     		}
		}		
		this.elementList = load ; 

	}
	
}