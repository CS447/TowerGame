package towergame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import towergame.entities.Player.PlayerState;
import towergame.tiles.TileUtil;

public class PlayerShadows {
	
	private Image shadowStand;
	private SpriteSheet ssShadowWalk;
	private Animation shadowWalk;
	
	public PlayerShadows(){
		ssShadowWalk = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_SHADOW_WALK, 64, 77);
		shadowWalk = new Animation(ssShadowWalk, 0, 0, 3, 0, true, 250, true );
		shadowStand = ResourceManager.getImage(TowerGame.SPRITE_SHADOW_STAND);
	}
	
	public void draw(Vector2f camera, Vector2f pos, PlayerState ps){
		float tempX = pos.getX();
		float tempY = pos.getY();
		
		tempX = TileUtil.toIsoX(pos.getX(), pos.getY()) + camera.getX() - 32;
		tempY = TileUtil.toIsoY(pos.getX(), pos.getY()) + camera.getY() - 57;
		
		switch (ps){
		case WALK_DOWN:
		case WALK_LEFT:
		case WALK_RIGHT:
		case WALK_UP:
			shadowWalk.draw(tempX, tempY);
			break;
		default:
			shadowStand.draw(tempX, tempY);
			break;
		
		}
	}
}
