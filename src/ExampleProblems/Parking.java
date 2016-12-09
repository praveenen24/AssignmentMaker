package ExampleProblems;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

public class Parking {
	
	public static void main(String[] args) {
		glp_prob lp;
		glp_smcp parm;
		SWIGTYPE_p_int ind;
		SWIGTYPE_p_double val;
		int ret;
		
		double d11 = 4.9; 
		
		System.out.println("-----------------------------Track and Field Problem-----------------------------");
		
		System.out.println("   |  SPOT 1  |  SPOT 2  |  SPOT 3  |  SPOT 4  |  SPOT 5  |  SPOT 6  |  SPOT 7  |");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("P1 |     3    |     0    |     1    |     2    |     1    |     3    |     2    |");
		System.out.println("P2 |     1    |     2    |     3    |     1    |     0    |     2    |     0    |");
		System.out.println("P3 |     2    |     3    |     2    |     0    |     0    |     1    |     1    |");
		System.out.println("P4 |     1    |     3    |     0    |     3    |     2    |     3    |     3    |");
		System.out.println("P5 |     0    |     1    |     3    |     2    |     3    |     2    |     0    |");
		
		System.out.println("Parking Problem");

		lp = GLPK.glp_create_prob();
		System.out.println("Problem created");
		GLPK.glp_set_prob_name(lp, "Parking Problem");

		GLPK.glp_add_cols(lp, 24);
		
		GLPK.glp_set_col_name(lp, 1, "P1S1");
		GLPK.glp_set_col_kind(lp, 1, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 1, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 2, "P1S2");
		GLPK.glp_set_col_kind(lp, 2, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 2, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 3, "P1S3");
		GLPK.glp_set_col_kind(lp, 3, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 3, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 4, "P1S4");
		GLPK.glp_set_col_kind(lp, 4, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 4, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 5, "P1S5");
		GLPK.glp_set_col_kind(lp, 5, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 5, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 6, "P1S6");
		GLPK.glp_set_col_kind(lp, 6, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 6, GLPKConstants.GLP_DB, 0, 1);
		
		GLPK.glp_set_col_name(lp, 7, "P2S1");
		GLPK.glp_set_col_kind(lp, 7, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 7, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 8, "P2S2");
		GLPK.glp_set_col_kind(lp, 8, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 8, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 9, "P2S3");
		GLPK.glp_set_col_kind(lp, 9, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 9, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 10, "P2S4");
		GLPK.glp_set_col_kind(lp, 10, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 10, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 11, "P2S5");
		GLPK.glp_set_col_kind(lp, 11, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 11, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 12, "P2S6");
		GLPK.glp_set_col_kind(lp, 12, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 12, GLPKConstants.GLP_DB, 0, 1);
		
		GLPK.glp_set_col_name(lp, 13, "P3S1");
		GLPK.glp_set_col_kind(lp, 13, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 13, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 14, "P3S2");
		GLPK.glp_set_col_kind(lp, 14, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 14, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 15, "P3S3");
		GLPK.glp_set_col_kind(lp, 15, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 15, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 16, "P3S4");
		GLPK.glp_set_col_kind(lp, 16, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 16, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 17, "P3S5");
		GLPK.glp_set_col_kind(lp, 17, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 17, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 18, "P3S6");
		GLPK.glp_set_col_kind(lp, 18, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 18, GLPKConstants.GLP_DB, 0, 1);
		
		GLPK.glp_set_col_name(lp, 19, "P4S1");
		GLPK.glp_set_col_kind(lp, 19, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 19, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 20, "P4S2");
		GLPK.glp_set_col_kind(lp, 20, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 20, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 21, "P4S3");
		GLPK.glp_set_col_kind(lp, 21, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 21, GLPKConstants.GLP_DB, 0, 1);		
		GLPK.glp_set_col_name(lp, 22, "P4S4");
		GLPK.glp_set_col_kind(lp, 22, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 22, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 23, "P4S5");
		GLPK.glp_set_col_kind(lp, 23, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 23, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 24, "P4S6");
		GLPK.glp_set_col_kind(lp, 24, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 24, GLPKConstants.GLP_DB, 0, 1);
		
		//CONSTRAINTS
		
		
		//OBJECTIVE FUNCTION
		GLPK.glp_set_obj_name (lp , "z");
		GLPK.glp_set_obj_dir (lp , GLPKConstants.GLP_MIN);
		GLPK.glp_set_obj_coef(lp, 0, 0);
		GLPK.glp_set_obj_coef(lp, 1, 2);
		GLPK.glp_set_obj_coef(lp, 2, 3);
		GLPK.glp_set_obj_coef(lp, 3, 1); 
		GLPK.glp_set_obj_coef(lp, 4, 4);
		GLPK.glp_set_obj_coef(lp, 5, 6);
		GLPK.glp_set_obj_coef(lp, 6, 5);
		
		GLPK.glp_set_obj_coef(lp, 7, 2);
		GLPK.glp_set_obj_coef(lp, 8, 3); 
		GLPK.glp_set_obj_coef(lp, 9, 1);
		GLPK.glp_set_obj_coef(lp, 10, 5);
		GLPK.glp_set_obj_coef(lp, 11, 4);
		GLPK.glp_set_obj_coef(lp, 12, 6);
		
		GLPK.glp_set_obj_coef(lp, 13, 3); 
		GLPK.glp_set_obj_coef(lp, 14, 2);
		GLPK.glp_set_obj_coef(lp, 15, 1);
		GLPK.glp_set_obj_coef(lp, 16, 4);
		GLPK.glp_set_obj_coef(lp, 17, 6);
		GLPK.glp_set_obj_coef(lp, 18, 5); 
		
		GLPK.glp_set_obj_coef(lp, 19, 3);
		GLPK.glp_set_obj_coef(lp, 20, 4);
		GLPK.glp_set_obj_coef(lp, 21, 1);
		GLPK.glp_set_obj_coef(lp, 22, 2);
		GLPK.glp_set_obj_coef(lp, 23, 5);
		GLPK.glp_set_obj_coef(lp, 24, 6);
	}
	
}
