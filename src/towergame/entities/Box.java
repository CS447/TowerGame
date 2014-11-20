package towergame.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;

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
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
