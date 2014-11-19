package towergame.entities;

import org.newdawn.slick.geom.Vector2f;

public abstract class Mechanism extends Entity{

	private boolean isOn;
	
	public Mechanism(Vector2f myPosition) {
		super(myPosition);
		
		isOn = false;
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
	
}
