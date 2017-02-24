package Main;

import java.util.List;
import java.util.Map;

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
	public void solve() {
		iocp = new glp_iocp();
		GLPK.glp_init_iocp(iocp);
		iocp.setPresolve(GLPKConstants.GLP_ON);
		setRet(GLPK.glp_intopt(getLp(), iocp));
		
		// Retrieve solution
        if (getRet() == 0) {
            DisplayLP.displayAllValues(getLp());
        } else {
            System.out.println("The problem could not be solved");
        }

        // Free memory
        GLPK.glp_delete_prob(getLp());
	}

	
	
}
