package towergame.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import towergame.TowerGame;

public class SplashState extends BasicGameState{

	private int timer;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		timer = 2000;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
		g.setColor(Color.white);
		g.drawString("Peachtree Tower", game.getContainer().getWidth()*.5f-60, game.getContainer().getHeight()*.5f-30);
		//g.drawString("Line 2", game.getContainer().getWidth()*.5f-40, game.getContainer().getHeight()*.5f-10);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		timer -= delta;
		
		Input input = container.getInput();
		TowerGame tg = (TowerGame) game;
		
		if (input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_ENTER) || timer <= 0) {
			//tg.enterState(TowerGame.MENUSTATE, new FadeOutTransition(), new FadeInTransition());
			tg.enterState(TowerGame.PLAYINGSTATE, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
