package towergame.tiles;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;

public class Tile {
	
	private Vector2f position;
	private Vector2f force;
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
		case 1: // Standard Tile
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BASIC);
			setForce(new Vector2f(0,0));
			break;
		case 2: // Conveyor Belt Up
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_U, 64, 40), 0, 0, 2, 0, true, 150, true );
			setForce(new Vector2f(0,-0.125f));
			break;
		case 3: // Conveyor Belt Right
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_R, 64, 40), 0, 0, 2, 0, true, 150, true );
			setForce(new Vector2f(0.125f,0));
			break;
		case 4: // Conveyor Belt Down
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_D, 64, 40), 0, 0, 2, 0, true, 150, true );
			setForce(new Vector2f(0,0.125f));
			break;
		case 5: // Conveyor Belt Left
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_L, 64, 40), 0, 0, 2, 0, true, 150, true );
			setForce(new Vector2f(-0.125f,0));
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
			case 3:
			case 4:
			case 5:
				animation.draw(temp.getX()+camera.getX(), temp.getY()+camera.getY());
				break;
			case 6:
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

	public Vector2f getForce() {
		return force;
	}

	public void setForce(Vector2f force) {
		this.force = force;
	}
}
