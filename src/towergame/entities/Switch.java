package towergame.entities;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import towergame.ResourceManager;
import towergame.TowerGame;
import towergame.circuits.Circuit;
import towergame.tiles.TileManager;
import towergame.tiles.TileUtil;

public class Switch extends Mechanism{
	
	private SpriteSheet sprites[] = new SpriteSheet[2];
	private Animation animations[] = new Animation[4];
	private int state;  //Flipped or not?
	
	public Switch(Vector2f myPosition) {
		super(myPosition);
		state = 1;
		
		sprites[0] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_SWITCH_ON, 64, 76);
		sprites[1] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_SWITCH_OFF, 64, 76);
		
		animations[0] = new Animation(sprites[0], 0, 0, 0, 0, true, 250, false );
		animations[1] = new Animation(sprites[1], 0, 0, 0, 0, true, 250, false );
		animations[2] = new Animation(sprites[0], 0, 0, 3, 0, true, 250, false );
		animations[3] = new Animation(sprites[1], 0, 0, 3, 0, true, 250, false );
		
		//Need switch sprites
	}
	
	public Switch(float x, float y) {
		this(new Vector2f(x, y));
	}
	
	public void draw(Vector2f camera) {
		// Sprite Size size of a full tile
		// Inhabits of full tile, activated by "E" press in a short radius around it
		// state 1 = not flipped, state 2 = flipped
		
		float tempX = getX();
		float tempY = getY();
		
		// These numbers setup the sprite accordingly (even though its -38 and not -51)
		tempX = TileUtil.toIsoX(getX(), getY()) + camera.getX() - 32;
		tempY = TileUtil.toIsoY(getX(), getY()) + camera.getY() - 38;
		
		switch(state){
		case 1:
			animations[0].draw(tempX, tempY);
			break;
		case 2:
			animations[1].draw(tempX, tempY);
			break;
		}		
	}
	
	public void flipState()
	{
		if (state == 1)
		{
			state = 2;
		}
		else
		{
			state = 1;
		}
	}
	
	public int getState()
	{
		return state;
	}
	
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int delta, TileManager tm, List<Circuit> cl) {
		// TODO Auto-generated method stub
		
	}

}
