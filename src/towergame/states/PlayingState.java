package towergame.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import towergame.WorldState;
import towergame.entities.Player;
import towergame.entities.Player.PlayerState;
import towergame.maps.TileMaps;
import towergame.tiles.TileManager;
import towergame.tiles.TileUtil;

public class PlayingState extends BasicGameState{

	WorldState ws;
	
	TileManager tileManager;
	Vector2f cameraPos;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		tileManager = new TileManager();
		
		ws = new WorldState();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setMusicOn(false);
		
		tileManager.clear();
		tileManager.loadMap(TileMaps.level1, 24, 12);
		//tileManager.loadMap(TileMaps.level0, 2, 2);
		cameraPos = TileMaps.level1CameraStart;
		
		ws.p1 = new Player(48, 224, true);
		//ws.p2 = new Player(64+32+16, 32+16-38-38, false);
		ws.p2 = new Player(128, 128, false);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.setAntiAlias(false);
		
		tileManager.draw(cameraPos);
		g.drawString("Camera:   (" + Float.toString(cameraPos.x)+", "+Float.toString(cameraPos.y)+")", 50, 50);
		g.drawString("Player 1: (" + Float.toString(ws.p1.getX())+", "+Float.toString(ws.p1.getY())+")", 50, 70);
		g.drawString("Isomet 1: (" + Float.toString(TileUtil.toCarX(ws.p1.getX(), ws.p1.getY()))+", "+Float.toString(TileUtil.toCarY(ws.p1.getX(), ws.p1.getY()))+")", 50, 90);
		//g.drawString("Player 2: (" + Float.toString(ws.p2.getX())+", "+Float.toString(ws.p2.getY())+")", 50, 90);
			
		
		Input input = container.getInput();
		
		
		// Just a temp concept of moving the camera. Want to base the camera on
		// Player's position (Not the way its currently set up). Need to change this.
		if (input.isKeyDown(Input.KEY_DOWN)){
			//cameraPos.y -= 2;
			ws.p1.setState(PlayerState.WALK_DOWN);
			ws.p1.walkDown();
		}
		if (input.isKeyDown(Input.KEY_RIGHT)){
			//cameraPos.x -= 2;
			ws.p1.setState(PlayerState.WALK_RIGHT);
			ws.p1.walkRight();
		}
		if (input.isKeyDown(Input.KEY_LEFT)){
			//cameraPos.x += 2;
			ws.p1.setState(PlayerState.WALK_LEFT);
			ws.p1.walkLeft();
		}
		if (input.isKeyDown(Input.KEY_UP)){
			//cameraPos.y += 2;
			ws.p1.setState(PlayerState.WALK_UP);
			ws.p1.walkUp();
		}
		
		if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT) &&
				!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP)){
			ws.p1.setStand();
		}
		
		
		ws.p1.draw(cameraPos, true);
		ws.p2.draw(cameraPos, false);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO
		
		ws.p1.update(delta);
		ws.p2.update(delta);
		
		cameraPos.x = ws.p1.getX() - 368;
		cameraPos.y = ws.p1.getY() - 262;
	}

	@Override
	public int getID() {
		return 2;
	}

}
