package BIP_tools;

import java.util.ArrayList;

public class Connector_Motif {
	private String connector_id;
	private ArrayList<Transition> relationsList;

	public Connector_Motif(String id) {
		this.connector_id = id;
		this.relationsList = new ArrayList<Transition>();
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
		return "\n\nConnector_Motif \n[\nconnector_id=" + connector_id + "\nRelationsList=" + relationsList + "\n]";
	}

	public void setRelationsList(ArrayList<Transition> relationsList) {
		this.relationsList = relationsList;
	} 
	

}
