package towergame.entities;

import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	private Vector2f position;
	protected Vector2f velocity;
	protected int state;
	
	public Entity(Vector2f myPosition){
		position = myPosition;
		velocity = new Vector2f(0,0);
		state = 0;
	}
	
	public Entity(float x, float y){
		this(new Vector2f(x, y));
	}
	
	public void setPosition(float x, float y){
		position.set(x, y);
	}
	
	public Vector2f getPosition(){
		return position;
	}
	
	public float getX(){
		return position.getX();
	}
	
	public float getY(){
		return position.getY();
	}
	
	public int getState(){
		return state;
	}
	
	public void setState(int myState){
		state = myState;
	}
	
	public abstract void draw(Vector2f camera);
	
	public abstract void update(long delta);
}
