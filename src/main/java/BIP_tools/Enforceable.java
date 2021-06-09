package BIP_tools;

public class Enforceable extends Transition{


	public Enforceable(String transition_name, State src, State dst) {
		super(transition_name, src, dst);
	}

	@Override
	public String toString() {
		return "\nEnforceable Transition \n\tTransition_name : "+ super.getTransition_name() +"\n\tCorrespond to the component : "+ super.getComponentOfTheTransition().getName()  +"\n\tIs Synchron : "+ super.getSynchron()+ "\n\tSrc : " + super.getSrc() + " \n\tDst : " + super.getDst();
	}




}
