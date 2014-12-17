package towergame.circuits;

public class AndQuadCircuit extends Circuit{

	public AndQuadCircuit(int id) {
		super(id, 4);
	}

	@Override
	public void doLogic() {
		if (inputList[0] == true && inputList[1] == true && inputList[2] == true && inputList[0] == true){
			power = true;
		} else {
			power = false;
			inputList[0] = false;
			inputList[1] = false;
			inputList[2] = false;
			inputList[3] = false;
		}
		
	}

}
