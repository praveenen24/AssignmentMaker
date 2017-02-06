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
	
	@Override
	public boolean equals(Object o) {
		AssignmentObject o1 = (AssignmentObject) o;
		if (this.toString().equals(o.toString())) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result * name.hashCode();
		result = 31 * result * propertyValues.hashCode();
		return result;
	}
}
