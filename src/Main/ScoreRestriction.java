package Main;

public class ScoreRestriction {

	private ScoreType type;
	private boolean continuous;
	
	public ScoreRestriction(ScoreType type, boolean continuous) {
		this.type = type;
		this.continuous = continuous;
	}
	
	public ScoreType getType() {
		return type;
	}
	
	public void setType(ScoreType type) {
		this.type = type;
	}
	
	public boolean isContinuous() {
		return continuous;
	}
	
	public void setContinuous(boolean continuous) {
		this.continuous = continuous;
	}
	
}
