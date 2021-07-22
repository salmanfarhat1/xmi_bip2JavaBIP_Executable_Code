package BIP_tools;

import java.util.ArrayList;

public class Main_Component {
	ArrayList<Component> components_arraylist;
	ArrayList<Connector_Motif> connectorsList;
	
	public Main_Component(ArrayList<Component> components_arraylist, ArrayList<Connector_Motif> connectorsList) {
		this.components_arraylist = components_arraylist;
		this.connectorsList = connectorsList;
	}
	
	
	public ArrayList<Component> getComponents_arraylist() {
		return components_arraylist;
	}
	
	public void setComponents_arraylist(ArrayList<Component> components_arraylist) {
		this.components_arraylist = components_arraylist;
	}
	
	public ArrayList<Connector_Motif> getConnectorsList() {
		return connectorsList;
	}
	
	public void setConnectorsList(ArrayList<Connector_Motif> connectorsList) {
		this.connectorsList = connectorsList;
	}
	
	
	public ArrayList<State> CreateStates(ArrayList<State> states_arr){
		ArrayList<State> new_states = new ArrayList<State>();
		for(int i = 0 ; i < states_arr.size() ; i++ ) {			
			new_states.add(new State("S" +states_arr.get(i).getState_name()));
			new_states.add(new State("R" +states_arr.get(i).getState_name()));
		}
		return new_states; 
	}
	
	private ArrayList<Transition> CreateTransitions(ArrayList<State> states_arr, Component c ) {
		ArrayList<Transition> new_trans = new ArrayList<Transition>();
		for(int i = 0 ; i < c.getStates().size() ; i++ ) {	// create spontoneous ports	for starting components 	
			if(c.getStates().get(i).getState_name().charAt(0) == 'S') {
				new_trans.add(new Spontaneous("Spon_"+c.getInitial_state().getState_name()+"_to_" + c.getStates().get(i).getState_name() , c.getInitial_state() ,  c.getStates().get(i), null ,c));
			}
		}
		
		for(int i = 0 ; i < c.getStates().size() ; i++ ) {	// create enforceable ports	for starting components 
			if(c.getStates().get(i).getState_name().charAt(0) == 'S') {
				new_trans.add(new Enforceable( c.getStates().get(i).getState_name() + "_to_" + c.getInitial_state().getState_name() ,  c.getStates().get(i) , c.getInitial_state() , null ,c));
		
			}
		}
		
		for(int i = 0 ; i < c.getStates().size() ; i++ ) {	// create spontoneous ports	for starting components 	
			if(c.getStates().get(i).getState_name().charAt(0) == 'R') {
				new_trans.add(new Spontaneous("Spon_"+c.getInitial_state().getState_name()+"_to_" + c.getStates().get(i).getState_name() , c.getInitial_state() ,  c.getStates().get(i), null ,c));
			}
		}
		
		for(int i = 0 ; i < c.getStates().size() ; i++ ) {	// create enforceable ports	for starting components 
			if(c.getStates().get(i).getState_name().charAt(0) == 'R') {
				new_trans.add(new Enforceable( c.getStates().get(i).getState_name() + "_to_" + c.getInitial_state().getState_name() ,  c.getStates().get(i) , c.getInitial_state() , null ,c));
		
			}
		}
		return new_trans;
	}
	
	
	public State getStateByName(Component main_controller_component, String state_name, char ch) {
		State s = null; 
		String complete_stat_name = ch+ state_name; 

		for(int i =0 ;  i < main_controller_component.getStates().size() ; i++) {
			if(complete_stat_name.equals(main_controller_component.getStates().get(i).getState_name())) {
				s = main_controller_component.getStates().get(i);
				

			}
		}
		return s;
	}
	
	
	private Transition getTransitionThatStartsFromStateX(Component main_controller_component, State s) {
		Transition t=null; 
		
		for(int i = 0; i < main_controller_component.getTransitions().size(); i++) {
			if(main_controller_component.getTransitions().get(i).getSrc().getState_name().equals(s.getState_name())) {
				 t = main_controller_component.getTransitions().get(i);
			}
		}
		return t;
	}
	
	private Transition getTransitionThatLeadsToStateX(Component main_controller_component, State s) {
		Transition t=null; 
		ArrayList<Transition> arr = new ArrayList<Transition>();
		for(int i = 0; i < main_controller_component.getTransitions().size(); i++) {
			if(main_controller_component.getTransitions().get(i).getDst().getState_name().equals(s.getState_name())) {
				 t = main_controller_component.getTransitions().get(i);
				 arr.add(t);
			}
		}
//		System.out.println("Start ");
		for(int i = 0; i < arr.size() ; i++) {
			if(arr.get(i).getTransition_name().split("_to_").length>1) {
//				System.out.println(arr.get(i).getTransition_name().split("_to_")[0] +"  "+arr.get(i).getTransition_name().split("_to_")[1]);
				if(!s.getState_name().equals(arr.get(i).getTransition_name().split("_to_")[0]) ) {
					 t=arr.get(i);
//					 System.out.println(arr.get(i).getTransition_name().split("_to_")[0]);		
				}
			}
		}
//		System.out.println("End");
		return t;
	}
	
	private Transition getTransitionThatLeadsToStateXFromStateY(Component com, State s, String src) {
		Transition t=null; 
		ArrayList<Transition> arr = new ArrayList<Transition>();
		for(int i = 0; i < com.getTransitions().size(); i++) {
			if(com.getTransitions().get(i).getDst().getState_name().equals(s.getState_name()) &&
					com.getTransitions().get(i).getSrc().getState_name().equals(src)) {
				 t = com.getTransitions().get(i);
				 arr.add(t);
			}
		}

//		System.out.println("End");
		return t;
	}
	
	
	public ArrayList<Connector_Motif> GenerateConnectors(Component main_controller_component) {
		
		// system.out.println(main_controller_component.toString());
		ArrayList<Connector_Motif> conn_list = new ArrayList<Connector_Motif>();
		Connector_Motif conn_S;
		Connector_Motif conn_R;
		Component c; 
		String state_name; 
		State S_state;
		State R_state; 
		Transition T1;
		Transition T2; 
		for(int i = 0; i < components_arraylist.size() ; i++) {
			conn_S = new Connector_Motif(null);
			conn_R = new Connector_Motif(null);
			c = components_arraylist.get(i);
			if(c.getUsableComponent() == true) { // these are the usable components 
				// system.out.println("Do the operation " +c.getStates().size());
				
				if(c.getStates().size() == 1) { // leaf components that only have one transition
					state_name = c.getStates().get(0).getState_name(); // there is only one state 
					
					S_state = getStateByName(main_controller_component, state_name, 'S');
					
					T1 = getTransitionThatStartsFromStateX(main_controller_component, S_state);
					T1.setSynchron(true);
					// system.out.println("state name :"+ T1);
					
					T2 = getTransitionThatLeadsToStateX(c, c.getStates().get(0));
					// system.out.println("state name :"+ T2);
					T2.setSynchron(true);
					conn_S.setConnector_id(T1.getTransition_name()+"_______"+T2.getTransition_name());
					conn_S.addEnd(T1);
					conn_S.addEnd(T2);
					conn_list.add(conn_S);
					
					
					R_state = getStateByName(main_controller_component, state_name, 'R');
					T1 = getTransitionThatStartsFromStateX(main_controller_component, R_state); //I will take the transitio that leads to Init from RGPS
					T1.setSynchron(true);
					
					T2 = getTransitionThatLeadsToStateX(c, c.getInitial_state());
					// system.out.println("state name :"+ T2);
					T2.setSynchron(true);
					
					conn_R.setConnector_id(T1.getTransition_name()+"_______"+T2.getTransition_name());
					conn_R.addEnd(T1);
					conn_R.addEnd(T2);
					conn_list.add(conn_R);
				}
				else{
					for(int j = 0 ; j < c.getStates().size() ; j++) {
						conn_S = new Connector_Motif(null);
						conn_R = new Connector_Motif(null);
						
						state_name = c.getStates().get(j).getState_name(); // for each state in the component
						S_state = getStateByName(main_controller_component, state_name, 'S');
						T1 = getTransitionThatStartsFromStateX(main_controller_component, S_state);
						T1.setSynchron(true);
//						System.out.println("state name :"+ T1);
//						 
//							
						T2 = getTransitionThatLeadsToStateX(c, c.getStates().get(j));
//						System.out.println("state name :"+ T2);
						 
						T2.setSynchron(true);
						conn_S.setConnector_id(T1.getTransition_name()+"_______"+T2.getTransition_name());
						conn_S.addEnd(T1);
						conn_S.addEnd(T2);
						conn_list.add(conn_S);
						
						R_state = getStateByName(main_controller_component, state_name, 'R'); //RGPS taking the state that
//						System.out.println("state name :"+ R_state);
						T1 = getTransitionThatStartsFromStateX(main_controller_component, R_state); //I will take the transition that leads to Init from RGPS
						T1.setSynchron(true);
						
						T2 = getTransitionThatLeadsToStateXFromStateY(c, c.getInitial_state(),state_name);
//						System.out.println("state name :"+ T2);
						T2.setSynchron(true);
						
						conn_R.setConnector_id(T1.getTransition_name()+"_______"+T2.getTransition_name());
						conn_R.addEnd(T1);
						conn_R.addEnd(T2);
						conn_list.add(conn_R);
					}
				} 
			}
		}
		return conn_list;
	}



	public Component CreateMainComponent() {
//		System.out.println("******************************************************************************************************************************************************************************");
//		System.out.println("************************************************************** System Description ********************************************************************************");
//		System.out.println("******************************************************************************************************************************************************************************");
//		
//		
//		for(int i = 0; i < this.components_arraylist.size();i++) {
//			System.out.println(components_arraylist.get(i).toString());
//		}
//		
//		for(int i = 0; i < this.connectorsList.size();i++) {
//			System.out.println(connectorsList.get(i).toString()	);
//		}
//		
//		System.out.println("******************************************************************************************************************************************************************************");
//		System.out.println("********************************************************************************************************************************************************");
//		System.out.println("******************************************************************************************************************************************************************************");
		
		//Component( name,  optional,  usableComponent,  State initial_state, ArrayList<State> states, ArrayList<Transition> transitions) {

		Component Main_Controller = new Component("Main_Controller", false, false, null, null, null);
		
		Main_Controller.setInitial_state(new State("init"));
		
		ArrayList<State> mainControllerStates = new ArrayList<State>();
		for(int i =0 ; i < components_arraylist.size(); i++) {
			if(components_arraylist.get(i).getUsableComponent() == true) {
				for(int  j = 0; j < components_arraylist.get(i).getStates().size(); j++ ) {
					mainControllerStates.add(components_arraylist.get(i).getStates().get(j));
				}
			}
		}
		Main_Controller.setStates(CreateStates(mainControllerStates)); // create states for the main component
			
		
//		ArrayList<Transition> mainControllerTransitions = new ArrayList<Transition>();
		Main_Controller.setTransitions(CreateTransitions(mainControllerStates, Main_Controller));
		
//		Main_Controller.GenerateCode();
//		
		
		
//		System.out.println("printing main controller details");
		//System.out.println(Main_Controller.toString());
		
		return Main_Controller; // returning the main component specifications state/ports/spec

	}



}
