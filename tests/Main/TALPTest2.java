package Main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.junit.Before;
import org.junit.Test;

import GUI.View.ProblemSetupPanel;

public class TALPTest2 {
	private LinearProblem lp;

	@Before
	public void TATest() {
		//Create TAs
		ObjectList objectList1 = new ObjectList("TA");
		AssignmentObject ta1 = new AssignmentObject("TA1");
		AssignmentObject ta2 = new AssignmentObject("TA2");
		AssignmentObject ta3 = new AssignmentObject("TA3");
		objectList1.add(ta1); objectList1.add(ta2); objectList1.add(ta3);
		//Create Courses
		ObjectList objectList2 = new ObjectList("Course");
		AssignmentObject course1 = new AssignmentObject("Course1");
		AssignmentObject course2 = new AssignmentObject("Course2");
		AssignmentObject course3 = new AssignmentObject("Course3");
		objectList2.add(course1); objectList2.add(course2); objectList2.add(course3);
		//Create the constraints 
		List<Constraint> constraints = new ArrayList<Constraint>();
		HorizontalConstraint hcon = new HorizontalConstraint("hor", new Bound(GLPKConstants.GLP_FX, 1, 1));
		constraints.addAll(ProblemSetupPanel.createHorizontalConstraints(hcon.getName(), objectList1.size(), objectList2.size(), 
				hcon.getBounds().getLowerBound(), hcon.getBounds().getUpperBound()));
		VerticalConstraint vcon = new VerticalConstraint("vert", new Bound(GLPKConstants.GLP_FX, 1, 1));
		constraints.addAll(ProblemSetupPanel.createVerticalConstraints(vcon.getName(), objectList1.size(), objectList2.size(), 
				vcon.getBounds().getLowerBound(), vcon.getBounds().getUpperBound()));
		
		// The objective values when using the program would just be received from user input
		// but for the purpose of this demo since there is no user input i am just going to hard code it.
		// So this isn't really apart of the automation but just to get the code to run
		Map<String, Double> objectiveValues = new HashMap<String, Double>();
		//ta1 objective values
		objectiveValues.put(ta1.getName()+course1.getName(), 1.0);
		objectiveValues.put(ta1.getName()+course2.getName(), 2.0);
		objectiveValues.put(ta1.getName()+course3.getName(), 3.0);
		//ta2 objective values
		objectiveValues.put(ta2.getName()+course1.getName(), 1.0);
		objectiveValues.put(ta2.getName()+course2.getName(), 2.0);
		objectiveValues.put(ta2.getName()+course3.getName(), 3.0);
		//ta3 objective values
		objectiveValues.put(ta3.getName()+course1.getName(), 1.0);
		objectiveValues.put(ta3.getName()+course2.getName(), 2.0);
		objectiveValues.put(ta3.getName()+course3.getName(), 3.0);

		lp = new LinearProblem("lp", objectList1, objectList2, constraints, GLPKConstants.GLP_MIN, objectiveValues);
		lp.getStringSolution();
	}
	
	@Test
	public void assignmentTest() {
		System.out.println("---------------Problem----------------");
		System.out.println("    | Course 1 | Course 2 | Course 3 |");
		System.out.println("--------------------------------------");
		System.out.println("TA1 |     1    |     2    |     3    |");
		System.out.println("TA2 |     1    |     2    |     3    |");
		System.out.println("TA3 |     1    |     2    |     3    |");
		System.out.println();
		System.out.println("-------------------Objective Value Test-----------------------------");
		double val = GLPK.glp_get_obj_val(lp.getProblem());
		System.out.println("Objective Value Expected: 6.0 | Actual : " + val);
		assertTrue(val == 6.0);
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
	}
}
