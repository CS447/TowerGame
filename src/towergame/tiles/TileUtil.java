package towergame.tiles;

import org.newdawn.slick.geom.Vector2f;

public class TileUtil {

	/* 
	 *      - Y              
	 *       ---             
	 *       \  \      + X 
	 *      ---------        
	 *       \  \  \  \      
	 *         --------      
	 *   - X      \  \       
	 *              ---       
	 *               + Y     
	 */
	
	/**
	 * Converts Cartesian Coordinates Into Isometric X Value
	 * @param carX
	 * @param carY
	 * @return
	 */
	public static float toIsoX(float carX, float carY){
		return (carX + carY);
	}
	
	/**
	 * Converts Cartesian Coordinates Into Isometric Y Value
	 * @param carX
	 * @param carY
	 * @return
	 */
	public static float toIsoY(float carX, float carY){
		return (carY-carX)/2;
	}
	
	/**
	 * Converts Isometric Coordinates into Cartesian X Value
	 * @param isoX
	 * @param isoY
	 * @return
	 */
	public static float toCarX(float isoX, float isoY){
		return ( (0.5f * isoX) - isoY);
	}
	
	/**
	 * Converts Isometric Coordinates into Cartesian Y Value
	 * @param isoX
	 * @param isoY
	 * @return
	 */
	public static float toCarY(float isoX, float isoY){
		return ( (0.5f * isoX) + isoY);
	}
	
	/**
	 * Converts a slick Vector2f in Cartesian to Isometric coordinates
	 * @param Cartesian
	 * @return
	 */
	public static Vector2f toIso(Vector2f Cartesian){
		Vector2f temp = new Vector2f();
		
		temp.x = toIsoX(Cartesian.getX(), Cartesian.getY());
		temp.y = toIsoY(Cartesian.getX(), Cartesian.getY());
		
		return temp;
	}
	
	/**
	 * Converts a slick Vector 2f in Isometric to Cartesian coordinates
	 * @param Isometric
	 * @return
	 */
	public static Vector2f toCar(Vector2f Isometric){
		Vector2f temp = new Vector2f();
		
		temp.x = toCarX(Isometric.getX(), Isometric.getY());
		temp.y = toCarY(Isometric.getX(), Isometric.getY());
		
		return temp;
	}
}
