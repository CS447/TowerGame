package towergame.entities;

import org.newdawn.slick.geom.Vector2f;

public class Mechanism extends Entity{

	private boolean isOn;
	private int state;
	
	public Mechanism(Vector2f myPosition) {
		super(myPosition);
		
		isOn = false;
		state = 0;
	}
	
	public Mechanism(float x, float y) {
		this(new Vector2f(x, y));
	}

	public boolean isOn(){
		return isOn;
	}
	
	/**
	 * Sets the boolean isOn
	 * @param power
	 */
	public void setPower(boolean power){
		isOn = power;
	}
	
	public int getState(){
		return state;
	}
	
	public void setState(int myState){
		state = myState;
	}
	
}
