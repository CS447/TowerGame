package towergame.tiles;

import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;
import towergame.circuits.Circuit;

public class Tile {
	
	private Vector2f position;
	private Vector2f force;
	private int style;
	private boolean power;
	
	private int circuit;
	private int input;
	
	private Image sprite;
	private Image sprite2;
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
			break;
		case 2: // Conveyor Belt Up
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_U, 64, 40), 0, 0, 2, 0, true, 150, true );
			break;
		case 3: // Conveyor Belt Right
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_R, 64, 40), 0, 0, 2, 0, true, 150, true );
			break;
		case 4: // Conveyor Belt Down
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_D, 64, 40), 0, 0, 2, 0, true, 150, true );
			break;
		case 5: // Conveyor Belt Left
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_L, 64, 40), 0, 0, 2, 0, true, 150, true );
			break;
		case 6:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON1);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF1);
			break;
		case 7:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON2);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF2);
			break;
		case 8:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON3);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF3);
			break;
		case 9:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON4);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF4);
			break;
		case 10:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON5);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF5);
			break;
		case 11:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON6);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF6);
			break;
		case 12:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON7);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF7);
			break;
		case 13:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON8);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF8);
			break;
		case 14:
			sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_ON9);
			sprite2 = ResourceManager.getImage(TowerGame.SPRITE_TILE_BUTTON_OFF9);
			break;
		case 15:
			animation = new Animation(ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TELEPORTER_ON, 64, 40), 0, 0, 13, 0, true, 150, true );
			break;
			
		}
	}
	
	public void setTileForce(){
		switch(style){
			case 1:
				setForce(new Vector2f(0,0));
				break;
			case 2:
				setForce(new Vector2f(0,-0.125f));
				break;
			case 3:
				setForce(new Vector2f(0.125f,0));
				break;
			case 4:
				setForce(new Vector2f(0,0.125f));
				break;
			case 5:
				setForce(new Vector2f(-0.125f,0));
				break;
			case 6:
				setForce(new Vector2f(0,0));
				break;
			default:
				setForce(new Vector2f(0,0));
				break;
		}
		if (this.power == false){
			setForce(new Vector2f(0,0));
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
				if (power == true){
					if (animation.isStopped() == true)
						animation.start();
				} else {
					animation.stop();
				}
				break;
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				if (isOn()){
					sprite.draw(temp.getX()+camera.getX(), temp.getY()+camera.getY());
				} else {
					sprite2.draw(temp.getX()+camera.getX(), temp.getY()+camera.getY());
				}
				break;
			case 15:
				animation.draw(temp.getX()+camera.getX(), temp.getY()+camera.getY());
				power = true;  //For testing purposes starting them all with power on.  You guys can change it, I just don't know how the circuits work just yet
				if (power == true){
					if (animation.isStopped() == true)
						animation.start();
				} else {
					animation.stop();
				}
				break;
			default:
			break;
		}
	}
	
	public void update(int delta, List<Circuit> cl){
		if (this.input != 0){
			this.power = false;
		}
		switch(style){
		case 1:
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			for (Circuit circuit: cl){
				if (circuit.getId() == this.circuit){
					this.setPower(circuit.isOn());
				}
			}
			break;
		default:
			break;
		}
		setTileForce();
	}
	
	public void update2(int delta, List<Circuit> cl){
		switch(style){
		case 1:
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
			this.power = true;
			for (Circuit circuit: cl){
				if (circuit.getId() == this.circuit){
					circuit.setInput(this.input, this.power);
				}
			}
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
		setTileForce();
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

	public int getCircuit() {
		return circuit;
	}

	public void setCircuit(int circuit) {
		this.circuit = circuit;
	}

	public int getInput() {
		return input;
	}

	public void setInput(int input) {
		this.input = input;
	}
}
