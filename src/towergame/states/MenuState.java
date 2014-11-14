package towergame.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.FontUtils;

import towergame.TowerGame;

public class MenuState extends BasicGameState{

	Image bg1;
	Image bg2;
	Image bg3;
	
	int flashCount;
	boolean flash;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		// Three Layer Background
		bg1 = new Image(TowerGame.MENU1);
		bg2 = new Image(TowerGame.MENU2);
		bg3 = new Image(TowerGame.MENU3);
		
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub
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
		
		
		// Title
		FontUtils.drawCenter(TowerGame.ricasso30, "Peachtree Tower", 165, 104, 470, Color.white);
		
		// [Press Enter]
		if (!flash)
			FontUtils.drawCenter(TowerGame.ricasso12, "[Press Enter]", 165, 164, 470, Color.white);
		
		// Darkening Layer
		bg3.draw();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		// Turn on and off "[Press Enter]"
		if (flashCount < 0){
			flash = !flash;
			flashCount = 750;
		} else {
			flashCount -= delta;
		}
		
	}

	@Override
	public int getID() {
		return 1;
	}

}
