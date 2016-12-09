package Bounds;

import org.gnu.glpk.GLPKConstants;

public class DoubleBound {
	private int boundType;
	private int lowerBound;
	private int upperBound;
	
	public DoubleBound(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;	
		boundType = GLPKConstants.GLP_DB;
	}
	
	public int getBoundType() {
		return boundType;
	}
	
	public void setBoundType(int boundType) {
		this.boundType = boundType;
	}
}
