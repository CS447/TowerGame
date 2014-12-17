package towergame.circuits;

public class ReverseIfPressedCircuit extends Circuit{

	public ReverseIfPressedCircuit(int id) {
		super(id, 1);
	}

	@Override
	public void doLogic() {
		if (inputList[0] == true){
			this.power = false;
		} else {
			this.power = true;
		}
		
		inputList[0] = false;
	}
}
