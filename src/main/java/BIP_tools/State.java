package BIP_tools;

public class State {
	private String state_name;

	public State(String state_name) {
		this.state_name = state_name;
	}
	


	public void setState_name(String state_name) {
		this.state_name =state_name; 
	}

	public String getState_name() {
		return state_name;
	}
	
	@Override
	public String toString() {
		return "State [state_name=" + state_name + "]";
	}
}
