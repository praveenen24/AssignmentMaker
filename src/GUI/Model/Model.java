package GUI.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import Main.AssignmentObject;
import Main.Constraint;
import Main.ObjectList;

public class Model extends Observable {
	
	private ObjectList objectList1;
	private ObjectList objectList2;
	private List<Constraint> constraints;
	private Map<String, Double> objectiveValues;

	public Model(String listName1, String listName2) {
		objectList1 = new ObjectList(listName1);
		objectList2 = new ObjectList(listName2);
		constraints = new ArrayList<Constraint>();
		objectiveValues = new HashMap<String, Double>();
	}
	
	public Model(ObjectList objectList1, ObjectList objectList2, List<Constraint> constraints) {
		this.objectList1 = objectList1;
		this.objectList2 = objectList2;
		this.constraints = constraints;
		objectiveValues = new HashMap<String, Double>();
	}
	
	public void setList1Name(String name) {
		objectList1.setName(name);
	}
	
	public String getList1Name() {
		return objectList1.getName();
	}
	
	public void addToList1(AssignmentObject object) {
		objectList1.add(object);
	}
	
	public void removeFromList1(AssignmentObject object) {
		objectList1.remove(object);
	}
	
	public ObjectList getObjectList1() {
		return objectList1;
	}
	
	public void setObjectList1(ObjectList objectList1) {
		this.objectList1 = objectList1;
		setChanged();
		notifyObservers("List1");
	}

	public void setList2Name(String name) {
		objectList2.setName(name);
	}
	
	public String getList2Name() {
		return objectList2.getName();
	}
	
	public void addToList2(AssignmentObject object) {
		objectList2.add(object);
	}
	
	public void removeFromList2(AssignmentObject object) {
		objectList2.remove(object);
	}
	
	public ObjectList getObjectList2() {
		return objectList2;
	}
	
	public void setObjectList2(ObjectList objectList2) {
		this.objectList2 = objectList2;
		setChanged();
		notifyObservers("List2");
	}
	
	public List<Constraint> getConstraints() {
		return constraints;
	}
	
	public void addConstraint(Constraint c) {
		constraints.add(c);
	}
	
	public void removeConstraint(Constraint c) {
		constraints.remove(c);
	}
	
	public Double getObjectiveValue(String key) {
		return objectiveValues.get(key);
	}
	
	public void putObjectiveValue(String key, Double value) {
		objectiveValues.put(key, value);
	}
	
	public Map<String, Double> getObjectiveValues() {
		return objectiveValues;
	}
	
	public void setObjectiveValues(Map<String,Double> objectiveValues) {
		this.objectiveValues = objectiveValues;
	}
}
