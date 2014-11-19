package towergame;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import towergame.states.PlayingState;
import towergame.states.MenuState;
import towergame.states.SplashState;

public class TowerGame extends StateBasedGame{

	public final int ScreenWidth;
	public final int ScreenHeight;
	
	public static final int SPLASHSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int PLAYINGSTATE = 2;
	
	public static final String MENU1 = "towergame/resources/menu1.png";
	public static final String MENU2 = "towergame/resources/menu2.png";
	public static final String MENU3 = "towergame/resources/menu3.png";
	
	public static final String RICASSO = "towergame/resources/Ricasso.ttf";
	
	public static final String BGM_MENU = "towergame/resources/mica_2.ogg";
	
	Font awtFont;
	public static TrueTypeFont ricasso12;
	public static TrueTypeFont ricasso20;
	public static TrueTypeFont ricasso30;
	
	public TowerGame(String title, int width, int height) {
		super(title);
		ScreenHeight = height;
		ScreenWidth = width;
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// Add States
		addState(new SplashState());
		addState(new MenuState());
		addState(new PlayingState());
		
		// Preload some Images
		
		// Preload some Sounds
		container.setSoundVolume(0.25f);
		
		// Preload some Music
		container.setMusicVolume(0.15f);
		SoundManager.loadMusic(BGM_MENU);
		
		// Preload some font
		// Used to load custom fonts
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream(RICASSO);
			
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(12f); // set font size
			ricasso12 = new TrueTypeFont(awtFont, true);
			awtFont = awtFont.deriveFont(20f); // set font size
			ricasso20 = new TrueTypeFont(awtFont, true);
			awtFont = awtFont.deriveFont(30f); // set font size
			ricasso30 = new TrueTypeFont(awtFont, true);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
