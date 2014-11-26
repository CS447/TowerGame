package towergame.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

public class TileManager {

	List<Tile> tileList;
	
	public class TileComparator implements Comparator<Tile>{

		@Override
		public int compare(Tile tile1, Tile tile2) {
			if (tile1.getPosition().getX() > tile2.getPosition().getX()){
				return -1;
			} else if (tile1.getPosition().getX() > tile2.getPosition().getX()){
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	public TileManager(){
		
		tileList = new ArrayList<Tile>();
		
	}
	
	/**
	 * Loads a map from TileMaps.java given the map array, width and height.
	 * Assumes all tiles are 32x32 in a Cartesian World (64x32 in Isometric)
	 * @param map
	 * @param width
	 * @param height
	 */
	public void loadMap(int[] map, int width, int height) {
		
		int count = 0;
		Tile temp;
		
		// Go through each width 0-w then
		// increment the height + 1 until complete
		for(int j = 0; j < height; j++){
			for(int i = 0; i < width; i++){
				temp = new Tile( i, j, map[count] );
				tileList.add(temp);
				count++;
			}
		}
		
		sort();
	}
	
	public void sort(){
		Collections.sort(tileList, new TileComparator());	
	}
	
	public void draw(Vector2f camera) {
		//System.out.println("Size: "+tileList.size());
		for(Tile tile : tileList ) {
			tile.draw(camera);
		}
	}
	
	public void clear(){
		tileList.clear();
	}
}
