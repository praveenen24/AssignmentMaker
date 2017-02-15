package Main;

public enum ScoreType {
	POSITIVE("Positive"),
	NEGATIVE("Negative"),
	BOTH("Positive or Negative");
	
	private String type;
	ScoreType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
}
