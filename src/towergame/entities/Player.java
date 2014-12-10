package towergame.entities;

import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import towergame.ResourceManager;
import towergame.TowerGame;
import towergame.circuits.Circuit;
import towergame.tiles.TileManager;
import towergame.tiles.TileUtil;

public class Player extends Entity{

	private boolean isPlayer1;
	private boolean isAlive;
	
	private int lastFacing;
	private float walkingSpeed;
	
	private Vector2f playerVelocity;
	
	private SpriteSheet ssStand[] = new SpriteSheet[4];
	private SpriteSheet ssWalk[]  = new SpriteSheet[4];;
	
	private Animation animationStand[] = new Animation[4];
	private Animation animationWalk[] = new Animation[4];;
	
	public PlayerState playerState;
	
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
	public enum PlayerState{
		WALK_UP(1), WALK_RIGHT(2), WALK_DOWN(3), WALK_LEFT(4),
		STAND_UP(5), STAND_RIGHT(6), STAND_DOWN(7), STAND_LEFT(8),
		SIT_UP(9), SIT_RIGHT(10), SIT_DOWN(11), SIT_LEFT(12),
		USE_UP(13), USE_RIGHT(14), USE_DOWN(15), USE_LEFT(16),
		DEAD(-1);
		
		private int pose;
		
		private PlayerState(int myPose) {
            this.pose = myPose;
		}
		
		public int getPose(){
			return pose;
		}
	}
	
	// ----------------------------------------------------------------------------------------
	// Player
	// ----------------------------------------------------------------------------------------
	
	public Player(Vector2f myPosition) {
		super(myPosition);
		
		isAlive = true;
		
		lastFacing = 3;
		walkingSpeed = 0.125f;
		
		playerVelocity = new Vector2f(0,0);
		
		playerState = playerState.STAND_LEFT;
		
		setupAnimations();
	}
	
	public Player(float x, float y, boolean player) {
		this(new Vector2f(x, y));
		setPlayer(player);
		setupAnimations();
	}
	
	private void setupAnimations(){
		if (isPlayer1){
			ssStand[0] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER1_STAND_U, 64, 76);
			ssStand[1] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER1_STAND_R, 64, 76);
			ssStand[2] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER1_STAND_D, 64, 76);
			ssStand[3] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER1_STAND_L, 64, 76);
			
			ssWalk[0] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER1_WALK_R, 64, 76);
			ssWalk[1] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER1_WALK_R, 64, 76);
			ssWalk[2] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER1_WALK_L, 64, 76);
			ssWalk[3] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER1_WALK_L, 64, 76);
		} else {
			ssStand[0] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER2_STAND_U, 64, 76);
			ssStand[1] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER2_STAND_R, 64, 76);
			ssStand[2] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER2_STAND_D, 64, 76);
			ssStand[3] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER2_STAND_L, 64, 76);
			
			ssWalk[0] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER2_WALK_R, 64, 76);
			ssWalk[1] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER2_WALK_R, 64, 76);
			ssWalk[2] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER2_WALK_L, 64, 76);
			ssWalk[3] = ResourceManager.getSpriteSheet(TowerGame.SPRITESHEET_PLAYER2_WALK_L, 64, 76);
		}
		
		// Setup Animations
		animationStand[0] = new Animation(ssStand[0], 0, 0, 0, 0, true, 250, false );
		animationStand[1] = new Animation(ssStand[1], 0, 0, 0, 0, true, 250, false );
		animationStand[2] = new Animation(ssStand[2], 0, 0, 0, 0, true, 250, false );
		animationStand[3] = new Animation(ssStand[3], 0, 0, 0, 0, true, 250, false );
		
		animationWalk[0] = new Animation(ssWalk[0], 0, 0, 3, 0, true, 250, true );
		animationWalk[1] = new Animation(ssWalk[1], 0, 0, 3, 0, true, 250, true );
		animationWalk[2] = new Animation(ssWalk[2], 0, 0, 3, 0, true, 250, true );
		animationWalk[3] = new Animation(ssWalk[3], 0, 0, 3, 0, true, 250, true );
	}
	
	public void setState(PlayerState state){
		this.setState(state.getPose());
		this.playerState = state;
	}
	
	public void setStand(){
		switch(lastFacing){
			case 1:
				setState(PlayerState.STAND_UP);
				break;
			case 2:
				setState(PlayerState.STAND_RIGHT);
				break;
			case 3:
				setState(PlayerState.STAND_DOWN);
				break;
			case 0:
				setState(PlayerState.STAND_LEFT);
				break;
		}
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
	 * 1 = up <br>
	 * 2 = right <br>
	 * 3 = down <br>
	 * 0 = left <br>
	 * @return direction
	 */
	public int isFacing(){
		return ( playerState.pose % 4 );
	}
	
	public void walkUp(){
		this.playerVelocity.y = -walkingSpeed;
		this.playerVelocity.x = walkingSpeed;
	}
	
	public void walkUpRight(){
		this.playerVelocity.x = walkingSpeed;
	}
	
	public void walkRight(){
		this.playerVelocity.x = walkingSpeed * 0.5f;
		this.playerVelocity.y = walkingSpeed * 0.5f;
	}
	
	public void walkDownRight(){
		this.playerVelocity.y = walkingSpeed;
	}
	
	public void walkDown(){
		this.playerVelocity.y = walkingSpeed;
		this.playerVelocity.x = -walkingSpeed;
	}
	
	public void walkDownLeft(){
		this.playerVelocity.x = -walkingSpeed;
	}
	
	public void walkLeft(){
		this.playerVelocity.x = -walkingSpeed * 0.5f;
		this.playerVelocity.y = -walkingSpeed * 0.5f;
	}
	
	public void walkUpLeft(){
		this.playerVelocity.y = -walkingSpeed;
	}
	
	@Override
	public void draw(Vector2f camera){
		float tempX = getX();
		float tempY = getY();
		
		tempX = TileUtil.toIsoX(getX(), getY()) + camera.getX() - 32;
		tempY = TileUtil.toIsoY(getX(), getY()) + camera.getY() - 57;
		
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
			animationStand[2].draw(tempX, tempY);
			break;
		case STAND_LEFT:
			animationStand[3].draw(tempX, tempY);
			break;
		case STAND_RIGHT:
			animationStand[1].draw(tempX, tempY);
			break;
		case STAND_UP:
			animationStand[0].draw(tempX, tempY);
			break;
		case USE_DOWN:
			break;
		case USE_LEFT:
			break;
		case USE_RIGHT:
			break;
		case USE_UP:
			break;
		case WALK_DOWN:
			animationWalk[2].draw(tempX, tempY);
			break;
		case WALK_LEFT:
			animationWalk[3].draw(tempX, tempY);
			break;
		case WALK_RIGHT:
			animationWalk[1].draw(tempX, tempY);
			break;
		case WALK_UP:
			animationWalk[0].draw(tempX, tempY);
			break;
		default:
			break;
		
		}
	}

	private void resetVelocity(){
		velocity = new Vector2f(0,0);
		playerVelocity = new Vector2f(0,0);
	}
	
	private void movePlayer(TileManager tm, float myX, float myY){
		Vector2f temp = new Vector2f(getX()+myX, getY()+myY);
		
		if (tm.tileStyle(temp) > 0) {
			this.setPosition(getX() + myX, getY() + myY);
		}
	}
	
	private void checkTile(TileManager tm, List<Circuit> cl, int delta){
		this.velocity = tm.tileForce(getPosition());
		tm.tileEvent(getPosition(), cl, delta);
	}

	public void update(int delta, TileManager tm, List<Circuit> cl) {
		state = playerState.pose;
		
		checkTile(tm, cl, delta);
		movePlayer( tm, (velocity.x+playerVelocity.x)*delta, (velocity.y+playerVelocity.y)*delta );
		resetVelocity();
		lastFacing = isFacing();
	}
	
	@Override
	public void update(long delta) {
		state = playerState.pose;
		
		//this.setPosition( getX() + (velocity.x+playerVelocity.x)*delta, 
		//		getY() + (velocity.y+playerVelocity.y)*delta );
		resetVelocity();
		lastFacing = isFacing();
	}
}
