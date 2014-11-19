package towergame.entities;

import org.newdawn.slick.geom.Vector2f;

public class Player extends Entity{

	private boolean isPlayer1;
	private boolean isAlive;
	
	public Player(Vector2f myPosition) {
		super(myPosition);
		
	}
	
	public Player(float x, float y) {
		this(new Vector2f(x, y));
	}
	
	/**
	 * Returns the player number:<br>
	 * If True, Player1 <br>
	 * If False, Player2
	 * @return isPlayer1
	 */
	public boolean isPlayer1(){
		return isPlayer1;
	}
	
	/**
	 * Sets the player number: <br>
	 * If True, Player1 <br>
	 * If False, Player2
	 * @param player1
	 */
	public void setPlayer(boolean player1){
		isPlayer1 = player1;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void setAlive(boolean fate){
		isAlive = fate;
	}

}
