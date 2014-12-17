package towergame.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.FontUtils;

import towergame.GameServer;
import towergame.ResourceManager;
import towergame.TowerGame;

public class MenuState extends BasicGameState{

	Image bg1;
	Image bg2;
	Image bg3;
	
	private int screen;
	private int main;
	private int startGame;
	private int options;
	
	private int flashCount;
	private boolean flash;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		// Three Layer Background
		bg1 = ResourceManager.getImage(TowerGame.MENU1);
		bg2 = ResourceManager.getImage(TowerGame.MENU2);
		bg3 = ResourceManager.getImage(TowerGame.MENU3);
		
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		screen = 0;
		main = 1;
		options = 1;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		// Back Half of Tower
		bg1.draw();
		
		// Draw Clouds in behind tower
		// TODO
		
		// Front Half of Tower
		bg2.draw();
		
		// Draw Clouds In front of tower
		// TODO
		
		switch (screen){
		case 0:
			renderTitle(g);
			break;
		case 1: 
			renderMenu(g);
			break;
		case 2: 
			renderStartGame(g);
			break;
		case 3: 
			renderOptions(g);
			break;
		default:
			break;
		}
		
		// Darkening Layer
		bg3.draw();
	}
	
	private void renderTitle(Graphics g){
		// Title
		FontUtils.drawCenter(TowerGame.ricasso30, "Peachtree Tower", 165, 104, 470, Color.white);
		
		// [Press Enter]
		if (!flash)
			FontUtils.drawCenter(TowerGame.ricasso12, "[Press Enter]", 165, 184, 470, Color.white);
	}
	
	private void renderMenu(Graphics g){
		FontUtils.drawCenter(TowerGame.ricasso30, "Peachtree Tower", 165, 60, 470, Color.white);
		FontUtils.drawCenter(TowerGame.ricasso20, "Start Game", 165, 125, 470, Color.gray);
		FontUtils.drawCenter(TowerGame.ricasso20, "Options", 165, 155, 470, Color.gray);
		
		switch (main){
		case 1:
			FontUtils.drawCenter(TowerGame.ricasso20, "- Start Game -", 165, 125, 470, Color.white);
			break;
		case 2:
			FontUtils.drawCenter(TowerGame.ricasso20, "- Options -", 165, 155, 470, Color.white);
			break;
		} 
	}
	
	private void renderStartGame(Graphics g){
		FontUtils.drawCenter(TowerGame.ricasso30, "Start Game", 165, 60, 470, Color.white);
		FontUtils.drawCenter(TowerGame.ricasso20, "Host", 165, 125, 470, Color.gray);
		FontUtils.drawCenter(TowerGame.ricasso20, "Join", 165, 155, 470, Color.gray);
		FontUtils.drawCenter(TowerGame.ricasso20, "Back", 165, 185, 470, Color.gray);
		
		switch (startGame){
		case 1:
			FontUtils.drawCenter(TowerGame.ricasso20, "- Host -", 165, 125, 470, Color.white);
			break;
		case 2:
			FontUtils.drawCenter(TowerGame.ricasso20, "- Join -", 165, 155, 470, Color.white);
			break;
		case 3:
			FontUtils.drawCenter(TowerGame.ricasso20, "- Back -", 165, 185, 470, Color.white);
			break;
		}
	}
	
	private void renderOptions(Graphics g){
		FontUtils.drawCenter(TowerGame.ricasso30, "Options", 165, 60, 470, Color.white);
		FontUtils.drawCenter(TowerGame.ricasso20, "Music Volume", 165, 125, 470, Color.gray);
		FontUtils.drawCenter(TowerGame.ricasso20, "SFX Volume", 165, 155, 470, Color.gray);
		FontUtils.drawCenter(TowerGame.ricasso20, "Back", 165, 185, 470, Color.gray);
		
		switch (options){
		case 1:
			FontUtils.drawCenter(TowerGame.ricasso20, "- Music Volume -", 165, 125, 470, Color.white);
			break;
		case 2:
			FontUtils.drawCenter(TowerGame.ricasso20, "- SFX Volume -", 165, 155, 470, Color.white);
			break;
		case 3:
			FontUtils.drawCenter(TowerGame.ricasso20, "- Back -", 165, 185, 470, Color.white);
			break;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		TowerGame tg = (TowerGame) game;
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_NUMPAD1))
			playBeep1();
		if (input.isKeyPressed(Input.KEY_NUMPAD2))
			playBeep2();
		if (input.isKeyPressed(Input.KEY_NUMPAD3))
			playBeep3();
		
		switch(screen){
		case 0:
			if (input.isKeyPressed(Input.KEY_ENTER)){
				screen = 1;
				playBeep3();
				input.clearKeyPressedRecord();
			}
			break;
		case 1: 
			menuControls(input);
			break;
		case 2: 
			startGameControls(tg, input);
			break;
		case 3: 
			optionControls(input);
			break;
		default: 
			break;
		}
		
		// Turn on and off "[Press Enter]"
		if (flashCount < 0){
			flash = !flash;
			flashCount = 750;
		} else {
			flashCount -= delta;
		}
		
	}
	
	private void menuControls(Input input){
		// Go back
		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			screen = 0;
			main = 1;
			playBeep2();
			input.clearKeyPressedRecord();
		}
		
		// Move up
		if ( (input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) ){
			if (main == 1){
				main = 2;
			} else {
				main--;
			}
			playBeep1();
		}
		// Move down
		if ( (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) ){
			if (main == 2){
				main = 1;
			} else {
				main++;
			}
			playBeep1();
		}
		
		// Press Enter
		if (input.isKeyPressed(Input.KEY_ENTER)){
			switch(main){
			// Start Game
			case 1:
				screen = 2;
				startGame = 1;
				break;
			// Options
			case 2: 
				screen = 3;
				options = 1;
				break;
			}
			playBeep3();
			input.clearKeyPressedRecord();
		}
	}
	
	private void startGameControls(TowerGame tg, Input input){
		// Go back
		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			screen = 1;
			startGame = 1;
			playBeep2();
			input.clearKeyPressedRecord();
		}
		
		// Move up
		if ( (input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) ){
			if (startGame == 1){
				startGame = 3;
			} else {
				startGame--;
			}
			playBeep1();
		}
		// Move down
		if ( (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) ){
			if (startGame == 3){
				startGame = 1;
			} else {
				startGame++;
			}
			playBeep1();
		}
		
		// Press Enter TODO Add Host and Join Options
		if (input.isKeyPressed(Input.KEY_ENTER)){
			switch(startGame){
			// Host
			case 1:
				new GameServer();
				tg.enterState(TowerGame.PLAYINGSTATE, new FadeOutTransition(), new FadeInTransition());
				TowerGame.player1 = true;
				ResourceManager.getMusic(TowerGame.BGM_LOADING).loop();
				break;
			// Join
			case 2:
				tg.enterState(TowerGame.PLAYINGSTATE, new FadeOutTransition(), new FadeInTransition());
				TowerGame.player1 = false;
				ResourceManager.getMusic(TowerGame.BGM_LOADING).loop();
				break;
			// Back
			case 3: 
				screen = 1;
				startGame = 1;
				break;
			}
			playBeep3();
			input.clearKeyPressedRecord();
		}
	}
	
	private void optionControls(Input input){
		// Go back
		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			screen = 1;
			options = 1;
			playBeep2();
			input.clearKeyPressedRecord();
		}
		
		// Move up
		if ( (input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) ){
			if (options == 1){
				options = 3;
			} else {
				options--;
			}
			playBeep1();
		}
		// Move down
		if ( (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) ){
			if (options == 3){
				options = 1;
			} else {
				options++;
			}
			playBeep1();
		}
		
		// Press Enter
		if (input.isKeyPressed(Input.KEY_ENTER)){
			switch(options){
			// Music Volume
			case 1:
				break;
			// SFX Volume
			case 2: 
				break;
			// Back
			case 3: 
				screen = 1;
				options = 1;
				playBeep3();
				break;
			}
			input.clearKeyPressedRecord();
		}
		
	}
	
	private void playBeep1(){
		ResourceManager.getSound(TowerGame.SFX_BEEP1).play();
	}
	
	private void playBeep2(){
		ResourceManager.getSound(TowerGame.SFX_BEEP2).play();
	}
	
	private void playBeep3(){
		ResourceManager.getSound(TowerGame.SFX_BEEP3).play();
	}
	private void playConfirm1(){
		ResourceManager.getSound(TowerGame.SFX_CONFIRM1).play();
	}
	
	@Override
	public int getID() {
		return 1;
	}

}
