package towergame.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import towergame.Pair;
import towergame.circuits.Circuit;

public class TileManager {

	List<Tile> tileList;
	public List<Pair> teleporterList;
	
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
		teleporterList = new ArrayList<Pair>();
		
	}
	
	/**
	 * Loads a map from TileMaps.java given the map array, width and height.
	 * Assumes all tiles are 32x32 in a Cartesian World (64x32 in Isometric)
	 * @param map
	 * @param width
	 * @param height
	 */
	public void loadMap(int[] map, int width, int height, int[] tpmap) {
		
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
		
		//Populate the teleporter list
		//teleporterList(0) and (1) are sisters, (2) and (3), and so on
		for (int i = 0; i < tpmap.length; i=i+2)
		{
			int j = tpmap[i];
			int k = tpmap[i+1];
			teleporterList.add(new Pair(j, k));
		}
		
		sort();
	}
	
	public void sort(){
		Collections.sort(tileList, new TileComparator());	
	}
	
	public int tileStyle(Vector2f pos){
		Vector2f temp = TileUtil.getCoordinate(pos);
		int style = -1;
		
		for(Tile tile : tileList ) {
			if (TileUtil.getCoordinate(tile.getPosition()).equals(temp)){
				style = tile.getStyle();
			}
		}
		
		return style;
	}
	
	public Vector2f tileForce(Vector2f pos){
		Vector2f temp = TileUtil.getCoordinate(pos);
		Vector2f force = new Vector2f(0,0);
		
		for(Tile tile : tileList ) {
			if (TileUtil.getCoordinate(tile.getPosition()).equals(temp)){
				force = tile.getForce();
			}
		}
		
		return force;
	}
	
	public void tileEvent(Vector2f pos, List<Circuit> cl, int delta){
		Vector2f temp = TileUtil.getCoordinate(pos);
		
		for(Tile tile : tileList ) {
			if (TileUtil.getCoordinate(tile.getPosition()).equals(temp)){
				tile.update2(delta, cl);
			}
		}
	}
	
	/**
	 * Give it the tile's Cartesian coordinate and it'll translate it to a tile coordinate.
	 * Takes the tile at that place and sets its circuit.
	 * 
	 * @param pos
	 * @param circuit
	 */
	public void setTileCircuit(Vector2f pos, int circuit, int inputID){
		Vector2f temp = TileUtil.getCoordinate(pos);
		for(Tile tile : tileList ) {
			if (TileUtil.getCoordinate(tile.getPosition()).equals(temp)){
				tile.setCircuit(circuit);
				tile.setInput(inputID);
			}
		}
	}
	
	/**
	 * Takes the tile's coordinate. Ex: (2,4) would be the third tile right and 5 tiles down
	 */
	public void setTileCircuit2(float x, float y, int circuit, int inputID){
		Vector2f temp = new Vector2f(x,y);
		for(Tile tile : tileList ) {
			if (TileUtil.getCoordinate(tile.getPosition()).equals(temp)){
				tile.setCircuit(circuit);
				tile.setInput(inputID);
			}
		}
	}
	
	public void draw(Vector2f camera) {
		//System.out.println("Size: "+tileList.size());
		for(Tile tile : tileList ) {
			tile.draw(camera);
			
			// For checking Tile Coordinates
			/*if (tile.getStyle() == 1) {
				System.out.println(TileUtil.getCoordinate(tile.getPosition()).toString());
				System.out.println(tile.getPosition().toString());
			}*/
		}
	}
	
	public void update(int delta, List<Circuit> cl){
		for(Tile tile : tileList ) {
			tile.update(delta, cl);
		}
	}
	
	public void removeExtras(){
		List<Tile> removeList = new ArrayList<Tile>();
		for(Tile tile : tileList ) {
			if (tile.getStyle() == 0){
				removeList.add(tile);
			}
		}
		for(Tile rTile : removeList){
			tileList.remove(rTile);
		}
	}
	
	public void clear(){
		tileList.clear();
		teleporterList.clear();
	}
}
