package towergame.tiles;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;

public class Teleporter extends Tile {
	
	//The other teleporter connected to this one
	private Teleporter sister;
	
	private Image sprite;
	private Animation animation;

	public Teleporter(Vector2f myPosition){
		super(myPosition);
	}
	
	public Teleporter(float x, float y){
		this(new Vector2f(x, y));
	}
	
	/**
	 * Create a new tile at the Tile Coordinate <br>
	 * Converts it into pixel (x, y) for you
	 * @param x
	 * @param y
	 * @param myStyle
	 */
	public Teleporter(int x, int y, int myStyle){
		this(new Vector2f(32*x, 32*y));
		
		setStyle(myStyle);
	}
	
	public void setImage(){
		switch(getStyle()){
		case 1:
			sprite = ResourceManager.getImage(TowerGame.SPRITESHEET_TELEPORTER_OFF);
			break;
		case 2:
			//Will be an already on teleporter, need to find out how to add that animation
			break;
		case 3:
			break;
		}
	}
	
	//Sets sister teleporter, and then sets the sister teleporter of the sister to this teleporter
	public void setSister(Teleporter sister) {
		if (this.sister == null) {
			this.sister = sister;
			sister.setSister(this);
		}
	}

}
