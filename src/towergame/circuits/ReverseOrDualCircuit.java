package towergame.circuits;

public class ReverseOrDualCircuit extends Circuit{
	
	public ReverseOrDualCircuit(int id) {
		super(id, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doLogic() {
		if (inputList[0] == true || inputList[1] == true){
			power = false;
		} else {
			power = true;
		}
		
		// Resets power if not still pressed
		inputList[0] = false;
		inputList[1] = false;
		
	}
}
