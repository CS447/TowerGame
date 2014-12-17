package towergame.entities;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import towergame.circuits.Circuit;
import towergame.tiles.TileManager;

public abstract class Mechanism extends Entity{

	private boolean isOn;
	private boolean isPushable;
	int squareRadius = 0;
	
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
	
	public boolean isPushable(){
		return isPushable;
	}
	
	public void setPushable(boolean pushable){
		isPushable = pushable;
	}
	
	public void addVelocity(Vector2f vel){
		this.velocity.add(vel);
	}
	
	public abstract void update(int delta, TileManager tm, List<Circuit> cl);
	
}
