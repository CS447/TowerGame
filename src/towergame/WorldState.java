package towergame;

import java.util.ArrayList;
import java.util.List;

import towergame.circuits.Circuit;
import towergame.entities.Mechanism;
import towergame.entities.Player;

public class WorldState {
	
	public long time;
	
	public int level;
	
	public Player p1;
	public Player p2;
	
	public List<Circuit> circuitList = new ArrayList<Circuit>();
	public List<Mechanism> mechanismList = new ArrayList<Mechanism>();
	
	//TODO Need to finish
	
}
