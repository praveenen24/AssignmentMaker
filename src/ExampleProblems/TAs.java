package ExampleProblems;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

import Main.DisplayLP;

public class TAs {

	public static void main(String[] args) {
		glp_prob lp;
		glp_smcp parm;
		SWIGTYPE_p_int ind;
		SWIGTYPE_p_double val;
		int ret;
		
		System.out.println("---------------Problem----------------");
		
		System.out.println("    | Course 1 | Course 2 | Course 3 |");
		System.out.println("--------------------------------------");
		System.out.println("TA1 |     1    |     2    |     3    |");
		System.out.println("TA2 |     2    |     3    |     1    |");
		System.out.println("TA3 |     3    |     1    |     2    |");
		

		lp = GLPK.glp_create_prob();
		System.out.println("Problem created");
		GLPK.glp_set_prob_name(lp, "taProblem");

		GLPK.glp_add_cols(lp, 9);
		GLPK.glp_set_col_name(lp, 1, "T1C1");
		GLPK.glp_set_col_kind(lp, 1, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 1, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 2, "T1C2");
		GLPK.glp_set_col_kind(lp, 2, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 2, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 3, "T1C3");
		GLPK.glp_set_col_kind(lp, 3, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 3, GLPKConstants.GLP_DB, 0, 1);

		GLPK.glp_set_col_name(lp, 4, "T2C1");
		GLPK.glp_set_col_kind(lp, 4, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 4, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 5, "T2C2");
		GLPK.glp_set_col_kind(lp, 5, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 5, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 6, "T2C3");
		GLPK.glp_set_col_kind(lp, 6, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 6, GLPKConstants.GLP_DB, 0, 1);

		GLPK.glp_set_col_name(lp, 7, "T3C1");
		GLPK.glp_set_col_kind(lp, 7, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 7, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 8, "T3C2");
		GLPK.glp_set_col_kind(lp, 8, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 8, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 9, "T3C3");
		GLPK.glp_set_col_kind(lp, 9, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 9, GLPKConstants.GLP_DB, 0, 1);

		//constraints
		GLPK.glp_add_rows(lp, 3);

		GLPK.glp_set_row_name (lp , 1, "c1");
		GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(3);
		val = GLPK.new_doubleArray(3);
		GLPK.intArray_setitem(ind, 1, 1);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 2);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 3);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.glp_set_mat_row(lp, 1, 3, ind, val);
		
		GLPK.glp_set_row_name (lp , 2, "c2");
		GLPK.glp_set_row_bnds(lp, 2, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(3);
		val = GLPK.new_doubleArray(3);
		GLPK.intArray_setitem(ind, 1, 4);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 5);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 6);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.glp_set_mat_row(lp, 2, 3, ind, val);

		GLPK.glp_set_row_name (lp , 3, "c3");
		GLPK.glp_set_row_bnds(lp, 3, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(3);
		val = GLPK.new_doubleArray(3);
		GLPK.intArray_setitem(ind, 1, 7);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 8);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 9);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.glp_set_mat_row(lp, 3, 3, ind, val);

		// Define objective

		GLPK . glp_set_obj_name (lp , "z");
		GLPK . glp_set_obj_dir (lp , GLPKConstants.GLP_MIN);
		GLPK.glp_set_obj_coef(lp, 1, 1);
		GLPK.glp_set_obj_coef(lp, 2, 2);
		GLPK.glp_set_obj_coef(lp, 3, 3); 
		
		GLPK.glp_set_obj_coef(lp, 4, 2);
		GLPK.glp_set_obj_coef(lp, 5, 3);
		GLPK.glp_set_obj_coef(lp, 6, 1);
		
		GLPK.glp_set_obj_coef(lp, 7, 3);
		GLPK.glp_set_obj_coef(lp, 8, 1);            
		GLPK.glp_set_obj_coef(lp, 9, 2);
		
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
