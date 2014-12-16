package towergame.states;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import towergame.BackgroundManager;
import towergame.ResourceManager;
import towergame.TowerGame;
import towergame.WorldState;
import towergame.circuits.Circuit;
import towergame.circuits.ReverseOrDualCircuit;
import towergame.circuits.ExitCircuit;
import towergame.entities.Entity;
import towergame.entities.EntityComparator;
import towergame.entities.MechanismManager;
import towergame.entities.Player;
import towergame.entities.Player.PlayerState;
import towergame.maps.ObjectMaps;
import towergame.maps.TileMaps;
import towergame.tiles.TileManager;
import towergame.tiles.TileUtil;

public class PlayingState extends BasicGameState{

	WorldState ws;
	
	static TileManager tileManager;
	static MechanismManager mechanismManager;
	static BackgroundManager backgroundManager;
	static Vector2f camera;
	static Image darkness;
	static float darknessAlpha;
	
	static List<Entity> entityList;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		tileManager = new TileManager();
		mechanismManager = new MechanismManager();
		backgroundManager = new BackgroundManager();
		
		entityList = new ArrayList<Entity>();
		
		ws = new WorldState();
		
		darkness = ResourceManager.getImage(TowerGame.DARKNESS);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setMusicOn(false);
		
		ws.circuitList.clear();
		tileManager.clear();
		
		entityList.clear();
		
		ws.level = 1;
		
		darknessAlpha = 1;
		
		loadLevel();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.setAntiAlias(false);
		
		backgroundManager.draw();
		tileManager.draw(camera);
		
		/*
		 * mechanismManager.draw(ws.mechanismList, camera);
		
		if (ws.p1.getY() < ws.p2.getY()){
			ws.p1.draw(camera);
			ws.p2.draw(camera);
		} else {
			ws.p2.draw(camera);
			ws.p1.draw(camera);
		}
		*/
		
		entityList.addAll(ws.mechanismList);
		entityList.add(ws.p1);
		entityList.add(ws.p2);
		
		Collections.sort(entityList, new EntityComparator());
		for(Entity temp: entityList){
			temp.draw(camera);
		}
		entityList.clear();
		
		darkness.draw();
		
		// Extra stuff
		g.drawString("Camera:   (" + Float.toString(camera.x)+", "+Float.toString(camera.y)+")", 50, 50);
		g.drawString("Player 1: (" + Float.toString(ws.p1.getX())+", "+Float.toString(ws.p1.getY())+")", 50, 70);
		g.drawString("Isometr : (" + Float.toString(TileUtil.toCarX(ws.p1.getX(), ws.p1.getY()))+", "+Float.toString(TileUtil.toCarY(ws.p1.getX(), ws.p1.getY()))+")", 50, 90);
		g.drawString("Tile    : (" + Float.toString( TileUtil.getCoordinateX(ws.p1.getX()) )+", "+Float.toString( TileUtil.getCoordinateY(ws.p1.getY()) )+")", 50, 110);
		
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
		// Debug Controls
		// ----------------------------------------------------------------------------------------
		
		if (input.isKeyPressed(Input.KEY_NUMPAD4)){
			darknessAlpha += 0.1f;
			darkness.setAlpha(darknessAlpha);
		} else if (input.isKeyPressed(Input.KEY_NUMPAD1)){
			darknessAlpha -= 0.1f;
			darkness.setAlpha(darknessAlpha);
		}
		
		// ----------------------------------------------------------------------------------------
		
		mechanismManager.update(ws.mechanismList, delta, tileManager, ws.circuitList);
		tileManager.update(delta, ws.circuitList);
		
		for (Circuit circuit: ws.circuitList){
			circuit.doLogic();
		}
		
		ws.p1.update(delta, tileManager, ws.circuitList);
		ws.p2.update(delta, tileManager, ws.circuitList);
		
		// ----------------------------------------------------------------------------------------
		// Level Clear (Players standing on next level tiles)
		// ----------------------------------------------------------------------------------------
		
		if (tileManager.tileStyle(ws.p1.getPosition()) == 99 &&
				tileManager.tileStyle(ws.p2.getPosition()) == 99) {
			ws.level++;
			loadLevel();
		}
		
		// ----------------------------------------------------------------------------------------
		
		// Update the background
		backgroundManager.update(delta, ws.p1.getPosition());
		
		// Set the camera position (368 and 262 are to center the camera around the player)
		camera = TileUtil.toIso(ws.p1.getPosition());
		camera.x = -camera.x + 368;
		camera.y = -camera.y + 262;
	}

	public void reset(int currLevel) {
		switch(ws.level) {
		case 1:
			ws.circuitList.clear();
			tileManager.clear();
			
			entityList.clear();
			
			ws.level = 1;
			
			darknessAlpha = 1;
			
			loadLevel();
			break;
		case 2:
			ws.circuitList.clear();
			tileManager.clear();
			
			entityList.clear();
			
			ws.level = 2;
			
			darknessAlpha = 1;
			
			loadLevel();
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
		ws.mechanismList.clear();
		ws.circuitList.clear();
		
		switch(ws.level){
			case 1:
				// Load Map
				tileManager.loadMap(TileMaps.level1, 24, 12, TileMaps.TPlevel1);
				mechanismManager.loadMap(ws.mechanismList, ObjectMaps.level1, 24, 12);
				
				// Set Players
				ws.p1 = new Player(48, 208, true);
				ws.p2 = new Player(48, 176, false);
				
				// Load Circuits
				ws.circuitList.add(new ReverseOrDualCircuit(1));
				ws.circuitList.add(new ReverseOrDualCircuit(2));
				ws.circuitList.add(new ExitCircuit(3));
				
				break;
			case 2:
				// Load Map
				tileManager.loadMap(TileMaps.level2, 26, 20, TileMaps.TPlevel2);
				mechanismManager.loadMap(ws.mechanismList, ObjectMaps.level2, 26, 20);

				
				// Set Players
				ws.p1 = new Player(48, 336, true);
				ws.p2 = new Player(48, 304, false);
				
				// Load Circuits
				ws.circuitList.add(new ReverseOrDualCircuit(1));
				ws.circuitList.add(new ReverseOrDualCircuit(2));
				
				break;	
		}
		// Remove blank tiles
		tileManager.removeExtras();
		
		// Set Camera
		camera = TileUtil.toIso(ws.p1.getPosition());
		camera.x = -camera.x + 368;
		camera.y = -camera.y + 262;
		
		initSpecialTiles();
	}
	
	public void initSpecialTiles(){
		switch(ws.level){
		case 1:
			// Setting fade tiles
			tileManager.setTileCircuit2(22, 5, 3, 0);
			tileManager.setTileCircuit2(22, 6, 3, 0);
			
			// Setting buttons
			tileManager.setTileCircuit2(4, 2, 1, 1);
			tileManager.setTileCircuit2(4, 9, 2, 2);
			tileManager.setTileCircuit2(19, 2, 1, 1);
			tileManager.setTileCircuit2(19, 9, 2, 2);
			tileManager.setTileCircuit2(21, 4, 3, 1);
			tileManager.setTileCircuit2(21, 7, 3, 2);
			
			for (int i = 6; i < 18; i++){
				// Setting conveyors
				tileManager.setTileCircuit2(i, 1, 1, 0);
				tileManager.setTileCircuit2(i, 2, 1, 0);
				tileManager.setTileCircuit2(i, 3, 1, 0);
				tileManager.setTileCircuit2(i, 8, 2, 0);
				tileManager.setTileCircuit2(i, 9, 2, 0);
				tileManager.setTileCircuit2(i, 10, 2, 0);
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
