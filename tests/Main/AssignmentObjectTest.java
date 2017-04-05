package Main;

import static org.junit.Assert.*;

import org.junit.Test;

public class AssignmentObjectTest {
	
	@Test
	public void assignmentObjectCreationTest() {
		AssignmentObject o1 = new AssignmentObject("Object1");
		assertTrue(o1.getName().equals("Object1"));
		o1.setName("Test");
		assertTrue(o1.getName().equals("Test"));
	}
	
	@Test
	public void assignmentObjectEquivalenceTest() {
		AssignmentObject o1 = new AssignmentObject("Object1");
		AssignmentObject o2 = new AssignmentObject("Object2");
		assertFalse(o1.equals(o2));
		o2.setName("Object1");
		assertTrue(o1.equals(o2));
	}

}
