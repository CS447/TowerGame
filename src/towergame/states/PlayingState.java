package towergame.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import towergame.WorldState;
import towergame.circuits.Circuit;
import towergame.circuits.ReverseOrQuadCircuit;
import towergame.entities.Box;
import towergame.entities.Player;
import towergame.entities.Player.PlayerState;
import towergame.maps.TileMaps;
import towergame.tiles.TileManager;
import towergame.tiles.TileUtil;

public class PlayingState extends BasicGameState{

	WorldState ws;
	
	static TileManager tileManager;
	static Vector2f cameraPos;
	private int currLevel;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		tileManager = new TileManager();
		
		ws = new WorldState();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setMusicOn(false);
		
		ws.circuitList.clear();
		tileManager.clear();
		
		ws.level = 1;
		
		loadLevel();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.setAntiAlias(false);
		
		tileManager.draw(cameraPos);
		g.drawString("Camera:   (" + Float.toString(cameraPos.x)+", "+Float.toString(cameraPos.y)+")", 50, 50);
		g.drawString("Player 1: (" + Float.toString(ws.p1.getX())+", "+Float.toString(ws.p1.getY())+")", 50, 70);
		g.drawString("Isometr : (" + Float.toString(TileUtil.toCarX(ws.p1.getX(), ws.p1.getY()))+", "+Float.toString(TileUtil.toCarY(ws.p1.getX(), ws.p1.getY()))+")", 50, 90);
		g.drawString("Tile    : (" + Float.toString( TileUtil.getCoordinateX(ws.p1.getX()) )+", "+Float.toString( TileUtil.getCoordinateY(ws.p1.getY()) )+")", 50, 110);
		
		
		if (ws.p1.getY() < ws.p2.getY()){
			ws.p1.draw(cameraPos);
			ws.p2.draw(cameraPos);
		} else {
			ws.p2.draw(cameraPos);
			ws.p1.draw(cameraPos);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO
		
		// ----------------------------------------------------------------------------------------
		// Game Controls
		// ----------------------------------------------------------------------------------------
		
		Input input = container.getInput();
		
		//Reset command first, hold LSHIFT, R, N to reset
		if (input.isKeyDown(Input.KEY_LSHIFT) && input.isKeyDown(Input.KEY_R) &&
				input.isKeyDown(Input.KEY_N)) {
			reset(ws.level);
			return;
		}
		
		if (input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W)){
			ws.p1.setState(PlayerState.WALK_LEFT);
			ws.p1.walkUpLeft();
		} else if (input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W)){
			ws.p1.setState(PlayerState.WALK_UP);
			ws.p1.walkUpRight();
		} else if (input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_S)){
			ws.p1.setState(PlayerState.WALK_DOWN);
			ws.p1.walkDownLeft();
		} else if (input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S)){
			ws.p1.setState(PlayerState.WALK_RIGHT);
			ws.p1.walkDownRight();
		} else if (input.isKeyDown(Input.KEY_D)){
			ws.p1.setState(PlayerState.WALK_RIGHT);
			ws.p1.walkRight();
		} else if (input.isKeyDown(Input.KEY_A)){
			ws.p1.setState(PlayerState.WALK_LEFT);
			ws.p1.walkLeft();
		} else if (input.isKeyDown(Input.KEY_W)){
			ws.p1.setState(PlayerState.WALK_UP);
			ws.p1.walkUp();
		} else if (input.isKeyDown(Input.KEY_S)){
			ws.p1.setState(PlayerState.WALK_DOWN);
			ws.p1.walkDown();
		} 
		
		if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_D) &&
				!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_W)){
			ws.p1.setStand();
			
		}
		
		// TODO Get rid of Player 2 controls
		// Only here to test without networking
		
		if (input.isKeyDown(Input.KEY_LEFT) && input.isKeyDown(Input.KEY_UP)){
			ws.p2.setState(PlayerState.WALK_LEFT);
			ws.p2.walkUpLeft();
		} else if (input.isKeyDown(Input.KEY_RIGHT) && input.isKeyDown(Input.KEY_UP)){
			ws.p2.setState(PlayerState.WALK_UP);
			ws.p2.walkUpRight();
		} else if (input.isKeyDown(Input.KEY_LEFT) && input.isKeyDown(Input.KEY_DOWN)){
			ws.p2.setState(PlayerState.WALK_DOWN);
			ws.p2.walkDownLeft();
		} else if (input.isKeyDown(Input.KEY_RIGHT) && input.isKeyDown(Input.KEY_DOWN)){
			ws.p2.setState(PlayerState.WALK_RIGHT);
			ws.p2.walkDownRight();
		} else if (input.isKeyDown(Input.KEY_RIGHT)){
			ws.p2.setState(PlayerState.WALK_RIGHT);
			ws.p2.walkRight();
		} else if (input.isKeyDown(Input.KEY_LEFT)){
			ws.p2.setState(PlayerState.WALK_LEFT);
			ws.p2.walkLeft();
		} else if (input.isKeyDown(Input.KEY_UP)){
			ws.p2.setState(PlayerState.WALK_UP);
			ws.p2.walkUp();
		} else if (input.isKeyDown(Input.KEY_DOWN)){
			ws.p2.setState(PlayerState.WALK_DOWN);
			ws.p2.walkDown();
		} 
		
		if (!input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_RIGHT) &&
				!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_UP)){
			ws.p2.setStand();
			
		}
		
		// ----------------------------------------------------------------------------------------
		
		tileManager.update(delta, ws.circuitList);
		
		for (Circuit circuit: ws.circuitList){
			circuit.doLogic();
		}
		
		ws.p1.update(delta, tileManager, ws.circuitList);
		ws.p2.update(delta, tileManager, ws.circuitList);
		
		// Set the camera position (368 and 262 are to center the camera around the player)
		cameraPos = TileUtil.toIso(ws.p1.getPosition());
		cameraPos.x = -cameraPos.x + 368;
		cameraPos.y = -cameraPos.y + 262;
	}

	public void reset(int currLevel) {
		switch(ws.level) {
		case 1:
			tileManager.clear();
			tileManager.loadMap(TileMaps.level1, 24, 12);
			
			ws.p1 = new Player(48, 208, true);
			ws.p2 = new Player(48, 176, false);
			
			//cameraPos = new Vector2f(0,0);
			cameraPos = TileUtil.toIso(ws.p1.getPosition());
			cameraPos.x = -cameraPos.x + 368;
			cameraPos.y = -cameraPos.y + 262;
			break;
		case 2:
			//Reset 2nd level
			break;
		case 3:
			//Reset 3rd level
			break;
		case 4:
			//Reset 4th level
		}
	}

	public void loadLevel(){
		tileManager.clear();
		
		switch(ws.level){
			case 1:
				// Load Map
				tileManager.loadMap(TileMaps.level1, 24, 12);
				
				// Set Players
				ws.p1 = new Player(48, 208, true);
				ws.p2 = new Player(48, 176, false);
				
				// Load Circuits
				ws.circuitList.add(new ReverseOrQuadCircuit(1));
				
				break;
			case 2:
				break;	
		}
		// Remove blank tiles
		tileManager.removeExtras();
		
		// Set Camera
		cameraPos = TileUtil.toIso(ws.p1.getPosition());
		cameraPos.x = -cameraPos.x + 368;
		cameraPos.y = -cameraPos.y + 262;
		
		initSpecialTiles();
	}
	
	public void initSpecialTiles(){
		switch(ws.level){
		case 1:
			tileManager.setTileCircuit2(4, 2, 1, 1);
			tileManager.setTileCircuit2(4, 9, 1, 2);
			tileManager.setTileCircuit2(19, 2, 1, 1);
			tileManager.setTileCircuit2(19, 9, 1, 2);
			for (int i = 6; i < 18; i++){
				tileManager.setTileCircuit2(i, 1, 1, 0);
				tileManager.setTileCircuit2(i, 2, 1, 0);
				tileManager.setTileCircuit2(i, 3, 1, 0);
				tileManager.setTileCircuit2(i, 8, 1, 0);
				tileManager.setTileCircuit2(i, 9, 1, 0);
				tileManager.setTileCircuit2(i, 10, 1, 0);
			}
			break;
		case 2:
			break;
		}
	}
	
	@Override
	public int getID() {
		return 2;
	}

}
