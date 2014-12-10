package towergame.circuits;

public class ReverseOrQuadCircuit extends Circuit{

	public ReverseOrQuadCircuit(int id) {
		super(id, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doLogic() {
		if (inputList[0] == true || inputList[1] == true || inputList[2] == true || inputList[0] == true){
			power = false;
		} else {
			power = true;
		}
		
		inputList[0] = false;
		inputList[1] = false;
		inputList[2] = false;
		inputList[3] = false;
		
	}

}
