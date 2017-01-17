package Main;

public class AssignmentVariable {
	private String name;
	private Bound bound;
	private int id;
	private double objectiveCoeff;
	
	public AssignmentVariable(int id, String name, Bound bound, double objectiveCoeff) {
		this.name = name;
		this.bound = bound;
		this.id = id;
		this.objectiveCoeff = objectiveCoeff;
	}
	
	public Bound getBound() {
		return bound;
	}
	
	public void setBound(Bound bound) {
		this.bound = bound;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getObjectiveCoeff() {
		return objectiveCoeff;
	}
	
	public void setObjectCoeff(double objectiveCoeff) {
		this.objectiveCoeff = objectiveCoeff;
	}
}
