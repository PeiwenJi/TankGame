package MapElement;

public class MapElementBase extends MapElement{
	public MapElementBase(int x,int y) {
		super(x,y);
		imgX = 19*34;
		imgY = 5*34;
		width = 34;
		type = "BASE";
		pass = true;
		destroy = false;
		boom = true;
	}
	public MapElement CloneElement(int x,int y) {
		return new MapElementBase(x,y);
	}
}
