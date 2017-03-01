package Main;

public class CustomConstraintVariable {

	private double multiplier;
	private String value;
	
	public CustomConstraintVariable(double multiplier, String value) {
		this.multiplier = multiplier;
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public double getMultiplier() {
		return multiplier;
	}
	
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	
	@Override
	public String toString() {
		return multiplier + "*" + value;
	}
}
