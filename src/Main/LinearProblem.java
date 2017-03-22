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
	protected glp_prob lp;
	protected glp_smcp parm;
	protected SWIGTYPE_p_int ind;
	protected SWIGTYPE_p_double val;
	protected int ret;
	protected String name;
	protected List<Constraint> constraints;
	protected Map<String, Double> objectiveValues;
	protected Map<String, Integer> indexes;
	protected List<AssignmentVariable> assignmentVarbs;
	protected ObjectList objectList1;
	protected ObjectList objectList2;
	protected int problemType;
	protected int columnSize;
	protected Map<String, List<AssignmentObject>> varbNames;

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
		indexes = new HashMap<String, Integer>();
		this.assignmentVarbs = new ArrayList<AssignmentVariable>();
		varbNames = new HashMap<String, List<AssignmentObject>>();
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
				List<AssignmentObject> varbs = new ArrayList<AssignmentObject>();
				String key = o1.getName()+o2.getName();
				varbs.add(o1); varbs.add(o2); varbNames.put(key, varbs);
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
			indexes.put(varb.getName(), index);
			GLPK.glp_set_col_kind(lp, index, GLPKConstants.GLP_BV);
			GLPK.glp_set_col_bnds(lp, index, b.getBoundType(), b.getLowerBound(), b.getUpperBound());
			index++;
		}
	}
	
	public glp_prob getProblem() {
		return lp;
	}
	
	public void setupObjective() {
		GLPK.glp_set_obj_name (lp , "TOTAL");
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
	
	public Map<String, List<AssignmentObject>> getVarbNames() {
		return varbNames;
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
            output = DisplayLP.getOutput(lp, varbNames);
        } else {
        	output = "The problem could not be solved";
        }

        // Free memory
        GLPK.glp_delete_prob(lp);
        return output;
	}
}
