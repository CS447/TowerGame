package towergame;


//A class made so I can store pairs of ints for teleporter coordinates
//Was unsure how to do this in Java, so just made one
public class Pair {
	private int x;
	private int y;
	public Pair(int x, int y){
        this.x = x;	        
        this.y = y;
	}
    public int getX(){ return x; }
    public int getY(){ return y; }
    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
}

