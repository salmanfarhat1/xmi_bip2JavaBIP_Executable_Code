package BIP_tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Component {
	
	private String name; 
	private State initial_state; 
	private ArrayList<State> states;
	private ArrayList<Transition> Transitions;

	
	public Component(String name, State initial_state, ArrayList<State> states, ArrayList<Transition> transitions) {
		this.name = name;
		this.initial_state = initial_state;
		this.states = states;
		Transitions = transitions;
	}


	
//	import org.javabip.annotations.ComponentType;
//	import org.javabip.annotations.Data;
//	import org.javabip.annotations.Guard;
//	import org.javabip.annotations.Port;
//	import org.javabip.annotations.Ports;
//	import org.javabip.annotations.Transition;
//	import org.javabip.api.PortType;
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		State S1 = new State("B");
		State S2 = new State("C");
		State S3 = new State("D");
		State S4 = new State("E");
		ArrayList<State> states_1 = new ArrayList<State>();
		states_1.add(S1);
		states_1.add(S2);
		states_1.add(S3);
		states_1.add(S4);
		Component c = new Component("component_1", new State("init") , states_1, null);
		System.out.println(c.toString());
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
				component_specification.append("\t@Port(name = " + name+ "_ports."+ name+ "_p_"+ Transitions.get(i).getTransition_name() + " , type = PortType.enforceable ),");
				component_specification.append(System.lineSeparator());
			}
			else {
				component_specification.append("\t@Port(name = " + name+ "_ports."+ name+ "_p_"+ Transitions.get(i).getTransition_name() + " , type = PortType.enforceable )");
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
		
		
		component_specification.append("\tpublic "+name+"_spec(){}");
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
			
			component_specification.append("@Transition(name =" + name +"_ports."+ pre_port_name + Transitions.get(i).getTransition_name() +", source = "+name +"_states."+pre_state_name+Transitions.get(i).getSrc().getState_name() + ", target = "+name +"_states."+pre_state_name+Transitions.get(i).getDst().getState_name() + ")");
			component_specification.append(System.lineSeparator());	
			
			component_specification.append("\tpublic void trans_"+Transitions.get(i).getSrc().getState_name()+"_to_"+Transitions.get(i).getDst().getState_name()+"(){");
			
			component_specification.append(System.lineSeparator());	
			component_specification.append("\t\tSystem.out.println( \"component name: "+name +" from :" +   Transitions.get(i).getSrc().getState_name()+ "---> " + Transitions.get(i).getDst().getState_name()+"\");");
			component_specification.append(System.lineSeparator());

			component_specification.append("\t}");	

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
	
	

	@Override
	public String toString() {
		return "Component [name=" + name  + "\n, initial_state=" + initial_state + "\n, states=" + states + "\n, Transitions="
				+ Transitions + "]";
		
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

}
