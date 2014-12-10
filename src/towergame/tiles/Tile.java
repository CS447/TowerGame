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
		this(new Vector2f(32*x, 32*y));
		
		setStyle(myStyle);
	}

	public void setImage(){
		switch(style){
		case 1:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BASIC);
			break;
		case 2:
			//Can use these cases for pressure plates on and off, no need for new class
			break;
		case 3:
			break;
		}
	}
	
	public void draw(Vector2f camera){
		Vector2f temp = TileUtil.toIso(this.position);
		
		switch(style){
			case 1:
				sprite.draw(temp.getX()+camera.getX(), temp.getY()+camera.getY());
				//System.out.println("("+position.x + ", " + position.y+") = (" +
				//		TileUtil.getCoordinateX(position.x) + ", " + 
				//		TileUtil.getCoordinateY(position.y)+")");
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
