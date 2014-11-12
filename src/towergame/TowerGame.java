package towergame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import towergame.states.PlayingState;
import towergame.states.MenuState;
import towergame.states.SplashState;

public class TowerGame extends StateBasedGame{

	public final int ScreenWidth;
	public final int ScreenHeight;
	
	public static final int SPLASHSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int PLAYINGSTATE = 2;
	
	public TowerGame(String title, int width, int height) {
		super(title);
		ScreenHeight = height;
		ScreenWidth = width;
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// Add States
		addState(new SplashState());
		addState(new MenuState());
		addState(new PlayingState());
		
		// Preload some Images
		
		// Preload some Sounds
		
		// Preload some Music
		
		// Preload some font
	}
	
	public static void main(String[] args) {
		AppGameContainer app;

		try {
			app = new AppGameContainer(new TowerGame("Peachtree Tower", 800, 600));
			//app.setIcons(new String[] { "towergame/resources/icon16.png", "towergame/resources/icon32.png" });
			app.setDisplayMode(800, 600, false);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
