package BIP_tools;

public class Guard {
	private String name;
	private String guardMethod;
	private Component relatedComponent; 

	public Guard(String name, String guardMethod, Component relatedComponent) {

		this.name = name;
		this.guardMethod = guardMethod;
		this.relatedComponent = relatedComponent; 
	}

	public Component getRelatedComponent() {
		return relatedComponent;
	}

	public void setRelatedComponent(Component relatedComponent) {
		this.relatedComponent = relatedComponent;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGuardMethod() {
		return guardMethod;
	}
	public void setGuardMethod(String guardMethod) {
		this.guardMethod = guardMethod;
	} 
	
}
