package Code_Generatation;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import BIP_tools.Component;
import BIP_tools.Enforceable;
import BIP_tools.Guard;
import BIP_tools.Guard_Exclude;
import BIP_tools.Guard_Implies;
import BIP_tools.Main_Component;
import BIP_tools.Connector_Motif;
import BIP_tools.State;
import BIP_tools.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Generate_JavaBIP_Code {
	
	public static final String state = "states_link_to_State_Base";  
	public static final String ends = "ends";  

	public static final String initial_state = "initial_s";  
	public static final String state_name = "state_name";  

	public static final String transition = "connectors";
	public static final String transition_name = "transition_name";
	public static final String transition_src = "src";
	public static final String transition_dst = "dst";

	public static final String component = "component";  
	
	// kind of initialization to deal with my xml file
	public static Element Get_JavaBIP_root_element(Document doc) {
//		System.out.println("Root element -> " + doc.getDocumentElement().getNodeName());

		NodeList First_tag_xmi_List = doc.getElementsByTagName("xmi:XMI"); // retrieving <xmi:XMI>...<xmi:XMI/> 
//		System.out.println("----------------------------" + First_tag_xmi_List.getLength());  
		Node First_Node_inside_xmi_XMI_tag = First_tag_xmi_List.item(0); // returns the Java_BIP_project     
		Element eElement_xmi_XMI = (Element) First_Node_inside_xmi_XMI_tag;   
		NodeList nList_2 = eElement_xmi_XMI.getElementsByTagName("Java_BIP_project"); // retrieving <Java_BIP_project>...<Java_BIP_project/>      
		return (Element) nList_2.item(0);
	}
	
	
	// return the initial state of a component
	public static State Get_Initial_State(Node initial_state_node) {
		Element initial_state_element = (Element) initial_state_node;
		String init_state_name = initial_state_element.getAttribute(state_name);
		
		return new State(init_state_name);
	}
	
	// return states for a component c
	public static ArrayList<State> Get_States(NodeList states_list) {
		int j; 
		ArrayList<State> states_arraylist = new ArrayList<State>();
		State s1;
		//StringBuilder str = new StringBuilder();
		
		for(j = 0; j < states_list.getLength(); j++) {
			
			Node state_node = states_list.item(j);
			Element state_element = (Element) state_node;
			s1 = new State(state_element.getAttribute(state_name));
			states_arraylist.add(s1);
			//System.out.println("state "+ (j+1) + " :" + state_element.getAttribute(state_name) );
		}
		
		//PrintStates(states_arraylist);
		return states_arraylist;
	}
	
	// Printing states  
	public static void PrintStates(ArrayList<State> states_arraylist) {
		for(int i = 0; i < states_arraylist.size(); i++) {
			System.out.println("state "+(i+1)+ " ----> "+ states_arraylist.get(i).getState_name());
		}
	}
	
	// return the Transition (enforceable transitiions (tans_name: name, src: state , dst: state))
	public static Transition Get_Transition(ArrayList<State> states_arraylist,Element transition_element, State init_state, char last_character_of_the_source_state,char last_character_of_the_destination) {
		Transition t;
		
		
		if(!Character.isDigit(last_character_of_the_source_state)) { // initial state is the init
			if(Character.isDigit(last_character_of_the_destination)) {
				int number_of_dst_state = Character.getNumericValue(last_character_of_the_destination);
				 t = new Enforceable(transition_element.getAttribute(transition_name), init_state, states_arraylist.get(number_of_dst_state),null, null);
			}
			else {
				t = new Enforceable(transition_element.getAttribute(transition_name), init_state, init_state,null, null);

			}
		}
		else {
			int number_of_src_state = Character.getNumericValue(last_character_of_the_source_state);

			if(Character.isDigit(last_character_of_the_destination)) {
				int number_of_dst_state = Character.getNumericValue(last_character_of_the_destination);
				t = new Enforceable(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), states_arraylist.get(number_of_dst_state),null, null);
			}
			else {
				t = new Enforceable(transition_element.getAttribute(transition_name), states_arraylist.get(number_of_src_state), init_state,null, null);

			}
		}
		return t;
	}


	// Create and return the transitions (enforceable transitiions (tans_name: name, src: state , dst: state))
	public static ArrayList<Transition> Get_transitions(NodeList transitions_list, ArrayList<State> states_arraylist , State init_state) {
		int j; 
		ArrayList<Transition> transitions_arraylist = new ArrayList<Transition>();
		
		for(j = 0; j < transitions_list.getLength(); j++) {
			Node transition_node = transitions_list.item(j);
			Element transition_element = (Element) transition_node;
			//system.out.println("Transition "+ (j) + " :" + transition_element.getAttribute(transition_name) );
			//system.out.println("src: " + transition_element.getAttribute(transition_src).charAt(transition_element.getAttribute(transition_src).length() - 1) );
			//system.out.println("dst: " + transition_element.getAttribute(transition_dst).charAt(transition_element.getAttribute(transition_dst).length() - 1) );
			
			
			// I take the last character to get the index of the destination state (in my xml format I have it like that )
			char last_character_of_the_destination= transition_element.getAttribute(transition_dst).charAt(transition_element.getAttribute(transition_dst).length() - 1);
			char last_character_of_the_source_state= transition_element.getAttribute(transition_src).charAt(transition_element.getAttribute(transition_src).length() - 1);

			// to get the tranistion with the right source and destination
			transitions_arraylist.add(Get_Transition(states_arraylist, transition_element,  init_state,  last_character_of_the_source_state, last_character_of_the_destination));
		}
		return transitions_arraylist;
	}
	
	private static Component findComponentByName(String com_name, ArrayList<Component> components_list) {
		for(int i =0; i < components_list.size(); i++) {
			if(com_name.equals(components_list.get(i).getName())) {
				return components_list.get(i);
			}
		}
		return null;
	}
	


	
	
	private static ArrayList<Guard> Get_guards(NodeList guard_list, ArrayList<Component> components_list, Component main_controller_component) {
		ArrayList<Guard> guards_arr = new ArrayList<Guard>();
		Guard guard = null;
		Component srcCom;
		Component dstCom; 
		State srcState; 
		State dstState; 

		for(int i = 0;  i < guard_list.getLength(); i++ ) {
			Node guard_node = guard_list.item(i);
			Element guard_element = (Element) guard_node;
//			System.out.println("guard name: "+guard_element.getAttribute("name"));
//			System.out.println("guard method :"+guard_element.getAttribute("guardMethod"));
//			Example of the name: Implies___srcComponent:Camera___dstComponnt:Screen
			if(guard_element.getAttribute("name").split("___")[0].equals("Implies")) { // check if it is an Implies guard
				// taking Camera
				guard = new Guard_Implies(null, null, null, null, null, null,null);
				guard.setName(guard_element.getAttribute("name"));
				guard.setGuardMethod(guard_element.getAttribute("guardMethod"));
//				System.out.println("hey ya khara shuf : " +guard_element.getAttribute("name") );
				srcCom = findComponentByName(guard_element.getAttribute("name").split("___")[1].split(":")[1], components_list); 		
				dstCom = findComponentByName(guard_element.getAttribute("name").split("___")[2].split(":")[1], components_list); // taking Camera		
				((Guard_Implies) guard).setSrcComponent(srcCom);
				((Guard_Implies) guard).setDstComponent(dstCom);
//				System.out.println("component printing : "+dstCom);
//				Example of guard method 
//				srcFeature:Camera___dstFeature:High_Resolution___srcTransition:init_to_Camera___dstTransitionHigh_Resolution_to_High_Resolution

				srcState = srcCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[0].split(":")[1]); 
				dstState = dstCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[1].split(":")[1]); 
				((Guard_Implies) guard).setSrcState(srcState);
				((Guard_Implies) guard).setDstState(dstState);
//				System.out.println("State printing : "+srcState);
				guards_arr.add(guard);	
				main_controller_component.addGuardToListOfGaurds(guard);
			}
			else if(guard_element.getAttribute("name").split("___")[0].equals("Exclude")) {
				guard = new Guard_Exclude(null, null, null, null, null, null,null);
				guard.setName(guard_element.getAttribute("name"));
				guard.setGuardMethod(guard_element.getAttribute("guardMethod"));
				System.out.println("Exclude stuff : " +guard_element.getAttribute("name") );
				srcCom = findComponentByName(guard_element.getAttribute("name").split("___")[1].split(":")[1], components_list); 		
				dstCom = findComponentByName(guard_element.getAttribute("name").split("___")[2].split(":")[1], components_list); 
				((Guard_Exclude) guard).setSrcComponent(srcCom);
				((Guard_Exclude) guard).setDstComponent(dstCom);
				
				srcState = srcCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[0].split(":")[1]); 
				dstState = dstCom.findStateByName(guard_element.getAttribute("guardMethod").split("___")[1].split(":")[1]); 
				((Guard_Exclude) guard).setSrcState(srcState);
				((Guard_Exclude) guard).setDstState(dstState);
				
				guards_arr.add(guard);	
				main_controller_component.addGuardToListOfGaurds(guard);
				
			}
		}
		
		return guards_arr;
	}


	public static Connector_Motif GetConnectorMotifs(NodeList endsList ,ArrayList<Component> components_arraylist, Element connectorMotif_element, Component main_controller_component){
		
		Connector_Motif connector_instance  = new Connector_Motif(connectorMotif_element.getAttribute("connector_id").toString());
		ArrayList<Transition> transitionList = new ArrayList<Transition>();
		int indexOfTheComponent, indexOfTheTransition; 
		for(int i = 0 ; i < endsList.getLength(); i++) {
			
			Node endNode = endsList.item(i);
			Element endElement = (Element) endNode;
//			System.out.println("end "+ (i) + " :" + endElement.getAttribute("xsi:type") );
//			System.out.println("end "+ (i) + " :" + endElement.getAttribute("description") );
//			System.out.println("end "+ (i) + " :" + endElement.getAttribute("one_enforceable") );
//			System.out.println("end "+ (i) + " :" + endElement.getAttribute("one_enforceable").lastIndexOf("@components.") );
			
			String[] sentences = endElement.getAttribute("one_enforceable").split("@components.");
			indexOfTheComponent = Character.getNumericValue(sentences[1].charAt(0));
			String[] sentences_2 = sentences[1].split("connectors.");
			indexOfTheTransition = Character.getNumericValue(sentences_2[1].charAt(0));
			if(endElement.getAttribute("xsi:type").equals("Trigger")) {
//				System.out.print("this is Trigger");
				components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition).setSynchron(true);
			}
			else {
				components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition).setSynchron(true);
			}
//			System.out.println("Component number :"+ indexOfTheComponent + " Index of the transition :"+ indexOfTheTransition);
//			System.out.println("ssss : "+ components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition).toString());
			transitionList.add(components_arraylist.get(indexOfTheComponent).getTransitions().get(indexOfTheTransition));
		}
		String parts[]; 
		String S = connector_instance.getConnector_id(); 
		parts = S.substring(8, S.length() ).split("__________"); // init_to_Camera__________init_to_High_Resolution

//		System.out.println("Array : " + parts[0].split("_to_")[1]); // camera 

		Transition t = main_controller_component.getTransitionsFromXtoY("S" + parts[0].split("_to_")[1] ,main_controller_component.getInitial_state().getState_name() );
		transitionList.add(t); // adding the transition from the main component
		//		String S = t.getTransition_name();
	
		connector_instance.setRelationsList(transitionList);
//		System.out.println("connector_id ----- : "+connector_instance.toString() );
		return connector_instance;
	}
	

	public static void GenerateGlue(ArrayList<Connector_Motif> connectorsList) {
//		import org.javabip.api.BIPGlue;
//		import org.javabip.glue.GlueBuilder;
//
//		import Exclude_FM_To_JavaBIP.*;
//		import Screen_Component.Screen_ports;
//		import Screen_Component.Screen_spec;
		
		
		StringBuilder glueCode = new StringBuilder();
		
		glueCode.append("package Output;");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("import org.javabip.api.BIPGlue;");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("import org.javabip.glue.GlueBuilder;");
		glueCode.append(System.lineSeparator());
		
//		glueCode.append("import Output.*");
//		glueCode.append(System.lineSeparator());



//public class Exclude_Glue {
//	private BIPGlue bipGlue;
//	
//	public Exclude_Glue(BIPGlue bipGlue) {
//		this.bipGlue = bipGlue;
//	}
//	
//	public Exclude_Glue() {
//		this.bipGlue = init();
//	}
//	
//	public BIPGlue getBipGlue() {
//		return bipGlue;
//	}
//
//	public void setBipGlue(BIPGlue bipGlue) {
//		this.bipGlue = bipGlue;
//	}
		
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());

		
		glueCode.append("public class Project_Glue{");
		glueCode.append(System.lineSeparator());
		glueCode.append("\tprivate BIPGlue bipGlue;");
		glueCode.append(System.lineSeparator());
		glueCode.append(System.lineSeparator());

		glueCode.append("\tpublic Project_Glue(BIPGlue bipGlue){");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tthis.bipGlue = bipGlue;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		

		glueCode.append(System.lineSeparator());

		
		glueCode.append("\tpublic Project_Glue(){");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tthis.bipGlue = init();");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		
		
		glueCode.append(System.lineSeparator());

		
		glueCode.append("\tpublic BIPGlue getBipGlue(){");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\treturn this.bipGlue;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		
		
		glueCode.append(System.lineSeparator());

		
		glueCode.append("\tpublic void setBipGlue(BIPGlue bipGlue){");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t\tthis.bipGlue = bipGlue;");
		glueCode.append(System.lineSeparator());
		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		
		
	
		
//private BIPGlue init () {
//	return bipGlue = new GlueBuilder() {
//		@Override
//		public void configure() {
		
		
		glueCode.append("\tprivate BIPGlue init () {");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("\t\treturn bipGlue = new GlueBuilder() {");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("\t\t\t@Override");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("\t\t\tpublic void configure() {");
		glueCode.append(System.lineSeparator());
		
		
		
		

		
//		// *********************************************************************************************************************************
//		port(Screen_spec.class, Screen_ports.screen_p_high_resolution).requires(Hold_exclude.class , "hold_exclude_p_state_3_to_state_4_high" );
//		port(Hold_exclude.class , "hold_exclude_p_state_3_to_state_4_high" ).requires(Screen_spec.class, Screen_ports.screen_p_high_resolution);
//
//		port(Screen_spec.class, Screen_ports.screen_p_high_resolution).accepts(Hold_exclude.class , "hold_exclude_p_state_3_to_state_4_high" );
//		port(Hold_exclude.class , "hold_exclude_p_state_3_to_state_4_high" ).accepts(Screen_spec.class, Screen_ports.screen_p_high_resolution);

		// *********************************************************************************************************************************
		
		
		Transition transition_base;
		Transition temp_transition;
//		StringBuilder acceptsbuilder  = new StringBuilder();
		
		for(int i = 0 ; i < connectorsList.size() ; i++ ) { // on all connector motifs that exist
			glueCode.append(System.lineSeparator());
			glueCode.append(System.lineSeparator());
			glueCode.append(System.lineSeparator());
			for(int j = 0 ; j < connectorsList.get(i).getRelationsList().size();j++) {// to for each end should be connected to all other ends 
				transition_base =  connectorsList.get(i).getRelationsList().get(j); // for motif i we take end j 
				
				if( transition_base.getSynchron() == true) { // this is a synchron transition
					
// 					For Require				
					glueCode.append("\t\t\t\tport(" + transition_base.getComponentOfTheTransition().getName()+"_spec.class, "+transition_base.getComponentOfTheTransition().getName()+ "_ports."+transition_base.getComponentOfTheTransition().getName()+"_p_"+transition_base.getTransition_name()+").requires(");
					for(int k =0 ; k < connectorsList.get(i).getRelationsList().size() ; k++ ) { // list of ends transitions that are engaged in the transaction 					
						if(j != k) {
							temp_transition = connectorsList.get(i).getRelationsList().get(k);
							
								
							glueCode.append(temp_transition.getComponentOfTheTransition().getName()+"_spec.class, "+temp_transition.getComponentOfTheTransition().getName()+ "_ports."+temp_transition.getComponentOfTheTransition().getName()+"_p_"+temp_transition.getTransition_name() + ", ");
							//glueCode.append(System.lineSeparator());
						}	
					}
					glueCode.replace(glueCode.length()-2, glueCode.length(), "");
					glueCode.append(");");
					glueCode.append(System.lineSeparator());
					
					
					
// 					For accepts
					glueCode.append("\t\t\t\tport(" + transition_base.getComponentOfTheTransition().getName()+"_spec.class, "+transition_base.getComponentOfTheTransition().getName()+ "_ports."+transition_base.getComponentOfTheTransition().getName()+"_p_"+transition_base.getTransition_name()+").accepts(");
					for(int k =0 ; k < connectorsList.get(i).getRelationsList().size() ; k++ ) { // list of ends transitions that are engaged in the transaction 					
						if(j != k) {
							temp_transition = connectorsList.get(i).getRelationsList().get(k);
							
								
							glueCode.append(temp_transition.getComponentOfTheTransition().getName()+"_spec.class, "+temp_transition.getComponentOfTheTransition().getName()+ "_ports."+temp_transition.getComponentOfTheTransition().getName()+"_p_"+temp_transition.getTransition_name() + ", ");
							//glueCode.append(System.lineSeparator());
						}	
					}
					glueCode.replace(glueCode.length()-2, glueCode.length(), "");
					glueCode.append(");");
					glueCode.append(System.lineSeparator());
				
				}
				else { // for trigger
//					For require
					for(int k =0 ; k < connectorsList.get(i).getRelationsList().size() ; k++ ) { // list of ends transitions that are engaged in the transaction 
						if(j != k) {
							temp_transition = connectorsList.get(i).getRelationsList().get(k);
							glueCode.append("\t\t\t\tport("+ transition_base.getComponentOfTheTransition().getName()+"_spec.class, "+transition_base.getComponentOfTheTransition().getName()+ "_ports."+transition_base.getComponentOfTheTransition().getName()+"_p_"+transition_base.getTransition_name()+")");
							glueCode.append(".requires();");
							glueCode.append(System.lineSeparator());
		
						}
					} 
					
//					For accepts
					glueCode.append("\t\t\t\tport(" + transition_base.getComponentOfTheTransition().getName()+"_spec.class, "+transition_base.getComponentOfTheTransition().getName()+ "_ports."+transition_base.getComponentOfTheTransition().getName()+"_p_"+transition_base.getTransition_name()+").accepts(");
					for(int k =0 ; k < connectorsList.get(i).getRelationsList().size() ; k++ ) { // list of ends transitions that are engaged in the transaction 					
						if(j != k) {
							temp_transition = connectorsList.get(i).getRelationsList().get(k);
							
								
							glueCode.append(temp_transition.getComponentOfTheTransition().getName()+"_spec.class, "+temp_transition.getComponentOfTheTransition().getName()+ "_ports."+temp_transition.getComponentOfTheTransition().getName()+"_p_"+temp_transition.getTransition_name() + ", ");
							//glueCode.append(System.lineSeparator());
						}	
					}
					glueCode.replace(glueCode.length()-2, glueCode.length(), "");
					glueCode.append(");");
					glueCode.append(System.lineSeparator());
				} 
			}
			
		}


		glueCode.append("\t\t\t}");
		glueCode.append(System.lineSeparator());
		
		glueCode.append("\t\t}.build();");
		glueCode.append(System.lineSeparator());

		glueCode.append("\t}");
		glueCode.append(System.lineSeparator());
		
		glueCode.append(System.lineSeparator());
		glueCode.append("}");
		
		
		try (FileOutputStream oS = new FileOutputStream(new File("./Output/"+"Project_Glue.java"))) {
			oS.write(glueCode.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
//	public static Component getParentComponent(Transition t) {}
	private static ArrayList<Connector_Motif> filterConnectorList(ArrayList<Connector_Motif> connectorsList,ArrayList<Connector_Motif> connectorsList_2) {
		// TODO Auto-generated method stub
		ArrayList<Connector_Motif> conn_list = new ArrayList<>();
		String[] parts;
		String S; 
		for(int i= 0 ; i < connectorsList.size(); i++) {
			
			if("Implies".equals(connectorsList.get(i).getConnector_id().substring(0, 7))) {
				S = connectorsList.get(i).getConnector_id();
				parts = S.substring(8, S.length() ).split("__________"); // init_to_Camera__________init_to_High_Resolution

//				System.out.println("Array : " + parts[0].split("_to_")[1]); // part[0] -> init_to_Camera ,,, ,parts[0].split("_to_")[1] --- > camera
				for(int j = 0 ; j < connectorsList_2.size(); j++) {
					if(!connectorsList_2.get(j).getConnector_id().contains(parts[0].split("_to_")[1])) {
						conn_list.add(connectorsList_2.get(j));
					}
				}
			}
			
		}
		return conn_list; 
	}
	public static void main(String[] args){
		int i,j;
		ArrayList<Component> components_arraylist = new ArrayList<Component>();
		try {
			 // creating a document builder object
			File inputFile = new File("/Users/salmanfarhat/eclipse-workspace/XMI_to_JavaBIP_Transformation/Input/ATL_NEW_TRANS_ssnew.xmi");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			 
			//	         Read the XML file to Document object.
			Document doc = dBuilder.parse(inputFile);
			 
			//	         (bring or return to a normal or standard condition or state) return a tree with the root element
			doc.getDocumentElement().normalize();
			 
			 
			// 			getting the Java_BIP_project Element
			Element eElement_Java_BIP_project = Get_JavaBIP_root_element(doc);
			 

			NodeList components_list = eElement_Java_BIP_project.getElementsByTagName("components");
			//system.out.println("number of components : " + eElement_Java_BIP_project.getElementsByTagName("components").getLength());
			Component c1;
			for( i = 0; i < components_list.getLength() ; i++) {
				c1 = new Component(null,null, null, null, null, null);
				Node component_node = components_list.item(i);
				if (component_node.getNodeType() == Node.ELEMENT_NODE)
				{
					    //Print each employee's detail
					Element component_element = (Element) component_node; 
					
					//system.out.println("Component name : "    + component_element.getAttribute("name"));
					c1.setName(component_element.getAttribute("name"));
					
					
					if(component_element.getAttribute("optional").equals("true")) {
						c1.setOptional(true);
					}else {
						c1.setOptional(false);
					}
					//system.out.println("optional : "    + c1.getOptional());
					
					if(component_element.getAttribute("usableComponent").equals("true")) {
						c1.setUsableComponent(true);
					}else {
						c1.setUsableComponent(false);
					}
					//system.out.println("Usable Component : "    + c1.getUsableComponent());

			
// ************************************************************** states **************************************************************
					NodeList states_list = component_element.getElementsByTagName(state);
					//system.out.println("number of states state: "+ (states_list.getLength()+1) );
					c1.setStates(Get_States(states_list)); 
// ************************************************************************************************************************************
					
// ************************************************************** initial state **************************************************************
					Node initial_state_node = component_element.getElementsByTagName(initial_state).item(0);
//					System.out.println("state 0 : "+ Get_Initial_State(initial_state_node));
					c1.setInitial_state(Get_Initial_State(initial_state_node));
// ************************************************************************************************************************************
					
// ************************************************************** transitions **************************************************************
					NodeList transitions_list = component_element.getElementsByTagName(transition);
					ArrayList<Transition> transitions_arraylist = Get_transitions(transitions_list ,  c1.getStates() ,c1.getInitial_state() );
					c1.setTransitions(transitions_arraylist);
					
					for(int h = 0 ; h < transitions_arraylist.size(); h++) {
						transitions_arraylist.get(h).setComponentOfTheTransition(c1);
					}

// ************************ Adding Component to the List ***************************************************************************
					components_arraylist.add(c1);
				 }
			 } 
			

// ************************************************************** guards **************************************************************
//			Creating main controller
			ArrayList<Connector_Motif> connectorsList = new ArrayList<>(); // Motifs from the one received from the ATL xml file 		
			Component main_controller_component;
			Main_Component main_component = new Main_Component(components_arraylist, connectorsList);
			main_controller_component = main_component.CreateMainComponent();  // return main component spec
			
			Component comp;
			ArrayList<Guard> guards_arraylist = new ArrayList<>();
			for( i = 0; i < components_list.getLength() ; i++) { // Loop on the list of components 
				
				Node component_node = components_list.item(i);
				if (component_node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element component_element = (Element) component_node;
					comp = findComponentByName(component_element.getAttribute("name"), components_arraylist);
//					System.out.println(comp.getName());
				// create guards after i have all components created
					NodeList guard_list = component_element.getElementsByTagName("guards"); // take all the guards for the component comp
					guards_arraylist = Get_guards(guard_list, components_arraylist, main_controller_component);
//					System.out.println(guards_arraylist);
					for(j =0 ; j < guards_arraylist.size(); j++) {
						guards_arraylist.get(j).setRelatedComponent(comp);
					}
					comp.setGuards(guards_arraylist);
//					main_controller_component.setGuards(guards_arraylist);
				}
			}
			
			
			for(i =0 ; i < components_arraylist.size(); i++) {
//				
				for(j =0 ; j < components_arraylist.get(i).getGuards().size(); j++) { // setting for each guard the main parent component
					System.out.println(components_arraylist.get(i).getName());
					System.out.println(components_arraylist.get(i).getGuards().get(j).toString());
				}
				
//				System.out.println("guards for component : "+ components_arraylist.get(i).getName()+ " "+ components_arraylist.get(i).getGuards().toString());
			}
//			System.out.println("5ara "+main_controller_component.getGuards().size());
			for(j =0 ; j < main_controller_component.getGuards().size(); j++) { // setting for each guard the main parent component
				System.out.println("Main_controller \n"+main_controller_component.getGuards().get(j).toString());
			}
// *****************************************************************generate code for components *******************************************************
			
			for(int l = 0; l < components_arraylist.size(); l++) {
				components_arraylist.get(l).GenerateCode();
				//System.out.println(components_arraylist.get(l).toString());
			}		
			main_controller_component.GenerateCode();
// ***************************************************************** Create Motifs *******************************************************
			

			components_arraylist.add(main_controller_component); // adding main component to the component array 

			Connector_Motif connector;
			
			NodeList connectorMotifsList = eElement_Java_BIP_project.getElementsByTagName("connector_motifs");
			//system.out.println("number of connector motifs : " + eElement_Java_BIP_project.getElementsByTagName("connector_motifs").getLength());


			for(i = 0 ; i < connectorMotifsList.getLength();i++) {
				Node connectorMotif_node = connectorMotifsList.item(i); // get the instances of connector_motifs each one by it self
				if (connectorMotif_node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element connectorMotif_element = (Element) connectorMotif_node;
					
//					System.out.println(" ppp pop opo po pop op opo po op op p po p  :" + connectorMotif_element.getAttribute("connector_id"));
					if(!connectorMotif_element.getAttribute("connector_id").split("_")[0].equals("Exclude")) {
						NodeList endsList = connectorMotif_element.getElementsByTagName(ends); // take the ends 
//						add the Motif to the List... GetConnectorMotifs Take the ends create the Motif
						connectorsList.add(GetConnectorMotifs(endsList , components_arraylist , connectorMotif_element, main_controller_component));
					}
					
						//do nothing for exclude
					
				}
			}
			
			
// ************************ Create Main Controller and generate code *******************************************************

			ArrayList<Connector_Motif> connectorsList_2 = new ArrayList<>();
			connectorsList_2 =  main_component.GenerateConnectors(main_controller_component); 
			
			// filter the arraylist of connectors by removing connectors that includes implies ports c
			connectorsList_2 = filterConnectorList(connectorsList, connectorsList_2); 
			
			connectorsList.addAll(connectorsList_2);
//			for(i =0  ; i <connectorsList_2.size();i++ ) {
//				connectorsList.add(connectorsList_2.get(i));
//			}
			

// ************************************** Generate Glue specifications *******************************************************
			GenerateGlue(connectorsList);
// ***************************************************************** System Description *******************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
			printSystemDescription( components_arraylist, connectorsList);
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
// ******************************************************************************************************************************
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	}




	public static void printSystemDescription(ArrayList<Component> components_arraylist,
			ArrayList<Connector_Motif> connectorsList) {
		// TODO Auto-generated method stub
		System.out.println("******************************************************************************************************************************************************************************");
		System.out.println("************************************************************** System Description ********************************************************************************");
		System.out.println("******************************************************************************************************************************************************************************");
		
		
		for( int i = 0; i < components_arraylist.size();i++) {
			System.out.println(components_arraylist.get(i).toString());
		}
		
		for(int  i = 0; i < connectorsList.size();i++) {
			System.out.println(connectorsList.get(i).toString()	);
		}
		
		
//		for( i = 0; i < connectorsList_2.size();i++) {
//			System.out.println(connectorsList_2.get(i).toString()	);
//		}
//		
		System.out.println("******************************************************************************************************************************************************************************");
		System.out.println("********************************************************************************************************************************************************");
		System.out.println("******************************************************************************************************************************************************************************");

		
	}
}
