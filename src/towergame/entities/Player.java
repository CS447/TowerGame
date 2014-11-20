package towergame.entities;

import org.newdawn.slick.geom.Vector2f;

public class Player extends Entity{

	private boolean isPlayer1;
	private boolean isAlive;
	
	public playerState playerState;
	
	 /* 
	 *       up              
	 *       ---             
	 *       \  \      right 
	 *      ---------        
	 *       \  \  \  \      
	 *         --------      
	 *   left     \  \       
	 *             ---       
	 *              down     
	 */
	
	/**
	 * The possible states the player is in
	 */
	public enum playerState{
		WALK_UP(1), WALK_RIGHT(2), WALK_DOWN(3), WALK_LEFT(4),
		STAND_UP(5), STAND_RIGHT(6), STAND_DOWN(7), STAND_LEFT(8),
		SIT_UP(9), SIT_RIGHT(10), SIT_DOWN(11), SIT_LEFT(12),
		DEAD(-1);
		
		private int pose;
		
		private playerState(int myPose) {
            this.pose = myPose;
		}
	}
	
	public Player(Vector2f myPosition) {
		super(myPosition);
		
		isAlive = true;
	}
	
	public Player(float x, float y) {
		this(new Vector2f(x, y));
	}
	
	/**
	 * Returns the player number:<br><br>
	 * True = Player1 <br>
	 * False = Player2
	 * @return isPlayer1
	 */
	public boolean isPlayer1(){
		return isPlayer1;
	}
	
	/**
	 * Sets the player number: <br><br>
	 * True = Player1 <br>
	 * False = Player2
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
	
	
	/**
	 * Returns the direction the player is facing. <br><br>
	 * 0 = up <br>
	 * 1 = right <br>
	 * 2 = down <br>
	 * 3 = left <br>
	 * @return direction
	 */
	public int isFacing(){
		return ( playerState.pose % 4 );
	}

	@Override
	public void draw() {
		
		switch(playerState){
		case DEAD:
			break;
		case SIT_DOWN:
			break;
		case SIT_LEFT:
			break;
		case SIT_RIGHT:
			break;
		case SIT_UP:
			break;
		case STAND_DOWN:
			break;
		case STAND_LEFT:
			break;
		case STAND_RIGHT:
			break;
		case STAND_UP:
			break;
		case WALK_DOWN:
			break;
		case WALK_LEFT:
			break;
		case WALK_RIGHT:
			break;
		case WALK_UP:
			break;
		default:
			break;
		}
		
	}

	@Override
	public void update(long delta) {
		state = playerState.pose;
		
	}
}
