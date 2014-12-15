package towergame.entities;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import towergame.ResourceManager;
import towergame.TowerGame;
import towergame.circuits.Circuit;
import towergame.tiles.TileManager;
import towergame.tiles.TileUtil;

public class Switch extends Mechanism{
	
	private Image sprite;
	private int state;  //Flipped or not?
	
	public Switch(Vector2f myPosition) {
		super(myPosition);
		
		//sprite = ResourceManager.getImage(TowerGame.SPRITE_SWITCH_OFF);
		//Need switch sprites
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
			//sprite = ResourceManager.getImage(TowerGame.SPRITE_SWITCH_OFF);
			sprite.draw(tempX, tempY);
			break;
		case 2:
			//sprite = ResourceManager.getImage(TowerGame.SPRITE_SWITCH_ON);
			sprite.draw(tempX, tempY);
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
