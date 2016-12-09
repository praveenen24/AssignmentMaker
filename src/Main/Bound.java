package Main;

public class Bound {
	private int boundType;
	private int lowerBound;
	private int upperBound;
	
	public Bound(int boundType, int lowerBound, int upperBound) {
		this.boundType = boundType;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public int getLowerBound() {
		return lowerBound;
	}
	
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	public int getUpperBound() {
		return upperBound;
	}
	
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}
	
	public int getBoundType() {
		return boundType;
	}
	
	public void setBoundType(int boundType) {
		this.boundType = boundType;
	}
	
	
	
}
