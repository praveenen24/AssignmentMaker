package Main;

import java.util.HashMap;

public class Constraint {
	private String name;
	private Bound bounds;
	private HashMap<Integer, Double> values;
	
	public Constraint(String name, Bound bounds) {
		this.name = name;
		this.bounds = bounds;
		values = new HashMap<Integer, Double>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Bound getBounds() {
		return bounds;
	}
	
	public void setBounds(Bound bounds) {
		this.bounds = bounds;
	}
	
	public void addValue(int coefficient, double d) {
		values.put(coefficient, d);
	}
	
	public HashMap<Integer, Double> getValues() {
		return values;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
