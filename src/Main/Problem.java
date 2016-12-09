package Main;

import java.util.ArrayList;
import java.util.List;

public class Problem {

	private List<Constraint> constraints;
	
	public Problem() {
		constraints = new ArrayList<Constraint>();
	}
	
	public void addConstraint(Constraint c) {
		constraints.add(c);
	}
	
	public void removeConstraint(Constraint c) {
		if (constraints.contains(c)) constraints.remove(c);
	}
	
}
