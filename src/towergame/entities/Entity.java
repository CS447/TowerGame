package towergame.entities;

import org.newdawn.slick.geom.Vector2f;

public class Entity {
	private Vector2f position;
	
	public Entity(Vector2f myPosition){
		position = myPosition;
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
}
