package Main;

public class Constraint {
	private String name;
	private Bound bounds;
	
	public Constraint(String name, Bound bounds) {
		this.name = name;
		this.bounds = bounds;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Bound getBounds() {
		return bounds;
	}
	
	public void setBounds(Bound bounds) {
		this.bounds = bounds;
	}
	
}
