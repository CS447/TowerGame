package towergame.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.FontUtils;

import towergame.TowerGame;
import towergame.maps.TileMaps;
import towergame.tiles.TileManager;
import towergame.tiles.TileUtil;

public class PlayingState extends BasicGameState{

	TileManager tileManager;
	
	float carX = 0;
	float carY = 0;
	float isoX = 0;
	float isoY = 0;
	
	float toCarX = 0;
	float toCarY = 0;
	float toIsoX = 0;
	float toIsoY = 0;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		tileManager = new TileManager();
		
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setMusicOn(false);
		
		tileManager.clear();
		tileManager.loadMap(TileMaps.level1, 24, 12);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		tileManager.draw();
		
		Input input = container.getInput();
		
		carX = input.getMouseX();
		carY = input.getMouseY();
		
		isoX = input.getMouseX();
		isoY = input.getMouseY();
		
		toCarX = TileUtil.toCarX(isoX, isoY);
		toCarY = TileUtil.toCarY(isoX, isoY);
		toIsoX = TileUtil.toIsoX(toCarX, toCarY);
		toIsoY = TileUtil.toIsoY(toCarX, toCarY);
		
		g.drawLine(15, 210, 15, 215);
		g.drawLine(20, 180, 20, 245);
		g.drawLine(145, 180, 145, 245);
		g.drawLine(150, 210, 150, 215);
		g.drawLine(20, 180, 145, 180);
		g.drawLine(20, 245, 145, 245);
		
		g.drawLine(toCarX/5+100-5, toCarY/5+100-5, toCarX/5+100+5, toCarY/5+100+5);
		g.drawLine(toCarX/5+100-5, toCarY/5+100+5, toCarX/5+100+5, toCarY/5+100-5);
		g.drawString("ToI("+Float.toString(toCarX)+", "+Float.toString(toCarY)+")", toCarX/5+110, toCarY/5+90);
		//g.drawLine(carX/10+100-5, carY/10+100-5, carX/10+100+5, carY/10+100+5);
		//g.drawLine(carX/10+100-5, carY/10+100+5, carX/10+100+5, carY/10+100-5);
		//g.drawString("C("+Float.toString(carX)+", "+Float.toString(carY)+")", carX/10+110, carY/10+90);
		
		g.drawString("Cartesian: "+"("+Float.toString(carX) + ", " + Float.toString(carY)+")",
				100, 20);
		g.drawString("Isometric: "+"("+Float.toString(toCarX) + ", " + Float.toString(toCarY)+")",
				100, 40);
		
		/*if (input.isKeyDown(input.KEY_W)){
			carY-=10;
		}
		if (input.isKeyDown(input.KEY_D)){
			carX+=10;
		}
		if (input.isKeyDown(input.KEY_S)){
			carY+=10;
		}
		if (input.isKeyDown(input.KEY_A)){
			carX-=10;
		}
		
		if (input.isKeyDown(input.KEY_UP)){
			isoY-=10;
		}
		if (input.isKeyDown(input.KEY_RIGHT)){
			isoX+=10;
		}
		if (input.isKeyDown(input.KEY_DOWN)){
			isoY+=10;
		}
		if (input.isKeyDown(input.KEY_LEFT)){
			isoX-=10;
		}*/
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO
	}

	@Override
	public int getID() {
		return 2;
	}

}
