package towergame.tiles;

import org.newdawn.slick.geom.Vector2f;

public class Tile {
	
	private Vector2f position;
	private int style;
	private boolean power;

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
		this(new Vector2f( (32 * x) , (16 *y) ));
		setStyle(myStyle);
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
	}

	public boolean isOn() {
		return power;
	}

	public void setPower(boolean power) {
		this.power = power;
	}
}
