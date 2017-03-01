package Main;

public class CustomConstraintBound {
	private int boundType;
	private Double limit;
	
	public CustomConstraintBound(int boundType, Double limit) {
		this.boundType = boundType;
		this.limit = limit;
	}
	
	public Double getLimit() {
		return limit;
	}
	
	public void setLimit(Double limit) {
		this.limit = limit;
	}
	
	public int getBoundType() {
		return boundType;
	}
	
	public void setBoundType(int boundType) {
		this.boundType = boundType;
	}
}
