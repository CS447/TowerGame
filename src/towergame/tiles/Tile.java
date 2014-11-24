package towergame.tiles;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;

public class Tile {
	
	private Vector2f position;
	private int style;
	private boolean power;
	
	private Image sprite;
	private Animation animation;

	public Tile(Vector2f myPosition){
		setPosition(myPosition);
	}
	
	public Tile(float x, float y){
		this(new Vector2f(x, y));
	}
	
	/**
	 * Create a new tile at the Tile Coordinate <br>
	 * Converts it into pixel (x, y) for you
	 * @param x
	 * @param y
	 * @param myStyle
	 */
	public Tile(int x, int y, int myStyle){
		// TileUtil.toIsoX((16 * x) , (16 * y)), TileUtil.toIsoY((16 * x) , (16 * y))
		//  (32 * x) + (32 * y)-100 , (-16 * x) + (16 * y)+400 
		//this(new Vector2f( (32 * x) + (32 * y)-100 , (-16 * x)+(16 * y)+400 ));
		this(new Vector2f(32*x-450, 32*y+350));
		setStyle(myStyle);
	}

	public void setImage(){
		switch(style){
		case 1:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BASIC);
			break;
		case 2:
			break;
		case 3:
			break;
		}
	}
	
	public void draw(){
		switch(style){
			case 1:
				sprite.draw(TileUtil.toIso(position).getX(),TileUtil.toIso(position).getY());
				//System.out.println("("+position.x + ", " + position.y+")");
				break;
			case 2:
				break;
			case 3:
				break;
		}
	}
	
	// -------------------
	// Getters and Setters
	// -------------------
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
		setImage();
	}

	public boolean isOn() {
		return power;
	}

	public void setPower(boolean power) {
		this.power = power;
	}
}
