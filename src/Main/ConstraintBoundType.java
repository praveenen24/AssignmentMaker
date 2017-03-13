package Main;

public enum ConstraintBoundType {
	GREATER_THAN("GREATER THAN OR EQUAL"),
	LESS_THAN("LESS THAN OR EQUAL");
	
	private String type;
	ConstraintBoundType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
}
