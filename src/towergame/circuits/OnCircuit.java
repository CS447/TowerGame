package towergame.circuits;

public class OnCircuit extends Circuit{

	public OnCircuit(int id) {
		super(id, 0);
	}

	@Override
	public void doLogic() {
		this.power = true;
	}

}
