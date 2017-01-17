package Main;

import java.util.HashMap;
import java.util.Map;

public class AssignmentObject {

	private String name;
	private Map<Property, Double> propertyValues;
	
	public AssignmentObject(String name) {
		this.name = name;
		propertyValues = new HashMap<Property, Double>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
