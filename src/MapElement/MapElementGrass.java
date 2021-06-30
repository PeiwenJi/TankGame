package MapElement;

public class MapElementGrass extends MapElement{
	public MapElementGrass(int x,int y) {
		super(x,y);
		imgX = 4*34;
		imgY = 7*34;
		width = 34;
		type = "GRASS";
		pass = true;
		destroy = false;
		boom = true;
	}
	public MapElement CloneElement(int x,int y) {
		return new MapElementGrass(x,y);
	}

}
