package towergame.entities;

import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;
import towergame.circuits.Circuit;
import towergame.tiles.TileManager;
import towergame.tiles.TileUtil;

public class Box extends Mechanism{

	private Image sprite;
	
	public Box(Vector2f myPosition) {
		super(myPosition);
		
		sprite = ResourceManager.getImage(TowerGame.SPRITE_BOX);
	}
	
	public Box(float x, float y) {
		this(new Vector2f(x, y));
	}

	
	
	@Override
	public void draw(Vector2f camera) {
		// Sprite Size 64x64
		// Box position is located at the center of the box that touches the ground 
		// 32px right and 51px down from top left of sprite
		
		float tempX = getX();
		float tempY = getY();
		
		// These numbers setup the sprite accordingly (even though its -38 and not -51)
		tempX = TileUtil.toIsoX(getX(), getY()) + camera.getX() - 32;
		tempY = TileUtil.toIsoY(getX(), getY()) + camera.getY() - 34;
		
		sprite.draw(tempX, tempY);
		
	}

	private void resetVelocity(){
		velocity = new Vector2f(0,0);
	}
	
	private void move(TileManager tm, float myX, float myY){
		Vector2f temp = new Vector2f(getX()+myX, getY()+myY);
		
		if (tm.tileStyle(temp) > 0) {
			this.setPosition(getX() + myX, getY() + myY);
		}
	}
	
	private void checkTile(TileManager tm, List<Circuit> cl, int delta){
		this.velocity = tm.tileForce(getPosition());
		tm.tileEvent(getPosition(), cl, delta);
	}

	@Override
	public void update(int delta, TileManager tm, List<Circuit> cl) {
		checkTile(tm, cl, delta);
		move(tm, velocity.x * delta, velocity.y * delta);
		resetVelocity();
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}
}
