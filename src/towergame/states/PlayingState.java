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

import towergame.ResourceManager;
import towergame.TowerGame;
import towergame.WorldState;
import towergame.circuits.Circuit;
import towergame.circuits.ReverseOrDualCircuit;
import towergame.circuits.ReverseOrQuadCircuit;
import towergame.entities.Box;
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
	static Vector2f camera;
	static Image darkness;
	static float darknessAlpha;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		tileManager = new TileManager();
		mechanismManager = new MechanismManager();
		
		ws = new WorldState();
		
		darkness = ResourceManager.getImage(TowerGame.DARKNESS);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setMusicOn(false);
		
		ws.circuitList.clear();
		tileManager.clear();
		
		ws.level = 1;
		
		darknessAlpha = 1;
		
		loadLevel();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.setAntiAlias(false);
		
		tileManager.draw(camera);
		
		/*mechanismManager.draw(ws.mechanismList, camera);
		
		if (ws.p1.getY() < ws.p2.getY()){
			ws.p1.draw(camera);
			ws.p2.draw(camera);
		} else {
			ws.p2.draw(camera);
			ws.p1.draw(camera);
		}*/
		
		List<Entity> temp = new ArrayList<Entity>();
		
		temp.addAll(ws.mechanismList);
		temp.add(ws.p1);
		temp.add(ws.p2);
		
		Collections.sort(temp, new EntityComparator());
		for(Entity t: temp){
			t.draw(camera);
		}
		
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
		
		tileManager.update(delta, ws.circuitList);
		
		for (Circuit circuit: ws.circuitList){
			circuit.doLogic();
		}
		
		ws.p1.update(delta, tileManager, ws.circuitList);
		ws.p2.update(delta, tileManager, ws.circuitList);
		
		// Set the camera position (368 and 262 are to center the camera around the player)
		camera = TileUtil.toIso(ws.p1.getPosition());
		camera.x = -camera.x + 368;
		camera.y = -camera.y + 262;
	}

	public void loadLevel(){
		tileManager.clear();
		ws.circuitList.clear();
		
		switch(ws.level){
			case 1:
				// Load Map
				tileManager.loadMap(TileMaps.level1, 24, 12);
				mechanismManager.loadMap(ws.mechanismList, ObjectMaps.level1, 24, 12);
				
				// Set Players
				ws.p1 = new Player(48, 208, true);
				ws.p2 = new Player(48, 176, false);
				
				// Load Circuits
				ws.circuitList.add(new ReverseOrDualCircuit(1));
				ws.circuitList.add(new ReverseOrDualCircuit(2));
				
				break;
			case 2:
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
			// Setting buttons
			tileManager.setTileCircuit2(4, 2, 1, 0);
			tileManager.setTileCircuit2(4, 9, 2, 1);
			tileManager.setTileCircuit2(19, 2, 1, 0);
			tileManager.setTileCircuit2(19, 9, 2, 1);
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
