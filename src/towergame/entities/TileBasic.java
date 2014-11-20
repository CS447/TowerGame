package towergame.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;

public class TileBasic extends Tile{
	
	private Image sprite;
	
	public TileBasic(Vector2f myPosition) {
		super(myPosition);

		sprite = ResourceManager.getImage(TowerGame.SPRITE_TILE_BASIC);
		
	}
	
	public TileBasic(float x, float y) {
		this(new Vector2f(x, y));
	}

	@Override
	public void draw() {
		// Sprite 32x20
		sprite.draw(getX(), getY()-8);
		
	}

	@Override
	public void update(long delta) {
		// TODO Auto-generated method stub
		
	}
}
