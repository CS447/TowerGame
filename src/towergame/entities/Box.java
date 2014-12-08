package towergame.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;
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
		tempY = TileUtil.toIsoY(getX(), getY()) + camera.getY() - 38;
		
		sprite.draw(tempX, tempY);
		
	}

	@Override
	public void update(long delta) {
		// TODO Auto-generated method stub
		
	}

}
