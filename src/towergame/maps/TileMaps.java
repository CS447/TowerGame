package towergame.maps;

import org.newdawn.slick.geom.Vector2f;

public class TileMaps {

	/*
	 * Note: May need to add tiles manually if they depend on circuits
	 * 
	 * 0 = No Tile
	 * 1 = Basic Tile
	 * 
	 * 
	 *     Example Map 10x10
	 * 
	 *                  Width x
	 *     -------------------------------->
	 *     |
	 *     | {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *     |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *  H  |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *  e  |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *  i  |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *  g  |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *  h  |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *  t  |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *     |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *  y  |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 *     |  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,} 
	 *     v
	 * 
	 */
	
	
	public static final int[] level0 = 
		{1, 0, 
		 1, 1};
	
	
	/**
	 * 24x12 Map
	 */
	public static final int[] level1 = 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
		 0, 0, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 0, 0, 
		 0, 0, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 0, 0,
		 0, 0, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 0, 0,
		 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0,
		 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0,
		 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0,
		 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0,
		 0, 0, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 0, 0,
		 0, 0, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 0, 0,
		 0, 0, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 0, 0,
		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	
	/**
	 * 50x1 Map
	 */
	public static final int[] level2 =
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	
	/**
	 * 50x1 Map
	 */
	public static final int[] level3 =
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	
}
