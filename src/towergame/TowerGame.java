package towergame;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import towergame.states.PlayingState;
import towergame.states.MenuState;
import towergame.states.SplashState;

public class TowerGame extends StateBasedGame{

	public static boolean player1;
	public static String remoteAddr = "192.168.1.198";
	public static boolean connected = false;
	
	public final int ScreenWidth;
	public final int ScreenHeight;
	
	public static final int SPLASHSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int PLAYINGSTATE = 2;
	
	public static final String MENU1 = "towergame/resources/menu1.png";
	public static final String MENU2 = "towergame/resources/menu2.png";
	public static final String MENU3 = "towergame/resources/menu3.png";
	
	public static final String PEACH = "towergame/resources/peach.png";
	public static final String PEACH16 = "towergame/resources/peach16.png";
	
	public static final String BACKGROUND1 = "towergame/resources/background1.png";
	public static final String BACKGROUND2 = "towergame/resources/background2.png";
	public static final String BACKGROUND3 = "towergame/resources/background3.png";
	public static final String BACKGROUND4 = "towergame/resources/background4.png";
	public static final String BACKGROUND5 = "towergame/resources/background5.png";
	
	public static final String BLACK = "towergame/resources/black.png";
	public static final String DARKNESS = "towergame/resources/darkness.png";
	
	public static final String SPRITE_SHADOW_STAND = "towergame/resources/shadowStand.png";
	public static final String SPRITESHEET_SHADOW_WALK = "towergame/resources/shadowWalk.png";
	
	public static final String SPRITESHEET_PLAYER1_STAND_U = "towergame/resources/p1StandUp.png";
	public static final String SPRITESHEET_PLAYER1_STAND_R = "towergame/resources/p1StandRight.png";
	public static final String SPRITESHEET_PLAYER1_STAND_D = "towergame/resources/p1StandDown.png";
	public static final String SPRITESHEET_PLAYER1_STAND_L = "towergame/resources/p1StandLeft.png";
	
	public static final String SPRITESHEET_PLAYER1_WALK_R = "towergame/resources/p1WalkRight.png";
	public static final String SPRITESHEET_PLAYER1_WALK_L = "towergame/resources/p1WalkLeft.png";
	
	public static final String SPRITESHEET_PLAYER2_STAND_U = "towergame/resources/p2StandUp.png";
	public static final String SPRITESHEET_PLAYER2_STAND_R = "towergame/resources/p2StandRight.png";
	public static final String SPRITESHEET_PLAYER2_STAND_D = "towergame/resources/p2StandDown.png";
	public static final String SPRITESHEET_PLAYER2_STAND_L = "towergame/resources/p2StandLeft.png";
	
	public static final String SPRITESHEET_PLAYER2_WALK_R = "towergame/resources/p2WalkRight.png";
	public static final String SPRITESHEET_PLAYER2_WALK_L = "towergame/resources/p2WalkLeft.png";
	
	public static final String SPRITE_TILE_BASIC = "towergame/resources/tileBasic.png";
	
	public static final String SPRITE_TILE_FADE_IN = "towergame/resources/tileFadeIn.png";
	public static final String SPRITE_TILE_FADE_OUT = "towergame/resources/tileFadeOut.png";
	
	public static final String SPRITE_TILE_BUTTON_ON1 = "towergame/resources/tileButtonOn1.png";
	public static final String SPRITE_TILE_BUTTON_OFF1 = "towergame/resources/tileButtonOff1.png";
	public static final String SPRITE_TILE_BUTTON_ON2 = "towergame/resources/tileButtonOn2.png";
	public static final String SPRITE_TILE_BUTTON_OFF2 = "towergame/resources/tileButtonOff2.png";
	public static final String SPRITE_TILE_BUTTON_ON3 = "towergame/resources/tileButtonOn3.png";
	public static final String SPRITE_TILE_BUTTON_OFF3 = "towergame/resources/tileButtonOff3.png";
	public static final String SPRITE_TILE_BUTTON_ON4 = "towergame/resources/tileButtonOn4.png";
	public static final String SPRITE_TILE_BUTTON_OFF4 = "towergame/resources/tileButtonOff4.png";
	public static final String SPRITE_TILE_BUTTON_ON5 = "towergame/resources/tileButtonOn5.png";
	public static final String SPRITE_TILE_BUTTON_OFF5 = "towergame/resources/tileButtonOff5.png";
	public static final String SPRITE_TILE_BUTTON_ON6 = "towergame/resources/tileButtonOn6.png";
	public static final String SPRITE_TILE_BUTTON_OFF6 = "towergame/resources/tileButtonOff6.png";
	public static final String SPRITE_TILE_BUTTON_ON7 = "towergame/resources/tileButtonOn7.png";
	public static final String SPRITE_TILE_BUTTON_OFF7 = "towergame/resources/tileButtonOff7.png";
	public static final String SPRITE_TILE_BUTTON_ON8 = "towergame/resources/tileButtonOn8.png";
	public static final String SPRITE_TILE_BUTTON_OFF8 = "towergame/resources/tileButtonOff8.png";
	public static final String SPRITE_TILE_BUTTON_ON9 = "towergame/resources/tileButtonOn9.png";
	public static final String SPRITE_TILE_BUTTON_OFF9 = "towergame/resources/tileButtonOff9.png";
	
	
	public static final String SPRITESHEET_TILE_CONVEYOR_U = "towergame/resources/conveyorUp.png";
	public static final String SPRITESHEET_TILE_CONVEYOR_R = "towergame/resources/conveyorRight.png";
	public static final String SPRITESHEET_TILE_CONVEYOR_D = "towergame/resources/conveyorDown.png";
	public static final String SPRITESHEET_TILE_CONVEYOR_L = "towergame/resources/conveyorLeft.png";
	
	public static final String SPRITESHEET_TELEPORTER_OFF = "towergame/resources/teleporterOff.png";
	public static final String SPRITESHEET_TELEPORTER_ON = "towergame/resources/teleporterOn.png";
	
	public static final String SPRITESHEET_SWITCH_ON = "towergame/resources/leverTurnOn.png";
	public static final String SPRITESHEET_SWITCH_OFF = "towergame/resources/leverTurnOff.png";
	
	public static final String SPRITE_BOX = "towergame/resources/box.png";
	
	public static final String RICASSO = "towergame/resources/Ricasso.ttf";
	
	public static final String SFX_BEEP1 = "towergame/resources/confirm_style_5_001.ogg";
	public static final String SFX_BEEP2 = "towergame/resources/confirm_style_5_002.ogg";
	public static final String SFX_BEEP3 = "towergame/resources/confirm_style_5_003.ogg";
	public static final String SFX_BEEP4 = "towergame/resources/confirm_style_5_004.ogg";

	public static final String SFX_CONFIRM1 = "towergame/resources/confirm_style_5_echo_003.ogg";
	public static final String SFX_CONFIRM2 = "towergame/resources/confirm_style_5_echo_004.ogg";
	
	public static final String BGM_MENU = "towergame/resources/mica_2.ogg";
	public static final String BGM_LOADING = "towergame/resources/elevator.ogg";
	public static final String BGM_LVL1 = "towergame/resources/comical_pirates.ogg";
	
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
		ResourceManager.loadImage(MENU1);
		ResourceManager.loadImage(MENU2);
		ResourceManager.loadImage(MENU3);
		
		ResourceManager.loadImage(BLACK);
		ResourceManager.loadImage(DARKNESS);
		
		ResourceManager.loadImage(SPRITE_SHADOW_STAND);
		ResourceManager.loadImage(SPRITESHEET_SHADOW_WALK);
		
		ResourceManager.loadImage(BACKGROUND1);
		ResourceManager.loadImage(BACKGROUND2);
		ResourceManager.loadImage(BACKGROUND3);
		ResourceManager.loadImage(BACKGROUND4);
		ResourceManager.loadImage(BACKGROUND5);
		
		ResourceManager.loadImage(SPRITE_BOX);
		ResourceManager.loadImage(SPRITE_TILE_BASIC);
		
		ResourceManager.loadImage(SPRITE_TILE_FADE_IN);
		ResourceManager.loadImage(SPRITE_TILE_FADE_OUT);
		
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON1);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF1);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON2);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF2);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON3);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF3);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON4);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF4);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON5);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF5);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON6);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF6);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON7);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF7);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON8);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF8);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_ON9);
		ResourceManager.loadImage(SPRITE_TILE_BUTTON_OFF9);
		
		ResourceManager.loadImage(SPRITESHEET_TILE_CONVEYOR_U);
		ResourceManager.loadImage(SPRITESHEET_TILE_CONVEYOR_R);
		ResourceManager.loadImage(SPRITESHEET_TILE_CONVEYOR_D);
		ResourceManager.loadImage(SPRITESHEET_TILE_CONVEYOR_L);
		
		ResourceManager.loadImage(SPRITESHEET_TELEPORTER_OFF);
		ResourceManager.loadImage(SPRITESHEET_TELEPORTER_ON);
		
		ResourceManager.loadImage(SPRITESHEET_SWITCH_ON);
		ResourceManager.loadImage(SPRITESHEET_SWITCH_OFF);
		
		ResourceManager.loadImage(SPRITESHEET_PLAYER1_STAND_U);
		ResourceManager.loadImage(SPRITESHEET_PLAYER1_STAND_R);
		ResourceManager.loadImage(SPRITESHEET_PLAYER1_STAND_D);
		ResourceManager.loadImage(SPRITESHEET_PLAYER1_STAND_L);
		
		ResourceManager.loadImage(SPRITESHEET_PLAYER1_WALK_R);
		ResourceManager.loadImage(SPRITESHEET_PLAYER1_WALK_L);
		
		ResourceManager.loadImage(SPRITESHEET_PLAYER2_STAND_U);
		ResourceManager.loadImage(SPRITESHEET_PLAYER2_STAND_R);
		ResourceManager.loadImage(SPRITESHEET_PLAYER2_STAND_D);
		ResourceManager.loadImage(SPRITESHEET_PLAYER2_STAND_L);
		
		ResourceManager.loadImage(SPRITESHEET_PLAYER2_WALK_R);
		ResourceManager.loadImage(SPRITESHEET_PLAYER2_WALK_L);
		
		// Preload some Sounds
		container.setSoundVolume(0.25f);
		ResourceManager.loadSound(SFX_BEEP1);
		ResourceManager.loadSound(SFX_BEEP2);
		ResourceManager.loadSound(SFX_BEEP3);
		ResourceManager.loadSound(SFX_BEEP4);
		ResourceManager.loadSound(SFX_CONFIRM1);
		ResourceManager.loadSound(SFX_CONFIRM2);
		
		// Preload some Music
		container.setMusicVolume(0.15f);
		ResourceManager.loadMusic(BGM_MENU);
		ResourceManager.loadMusic(BGM_LOADING);
		ResourceManager.loadMusic(BGM_LVL1);
		
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
			app.setIcons(new String[] { PEACH16, PEACH });
			app.setDisplayMode(800, 600, false);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
