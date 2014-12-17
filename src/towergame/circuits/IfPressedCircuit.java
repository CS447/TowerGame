package towergame.circuits;

public class IfPressedCircuit extends Circuit{

	public IfPressedCircuit(int id) {
		super(id, 1);
		}

		@Override
		public void doLogic() {
			if (inputList[0] == true){
				this.power = true;
			} else {
				this.power = false;
			}
			
			inputList[0] = false;
		}

}
