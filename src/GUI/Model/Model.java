package GUI.Model;

import java.util.ArrayList;
import java.util.List;

import Main.AssignmentObject;
import Main.Constraint;
import Main.ObjectList;

public class Model {
	
	private ObjectList objectList1;
	private ObjectList objectList2;
	private List<Constraint> constraints;

	public Model(String listName1, String listName2) {
		objectList1 = new ObjectList(listName1);
		objectList2 = new ObjectList(listName2);
		constraints = new ArrayList<Constraint>();
	}
	
	public Model(ObjectList objectList1, ObjectList objectList2, List<Constraint> constraints) {
		this.objectList1 = objectList1;
		this.objectList2 = objectList2;
		this.constraints = constraints;
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
	
	public List<Constraint> getConstraints() {
		return constraints;
	}
	
	public void addConstraint(Constraint c) {
		constraints.add(c);
	}
	
	public void removeConstraint(Constraint c) {
		constraints.remove(c);
	}
}
