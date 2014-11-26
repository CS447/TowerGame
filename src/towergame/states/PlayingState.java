package towergame.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import towergame.entities.Player;
import towergame.entities.Player.PlayerState;
import towergame.maps.TileMaps;
import towergame.tiles.TileManager;

public class PlayingState extends BasicGameState{

	TileManager tileManager;
	Vector2f cameraPos;
	
	Player p1;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		tileManager = new TileManager();
		
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setMusicOn(false);
		
		tileManager.clear();
		tileManager.loadMap(TileMaps.level1, 24, 12);
		cameraPos = TileMaps.level1CameraStart;
		
		p1 = new Player(368, 262);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.setAntiAlias(false);
		
		tileManager.draw(cameraPos);
		g.drawString("Camera: (" + Float.toString(cameraPos.x)+", "+Float.toString(cameraPos.y)+")", 50, 50);
		
		
		
		Input input = container.getInput();
		
		if (input.isKeyDown(input.KEY_DOWN)){
			cameraPos.y -= 2;
			p1.setState(PlayerState.WALK_DOWN);
		}
		if (input.isKeyDown(input.KEY_RIGHT)){
			cameraPos.x -= 2;
			p1.setState(PlayerState.WALK_RIGHT);
		}
		if (input.isKeyDown(input.KEY_LEFT)){
			cameraPos.x += 2;
			p1.setState(PlayerState.WALK_LEFT);
		}
		if (input.isKeyDown(input.KEY_UP)){
			cameraPos.y += 2;
			p1.setState(PlayerState.WALK_UP);
		}
		
		if (!input.isKeyDown(input.KEY_DOWN) && !input.isKeyDown(input.KEY_RIGHT) &&
				!input.isKeyDown(input.KEY_LEFT) && !input.isKeyDown(input.KEY_UP)){
			p1.setStand();
		}
		
		
		p1.draw();
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO
		
		p1.update(delta);
	}

	@Override
	public int getID() {
		return 2;
	}

}
