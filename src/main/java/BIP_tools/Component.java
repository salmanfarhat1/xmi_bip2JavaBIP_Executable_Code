package BIP_tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PrimitiveIterator.OfDouble;

//import org.javabip.annotations.Guard;


public class Component {
	
	private String name; 
	private Boolean optional; 
	private Boolean usableComponent; 
	private State initial_state; 
	private ArrayList<State> states;
	private ArrayList<Transition> Transitions;
	private ArrayList<Guard> guards; 

	
	public Component(String name, Boolean optional, Boolean usableComponent,  State initial_state, ArrayList<State> states, ArrayList<Transition> transitions) {
		this.name = name;
		this.optional = optional;
		this.usableComponent = usableComponent;
		this.initial_state = initial_state;
		this.states = states;
		Transitions = transitions;
		guards = new ArrayList<Guard>();
	}

//	import org.javabip.annotations.ComponentType;
//	import org.javabip.annotations.Data;
//	import org.javabip.annotations.Guard;
//	import org.javabip.annotations.Port;
//	import org.javabip.annotations.Ports;
//	import org.javabip.annotations.Transition;
//	import org.javabip.api.PortType;
	


	public ArrayList<Guard> getGuards() {
		return guards;
	}

	public void setGuards(ArrayList<Guard> guards) {
		this.guards = guards;
	}

	public Boolean getUsableComponent() {
		return usableComponent;
	}



	public void setUsableComponent(Boolean usableComponent) {
		this.usableComponent = usableComponent;
	}



	public Boolean getOptional() {
		return optional;
	}



	public void setOptional(Boolean optional) {
		this.optional = optional;
	}



	public static void main(String[] args) {

		System.out.println(" Run Generate_JavaBIP_Code" );
	}



	public void GenerateCode() {
		CreateCode_for_states();
		CreateCode_for_ports();
		CreateCodeForComponentSpec();
	}
	
//		
//
	public void CreateCode_for_ports() {
		StringBuilder Component_ports = new StringBuilder();
		Component_ports.append("package Output;");
		Component_ports.append(System.lineSeparator());

		Component_ports.append("public class " + this.name+"_ports" + "{");
		Component_ports.append(System.lineSeparator());
		

		for(int i = 0; i < Transitions.size() ; i++) {
			Component_ports.append("\t");
			Component_ports.append("public static final String "+ name+ "_"+ "p"+"_"+ Transitions.get(i).getTransition_name() +" = \"" + name+ "_"+ "p"+"_"+ Transitions.get(i).getTransition_name() +"\";");
			Component_ports.append(System.lineSeparator());
		}
		Component_ports.append("}");
//		package Exclude_FM_To_JavaBIP;
		
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+this.name+"_ports.java"))) {
			oS.write(Component_ports.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void CreateCode_for_states() {
		StringBuilder Component_states = new StringBuilder();
		Component_states.append("package Output;");
		Component_states.append(System.lineSeparator());
		Component_states.append("public class " + this.name+"_states" + "{");
		Component_states.append(System.lineSeparator());
		
		Component_states.append("\t");
		Component_states.append("public static final String "+ name+ "_"+ "s"+"_"+initial_state.getState_name() +" = \"" + name+ "_"+ "s"+"_"+initial_state.getState_name() +"\";");		
		Component_states.append(System.lineSeparator());

		for(int i = 0; i < states.size() ; i++) {
			Component_states.append("\t");
			Component_states.append("public static final String "+ name+ "_s_"+ states.get(i).getState_name() +" = \"" + name+ "_"+ "s"+"_"+ states.get(i).getState_name() +"\";" );
			Component_states.append(System.lineSeparator());
		}
		Component_states.append("}");
//		package Exclude_FM_To_JavaBIP;
		
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+this.name+"_states.java"))) {
			oS.write(Component_states.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void CreateCodeForComponentSpec() {

		StringBuilder component_specification = new StringBuilder();

		component_specification.append("package Output;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.ComponentType;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.Data;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.Guard;");
		component_specification.append(System.lineSeparator());

		component_specification.append("import org.javabip.annotations.Port;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.Ports;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.annotations.Transition;");
		component_specification.append(System.lineSeparator());
		
		component_specification.append("import org.javabip.api.PortType;");
		component_specification.append(System.lineSeparator());
		
		
//		@Ports({
//			@Port(name = Controller_exclude_ports.controller_exclude_p_basic , type = PortType.enforceable),
//			@Port(name = Controller_exclude_ports.controller_exclude_p_not_basic , type = PortType.enforceable),
//			@Port(name = Controller_exclude_ports.controller_exclude_p_valid_gps , type = PortType.enforceable),
//			@Port(name = Controller_exclude_ports.controller_exclude_p_reset , type = PortType.enforceable)
//
//		})
		
		component_specification.append("@Ports({");
		component_specification.append(System.lineSeparator());
		for(int i = 0 ; i < Transitions.size(); i++) {
			if(i != Transitions.size() - 1) {
				if(Transitions.get(i) instanceof Enforceable) {
					component_specification.append("\t@Port(name = " + name+ "_ports."+ name+ "_p_"+ Transitions.get(i).getTransition_name() + " , type = PortType.enforceable ),");
				}
				else {
					component_specification.append("\t@Port(name = " + name+ "_ports."+ name+ "_p_"+ Transitions.get(i).getTransition_name() + " , type = PortType.spontaneous ),");

				}
				component_specification.append(System.lineSeparator());
			}
			else {
				if(Transitions.get(i) instanceof Enforceable) {
					component_specification.append("\t@Port(name = " + name+ "_ports."+ name+ "_p_"+ Transitions.get(i).getTransition_name() + " , type = PortType.enforceable )");
				}
				else {
					component_specification.append("\t@Port(name = " + name+ "_ports."+ name+ "_p_"+ Transitions.get(i).getTransition_name() + " , type = PortType.spontaneous )");

				}
				component_specification.append(System.lineSeparator());
			}

		}
		component_specification.append("})");
		component_specification.append(System.lineSeparator());

//		@ComponentType(initial = Controller_exclude_states.controller_exclude_s_init , name ="controller_exclude")
//		public class Controller_exclude_spec {
//			
//			public Controller_exclude_spec() {
//				
//			}
		component_specification.append("@ComponentType(initial = "+name+"_states." + name +"_s_" +initial_state.getState_name() +" , name =\""+name +"\")");
		component_specification.append(System.lineSeparator());
		component_specification.append("public class "+name+"_spec{");
		component_specification.append(System.lineSeparator());	
		ArrayList<String> guardVariables = new ArrayList<>();
		if(this.name.equals("Main_Controller")) {
//			System.out.println("ayr "+ this.guards.size());
			for(int  i = 0; i < this.guards.size(); i++) {
				if(guards.get(i) instanceof Guard_Implies) {
					// if the arraylist of variables doesn't contain the varaiable then we add it 
					if(!guardVariables.contains(((Guard_Implies) guards.get(i)).getSrcState().getState_name())) {
						component_specification.append("\tprivate Boolean " + ((Guard_Implies) guards.get(i)).getSrcState().getState_name()+";");
						component_specification.append(System.lineSeparator());
//						System.out.println("iiiiiiiiii");
						guardVariables.add(((Guard_Implies) guards.get(i)).getSrcState().getState_name());
					}
//					
				} 
				else if(guards.get(i) instanceof Guard_Exclude) {
					if(!guardVariables.contains(((Guard_Exclude) guards.get(i)).getSrcState().getState_name())) {
						component_specification.append("\tprivate Boolean " + ((Guard_Exclude) guards.get(i)).getSrcState().getState_name()+";");
						component_specification.append(System.lineSeparator());
//						System.out.println("iiiiiiiiii");
						guardVariables.add(((Guard_Exclude) guards.get(i)).getSrcState().getState_name());
					}
					if(!guardVariables.contains(((Guard_Exclude) guards.get(i)).getDstState().getState_name())) {
						component_specification.append("\tprivate Boolean " + ((Guard_Exclude) guards.get(i)).getDstState().getState_name()+";");
						component_specification.append(System.lineSeparator());
						guardVariables.add(((Guard_Exclude) guards.get(i)).getDstState().getState_name());	
					}
					
				}
				
				
			}	
		} 
//		
//		
//		
		component_specification.append("\tpublic "+name+"_spec(){");
		component_specification.append(System.lineSeparator());
		if(this.name.equals("Main_Controller")) {
//			for(int  i = 0; i < this.guards.size(); i++) {
//				if(guards.get(i) instanceof Guard_Implies) {
//					component_specification.append("\t\t"+ ((Guard_Implies) guards.get(i)).getSrcState().getState_name()+"= false;");
//					component_specification.append(System.lineSeparator());
////					System.out.println("fuuuuuuuuuuuuuuuuuuuuuuuuck" +((Guard_Implies) guards.get(i)).getSrcState().getState_name());
//				} 
//			}
			for(int i = 0; i < guardVariables.size(); i++) {
				component_specification.append("\t\t"+ guardVariables.get(i)+" = false;");
				component_specification.append(System.lineSeparator());
			}
			
			
		}
//		

		component_specification.append("\t}"+System.lineSeparator()); // end of the constructor 
		
		
		for(int i = 0; i < guardVariables.size(); i++) {
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
			component_specification.append("@Guard(name = \"is"+ guardVariables.get(i)+"On\")");
			component_specification.append(System.lineSeparator());
			component_specification.append("public Boolean check" + guardVariables.get(i) +"(){");
			component_specification.append(System.lineSeparator());
			component_specification.append("\t return "+  guardVariables.get(i)  + ";");
			component_specification.append(System.lineSeparator());
			component_specification.append("}");
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
		}
		
		
		component_specification.append(System.lineSeparator());
	
	
		component_specification.append(System.lineSeparator());	
		component_specification.append(System.lineSeparator());	
		component_specification.append(System.lineSeparator());	

//		@Transition(name = GPS_ports.gps_component_p_gps, source = GPS_states.gps_component_s_init, target = GPS_states.gps_component_s_gps)
//		public void callAPI_1() {
//		System.out.println("************");
//			System.out.println("gps_component  init ---> gps");
//			System.out.println("************ In state gps");
//		}
		String pre_port_name = name+"_p_"; 
		String pre_state_name = name+"_s_"; 
		for(int i = 0 ; i < Transitions.size();i++) {
//			woow 
			component_specification.append("@Transition(name =" + name +"_ports."+ pre_port_name + Transitions.get(i).getTransition_name() +", source = "+name +"_states."+pre_state_name+Transitions.get(i).getSrc().getState_name() + ", target = "+name +"_states."+pre_state_name+Transitions.get(i).getDst().getState_name());
			// for Implies Guard additions to add the guard condition to the enforceable transition 
			if(matchDstStateInOneOfTheImpliesGuardsWithTheSrcStateOfTrans_reset(Transitions.get(i).getSrc().getState_name())) {
				// if the dst state of the implies guard is equal to the src state of the transition  (state name)
				// for the reset case... this is for the implies 
				component_specification.append( ", guard = \"!is" + getSrcImpliesGuardName(Transitions.get(i).getSrc().getState_name())+"On\"");
			}
			
			if(doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") != null) {
				component_specification.append(", guard = \"!is" + doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") +"On\""     );
			}
//			else if(doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "R") != null) {
//				component_specification.append(", guard = \"!is" + doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "R") +"On\""     );
//			}
			
			component_specification.append( ")");
			component_specification.append(System.lineSeparator());	
			
			component_specification.append("\tpublic void trans_"+Transitions.get(i).getSrc().getState_name()+"_to_"+Transitions.get(i).getDst().getState_name()+"(){");
			
			component_specification.append(System.lineSeparator());	
			
			// For Implies additions to add Camera = true/false
			if(matchSrcStateInOneOfTheImpliesGuards_withTheSrcStateOftransition_start(Transitions.get(i).getSrc().getState_name()) == true) {
				component_specification.append("\t\t"+Transitions.get(i).getSrc().getState_name().substring(1) + " = true;");
				component_specification.append(System.lineSeparator());
			}else if(matchSrcStateInOneOfTheImpliesGuards_withTheSrcStateOfTrans_reset(Transitions.get(i).getSrc().getState_name()) == true) {
				component_specification.append("\t\t"+Transitions.get(i).getSrc().getState_name().substring(1) + " = false;");
				component_specification.append(System.lineSeparator());
			}
			
			
			// in case of Exclude I want to turn on and of the options according to the selection
			if(doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") != null) {
//				component_specification.append(", guard = \"!is" + doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") +"On\""     );
				component_specification.append("\t\t"+  Transitions.get(i).getSrc().getState_name().substring(1)   + " = true;");
				component_specification.append(System.lineSeparator());
			}
			else if(doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "R") != null) {
 				component_specification.append("\t\t"+  Transitions.get(i).getSrc().getState_name().substring(1)  + " = false;");
				
				component_specification.append(System.lineSeparator());
			}
			
			component_specification.append("\t\tSystem.out.println( \"component name: "+name +" from :" +   Transitions.get(i).getSrc().getState_name()+ "---> " + Transitions.get(i).getDst().getState_name()+"\");");
			component_specification.append(System.lineSeparator());

			component_specification.append("\t}");	
			// for Implies additions to write the internal transition and the guard
			if(matchDstStateInOneOfTheImpliesGuardsWithTheSrcStateOfTrans_reset(Transitions.get(i).getSrc().getState_name())) {
				component_specification.append(System.lineSeparator());
				component_specification.append("@Transition(name =\"\", source = "+name +"_states."+pre_state_name+Transitions.get(i).getSrc().getState_name() + ", target = "+name +"_states."+pre_state_name+Transitions.get(i).getDst().getState_name() + ", guard = \"is" + getSrcImpliesGuardName(Transitions.get(i).getSrc().getState_name())+"On\")");
				
				component_specification.append(System.lineSeparator());
				component_specification.append("\tpublic void Internal_trans_"+Transitions.get(i).getSrc().getState_name()+"_to_"+Transitions.get(i).getDst().getState_name()+"(){");
				component_specification.append(System.lineSeparator());
				component_specification.append("\t\tSystem.out.println( \"component name: "+name +" from :" +   Transitions.get(i).getSrc().getState_name()+ "---> " + Transitions.get(i).getDst().getState_name()+" (nothing Done)\");");
				component_specification.append(System.lineSeparator());
				component_specification.append("\t}");	
				
//				component_specification.append(System.lineSeparator());
//				component_specification.append("@Guard(name = \"is"+ getSrcImpliesGuardName(Transitions.get(i).getSrc().getState_name())+"On\")");
//				component_specification.append(System.lineSeparator());
//				component_specification.append("public Boolean check" + getSrcImpliesGuardName(Transitions.get(i).getSrc().getState_name())+"(){");
//				component_specification.append(System.lineSeparator());
//				component_specification.append("\t return "+  getSrcImpliesGuardName(Transitions.get(i).getSrc().getState_name()) + ";");
//				component_specification.append(System.lineSeparator());
//				component_specification.append("}");

			}
			
			if(doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") != null) {
				component_specification.append(System.lineSeparator());
				component_specification.append("@Transition(name =\"\", source = "+name +"_states."+pre_state_name+Transitions.get(i).getSrc().getState_name() + ", target = "+name +"_states."+pre_state_name+Transitions.get(i).getDst().getState_name() + ", guard = \"is" + doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") +"On\")");
				
				component_specification.append(System.lineSeparator());
				component_specification.append("\tpublic void Internal_trans_"+Transitions.get(i).getSrc().getState_name()+"_to_"+Transitions.get(i).getDst().getState_name()+"(){");
				component_specification.append(System.lineSeparator());
				component_specification.append("\t\tSystem.out.println( \"component name: "+name +" from :" +   Transitions.get(i).getSrc().getState_name()+ "---> " + Transitions.get(i).getDst().getState_name()+" (nothing Done cannot be both on )\");");
				component_specification.append(System.lineSeparator());
				component_specification.append("\t}");	
				
//				component_specification.append(System.lineSeparator());
//				component_specification.append("@Guard(name = \"is"+ doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") +"On\")");
//				component_specification.append(System.lineSeparator());
//				component_specification.append("public Boolean check" + doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") +"(){");
//				component_specification.append(System.lineSeparator());
//				component_specification.append("\t return "+  doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(Transitions.get(i).getSrc().getState_name() , "S") + ";");
//				component_specification.append(System.lineSeparator());
//				component_specification.append("}");
			}
			
			
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
			component_specification.append(System.lineSeparator());
		}
		
		component_specification.append("}");
		component_specification.append(System.lineSeparator());	
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+this.name+"_spec.java"))) {
			oS.write(component_specification.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private String doesSrcStateOfTheTransitionMatchAState_src_or_dst_InOneOfTheExcludeGuards(String srcStateTrans, String choix) {

		for(int i =0 ; i < guards.size(); i++) {
			if(guards.get(i).getName().split("___")[0].equals("Exclude")) { // if it is an Implies Guard 
//				 System.out.println(state_name);
				 if(   srcStateTrans.equals(choix + ((Guard_Exclude) guards.get(i)).getSrcState().getState_name())  ) {
					 
					 
					 return ((Guard_Exclude) guards.get(i)).getDstState().getState_name(); 
				 }
				 else if(srcStateTrans.equals(choix + ((Guard_Exclude) guards.get(i)).getDstState().getState_name())){
					 return  ((Guard_Exclude) guards.get(i)).getSrcState().getState_name();
				 }
				 
			}
		}
		return null;
	}
	
	private String getSrcImpliesGuardName(String state_name) { 
		// matching the state name (sent) with the destination state of the guard and if they match return the name of the source state
		for(int i =0 ; i < guards.size(); i++) {
			if(guards.get(i).getName().split("___")[0].equals("Implies")) { // if it is an Implies Guard 
//				 System.out.println(state_name);
				 if(   state_name.equals("R" + ((Guard_Implies) guards.get(i)).getDstState().getState_name()) ) {
//					 System.out.println( "Matched " + ((Guard_Implies) guards.get(i)).getDstState().getState_name());
					 return ((Guard_Implies) guards.get(i)).getSrcState().getState_name(); 
				 }
			}
		}
		return "no_no_oops";
	}

	private boolean matchSrcStateInOneOfTheImpliesGuards_withTheSrcStateOftransition_start(String state_name) {
		for(int i =0 ; i < guards.size(); i++) {
			if(guards.get(i).getName().split("___")[0].equals("Implies")) { // if it is an Implies Guard 
//				 System.out.println(state_name);
				 if(   state_name.equals("S" + ((Guard_Implies) guards.get(i)).getSrcState().getState_name()) ) {
//					 System.out.println( "Matched " + ((Guard_Implies) guards.get(i)).getSrcState().getState_name());
					 return true; 
				 }
			}
		}
		return false;
	}
	private boolean matchSrcStateInOneOfTheImpliesGuards_withTheSrcStateOfTrans_reset(String state_name) {
		for(int i =0 ; i < guards.size(); i++) {
			if(guards.get(i).getName().split("___")[0].equals("Implies")) { // if it is an Implies Guard 
//				 System.out.println(state_name);
				 if(   state_name.equals("R" + ((Guard_Implies) guards.get(i)).getSrcState().getState_name()) ) {
//					 System.out.println( "Matched " + ((Guard_Implies) guards.get(i)).getSrcState().getState_name());
					 return true; 
				 }
			}
		}
		return false;
	}
	
	
	private boolean matchDstStateInOneOfTheImpliesGuardsWithTheSrcStateOfTrans_reset(String state_name) {
		// if the dst state of the implies guard is equal to the src state of the transition  (state name)
		// for the reset case
		for(int i =0 ; i < guards.size(); i++) {
			if(guards.get(i).getName().split("___")[0].equals("Implies")) { // if it is an Implies Guard 
//				 System.out.println(state_name);
				 if(   state_name.equals("R" + ((Guard_Implies) guards.get(i)).getDstState().getState_name()) ) {
//					 System.out.println( "Matched " + ((Guard_Implies) guards.get(i)).getDstState().getState_name());
					 return true; 
				 }
			}
		}
		return false;
	}
	
	
	@Override
	public String toString() {	
		String S = "Component *** " + this.name + " ***\n";
		S += "\tOptional Component " + this.optional + "\n";
		S += "\tUsable Component " + this.usableComponent + "\n";
		S += "\tInitial State: "+ initial_state.getState_name()+ "\n";
		S += "\tStates: ";
		for(int i=0; i < states.size(); i++ ) {
			
			if(states.size() == 1) {
				S += "[" + states.get(i).getState_name() + "]";
			} 
			else {
				if(i ==0) 
					S += "[" + states.get(i).getState_name() + ", ";
				else if(i != states.size()-1 ) 
					S += states.get(i).getState_name() + ", ";
				else
					S += states.get(i).getState_name() + "]";
			}
		}	
		S += "\n\tTransitions: \n";	
		for(int i=0; i < Transitions.size(); i++ ) {
			S += Transitions.get(i).toString();	
		}
		S +="\n\n\n"; 
		return S;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public State getInitial_state() {
		return initial_state;
	}



	public void setInitial_state(State initial_state) {
		this.initial_state = initial_state;
	}



	public ArrayList<State> getStates() {
		return states;
	}



	public void setStates(ArrayList<State> states) {
		this.states = states;
	}



	public ArrayList<Transition> getTransitions() {
		return Transitions;
	}



	public void setTransitions(ArrayList<Transition> transitions) {
		Transitions = transitions;
	}

	public Transition getTransitionsFromXtoY(String src, String dst) {
		Transition t = null; 
		for(int i= 0 ; i < this.Transitions.size() ; i++) {
			if(Transitions.get(i).getDst().getState_name().equals(dst) && Transitions.get(i).getSrc().getState_name().equals(src) ) {
				t= Transitions.get(i);
			}
		}
		return t;
	}

	public State findStateByName(String state_name) {
		for(int i = 0; i < this.states.size(); i++ ) {
			if(this.states.get(i).getState_name().equals(state_name)) {
				return this.states.get(i);
			}
		}
		return null;
	}

	public void addGuardToListOfGaurds(Guard guard) {
		this.guards.add(guard);
	}

}
