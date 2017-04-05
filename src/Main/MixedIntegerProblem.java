package Main;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.glp_iocp;

public class MixedIntegerProblem extends LinearProblem {

	private glp_iocp iocp;

	public MixedIntegerProblem(String name, ObjectList objectList1, ObjectList objectList2,
			List<Constraint> constraints, int problemType, Map<String, Double> objectiveValues) {
		super(name, objectList1, objectList2, constraints, problemType, objectiveValues);
	}

	@Override
	public void addConstraints() {
		for (int i = 0; i < constraints.size(); i++) {
			Constraint c = constraints.get(i);
			if (c instanceof CustomConstraint) {
				CustomConstraint cus = (CustomConstraint) c;
				CustomConstraintBound b = cus.getCustomBounds();
				GLPK.glp_set_row_name (lp , i+1, "c"+i+1);
				GLPK.glp_set_row_bnds(lp, i+1, b.getBoundType(), 0, b.getLimit());
				ind = GLPK.new_intArray(cus.getVariable().size());
				val = GLPK.new_doubleArray(cus.getVariable().size());
				int index = 1;
				for (CustomConstraintVariable varb : cus.getVariable()) {
					GLPK.intArray_setitem(ind, index, indexes.get(varb.getValue()));
					GLPK.doubleArray_setitem(val, index, varb.getMultiplier());
					index++;
				}
				GLPK.glp_set_mat_row(lp, i+1, cus.getVariable().size(), ind, val);
			} else {
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
	}

	@Override
	public String getStringSolution() {
		iocp = new glp_iocp();
		GLPK.glp_init_iocp(iocp);
		iocp.setPresolve(GLPKConstants.GLP_ON);
		ret = GLPK.glp_intopt(lp, iocp);
		String output;
		// Retrieve solution
		if (ret == 0) {
            output = DisplayLP.getMipSolution(lp, varbNames);
        } else {
        	output = "No Feasible Solution Can Be Found";
        }

        // Free memory
        GLPK.glp_delete_prob(lp);
        return output;
	}





}
