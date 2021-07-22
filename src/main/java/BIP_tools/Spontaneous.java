package BIP_tools;

public class Spontaneous extends Transition{

	public Spontaneous(String transition_name, State src, State dst,Boolean synchron, Component componentOfTheTransition) {
		super(transition_name, src, dst, synchron, componentOfTheTransition );
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
//		return "\nEnforceable Transition \n\tTransition_name : "+ super.getTransition_name() +"\n\tCorrespond to the component : "+ super.getComponentOfTheTransition().getName()  +"\n\tIs Synchron : "+ super.getSynchron()+ "\n\tSrc : " + super.getSrc() + " \n\tDst : " + super.getDst();
		return "\t\tSpontaneous  Transition {Transition_name : "+ super.getTransition_name() +", For component : "+ super.getComponentOfTheTransition().getName()  +", Synchron : "+ super.getSynchron()+ ", Src : " + super.getSrc() + ", Dst : " + super.getDst()+ "}\n";
		
	}

}
