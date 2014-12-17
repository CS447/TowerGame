package towergame.maps;

public class TileMaps {

	/*
	 * Note: May need to add tiles manually if they depend on circuits
	 * 
	 * 0 = No Tile
	 * 1 = Basic Tile
	 * 2 = Conveyor Belt Up
	 * 3 = Conveyor Belt Right
	 * 4 = Conveyor Belt Down
	 * 5 = Conveyor Belt Left
	 * 6 = Tile Button 
	 * 15 = Teleporter
	 * 
	 * 99 = Level Exit
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
	 * 
	 * 
	 * Teleporter lists are {X-coord, Y-coord, X-coord of sister, Y-coord of sister}
	 * So { 526, 238, 454, 532} would only be two teleporters linked together
	 * one at 526, 236 and the other at 454, 532.
	 * Some may only be one way, so the "sister coords" are that of a normal tile
	 */
	
	
	public static final int[] level0 = 
		{1, 1, 
		 1, 1};
	
	
	/**
	 * 24x12 Map
	 */
	public static final int[] level1 = 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
		 0, 0, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 0, 0, 
		 0, 0, 1, 1, 8, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 8, 1, 1, 0, 0,
		 0, 0, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 0, 0,
		 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 13, 0, 0,
		 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, -1, 99,
		 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, -1, 99,
		 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 13, 0, 0,
		 0, 0, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 0, 0,
		 0, 0, 1, 1, 7, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 7, 1, 1, 0, 0,
		 0, 0, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 0, 0,
		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	
	public static final int[] TPlevel1 = {0,0,0,0};
	
	/**
	 * 26x20 Map
	 */
	public static final int[] level2 =
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
		 0, 0, 0, 0, 0, 0, 15, 1, 1, 1, 0, 0, 15, 1, 1, 1, 0, 0, 15, 1, 1, 1, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 1, 1, 1, 15, 0, 0, 1, 1, 1, 15, 0, 0, 1, 1, 1, 15, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 1, 1, 1, 15, 0, 0, 1, 1, 1, 15, 0, 0, 1, 1, 1, 15, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 1, 15, 15, 1, 0, 0, 1, 15, 15, 1, 0, 0, 1, 15, 15, 1, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		 0, 0, 1, 1, 15, 0, 0, 1, 1, 1, 15, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 15, 0, 0,
		 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 13, 1, 1, -1, 15, 0, 1, 1, 1, 1, 13, 1, 0, 0,
		 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, -1, 99,
		 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, -1, 99,
		 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 13, 1, 1, -1, 15, 0, 1, 1, 1, 1, 13, 1, 0, 0,
		 0, 0, 1, 1, 15, 0, 0, 1, 1, 1, 15, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 15, 0, 0,
		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 1, 15, 15, 1, 0, 0, 1, 15, 15, 1, 0, 0, 1, 15, 15, 1, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 1, 1, 1, 15, 0, 0, 1, 1, 1, 15, 0, 0, 1, 1, 1, 15, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 1, 1, 1, 15, 0, 0, 1, 1, 1, 15, 0, 0, 1, 1, 1, 15, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 15, 1, 1, 1, 0, 0, 15, 1, 1, 1, 0, 0, 15, 1, 1, 1, 0, 0, 0, 0,
		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		 };
	
	public static final int[] TPlevel2 = 
		{ 144, 240, 208, 48,
		  144, 400, 208, 592,
		  240, 144, 48, 320,
		  272, 144, 48, 320,
		  304, 112, 400, 48,
		  304, 80, 48, 320,
		  432, 144, 48, 320,
		  464, 144, 592, 48,
		  496, 112, 48, 320,
		  496, 80, 48, 320,
		  624, 144, 336, 240,
		  656, 144, 48, 320,
		  688, 112, 48, 320,
		  688, 80, 48, 320,
		  528, 272, 592, 272,
		  752, 240, 48, 320,
		  240, 496, 400, 592,
		  272, 496, 48, 320,
		  304, 528, 48, 320,
		  304, 560, 48, 320,
		  432, 496, 48, 320,
		  464, 496, 48, 320,
		  496, 528, 48, 320,
		  496, 560, 592, 592,
		  624, 496, 48, 320,
		  656, 496, 48, 320,
		  688, 528, 336, 400,
		  688, 560, 48, 320,
		  528, 368, 592, 368,
		  752, 400, 48, 320
		  };
	
	/**
	 * 50x1 Map
	 */
	public static final int[] level3 =
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	
	/**
	 * 24x22 Map
	 */
	public static final int[] level4 = 
		{ 0, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 14,
		  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  6,  1,  1, -1,  1,  3,  1,  5,  1,  3,  1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  0,  1,  0,  4,  0,  4,  0,  2,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  0,  1,  0,  1,  3,  1,  5,  1,  5,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  0,  1,  0,  4,  0,  4,  0,  4,  0,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  0, -1,  0,  1,  3,  1,  5,  1,  3,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  1,  1,  1,  0,  0,  4,  0,  2,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,
		  0,  0,  1,  9,  1,  0, -1,  1,  5,  1,  3,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,
		  0,  0,  1,  1,  1,  0,  8,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 13,  0,  0,
		  0, 17, 17, 17, 17,  5,  5,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1, -1, 99,
		  0, 17, 17, 17, 17,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1, -1, 99,
		  0,  0,  1,  1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 13,  0,  0,
		  0,  0,  1,  9,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,
		  0,  0,  1,  1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,
		  0,  0,  0, -1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  7,  1,  1, -1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		  0, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 14, };
}
