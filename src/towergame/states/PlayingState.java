package towergame.states;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.FontUtils;

import towergame.BackgroundManager;
import towergame.GameClient;
import towergame.GameServer;
import towergame.PlayerShadows;
import towergame.ResourceManager;
import towergame.TowerGame;
import towergame.WorldState;
import towergame.circuits.AndQuadCircuit;
import towergame.circuits.Circuit;
import towergame.circuits.IfPressedCircuit;
import towergame.circuits.OnCircuit;
import towergame.circuits.OneTimeOnSingleCircuit;
import towergame.circuits.ReverseIfPressedCircuit;
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
	GameClient client;
	Player player;
	Player otherPlayer;
	
	PlayerState lastState;
	boolean paused;
	
	static TileManager tileManager;
	static MechanismManager mechanismManager;
	static BackgroundManager backgroundManager;
	static PlayerShadows playerShadows;
	static Vector2f camera;
	static Image black;
	static float blackAlpha;
	static Image darkness;
	static float darknessAlpha;
	
	static List<Entity> entityList;
	
	static int reset;
	
	static boolean syncGame;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		tileManager = new TileManager();
		mechanismManager = new MechanismManager();
		backgroundManager = new BackgroundManager();
		playerShadows = new PlayerShadows();
		
		entityList = new ArrayList<Entity>();
		
		ws = new WorldState();
		
		black = ResourceManager.getImage(TowerGame.BLACK);
		darkness = ResourceManager.getImage(TowerGame.DARKNESS);

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		//container.setMusicOn(false);
		
		ws.circuitList.clear();
		tileManager.clear();
		
		entityList.clear();
		
		ws.level = 0;
		
		blackAlpha = 0;
		darknessAlpha = 1;
		
		reset = 0;
		
		loadLevel();
			
		if (TowerGame.player1) {
			client = new GameClient("127.0.0.1", GameServer.LISTEN_PORT);			
		} else {
			client = new GameClient(TowerGame.remoteAddr, GameServer.LISTEN_PORT);
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.setAntiAlias(false);
		
		
		backgroundManager.draw();
		tileManager.draw(camera);
		
		playerShadows.draw( camera, ws.p1.getPosition(), ws.p1.getPlayerState() );
		playerShadows.draw( camera, ws.p2.getPosition(), ws.p2.getPlayerState() );
		
		entityList.addAll(ws.mechanismList);
		entityList.add(ws.p1);
		entityList.add(ws.p2);
		
		Collections.sort(entityList, new EntityComparator());
		for(Entity temp: entityList){
			temp.draw(camera);
		}
		entityList.clear();
		
		black.draw();
		darkness.draw();
		
		if (syncGame == false) {
			FontUtils.drawCenter(TowerGame.ricasso30, "Waiting for other player...", 165, 104, 470, Color.white);
			black.setAlpha(0);
		}
		
		// Extra stuff
		//g.drawString("Camera:   (" + Float.toString(camera.x)+", "+Float.toString(camera.y)+")", 50, 50);
		//g.drawString("Player 1: (" + Float.toString(ws.p1.getX())+", "+Float.toString(ws.p1.getY())+")", 50, 70);
		//g.drawString("Isometr : (" + Float.toString(TileUtil.toCarX(ws.p1.getX(), ws.p1.getY()))+", "+Float.toString(TileUtil.toCarY(ws.p1.getX(), ws.p1.getY()))+")", 50, 90);
		//g.drawString("Tile    : (" + Float.toString( TileUtil.getCoordinateX(ws.p1.getX()) )+", "+Float.toString( TileUtil.getCoordinateY(ws.p1.getY()) )+")", 50, 110);
		//System.out.println("Tile    : (" + Float.toString( TileUtil.getCoordinateX(ws.p1.getX()) )+", "+Float.toString( TileUtil.getCoordinateY(ws.p1.getY()) )+")");
		if (paused) {
			g.setColor(new Color(0, 0, 0, 128));
			g.fillRect(0, 0, container.getWidth(), container.getHeight());
			g.setColor(Color.white);
			g.drawString("Paused", 200, 200);
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
		
		if (TowerGame.connected || !TowerGame.player1) { 			
			if (syncGame == false){
				reset += delta*1.75;
				if (reset >= 1000)
					ws.level = 1;
			}
			
			if (input.isKeyPressed(Input.KEY_P))
			{
				client.Writer.println("pause");
				paused = !paused;
			}
			
			if (!paused) {
				//Reset command first, hold LSHIFT, R, N to reset
				if (input.isKeyDown(Input.KEY_LSHIFT) && input.isKeyDown(Input.KEY_R) &&
						input.isKeyDown(Input.KEY_N)) {
					reset();
					client.Writer.println("reset");
					return;
				}
			
				//Flipping Switches
				if (input.isKeyDown(Input.KEY_E))
				{
						//Screw it I'm just gonna use switch tiles
				}
				
				
					
				if (input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_W)){
					player.setState(PlayerState.WALK_LEFT);
					player.walkUpLeft();
					client.Writer.println("move upleft");
				} else if (input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_W)){
					player.setState(PlayerState.WALK_UP);
					player.walkUpRight();
					client.Writer.println("move upright");
				} else if (input.isKeyDown(Input.KEY_A) && input.isKeyDown(Input.KEY_S)){
					player.setState(PlayerState.WALK_DOWN);
					player.walkDownLeft();
					client.Writer.println("move downleft");
				} else if (input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_S)){
					player.setState(PlayerState.WALK_RIGHT);
					player.walkDownRight();
					client.Writer.println("move downright");
				} else if (input.isKeyDown(Input.KEY_D)){
					player.setState(PlayerState.WALK_RIGHT);
					player.walkRight();
					client.Writer.println("move right");
				} else if (input.isKeyDown(Input.KEY_A)){
					player.setState(PlayerState.WALK_LEFT);
					player.walkLeft();
					client.Writer.println("move left");
				} else if (input.isKeyDown(Input.KEY_W)){
					player.setState(PlayerState.WALK_UP);
					player.walkUp();
					client.Writer.println("move up");
				} else if (input.isKeyDown(Input.KEY_S)){
					player.setState(PlayerState.WALK_DOWN);
					player.walkDown();
					client.Writer.println("move down");
				} 
				
				if (!input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_D) &&
						!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_W)){
					player.setStand();
					if (player.playerState != lastState) {
						client.Writer.println("stand");
					}
				}
				
				lastState = player.playerState;
				
				client.Writer.flush();
			}
						
			try {
				if (client.Reader.ready()) {
					String line = client.Reader.readLine();
					
					String[] tokens = line.split(" ");
					
					switch (tokens[0]) {
					case "state":
						break;
					case "move":
						switch (tokens[1]) {
						case "left":
							otherPlayer.setState(PlayerState.WALK_LEFT);
							otherPlayer.walkLeft();
							break;
						case "right":
							otherPlayer.setState(PlayerState.WALK_RIGHT);
							otherPlayer.walkRight();
							break;
						case "up":
							otherPlayer.setState(PlayerState.WALK_UP);
							otherPlayer.walkUp();
							break;
						case "down":
							otherPlayer.setState(PlayerState.WALK_DOWN);
							otherPlayer.walkDown();
							break;
						case "downleft":
							otherPlayer.setState(PlayerState.WALK_DOWN);
							otherPlayer.walkDownLeft();
							break;
						case "downright":
							otherPlayer.setState(PlayerState.WALK_RIGHT);
							otherPlayer.walkDownRight();
							break;
						case "upleft":
							otherPlayer.setState(PlayerState.WALK_LEFT);
							otherPlayer.walkUpLeft();
							break;
						case "upright":
							otherPlayer.setState(PlayerState.WALK_UP);
							otherPlayer.walkUpRight();
							break;
						}
						break;
					case "stand":
						otherPlayer.setStand();
						break;
					case "activate":
						break;
					case "reset":
						reset();
						return;
					case "pause":
						paused = !paused;
						break;
					default:
						break;
					}
				}
			} catch (IOException e) {
				System.out.println("NetError: " + e);
			}
			
			
			// Reset the level if held down
			if (input.isKeyDown(Input.KEY_R)){
				reset += delta;
			} else {
				reset -= delta;
			}
			blackAlpha = reset/1000f;
			black.setAlpha(blackAlpha);
			
			if (reset <= 0){
				reset = 0;
			}
			if (reset >= 1000){
				syncGame = true;
				reset = 0;
				reset();
				client.Writer.println("reset");
				return;
			}
			
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
		
		mechanismManager.collide(player, ws.mechanismList);
		mechanismManager.collide(otherPlayer, ws.mechanismList);
		
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
		backgroundManager.update(delta, player.getPosition());
		
		// Set the camera position (368 and 262 are to center the camera around the player)
		camera = TileUtil.toIso(player.getPosition());
		camera.x = -camera.x + 368;
		camera.y = -camera.y + 262;
	}

	public void reset() {
		switch(ws.level) {
		case 1:
			ws.circuitList.clear();
			tileManager.clear();
			
			entityList.clear();
			
			darknessAlpha = 1;
			
			loadLevel();
			break;
		case 2:
			ws.circuitList.clear();
			tileManager.clear();
			
			entityList.clear();
			
			darknessAlpha = 1;
			
			loadLevel();
			//Reset 2nd level
			break;
		case 3:
			//Reset 3rd level
			ws.circuitList.clear();
			tileManager.clear();
			
			entityList.clear();
			
			darknessAlpha = 1;
			
			loadLevel();
			break;
		case 4:
			ws.circuitList.clear();
			tileManager.clear();
			
			entityList.clear();
			
			darknessAlpha = 1;
			
			loadLevel();
		}
	}

	public void loadLevel(){
		tileManager.clear();
		ws.mechanismList.clear();
		ws.circuitList.clear();
		
		switch(ws.level){
			case 0:
				// Load Map
				tileManager.loadMap(TileMaps.level0, 2, 2, TileMaps.TPlevel1);
				
				// Set Players
				ws.p1 = new Player(16, 16, true);
				ws.p2 = new Player(48, 48, false);
				break;
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
				
				// Load Music
				ResourceManager.getMusic(TowerGame.BGM_LVL1).loop();
				break;
			case 2:
				// Load Map
				tileManager.loadMap(TileMaps.level2, 26, 20, TileMaps.TPlevel2);
				mechanismManager.loadMap(ws.mechanismList, ObjectMaps.level2, 26, 20);

				
				// Set Players
				ws.p1 = new Player(48, 336, true);
				ws.p2 = new Player(48, 304, false);
				
				// Load Circuits
				ws.circuitList.add(new ExitCircuit(1));
				ws.circuitList.add(new ExitCircuit(2));
				break;
			case 4:
				// Load Map
				tileManager.loadMap(TileMaps.level4, 24, 22, TileMaps.TPlevel1);
				mechanismManager.loadMap(ws.mechanismList, ObjectMaps.level4, 24, 22);
				
				// Set Players
				ws.p1 = new Player(48, 368, true);
				ws.p2 = new Player(48, 336, false);
				// Load Circuits
				ws.circuitList.add(new ExitCircuit(1));
				ws.circuitList.add(new OneTimeOnSingleCircuit(2));
				ws.circuitList.add(new OneTimeOnSingleCircuit(3));
				ws.circuitList.add(new OnCircuit(4));
				ws.circuitList.add(new AndQuadCircuit(5));
				ws.circuitList.add(new OneTimeOnSingleCircuit(6));
				ws.circuitList.add(new IfPressedCircuit(7));
				ws.circuitList.add(new OneTimeOnSingleCircuit(8));
				ws.circuitList.add(new ReverseIfPressedCircuit(9));
				ws.circuitList.add(new IfPressedCircuit(10));
				ws.circuitList.add(new ReverseIfPressedCircuit(11));
				ws.circuitList.add(new ReverseIfPressedCircuit(12));
				ws.circuitList.add(new OneTimeOnSingleCircuit(13));
				ws.circuitList.add(new OneTimeOnSingleCircuit(14));
				ws.circuitList.add(new OneTimeOnSingleCircuit(15));
				ws.circuitList.add(new ExitCircuit(16));

				break;	
			case 3:
				// Load Map
				tileManager.loadMap(TileMaps.level3, 23, 17, TileMaps.TPlevel1);
				mechanismManager.loadMap(ws.mechanismList, ObjectMaps.level3, 23, 17);

				
				// Set Players
				ws.p1 = new Player(48, 336, true);
				ws.p2 = new Player(48, 304, false);
				
				// Load Circuits
				ws.circuitList.add(new ReverseOrDualCircuit(1));
				ws.circuitList.add(new ReverseOrDualCircuit(2));
				ws.circuitList.add(new ReverseOrDualCircuit(3));
				ws.circuitList.add(new ReverseOrDualCircuit(4));
				ws.circuitList.add(new ReverseOrDualCircuit(5));
				ws.circuitList.add(new ExitCircuit(6));
				ws.circuitList.add(new ReverseOrDualCircuit(7));

				// Load Music
				//ResourceManager.getMusic(TowerGame.BGM_LVL1).loop();
				break;
		}
		

		if (TowerGame.player1) {
			player = ws.p1;
			otherPlayer = ws.p2;
		} else {
			player = ws.p2;
			otherPlayer = ws.p1;
		}
		
		// Remove blank tiles
		tileManager.removeExtras();
		
		// Set Camera
		camera = TileUtil.toIso(player.getPosition());
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
			//Fade tiles
			tileManager.setTileCircuit2(24, 9, 2, 0);
			tileManager.setTileCircuit2(24, 10, 2, 0);
			tileManager.setTileCircuit2(15, 8, 1, 0);
			tileManager.setTileCircuit2(15, 11, 1, 0);
			
			//Buttons
			tileManager.setTileCircuit2(22, 8, 2, 1);
			tileManager.setTileCircuit2(22, 11, 2, 2);
			tileManager.setTileCircuit2(12, 8, 1, 1);
			tileManager.setTileCircuit2(12, 11, 1, 2);
			break;
		case 3:
			//Fade tiles
			tileManager.setTileCircuit2(20, 10, 6, 0);
			tileManager.setTileCircuit2(20, 11, 6, 0);
			
			//Buttons
			tileManager.setTileCircuit2(3, 13, 1, 1);
			tileManager.setTileCircuit2(5, 1, 2, 1);
			tileManager.setTileCircuit2(8, 10, 3, 1);
			tileManager.setTileCircuit2(10, 10, 4, 1);
			tileManager.setTileCircuit2(7, 4, 5, 1);
			tileManager.setTileCircuit2(19, 9, 6, 1);
			tileManager.setTileCircuit2(19, 12, 6, 2);
			
			//Never ending, button hidden off map
			tileManager.setTileCircuit2(23, 17, 7, 1);
			tileManager.setTileCircuit2(22, 17, 3, 2);
			tileManager.setTileCircuit2(21, 17, 2, 2);
			tileManager.setTileCircuit2(20, 17, 1, 2);
			tileManager.setTileCircuit2(19, 17, 4, 2);
			tileManager.setTileCircuit2(18, 17, 5, 2);
			
			//Conveyors
			for (int i = 0; i < 2; i++)
			{
				tileManager.setTileCircuit2(3+i, 1, 7, 0);
			}
			for (int i = 0; i < 4; i++)
			{
				tileManager.setTileCircuit2(2, 1+i, 7, 0);
			}
			for (int i = 0; i < 11; i++)
			{
				tileManager.setTileCircuit2(4, 5+i, 1, 0);
			}
			for (int i = 0; i < 14; i++)
			{
				tileManager.setTileCircuit2(5, 2+i, 2, 0);
			}
			for (int i = 0; i < 9; i++)
			{
				tileManager.setTileCircuit2(6, 7+i, 2, 0);
			}
			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					tileManager.setTileCircuit2(7+i, 7+j, 3, 0);
				}
			}
			for (int i = 0; i < 9; i++)
			{
				tileManager.setTileCircuit2(14, 7+i, 4, 0);
			}
			for (int i = 0; i < 11; i++)
			{
				tileManager.setTileCircuit2(15, 5+i, 5, 0);
			}
			for (int i = 0; i < 2; i++)
			{
				tileManager.setTileCircuit2(8, 12+i, 7, 0);
			}
			break;
			
		case 4:
			//Fade tiles
			tileManager.setTileCircuit2(1, 10, 1, 0);
			tileManager.setTileCircuit2(2, 10, 1, 0);
			tileManager.setTileCircuit2(3, 10, 1, 0);
			tileManager.setTileCircuit2(4, 10, 1, 0);
			tileManager.setTileCircuit2(1, 11, 1, 0);
			tileManager.setTileCircuit2(2, 11, 1, 0);
			tileManager.setTileCircuit2(3, 11, 1, 0);
			tileManager.setTileCircuit2(4, 11, 1, 0);
			tileManager.setTileCircuit2(2, 12, 1, 0);
			tileManager.setTileCircuit2(3, 12, 1, 0);
			tileManager.setTileCircuit2(4, 12, 1, 0);
			
			tileManager.setTileCircuit2(3, 6, 1, 0);
			tileManager.setTileCircuit2(3, 15, 1, 0);
			
			tileManager.setTileCircuit2(4, 2, 3, 0);
			tileManager.setTileCircuit2(4, 19, 2, 0);
			
			tileManager.setTileCircuit2(6, 8, 5, 0);
			
			tileManager.setTileCircuit2(15, 17, 7, 0);
			tileManager.setTileCircuit2(12, 2, 8, 0);
			
			for (int i = 13; i < 20; i++)
				tileManager.setTileCircuit2(10, i, 6, 0);
			
			tileManager.setTileCircuit2(16, 10, 10, 0);
			tileManager.setTileCircuit2(18, 2, 10, 0);
			
			tileManager.setTileCircuit2(21, 8, 13, 0);
			tileManager.setTileCircuit2(21, 13, 13, 0);
			
			tileManager.setTileCircuit2(22, 10, 16, 0);
			tileManager.setTileCircuit2(22, 11, 16, 0);
			
			tileManager.setTileCircuit2(21, 9, 15, 0);
			tileManager.setTileCircuit2(21, 10, 15, 0);
			tileManager.setTileCircuit2(21, 11, 14, 0);
			tileManager.setTileCircuit2(21, 12, 14, 0);
			
			//Buttons
			tileManager.setTileCircuit2(3, 8, 1, 1);
			tileManager.setTileCircuit2(3, 13, 1, 2);
			
			tileManager.setTileCircuit2(1, 2, 2, 1);
			tileManager.setTileCircuit2(1, 19, 3, 1);
			
			tileManager.setTileCircuit2(6, 13, 5, 1);
			tileManager.setTileCircuit2(8, 13, 5, 2);
			tileManager.setTileCircuit2(8, 18, 5, 3);
			tileManager.setTileCircuit2(9, 15, 5, 4);
			
			tileManager.setTileCircuit2(6, 9, 6, 1);
			
			tileManager.setTileCircuit2(13, 14, 7, 1);
			
			tileManager.setTileCircuit2(18, 16, 8, 1);
			tileManager.setTileCircuit2(15, 13, 9, 1);
			
			tileManager.setTileCircuit2(19, 18, 10, 1);
			tileManager.setTileCircuit2(19, 19, 11, 1);
			tileManager.setTileCircuit2(20, 19, 12, 1);
			
			tileManager.setTileCircuit2(19, 10, 13, 1);
			
			tileManager.setTileCircuit2(23, 0, 14, 1);
			tileManager.setTileCircuit2(23, 21, 15, 1);
			
			// Exit Buttons
			tileManager.setTileCircuit2(22, 2, 16, 1);
			tileManager.setTileCircuit2(22, 19, 16, 2);
			
			// Setting conveyors
			for (int i = 1; i < 23; i++){
				tileManager.setTileCircuit2(i, 0, 1, 0);
				tileManager.setTileCircuit2(i, 21, 1, 0);
			}
			for (int i = 5; i < 12; i++){
				for (int j = 2; j < 9; j++){
					if (!(i == 6 && j == 8))
						tileManager.setTileCircuit2(i, j, 4, 0);
				}
			}
			for (int i = 5; i < 13; i++){
				tileManager.setTileCircuit2(i, 10, 4, 0);
			}
			for (int i = 5; i < 10; i++){
				tileManager.setTileCircuit2(i, 11, 4, 0);
			}
			for (int i = 3; i < 14; i++){
				tileManager.setTileCircuit2(13, i, 9, 0);
			}
			tileManager.setTileCircuit2(14, 2, 9, 0);
			
			tileManager.setTileCircuit2(15, 4, 11, 0);
			tileManager.setTileCircuit2(15, 5, 11, 0);
			tileManager.setTileCircuit2(17, 8, 11, 0);
			tileManager.setTileCircuit2(17, 9, 11, 0);
			
			tileManager.setTileCircuit2(15, 8, 12, 0);
			tileManager.setTileCircuit2(15, 9, 12, 0);
			tileManager.setTileCircuit2(17, 4, 12, 0);
			tileManager.setTileCircuit2(17, 5, 12, 0);
			break;
		}
	}
	
	@Override
	public int getID() {
		return 2;
	}

}
