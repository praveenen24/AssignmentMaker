package Main;

import java.util.List;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

import ExampleProblems.DisplayLP;

public class LinearProblem {
	private glp_prob lp;
	private glp_smcp parm;
	private SWIGTYPE_p_int ind;
	private SWIGTYPE_p_double val;
	private int ret;
	private List<Constraint> constraints;
	private List<Integer> objectiveValues;
	private ObjectList objectList1;
	private ObjectList objectList2;
	private int problemType;
	private int columnSize;


	public LinearProblem(String name, ObjectList objectList1, ObjectList objectList2, 
			List<Constraint> constraints, int problemType, List<Integer> objectiveValues) {
		lp = GLPK.glp_create_prob();
		GLPK.glp_set_prob_name(lp, name);
		this.constraints = constraints;
		this.objectList1 = objectList1;
		this.objectList2 = objectList2;
		this.problemType = problemType;
		columnSize = this.objectList1.size() * this.objectList2.size();
		GLPK.glp_add_cols(lp, columnSize);
		GLPK.glp_add_rows(lp, this.constraints.size());
	}
	
	public void addVariables() {
		GLPK.glp_add_cols(lp, columnSize);
		int index = 1;
		for (AssignmentObject o1 : objectList1) {
			for (AssignmentObject o2 : objectList2) {
				GLPK.glp_set_col_name(lp, index, o1.getName()+o2.getName());
				GLPK.glp_set_col_kind(lp, index, GLPKConstants.GLP_CV);
				GLPK.glp_set_col_bnds(lp, index, GLPKConstants.GLP_DB, 0, 1);
				index++;
			}
		}
	}
	
	public void setupObjective() {
		GLPK.glp_set_obj_name (lp , "Z");
		GLPK.glp_set_obj_dir (lp , problemType);
		for (Integer i : objectiveValues) {
			GLPK.glp_set_obj_coef(lp, objectiveValues.indexOf(i), i);
		}
	}

	public void addConstraints() {
		for (int i = 1; i < constraints.size()+1; i++) {
			Constraint c = constraints.get(i);
			Bound b = c.getBounds();
			GLPK.glp_set_row_name (lp , i, "c"+i);
			GLPK.glp_set_row_bnds(lp, i, b.getBoundType(), b.getLowerBound(), b.getUpperBound());
			ind = GLPK.new_intArray(3);
			val = GLPK.new_doubleArray(3);
			//			GLPK.intArray_setitem(ind, 1, 1);
			//			GLPK.doubleArray_setitem(val, 1, 1.0);
			//			GLPK.intArray_setitem(ind, 2, 2);
			//			GLPK.doubleArray_setitem(val, 2, 1.0);
			//			GLPK.intArray_setitem(ind, 3, 3);
			//			GLPK.doubleArray_setitem(val, 3, 1.0);
			GLPK.glp_set_mat_row(lp, i, 3, ind, val);
		}
	}

	public void solveLP() {
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

}
