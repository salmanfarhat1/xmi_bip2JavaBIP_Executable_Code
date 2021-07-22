package BIP_tools;

import java.util.ArrayList;

public class Connector_Motif {
	private String connector_id;
	private ArrayList<Transition> relationsList;

	public Connector_Motif(String id) {
		this.connector_id = id;
		this.relationsList = new ArrayList<Transition>();
	}
	public void addEnd(Transition t) {
		
		relationsList.add(t);
	}
	public String getConnector_id() {
		return connector_id;
	}

	public void setConnector_id(String connector_id) {
		this.connector_id = connector_id;
	}

	public ArrayList<Transition> getRelationsList() {
		return relationsList;
	}

	@Override
	public String toString() {
		String S = "\n[\nMotif: connector_id=" + connector_id + "\n\tPorts Engaged in the connector: \n\t{\n"; 
		for(int i = 0 ; i < relationsList.size(); i++) {
			S += relationsList.get(i).toString();
		}
		S +="\t}";
		return S;
				
				//"\n[\nMotif: connector_id=" + connector_id + "\nPorts Engaged in the connector: \n{\n" + relationsList + "\n}";
	}

	public void setRelationsList(ArrayList<Transition> relationsList) {
		this.relationsList = relationsList;
	} 
	

}
