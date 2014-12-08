package towergame.circuits;

public class TwoInputCircuit extends Circuit{

	public TwoInputCircuit(int id) {
		super(id, 2);
	}

	@Override
	public void doLogic() {
		if (inputList[1] == true && inputList[2] == true){
			this.power = true;
		} else {
			this.power = false;
		}
		
	}

}
