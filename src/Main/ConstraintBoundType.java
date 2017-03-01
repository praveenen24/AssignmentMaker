package Main;

public enum ConstraintBoundType {
	GREATER_THAN("GREATER THAN"),
	LESS_THAN("LESS THAN");
	
	private String type;
	ConstraintBoundType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
}
