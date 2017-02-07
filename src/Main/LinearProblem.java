package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

public class LinearProblem {
	private glp_prob lp;
	private glp_smcp parm;
	private SWIGTYPE_p_int ind;
	private SWIGTYPE_p_double val;
	private int ret;
	private String name;
	private List<Constraint> constraints;
	private Map<String, Double> objectiveValues;
	private List<AssignmentVariable> assignmentVarbs;
	private ObjectList objectList1;
	private ObjectList objectList2;
	private int problemType;
	private int columnSize;


	public LinearProblem(String name, ObjectList objectList1, ObjectList objectList2, 
			List<Constraint> constraints, int problemType, Map<String, Double> objectiveValues) {
		lp = GLPK.glp_create_prob();
		GLPK.glp_set_prob_name(lp, name);
		this.constraints = constraints;
		this.name = name;
		this.objectList1 = objectList1;
		this.objectList2 = objectList2;
		this.problemType = problemType;
		this.objectiveValues = objectiveValues;
		this.assignmentVarbs = new ArrayList<AssignmentVariable>();
		columnSize = this.objectList1.size() * this.objectList2.size();
		if (constraints.size() > 0) GLPK.glp_add_rows(lp, this.constraints.size());
		createVariables();
		setupObjective();
		addConstraints();
	}
	
	public void createVariables() {
		int identifier = 1;
		int index1 = 0;
		int index2 = 0;
		for (AssignmentObject o1 : objectList1) {
			for (AssignmentObject o2 : objectList2) {
				String key = o1.getName()+o2.getName();
				assignmentVarbs.add(new AssignmentVariable(identifier, key, 
						new Bound(GLPKConstants.GLP_DB, 0, 1), objectiveValues.get(key)));
				identifier++;
				index2++;
			}
			index1++;
			index2 = 0;
		}
		addVariables();
	}
	
	public void addVariables() {
		GLPK.glp_add_cols(lp, columnSize);
		int index = 1;
		for (AssignmentVariable varb : assignmentVarbs) {
			Bound b = varb.getBound();
			GLPK.glp_set_col_name(lp, index, varb.getName());
			GLPK.glp_set_col_kind(lp, index, GLPKConstants.GLP_CV);
			GLPK.glp_set_col_bnds(lp, index, b.getBoundType(), b.getLowerBound(), b.getUpperBound());
			index++;
		}
	}
	
	public void setupObjective() {
		GLPK.glp_set_obj_name (lp , "Z");
		GLPK.glp_set_obj_dir (lp , problemType);
		for (AssignmentVariable varb : assignmentVarbs) {
			GLPK.glp_set_obj_coef(lp, varb.getID(), varb.getObjectiveCoeff());
		}
	}

	public void addConstraints() {
		for (int i = 0; i < constraints.size(); i++) {
			Constraint c = constraints.get(i);
			Bound b = c.getBounds();
			Set<Entry<Integer, Double>> entries = c.getValues().entrySet();
			GLPK.glp_set_row_name (lp , i+1, "c"+i+1);
			GLPK.glp_set_row_bnds(lp, i+1, b.getBoundType(), b.getLowerBound(), b.getUpperBound());
			ind = GLPK.new_intArray(entries.size());
			val = GLPK.new_doubleArray(entries.size());
			int index = 1;
			for (Entry<Integer, Double> e : entries) {
				GLPK.intArray_setitem(ind, index, e.getKey());
				GLPK.doubleArray_setitem(val, index, e.getValue());
				index++;
			}
			GLPK.glp_set_mat_row(lp, i+1, entries.size(), ind, val);
		}
	}

	@Override
	public String toString() {
		return name;
	}
	
	public void solve() {
		// Solve model
        parm = new glp_smcp();
        GLPK.glp_init_smcp(parm);
        ret = GLPK.glp_simplex(lp, parm);

        // Retrieve solution
        if (ret == 0) {
            DisplayLP.displayAllValues(lp);
        } else {
            System.out.println("The problem could not be solved");
        }

        // Free memory
        GLPK.glp_delete_prob(lp);
	}
	
	public String getStringSolution() {
		// Solve model
        parm = new glp_smcp();
        GLPK.glp_init_smcp(parm);
        ret = GLPK.glp_simplex(lp, parm);
        String output = "";
        // Retrieve solution
        if (ret == 0) {
            output = DisplayLP.getOutput(lp);
        } else {
        	output = "The problem could not be solved";
        }

        // Free memory
        GLPK.glp_delete_prob(lp);
        return output;
	}
	
	public static void main(String[] args) {
		//Object Creation
		AssignmentObject ta1 = new AssignmentObject("TA1");
		AssignmentObject ta2 = new AssignmentObject("TA2");
		AssignmentObject ta3 = new AssignmentObject("TA3");
		HashSet<AssignmentObject> taList = new HashSet<AssignmentObject>();
		taList.add(ta1); taList.add(ta2); taList.add(ta3);
		AssignmentObject course1 = new AssignmentObject("Course1");
		AssignmentObject course2 = new AssignmentObject("Course2");
		AssignmentObject course3 = new AssignmentObject("Course3");
		HashSet<AssignmentObject> courseList = new HashSet<AssignmentObject>();
		courseList.add(course1); courseList.add(course2); courseList.add(course3);
	
		ObjectList tas = new ObjectList("TA", taList);
		ObjectList courses = new ObjectList("COURSE", courseList);
		
		//ObjectiveValues
		List<Integer> objectiveVal = new ArrayList<Integer>();
		objectiveVal.add(1);objectiveVal.add(2);objectiveVal.add(3);
		objectiveVal.add(3);objectiveVal.add(1);objectiveVal.add(1);
		objectiveVal.add(2);objectiveVal.add(3);objectiveVal.add(1);
		
		//Constraints 
		List<Constraint> constraints = new ArrayList<Constraint>();
		Constraint c1 = new Constraint("c1", new Bound(GLPKConstants.GLP_FX, 1, 1));
		c1.addValue(1, 1.0);c1.addValue(2, 1.0);c1.addValue(3, 1.0);
		Constraint c2 = new Constraint("c2", new Bound(GLPKConstants.GLP_FX, 1, 1));
		c2.addValue(4, 1.0);c2.addValue(5, 1.0);c2.addValue(6, 1.0);
		Constraint c3 = new Constraint("c3", new Bound(GLPKConstants.GLP_FX, 1, 1));
		c3.addValue(7, 1.0);c3.addValue(8, 1.0);c3.addValue(9, 1.0);
		constraints.add(c1); constraints.add(c2); constraints.add(c3);
		
		//LinearProblem lp = new LinearProblem("lp", tas, courses, constraints, GLPKConstants.GLP_MIN, objectiveVal);
		//lp.solve();
	}

}
