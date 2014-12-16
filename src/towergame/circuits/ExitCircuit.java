package towergame.circuits;

public class ExitCircuit extends Circuit{

	private boolean solved;
	
	public ExitCircuit(int id) {
		super(id, 2);
	}

	@Override
	public void doLogic() {
		if (inputList[0] == true && inputList[1] == true){
			this.power = true;
			this.solved = true;
		} else {
			this.power = false;
		}
		
		if (solved == false){
			inputList[0] = false;
			inputList[1] = false;
		}
	}

}
