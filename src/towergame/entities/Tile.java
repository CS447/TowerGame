package towergame.entities;

import org.newdawn.slick.geom.Vector2f;

public abstract class Tile extends Entity{
	
	public Tile(Vector2f myPosition) {
		super(myPosition);
		// TODO Auto-generated constructor stub
	}
	
	public Tile(float x, float y) {
		this(new Vector2f(x, y));
	}

}
