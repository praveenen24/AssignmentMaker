import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

public class Track {

	public static void main(String[] args) {
		glp_prob lp;
		glp_smcp parm;
		SWIGTYPE_p_int ind;
		SWIGTYPE_p_double val;
		int ret;

		System.out.println("Track and Field Problem");
		
		
		System.out.println("-----------------------------Track and Field Problem-----------------------------");
		
		System.out.println("   | EVENT 1  | EVENT 2  | EVENT 3  | EVENT 4  | EVENT 5  | EVENT 6  | EVENT 7  |");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("A1 |     3    |     0    |     1    |     2    |     1    |     3    |     2    |");
		System.out.println("A2 |     1    |     2    |     3    |     1    |     0    |     2    |     0    |");
		System.out.println("A3 |     2    |     3    |     2    |     0    |     0    |     1    |     1    |");
		System.out.println("A4 |     1    |     3    |     0    |     3    |     2    |     3    |     3    |");
		System.out.println("A5 |     0    |     1    |     3    |     2    |     3    |     2    |     0    |");
		

		lp = GLPK.glp_create_prob();
		System.out.println("");
		GLPK.glp_set_prob_name(lp, "Track and Field Problem");

		GLPK.glp_add_cols(lp, 35);
		// Athlete 1 variables
		GLPK.glp_set_col_name(lp, 1, "A1E1");
		GLPK.glp_set_col_kind(lp, 1, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 1, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 2, "A1E2");
		GLPK.glp_set_col_kind(lp, 2, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 2, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 3, "A1E3");
		GLPK.glp_set_col_kind(lp, 3, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 3, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 4, "A1E4");
		GLPK.glp_set_col_kind(lp, 4, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 4, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 5, "A1E5");
		GLPK.glp_set_col_kind(lp, 5, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 5, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 6, "A1E6");
		GLPK.glp_set_col_kind(lp, 6, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 6, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 7, "A1E7");
		GLPK.glp_set_col_kind(lp, 7, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 7, GLPKConstants.GLP_DB, 0, 1);
		
		
		// Athlete 2 variables
		GLPK.glp_set_col_name(lp, 8, "A2E1");
		GLPK.glp_set_col_kind(lp, 8, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 8, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 9, "A2E2");
		GLPK.glp_set_col_kind(lp, 9, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 9, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 10, "A2E3");
		GLPK.glp_set_col_kind(lp, 10, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 10, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 11, "A2E4");
		GLPK.glp_set_col_kind(lp, 11, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 11, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 12, "A2E5");
		GLPK.glp_set_col_kind(lp, 12, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 12, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 13, "A2E6");
		GLPK.glp_set_col_kind(lp, 13, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 13, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 14, "A2E7");
		GLPK.glp_set_col_kind(lp, 14, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 14, GLPKConstants.GLP_DB, 0, 1);
		

		// Athlete 3 variables		
		GLPK.glp_set_col_name(lp, 15, "A3E1");
		GLPK.glp_set_col_kind(lp, 15, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 15, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 16, "A3E2");
		GLPK.glp_set_col_kind(lp, 16, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 16, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 17, "A3E3");
		GLPK.glp_set_col_kind(lp, 17, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 17, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 18, "A3E4");
		GLPK.glp_set_col_kind(lp, 18, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 18, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 19, "A3E5");
		GLPK.glp_set_col_kind(lp, 19, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 19, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 20, "A3E6");
		GLPK.glp_set_col_kind(lp, 20, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 20, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 21, "A3E7");
		GLPK.glp_set_col_kind(lp, 21, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 21, GLPKConstants.GLP_DB, 0, 1);

		// Athlete 4 variables		
		GLPK.glp_set_col_name(lp, 22, "A4E1");
		GLPK.glp_set_col_kind(lp, 22, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 22, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 23, "A4E2");
		GLPK.glp_set_col_kind(lp, 23, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 23, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 24, "A4E3");
		GLPK.glp_set_col_kind(lp, 24, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 24, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 25, "A4E4");
		GLPK.glp_set_col_kind(lp, 25, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 25, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 26, "A4E5");
		GLPK.glp_set_col_kind(lp, 26, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 26, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 27, "A4E6");
		GLPK.glp_set_col_kind(lp, 27, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 27, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 28, "A4E7");
		GLPK.glp_set_col_kind(lp, 28, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 28, GLPKConstants.GLP_DB, 0, 1);

		// Athlete 5 variables		
		GLPK.glp_set_col_name(lp, 29, "A5E1");
		GLPK.glp_set_col_kind(lp, 29, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 29, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 30, "A5E2");
		GLPK.glp_set_col_kind(lp, 30, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 30, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 31, "A5E3");
		GLPK.glp_set_col_kind(lp, 31, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 31, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 32, "A5E4");
		GLPK.glp_set_col_kind(lp, 32, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 32, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 33, "A5E5");
		GLPK.glp_set_col_kind(lp, 33, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 33, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 34, "A5E6");
		GLPK.glp_set_col_kind(lp, 34, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 34, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 35, "A5E7");
		GLPK.glp_set_col_kind(lp, 35, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 35, GLPKConstants.GLP_DB, 0, 1);

		//CONSTRAINTS
		//constraints
		GLPK.glp_add_rows(lp, 12);

		GLPK.glp_set_row_name (lp , 1, "c1");
		GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_DB, 1, 2);
		ind = GLPK.new_intArray(7);
		val = GLPK.new_doubleArray(7);
		GLPK.intArray_setitem(ind, 1, 1);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 2);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 3);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 4);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 5);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.intArray_setitem(ind, 6, 6);
		GLPK.doubleArray_setitem(val, 6, 1.0);
		GLPK.intArray_setitem(ind, 7, 7);
		GLPK.doubleArray_setitem(val, 7, 1.0);
		GLPK.glp_set_mat_row(lp, 1, 7, ind, val);
		
		GLPK.glp_set_row_name (lp , 2, "c2");
		GLPK.glp_set_row_bnds(lp, 2, GLPKConstants.GLP_DB, 1, 2);
		ind = GLPK.new_intArray(7);
		val = GLPK.new_doubleArray(7);
		GLPK.intArray_setitem(ind, 1, 8);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 9);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 10);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 11);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 12);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.intArray_setitem(ind, 6, 13);
		GLPK.doubleArray_setitem(val, 6, 1.0);
		GLPK.intArray_setitem(ind, 7, 14);
		GLPK.doubleArray_setitem(val, 7, 1.0);
		GLPK.glp_set_mat_row(lp, 2, 7, ind, val);
		
		GLPK.glp_set_row_name (lp , 3, "c3");
		GLPK.glp_set_row_bnds(lp, 3, GLPKConstants.GLP_DB, 1, 2);
		ind = GLPK.new_intArray(7);
		val = GLPK.new_doubleArray(7);
		GLPK.intArray_setitem(ind, 1, 15);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 16);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 17);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 18);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 19);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.intArray_setitem(ind, 6, 20);
		GLPK.doubleArray_setitem(val, 6, 1.0);
		GLPK.intArray_setitem(ind, 7, 21);
		GLPK.doubleArray_setitem(val, 7, 1.0);
		GLPK.glp_set_mat_row(lp, 3, 7, ind, val);
		
		GLPK.glp_set_row_name (lp , 4, "c4");
		GLPK.glp_set_row_bnds(lp, 4, GLPKConstants.GLP_DB, 1, 2);
		ind = GLPK.new_intArray(7);
		val = GLPK.new_doubleArray(7);
		GLPK.intArray_setitem(ind, 1, 22);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 23);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 24);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 25);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 26);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.intArray_setitem(ind, 6, 27);
		GLPK.doubleArray_setitem(val, 6, 1.0);
		GLPK.intArray_setitem(ind, 7, 28);
		GLPK.doubleArray_setitem(val, 7, 1.0);
		GLPK.glp_set_mat_row(lp, 4, 7, ind, val);
		
		GLPK.glp_set_row_name (lp , 5, "c5");
		GLPK.glp_set_row_bnds(lp, 5, GLPKConstants.GLP_DB, 1, 2);
		ind = GLPK.new_intArray(7);
		val = GLPK.new_doubleArray(7);
		GLPK.intArray_setitem(ind, 1, 29);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 30);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 31);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 32);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 33);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.intArray_setitem(ind, 6, 34);
		GLPK.doubleArray_setitem(val, 6, 1.0);
		GLPK.intArray_setitem(ind, 7, 35);
		GLPK.doubleArray_setitem(val, 7, 1.0);
		GLPK.glp_set_mat_row(lp, 5, 7, ind, val);
		
		//LIMIT COLUMNS
		GLPK.glp_set_row_name (lp , 6, "c6");
		GLPK.glp_set_row_bnds(lp, 6, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(5);
		val = GLPK.new_doubleArray(5);
		GLPK.intArray_setitem(ind, 1, 1);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 8);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 15);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 22);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 29);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.glp_set_mat_row(lp, 6, 5, ind, val);

		GLPK.glp_set_row_name (lp , 7, "c7");
		GLPK.glp_set_row_bnds(lp, 7, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(5);
		val = GLPK.new_doubleArray(5);
		GLPK.intArray_setitem(ind, 1, 2);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 9);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 16);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 23);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 30);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.glp_set_mat_row(lp, 7, 5, ind, val);
		
		GLPK.glp_set_row_name (lp , 8, "c8");
		GLPK.glp_set_row_bnds(lp, 8, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(5);
		val = GLPK.new_doubleArray(5);
		GLPK.intArray_setitem(ind, 1, 3);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 10);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 17);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 24);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 31);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.glp_set_mat_row(lp, 8, 5, ind, val);
		
		GLPK.glp_set_row_name (lp , 9, "c9");
		GLPK.glp_set_row_bnds(lp, 9, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(5);
		val = GLPK.new_doubleArray(5);
		GLPK.intArray_setitem(ind, 1, 4);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 11);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 18);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 25);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 32);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.glp_set_mat_row(lp, 9, 5, ind, val);
		
		GLPK.glp_set_row_name (lp , 10, "c10");
		GLPK.glp_set_row_bnds(lp, 10, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(5);
		val = GLPK.new_doubleArray(5);
		GLPK.intArray_setitem(ind, 1, 5);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 12);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 19);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 26);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 33);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.glp_set_mat_row(lp, 10, 5, ind, val);
		
		GLPK.glp_set_row_name (lp , 11, "c11");
		GLPK.glp_set_row_bnds(lp, 11, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(5);
		val = GLPK.new_doubleArray(5);
		GLPK.intArray_setitem(ind, 1, 6);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 13);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 20);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 27);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 34);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.glp_set_mat_row(lp, 11, 5, ind, val);
		
		GLPK.glp_set_row_name (lp , 12, "c12");
		GLPK.glp_set_row_bnds(lp, 12, GLPKConstants.GLP_FX, 1, 1);
		ind = GLPK.new_intArray(5);
		val = GLPK.new_doubleArray(5);
		GLPK.intArray_setitem(ind, 1, 7);
		GLPK.doubleArray_setitem(val, 1, 1.0);
		GLPK.intArray_setitem(ind, 2, 14);
		GLPK.doubleArray_setitem(val, 2, 1.0);
		GLPK.intArray_setitem(ind, 3, 21);
		GLPK.doubleArray_setitem(val, 3, 1.0);
		GLPK.intArray_setitem(ind, 4, 28);
		GLPK.doubleArray_setitem(val, 4, 1.0);
		GLPK.intArray_setitem(ind, 5, 35);
		GLPK.doubleArray_setitem(val, 5, 1.0);
		GLPK.glp_set_mat_row(lp, 12, 5, ind, val);
		
		//OBJECTIVE FUNCTION
		GLPK.glp_set_obj_name (lp , "z");
		GLPK.glp_set_obj_dir (lp , GLPKConstants.GLP_MAX);

		GLPK.glp_set_obj_coef(lp, 0, 0);
		
		GLPK.glp_set_obj_coef(lp, 1, 3);
		GLPK.glp_set_obj_coef(lp, 2, 0);
		GLPK.glp_set_obj_coef(lp, 3, 1); 
		GLPK.glp_set_obj_coef(lp, 4, 2);
		GLPK.glp_set_obj_coef(lp, 5, 1);
		GLPK.glp_set_obj_coef(lp, 6, 3);
		GLPK.glp_set_obj_coef(lp, 7, 2);
		
		GLPK.glp_set_obj_coef(lp, 8, 1); 
		GLPK.glp_set_obj_coef(lp, 9, 2);
		GLPK.glp_set_obj_coef(lp, 10, 3);
		GLPK.glp_set_obj_coef(lp, 11, 1);
		GLPK.glp_set_obj_coef(lp, 12, 0);
		GLPK.glp_set_obj_coef(lp, 13, 2); 
		GLPK.glp_set_obj_coef(lp, 14, 0);
		
		GLPK.glp_set_obj_coef(lp, 15, 2);
		GLPK.glp_set_obj_coef(lp, 16, 3);
		GLPK.glp_set_obj_coef(lp, 17, 2);
		GLPK.glp_set_obj_coef(lp, 18, 0); 
		GLPK.glp_set_obj_coef(lp, 19, 1);
		GLPK.glp_set_obj_coef(lp, 20, 1);
		GLPK.glp_set_obj_coef(lp, 21, 1);
		
		GLPK.glp_set_obj_coef(lp, 22, 1);
		GLPK.glp_set_obj_coef(lp, 23, 3);
		GLPK.glp_set_obj_coef(lp, 24, 0);
		GLPK.glp_set_obj_coef(lp, 25, 3); 
		GLPK.glp_set_obj_coef(lp, 26, 2);
		GLPK.glp_set_obj_coef(lp, 27, 3);
		GLPK.glp_set_obj_coef(lp, 28, 3);
		
		GLPK.glp_set_obj_coef(lp, 29, 0);
		GLPK.glp_set_obj_coef(lp, 30, 1); 
		GLPK.glp_set_obj_coef(lp, 31, 3);
		GLPK.glp_set_obj_coef(lp, 32, 2);
		GLPK.glp_set_obj_coef(lp, 33, 3);
		GLPK.glp_set_obj_coef(lp, 34, 2);
		GLPK.glp_set_obj_coef(lp, 35, 0);
		
		// Solve model
        parm = new glp_smcp();
        GLPK.glp_init_smcp(parm);
        ret = GLPK.glp_simplex(lp, parm);
        
        // Retrieve solution
        if (ret == 0) {
            DisplayLP.write_lp_solution(lp);
        } else {
            System.out.println("The problem could not be solved");
        }
        
        // Free memory
        GLPK.glp_delete_prob(lp);

	}
}
