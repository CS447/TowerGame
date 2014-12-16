package towergame;

import org.newdawn.slick.Image;

public class BackgroundManager {

	Image bg1a;
	Image bg2a;
	Image bg3a;
	Image bg4a;
	Image bg5a;
	Image bg1b;
	Image bg2b;
	Image bg3b;
	Image bg4b;
	Image bg5b;
	
	int time;
	
	int time1a;
	int time1b;
	int time2a;
	int time2b;
	int time3a;
	int time3b;
	int time4a;
	int time4b;
	int time5a;
	int time5b;
	
	int count1;
	int count2;
	int count3;
	int count4;
	int count5;
	
	public BackgroundManager(){
		bg1a = ResourceManager.getImage(TowerGame.BACKGROUND1);
		bg1b = ResourceManager.getImage(TowerGame.BACKGROUND1);
		bg2a = ResourceManager.getImage(TowerGame.BACKGROUND2);
		bg2b = ResourceManager.getImage(TowerGame.BACKGROUND2);
		bg3a = ResourceManager.getImage(TowerGame.BACKGROUND3);
		bg3b = ResourceManager.getImage(TowerGame.BACKGROUND3);
		bg4a = ResourceManager.getImage(TowerGame.BACKGROUND4);
		bg4b = ResourceManager.getImage(TowerGame.BACKGROUND4);
		bg5a = ResourceManager.getImage(TowerGame.BACKGROUND5);
		bg5b = ResourceManager.getImage(TowerGame.BACKGROUND5);
		
		time = 0;
		
		time1a = 0;
		time1b = -800;
		time2a = 0;
		time2b = -800;
		time3a = 0;
		time3b = -800;
		time4a = 0;
		time4b = -800;
		time5a = 0;
		time5b = -800;
	}
	
	public void draw(){
		bg1a.draw(time1a, 0);
		bg1b.draw(time1b, 0);
		bg2a.draw(time2a, 0);
		bg2b.draw(time2b, 0);
		bg3a.draw(time3a, 0);
		bg3b.draw(time3b, 0);
		bg4a.draw(time4a, 0);
		bg4b.draw(time4b, 0);
		bg5a.draw(time5a, 0);
		bg5b.draw(time5b, 0);
	}
	
	private void increment(int delta){
		count1 += delta;
		count2 += delta;
		count3 += delta;
		count4 += delta;
		count5 += delta;
		
		if (count1 >= 25){
			count1 -= 25;
			time1a++;
			time1b++;
		}
		if (count2 >= 75){
			count2 -= 75;
			time2a++;
			time2b++;
		}
		if (count3 >= 150){
			count3 -= 150;
			time3a++;
			time3b++;
		}
		if (count4 >= 300){
			count4 -= 300;
			time4a++;
			time4b++;
		}
		if (count5 >= 500){
			count5 -= 500;
			time5a++;
			time5b++;
		}
	}
	
	private void reset(){
		if (time1a >= 800)
			time1a -= 1600;
		if (time1b >= 800)
			time1b -= 1600;
		if (time2a >= 800)
			time2a -= 1600;
		if (time2b >= 800)
			time2b -= 1600;
		if (time3a >= 800)
			time3a -= 1600;
		if (time3b >= 800)
			time3b -= 1600;
		if (time4a >= 800)
			time4a -= 1600;
		if (time4b >= 800)
			time4b -= 1600;
		if (time5a >= 800)
			time5a -= 1600;
		if (time5b >= 800)
			time5b -= 1600;
	}
	
	public void update(int delta){
		increment(delta);
		reset();
	}
}
