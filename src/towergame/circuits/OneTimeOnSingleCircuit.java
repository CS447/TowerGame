package towergame.circuits;

public class OneTimeOnSingleCircuit extends Circuit{

	private boolean solved;
	
	public OneTimeOnSingleCircuit(int id) {
		super(id, 1);
	}

	@Override
	public void doLogic() {
		if (inputList[0] == true){
			this.power = true;
			this.solved = true;
		} else {
			this.power = false;
		}
		
		if (solved == false){
			inputList[0] = false;
		}
	}
}
