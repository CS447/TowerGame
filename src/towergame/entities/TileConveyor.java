package towergame.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;

public class TileConveyor extends Tile{
	
	private SpriteSheet ssUp;
	private SpriteSheet ssRight;
	private SpriteSheet ssDown;
	private SpriteSheet ssLeft;
	
	private Animation animation;
	
	
	public TileConveyor(Vector2f myPosition) {
		super(myPosition);

		ssUp = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_U, 32, 20);
		ssRight = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_R, 32, 20);
		ssDown = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_D, 32, 20);
		ssLeft = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_TILE_CONVEYOR_L, 32, 20);
		
		setAnimation();
		
	}
	
	public TileConveyor(float x, float y) {
		this(new Vector2f(x, y));
	}
	
	private void setAnimation(){
		switch (state){
		case 0:
			animation = new Animation(ssUp, 0, 0, 2, 0, true, 400, true );
			break;
		case 1:
			animation = new Animation(ssRight, 0, 0, 2, 0, true, 400, true );
			break;
		case 2:
			animation = new Animation(ssDown, 0, 0, 2, 0, true, 400, true );
			break;
		case 3:
			animation = new Animation(ssLeft, 0, 0, 2, 0, true, 400, true );
			break;
		}
		animation.setLooping(true);
	}
	
	@Override
	public void setState(int myState){
		state = myState;
		setAnimation();
	}
	
	@Override
	public void draw() {
		// Sprite 32x20
		animation.draw(getX(), getY()-8);
		
	}

	@Override
	public void update(long delta) {
		animation.update(delta);
		
	}
}
