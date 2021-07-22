package BIP_tools;

public class Transition {
	private String transition_name;
	private State src;
	private State dst;
	private Boolean synchron;
	private Component componentOfTheTransition;
	
	public Component getComponentOfTheTransition() {
		return componentOfTheTransition;
	}

	public void setComponentOfTheTransition(Component componentOfTheTransition) {
		this.componentOfTheTransition = componentOfTheTransition;
	}

	public Transition(String transition_name, State src, State dst,  Boolean synchron ,Component componentOfTheTransition  ) {
		this.transition_name = transition_name;
		this.src = src;
		this.dst = dst;
		this.componentOfTheTransition = componentOfTheTransition;
		this.synchron = synchron;
	}

	@Override
	public String toString() {
		return "\n\n\nTransition \n[ Component Of The Transition "+componentOfTheTransition.getName() +"transition_name=" + transition_name + "is Synchron " + synchron +", src=" + src.getState_name() + ", dst=" + dst.getState_name() + "\n\n]\n\n";
	}

	public String getTransition_name() {
		return transition_name;
	}

	public void setTransition_name(String transition_name) {
		this.transition_name = transition_name;
	}

	public State getSrc() {
		return src;
	}

	public void setSrc(State src) {
		this.src = src;
	}

	public State getDst() {
		return dst;
	}

	public void setDst(State dst) {
		this.dst = dst;
	}
	public Boolean getSynchron() {
		return synchron;
	}

	public void setSynchron(Boolean synchron) {
		this.synchron = synchron;
	}
}
