package Main;

import java.util.List;

public class CustomConstraint extends Constraint {

	private List<CustomConstraintVariable> variables;
	private CustomConstraintBound customBounds;
	
	public CustomConstraint(String name, CustomConstraintBound customBounds, List<CustomConstraintVariable> variables) {
		super(name, null);
		this.customBounds = customBounds;
		this.variables = variables;
	}
	
	public List<CustomConstraintVariable> getVariable() {
		return variables;
	}

	public void setCustomConstraintBound(CustomConstraintBound customBounds) {
		this.customBounds = customBounds;
	}
	
	public CustomConstraintBound getCustomBounds() {
		return customBounds;
	}
	
}
