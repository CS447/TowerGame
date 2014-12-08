package towergame.circuits;

/**
 * Circuits are either true or false. Circuits are used to determine
 * if devices or tiles should be running or not. Circuits have input
 * and are given input states of true or false by mechanisms. All circuits
 * must run logic. Each circuit has its own logic that determines its
 * power state based on the input it is given.
 * 
 *
 */
public abstract class Circuit {

	private final int id;
	protected boolean power;
	public boolean[] inputList;
	
	public Circuit(int id, int inputSize) {
		this.id = id;
		this.inputList = new boolean[inputSize];
	}
	
	
	public void setInput(int id, boolean power){
		inputList[id] = power;
	}
	
	/**
	 * A circuit's update method. <br><br>
	 * Used to determine the power status of <br>
	 * the circuit based on the circuits input.
	 */
	public abstract void doLogic();
	
	// -------
	// Getters
	// -------
	
	public boolean isOn() {
		return power;
	}

	public int getId() {
		return id;
	}
	
	public int inputSize() {
		return inputList.length;
	}
}
