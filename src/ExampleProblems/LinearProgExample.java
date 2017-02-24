package ExampleProblems;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.GlpkException;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

public class LinearProgExample {
	// Maximize z = 10*x1 + 6*x2 + 4*x3
	//
	// subject to
	// x1 + x2 + x3 <= 100
	// 10*x1 + 4*x2 + 5*x3 <= 600
	// 2*x1 + 2*x2 + 6*x3 <= 300

	// where,
	// 0.0 <= x1 <= infinte
	// 0.0 <= x2 <= infinte
	// 0.0 <= x3 <= infinte

	// p,q,r = constraints
	// -infinte < p <= 100
	// -infinte < q <= 600
	// -infinte < r <= 300

	public static void main(String[] arg) {
		glp_prob lp;
		glp_smcp parm;
		SWIGTYPE_p_int ind;
		SWIGTYPE_p_double val;
		int ret;

		try {
			// Create problem
			lp = GLPK.glp_create_prob();
			System.out.println("GLPK Example Created");
			GLPK.glp_set_prob_name(lp, "GLPK Example");

			// Define columns
			GLPK.glp_add_cols(lp, 3);
			GLPK.glp_set_col_name(lp, 1, "x1");
			GLPK.glp_set_col_kind(lp, 1, GLPKConstants.GLP_CV);
			GLPK.glp_set_col_bnds(lp, 1, GLPKConstants.GLP_LO, 0, 0);
			GLPK.glp_set_col_name(lp, 2, "x2");
			GLPK.glp_set_col_kind(lp, 2, GLPKConstants.GLP_CV);
			GLPK.glp_set_col_bnds(lp, 2, GLPKConstants.GLP_LO, 0, 0);
			GLPK.glp_set_col_name(lp, 3, "x3");
			GLPK.glp_set_col_kind(lp, 3, GLPKConstants.GLP_CV);
			GLPK.glp_set_col_bnds(lp, 3, GLPKConstants.GLP_LO, 0, 0);

			// Create constraints

			// Allocate memory
			ind = GLPK.new_intArray(4);
			val = GLPK.new_doubleArray(4);

			// Create rows
			GLPK.glp_add_rows(lp, 3);

			// Set row details
			// p = x1 + x2 + x3
			GLPK.glp_set_row_name(lp, 1, "p");
			GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_UP, 0, 100);
			GLPK.intArray_setitem(ind, 1, 1);
			GLPK.intArray_setitem(ind, 2, 2);
			GLPK.intArray_setitem(ind, 3, 3);
			GLPK.doubleArray_setitem(val, 1, 1.0);
			GLPK.doubleArray_setitem(val, 2, 1.0);
			GLPK.doubleArray_setitem(val, 3, 1.0);
			GLPK.glp_set_mat_row(lp, 1, 3, ind, val);
			
            GLPK.glp_set_row_name(lp, 1, "c1");
            GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_DB, 0, 0.2);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.doubleArray_setitem(val, 1, 1.);
            GLPK.doubleArray_setitem(val, 2, -.5);
            GLPK.glp_set_mat_row(lp, 1, 2, ind, val);

			// q = 10*x1 + 4*x2 + 5*x3
			GLPK.glp_set_row_name(lp, 2, "q");
			GLPK.glp_set_row_bnds(lp, 2, GLPKConstants.GLP_UP, 0, 600);
			GLPK.intArray_setitem(ind, 1, 1);
			GLPK.intArray_setitem(ind, 2, 2);
			GLPK.intArray_setitem(ind, 3, 3);
			GLPK.doubleArray_setitem(val, 1, 10.0);
			GLPK.doubleArray_setitem(val, 2, 4.0);
			GLPK.doubleArray_setitem(val, 3, 5.0);
			GLPK.glp_set_mat_row(lp, 2, 3, ind, val);

			// r = 2*x1 + 2*x2 + 6*x3
			GLPK.glp_set_row_name(lp, 3, "r");
			GLPK.glp_set_row_bnds(lp, 3, GLPKConstants.GLP_UP, 0, 300);
			GLPK.intArray_setitem(ind, 1, 1);
			GLPK.intArray_setitem(ind, 2, 2);
			GLPK.intArray_setitem(ind, 3, 3);
			GLPK.doubleArray_setitem(val, 1, 2.0);
			GLPK.doubleArray_setitem(val, 2, 2.0);
			GLPK.doubleArray_setitem(val, 3, 6.0);
			GLPK.glp_set_mat_row(lp, 3, 3, ind, val);

			// Free memory
			GLPK.delete_intArray(ind);
			GLPK.delete_doubleArray(val);

			// Define objective
			GLPK.glp_set_obj_name(lp, "z");
			GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MAX);
			GLPK.glp_set_obj_coef(lp, 0, 0);
			GLPK.glp_set_obj_coef(lp, 1, 10);
			GLPK.glp_set_obj_coef(lp, 2, 6);
			GLPK.glp_set_obj_coef(lp, 3, 4);

			// Write model to file
			// GLPK.glp_write_lp(lp, null, "lp.lp");

			// Solve model
			parm = new glp_smcp();
			GLPK.glp_init_smcp(parm);
			ret = GLPK.glp_simplex(lp, parm);

			// Retrieve solution
			if (ret == 0) {
				write_lp_solution(lp);
			} else {
				System.out.println("The problem could not be solved");
			}

			// Free memory
			GLPK.glp_delete_prob(lp);
		} catch (GlpkException ex) {
			ex.printStackTrace();
			ret = 1;
		}
		System.exit(ret);
	}

	/**
	 * write simplex solution
	 * 
	 * @param lp
	 *            problem
	 */
	static void write_lp_solution(glp_prob lp) {
		int i;
		int n;
		String name;
		double val;

		name = GLPK.glp_get_obj_name(lp);
		val = GLPK.glp_get_obj_val(lp);
		System.out.print(name);
		System.out.print(" = ");
		System.out.println(val);
		n = GLPK.glp_get_num_cols(lp);
		for (i = 1; i <= n; i++) {
			name = GLPK.glp_get_col_name(lp, i);
			val = GLPK.glp_get_col_prim(lp, i);
			System.out.print(name);
			System.out.print(" = ");
			System.out.println(val);
		}
	}
}

