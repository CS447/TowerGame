package towergame.entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import towergame.circuits.Circuit;
import towergame.tiles.Tile;
import towergame.tiles.TileManager;

public class MechanismManager {
	
	public class MechanismComparator implements Comparator<Mechanism>{

		@Override
		public int compare(Mechanism object1, Mechanism object2) {
			if (object1.getPosition().getX() > object2.getPosition().getX()){
				return -1;
			} else if (object1.getPosition().getX() > object2.getPosition().getX()){
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	
	public MechanismManager(){
		
	}
	
	public void loadMap(List<Mechanism> list, int[] map, int width, int height){
		
		int count = 0;
		Mechanism temp;
		
		// Go through each width 0-w then
		// increment the height + 1 until complete
		for(int j = 0; j < height; j++){
			for(int i = 0; i < width; i++){
				
				// Need to convert mechanism coordinates into world coordinates (*32)
				switch(map[count]){
					case 0:
						break;
					case 1:
						temp = new Box( i*32+16, j*32+16 );
						list.add(temp);
						break;
					default:
						temp = new Box( i*32, j*32);
						list.add(temp);
						break;
				}
				count++;
			}
		}
		
		sort(list);
	}
	
	public void sort(List<Mechanism> list){
		Collections.sort(list, new MechanismComparator());
	}
	
	public void draw(List<Mechanism> list, Vector2f camera){
		for (Mechanism mech: list){
			mech.draw(camera);
		}
	}
	
	// Shouldn't have extras due to not loading objects when Map = 0
	/*public void removeExtras(List<Mechanism> list){
		List<Mechanism> removeList = new ArrayList<Mechanism>();
		for(Mechanism mech : list ) {
			if (mech.getStyle() == 0){
				removeList.add(mech);
			}
		}
		for(Mechanism rMech : removeList){
			list.remove(rMech);
		}
	}*/
	
	public void update(List<Mechanism> list, int delta, TileManager tm, List<Circuit> cl){
		for(Mechanism mech : list ) {
			mech.update(delta, tm, cl);
		}
	}
}
