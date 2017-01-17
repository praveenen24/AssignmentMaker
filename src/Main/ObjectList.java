package Main;

import java.util.HashSet;

public class ObjectList extends HashSet<AssignmentObject> {
	private String name;
	
	public ObjectList(String name) {
		this.name = name;
	}

	public ObjectList(String name, HashSet<AssignmentObject> initialList) {
		this.name = name;
		addAll(initialList);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
